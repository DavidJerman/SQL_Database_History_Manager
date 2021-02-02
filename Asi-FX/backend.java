import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Communication with the SQL database log
 *
@author - Rok Mlinar Vahtar, Gašper Suhadolnik
@version - 1.2.2021
@since - 25.1.2021
**/
public class backend {


    Connection connection; //Povazava z SQL serverjem
    String currentDatabase = "remote11"; //Ime baze na kateri trenutno izvajamo operacije
    TreeMap<String,String> nameAndIP_map = new TreeMap<String,String>(); //Map imen in ip naslovov
    Language language;


    /**
     Privzeti konstruktor vzpostavi povezavo s podatkovno bazo in nastavi currentDatabase na privzeto vrednost
     **/
    public backend(){
        //checkConnection(); //vzpostavi povezavo ob kreiranju novega objekta razreda backend
        //this.currentDatabase = "remote11"; //privzeta podatkovna baza, s katero se operira, je remote11 (lahko se nastavi tudi katero drugo)
        try {
            language = new Language("Asi-FX\\language_packs\\sl_backend.cfg"); //default language file (slovenski)
        }catch (IOException e){
            System.out.println("napaka pri nastavljanju jezika");
            e.printStackTrace();
        }
    }

    /**
     Setter za language
     @param languageFile ime jezikovne datoteke
     **/
    public void setLanguage(Language languageFile) {
        this.language = languageFile;
    }


    /** Konstruktor, ki kot parameter prejme samo ime jezikovne datoteke.
     * @param languageFile ime jezikovne datoteke
     */
    public backend(String languageFile) throws IOException{
        checkConnection();
        language = new Language(languageFile);
    }

    /**
     Nov konstruktor, ki kot parameter prejme ime podatkovne baze, katero želi ob povezavi uporabiti
     @param database ime podatkovne baze na serverju, do katere želimo dostopati
     @param languageFile ime jezikovne datoteke, ki se uporabi za prikaz
     @throws IOException ko je podan neobstoječ language file
     **/
    public backend(String database, String languageFile) throws IOException{
        checkConnection(); //vzpostavi povezavo z bazo ob kreiranju objekta
        changeDatabase(database);
        this.currentDatabase = database; //nastavi bazo podatkov na tisto, kjer
        language = new Language(languageFile);
    }




    /**
     Vrne metapodatke o tabeli

     @param tableName
     @return String[]{kdo ustvaril, kdaj ustvaril, kdo izbrisal, kdaj izbrisal}
     **/
    public String[] getTableInfo(String tableName){
        //Dobi log kreacije
        ResultSet resultSet = executeQuery(
                "SELECT user_host, event_time FROM mysql.general_log " +
                        "WHERE argument LIKE '%" + tableName + "%'" +
                        "AND argument LIKE '%CREATE TABLE%' AND argument LIKE '%create table%' " +
                        "AND argument NOT LIKE  '%SHOW%' AND argument NOT LIKE  '%show%' " +
                        "AND argument NOT LIKE  '%SELECT%' AND argument NOT LIKE  '%select%' ");

        String[] ret = new String[4];

        //Vpiše ip kreatorja in datum kreacije
        try {
            resultSet.next();
            ret[0] = userhostToIP(resultSet.getString(1));
            ret[1] = resultSet.getString(2);


        } catch (SQLException exception) {
            ret[0] = null; ret[1] = null;
        }

        //Dobi log brisanja
        resultSet = executeQuery(
                "SELECT user_host, event_time FROM mysql.general_log " +
                        "WHERE argument LIKE '%TMlinarVahtarRok7%'" +
                        "AND (argument LIKE '%DROP TABLE%' OR argument LIKE '%drop table%') " +
                        "AND argument NOT LIKE  '%SHOW%' AND argument NOT LIKE  '%show%' " +
                        "AND argument NOT LIKE  '%SELECT%' AND argument NOT LIKE  '%select%' ");


        //Vpiše ip izbrisatelja in datum izbrisanja
        try {
            resultSet.next();
            ret[2] = userhostToIP(resultSet.getString(1));
            ret[3] = resultSet.getString(2);


        } catch (SQLException exception){
            ret[2] = null; ret[3] = null;
        }
        return ret;
    }

    //--------
    /** Metoda vrne objekt HashMap, v katerem se nahajajo podatki, ki si jih je določen uporabnik v podani tabeli ogledoval.
     *
     * opomba: metoda ima še možnosti izboljšave... (preveč decimalk pri datumu, ...)
     *
     * @param user uporabnik, ki si je ogledoval podatke v tabeli (treba je vnesti uporabnikov ip naslov)
     * @param table tabela, iz katere je uporabnik ogledoval podatke
     * @return HashMap<String, String> (ključ: datum; vrednost: podatek)
     */
    public HashMap<String, String> getDataSelect(String user, String table){
        checkConnection();
        if(table.contains(".")) table = table.substring(table.indexOf(".")+1);

        //ne pokaži rezultata, kjer je ime tabele enako mysql.general_log, ker drugače pokaže tudi podatke iz log-ov, ker se pri njih pojavijo ravno taki podatki, ki jih s to metodo iščemo
        //to je začasna rešitev, treba bo enkrat popravit in ob zapisu preverit, ali stolpci sploh obstajajo v tabeli in v nasprotnem primeru ignorirati te vrstice
        String[][] rezultat = queryToStringArray("select event_time, argument from mysql.general_log where argument like 'select%' and user_host like '%"+user+"%' and argument like '%"+ table +"%' and argument not like '%mysql.general_log%';");
        HashMap<String, String> vrednosti = new HashMap<>();

        for(int i = 1; i < rezultat[0].length; i++){ //po vrsticah ()
            String zapisi = rezultat[1][i];
            zapisi = zapisi.toLowerCase();
            int j = zapisi.indexOf("from");
            String value;

            //preveri, ali je stavek standardne oblike, ali ni (ali ima select <> from <>)
            if(j < 0) {
                value = zapisi.substring(zapisi.indexOf("select") + 6);
            }
            else {
                value = zapisi.substring(zapisi.indexOf("select") + 6, j);
            }

            //zamenjaj nekatere stvari v nizu poizvedbe:
            if(value.contains("*")){
                value = value.replace("*", language.all);
            }
            if(value.contains("where")){
                value = value.replace("where", language.where);
            }
            if(value.contains("distinct")){
                value = value.replace("distinct", language.distinct);
            }
            if(value.contains("count")){
                value = value.replace("count", language.count);
            }
            if(value.contains(" as ")){
                value = value.replace(" as ", language.as);
            }

            //zapiši vrednost v hashmap
            vrednosti.put(rezultat[0][i], value);

            zapisi = null;
            value = null;
        }

        return vrednosti;
    }

    /** Metoda vrne objekt HashMap, v katerem se nahajajo podatki, ki jih je določen uporabnik v podani tabeli ažuriral.
     *
     * opomba: metoda ima možnosti za izboljšavo
     *
     * @param user uporabnik, ki je ažuriral podatke v tabeli (treba je vnesti uporabnikov ip naslov)
     * @param table tabela, v kateri je uporabnik ažuriral podatke
     * @return HashMap<String, String> (ključ: datum; vrednost: podatek)
     */
    public HashMap<String, String> getDataUpdate(String user, String table){ //DELAM GAŠPER
        checkConnection();
        if(table.contains(".")) table = table.substring(table.indexOf(".")+1);

        String[][] rezultat = queryToStringArray("select event_time, argument from mysql.general_log where user_host like '%"+user+"%' and argument like 'update%' and argument like '%"+ table +"%';"); //'%86.61.30.67%';");
        HashMap<String, String> vrednosti = new HashMap<>();

        for(int i = 1; i < rezultat[0].length; i++){ //po vrsticah ()
            String zapisi = rezultat[1][i];
            zapisi = zapisi.toLowerCase();
            String value;
            value = zapisi.substring(zapisi.indexOf("set")+4); //update <table name> SET ... -> poiščemo set, ker za njem sledijo vrednosti

            //zamenjaj nekatere stvari v nizu poizvedbe:
            value = value.replaceAll("`", ""); //zamenja vse "čudne" narekovaje (`` <- tele)
            if(value.contains("where")){
                value = value.replace("where", language.where);
            }
            if(value.contains("is null")){
                value = value.replace("is null", language.is_null);
            }
            if(value.contains(" and ")){
                value = value.replace(" and ", language.and);
            }
            //zapiši vrednost v hashmap
            vrednosti.put(rezultat[0][i], value);

            zapisi = null;
            value = null;
        }

        return vrednosti;
    }

    /** Metoda vrne objekt HashMap, v katerem se nahajajo podatki, ki si jih je določen uporabnik v podani tabeli ogledoval.
     *
     * @param user uporabnik, ki je vstavljal podatke v tabelo (treba je vnesti uporabnikov ip naslov)
     * @param table tabela, v katero je uporabnik vstavljal podatke
     * @return HashMap<String, String> (ključ: datum; vrednost: podatek)
     */
    public HashMap<String, String> getDataInsert(String user, String table){
        checkConnection();
        if(table.contains(".")) table = table.substring(table.indexOf(".")+1);

        String[][] rezultat = queryToStringArray("select event_time, argument from mysql.general_log where user_host like '%"+user+"%' and argument like 'insert%' and argument like '%"+table+"%';"); //table mora biti znotraj enojnih narekovajev in znotraj procentov
        HashMap<String, String> vrednosti = new HashMap<>();

        for(int i = 1; i < rezultat[0].length; i++){ //po vrsticah ()
            String zapisi = rezultat[1][i];
            String value = zapisi;
            zapisi = zapisi.toLowerCase();

            //----
            if(zapisi.contains("values")){
                value = value.substring(zapisi.indexOf("values")+6);
            }
            else value = value.substring(zapisi.lastIndexOf("("));
            value = value.replaceAll("\\(", "").replaceAll("\\)", "");
            value = value.replaceAll("'", "");
            //----

            //zapiši vrednost v hashmap
            vrednosti.put(rezultat[0][i], value);

            zapisi = null;
            value = null;
        }

        return vrednosti;
    }

    /** Metoda vrne objekt HashMap, v katerem se nahajajo podatki, ki jih je določen uporabnik iz podane tabele brisal.
     *
     * @param user uporabnik, ki je brisal podatke iz tabeli (treba je vnesti uporabnikov ip naslov)
     * @param table tabela, iz katere je uporabnik brisal podatke
     * @return HashMap<String, String> (ključ: datum; vrednost: podatek)
     */
    public HashMap<String, String> getDataDelete(String user, String table){
        checkConnection();
        if(table.contains(".")) table = table.substring(table.indexOf(".")+1);

        String[][] rezultat = queryToStringArray("select event_time, argument from mysql.general_log where user_host like '%"+user+"%' and argument like 'delete%' and argument like '%"+table+"%';");
        HashMap<String, String> vrednosti = new HashMap<>();

        for(int i = 1; i < rezultat[0].length; i++){ //po vrsticah ()
            String zapisi = rezultat[1][i];
            String value = zapisi;
            zapisi = zapisi.toLowerCase();

            //----
            if(zapisi.contains("where")){ //če vsebuje pogoj where, potem še uredi zapis, drugače vrni string "vse vrstice"
                value = value.substring(zapisi.indexOf("where"));
                value = value.replace("where", language.where).replace("WHERE", language.where);
                value = value.replaceAll("LIKE", language.like).replaceAll("like", language.like);


                value = value.replaceAll(" AND ", language.and);

                value = value.replaceAll("'", "").replaceAll("`", "");



            }
            else value = "vse vrstice tabele";

            //value = value.replaceAll("\\(", "").replaceAll("\\)", "");
            //value = value.replaceAll("'", "");
            //----

            //zapiši vrednost v hashmap
            vrednosti.put(rezultat[0][i], value);

            zapisi = null;
            value = null;
        }

        return vrednosti;
    }
    //--------







    /**
     Vrne tabelo IP naslovov vseh uporabnikov

     @return IP adress
     **/
    public String[] getAllUsersIP(){
        //Izbere vse različne primere user_host
        ResultSet resultSet = executeQuery("SELECT DISTINCT user_host FROM mysql.general_log");

        //Izloči podvojitve IP naslovov
        TreeSet<String> a = new TreeSet<>();
        try {
            while (resultSet.next()) {
                a.add(userhostToIP(resultSet.getString(1)));
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        //Vrne String array
        String[] arr = new String[a.size()];
        arr = a.toArray(arr);
        return Arrays.copyOfRange(arr,1,arr.length);
    }


    /**
     Vrne tabelo vseh poimenovanih uporabnikov

     @return String array of all named users
     **/
    public String[] getAllUsersName(){
        //Preveri da je nameAndIP_map ustvarjen
        if(nameAndIP_map.isEmpty())setupNameIP_map();

        //Vrne vse poimenovane uporabnike
        String[] arr = new String[nameAndIP_map.size()];
        nameAndIP_map.values().toArray(arr);

        String[] ret = new String[arr.length/2];
        int retI = 0;
        for(int i = 0; i < arr.length; i++)
            if(!isIP(arr[i])){
                ret[retI] = arr[i];
                retI++;
            }

        return ret;
    }



    /**
     Opis

     @param table Ime tabele
     @return Vracanje
     **/
    public String[] getAllUsersIP(String table){
        //Izbere vse različne primere user_host
        ResultSet resultSet = executeQuery("SELECT DISTINCT user_host FROM mysql.general_log WHERE argument LIKE '%" + table + "%';");

        //Izloči podvojitve IP naslovov
        TreeSet<String> a = new TreeSet<>();
        try {
            while (resultSet.next()) {
                a.add(userhostToIP(resultSet.getString(1)));
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        //Vrne String array
        String[] arr = new String[a.size()];
        arr = a.toArray(arr);
        return Arrays.copyOfRange(arr,1,arr.length);
    }

    /**
     Preveri, če je podavi string IP naslov ali ime

     @return boolean
     **/
    private boolean isIP(String string){
        int dotCount = 0;
        for(int i = 0; i < string.length(); i++)
            if(string.charAt(i) == '.')dotCount++;

        if(dotCount == 3)return true;

        return false;
    }




    /**
     Vrne tabelo vseh poimenovanih uporabnikov, ki so delali z določeno tabelo

     @return String array of all named users
     **/
    public String[] getAllUsersName(String table){
        //Preveri da je nameAndIP_map ustvarjen
        if(nameAndIP_map.isEmpty())setupNameIP_map();

        String[] arr = getAllUsersIP(table);

        ArrayList<String> temp = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            if(nameAndIP_map.containsKey(arr[i]) && !temp.contains(arr[i]))temp.add(nameAndIP_map.get(arr[i]));
        }

        arr = new String[temp.size()];
        return temp.toArray(arr);
    }




    /**
     Metoda vrne vse tabele iz vseh shem podatkovne baze.

     @return arr2; vsebuje želene podatke
     **/
    public String[] getAllTablesAllDatabases(){
        String[][] arr1 = queryToStringArray("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES;");

        String[] arr2 = new String[arr1[0].length - 1];
        for(int i = 1; i <= arr2.length; i++) {
            arr2[i - 1] = arr1[0][i];
        }


        return arr2;
    }

    /**
     Metoda vrne vse tabele iz trenutno izbrane sheme.

     @return arr2; vrni zahtevane podatke
     **/
    public String[] getAllTablesCurrentDatabase(){
        String[][] arr1 = queryToStringArray("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"+ currentDatabase +"';");

        String[] arr2 = new String[arr1[0].length - 1];
        for(int i = 1; i <= arr2.length; i++) {
            arr2[i - 1] = arr1[0][i];
        }


        return arr2;

    }


    /**
     Metoda nastavi spremenljivko currentDatabase
     @param databaseName ime podatkovne baze, iz katere želimo podatke
     **/
    public void setCurrentDatabase(String databaseName){
        this.currentDatabase = databaseName;
    }


    /**
     Metoda izvede sql query in vrne objekt tipa ResultSet

     @param sqlSelectStatement sql query, ki se izvede
     @return ResultSet
     **/
    public ResultSet getCustomQuery(String sqlSelectStatement){
        return executeQuery(sqlSelectStatement);
    }


    /**
     * Metoda queryToStringArray vrne dvodimenzionalno tabelo nizov, v kateri se nahaja tabela, ki jo poizvedba vrača
     * @param sql stavek sql s poizvedbo
     * @return vrne se dvodimenzionalna tabela nizov, v kateri so v posameznih podtabelah zapisane vrednosti vrstic, ki jih koda vrne
     */
    public String[][] queryToStringArray(String sql){ //vrni tabelo nizov (vrednosti po stolpcih (vrsticah)) //nastavi na private
        String[][] vrni = {{}};
        try {
            ResultSet rezultat = executeQuery(sql); //pridobi rezultat poizvedbe

            //-----
            //inicializacija tabele: pridobi število stolpcev in število vrstic (za kreiranje tabele nizov)
            int stolpci = rezultat.getMetaData().getColumnCount(); //število stolpcev

            rezultat.last(); //postavi števec rezultata na zadnjo vrstico
            int vrstice = rezultat.getRow(); //pridobi število zadnje vrstice
            rezultat.beforeFirst(); //postavi na zacetek
            //------

            vrni = new String[stolpci][vrstice+1]; //na mesto 0 dodaj se ime stolpca:

            //v tabelo zapiši imena stolpcev:
            for(int i = 0; i < stolpci; i++){
                vrni[i][0] = rezultat.getMetaData().getColumnLabel(i+1); //na prva mesta zapiši ime stolpcev
            }

            int stVrstice = 1; //na mesto 0 se doda ime stolpca
            while (rezultat.next()) {
                for (int i = 1; i <= stolpci; i++) {
                    //ker je lahko vrednost tudi null, je treba to upostevat:
                    if (rezultat.getObject(i) == null) {
                        vrni[i-1][stVrstice] = "[NULL]";
                    } else {
                        vrni[i-1][stVrstice] = rezultat.getObject(i).toString();
                    }
                }
                stVrstice += 1; //povečaj vrstice za 1
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return vrni;
    }



    /**
     Funkcija izvede sql query in vrne ResultSet

     @param sql stavek sql, katerega rezultat vrača metoda
     @return resultSet
     **/
    private ResultSet executeQuery(String sql){
        checkConnection();//preveri povezavo

        Statement statementmt=null;
        ResultSet resultSet=null;
        try {
            statementmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statementmt.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }


    /**
     Funkcija IP pretvori v ime preko avtorjev ustvarjenih poimenovanih tabel;

     @param IP ip naslov, ki se, če obstaja, pretvori v ime
     @return name ali null
     **/
    public String IPtoName(String IP){
        if(nameAndIP_map.containsKey(IP))
            return nameAndIP_map.get(IP);

        return null;

    }

    /**
     Funkcija name pretvori v ime preko avtorjev ustvarjenih poimenovanih tabel;

     @param name ime, ki se pretvori v ip naslov
     @return IP ali null
     **/
    public String nameToIP(String name){
        if(nameAndIP_map.containsKey(name))
            return nameAndIP_map.get(name);

        return null;

    }

    /**
     Ustvari hashmap iz IP-jev in avtorjev ustvarjenih poimenovanih tabel
     **/
    public void setupNameIP_map(){                                                     /////////////////////////////////////Information schema - table creation date
        ResultSet resultSet = executeQuery("CALL tableCreators();");

        try {
            while (resultSet.next()) {
                String user_host = resultSet.getString(2);
                String argument = resultSet.getString(6);

                //Izreže ip iz userhost
                user_host = userhostToIP(user_host);



                //Odstrani: /* ApplicationName=IntelliJ IDEA 2020.3.1 */ pred create table
                if(argument.startsWith("/*")){
                    argument = argument.substring(argument.indexOf("*/")+3);
                }


                try {
                    //Preveri, da se argument začne z CREATE TABLE
                    if (argument.split(" ")[0].equalsIgnoreCase("CREATE") && argument.split(" ")[1].equalsIgnoreCase("TABLE")) {
                        argument = argument.split(" ")[2];

                        //Odstrani '' okoli imena
                        if (argument.charAt(0) == '`' || argument.charAt(0) == '\'')
                            argument = argument.substring(1, argument.length() - 1);

                        //Dovoli le T+Priimek+Ime+danRojstva tabele
                        if (!(argument.charAt(0) == 'T')) continue;

                        //Obreže T in končno številko
                        argument = argument.substring(1);
                        for(int i = 0; i < argument.length(); i++){
                            if(argument.charAt(i) == '0' ||
                                argument.charAt(i) == '1' ||
                                argument.charAt(i) == '2' ||
                                argument.charAt(i) == '3' ||
                                argument.charAt(i) == '4' ||
                                argument.charAt(i) == '5' ||
                                argument.charAt(i) == '6' ||
                                argument.charAt(i) == '7' ||
                                argument.charAt(i) == '8' ||
                                argument.charAt(i) == '9')
                            {
                            argument = argument.substring(0,i);
                            break;
                            }
                        }

                        //Vstavi ime in IP v hashmap
                        nameAndIP_map.put(user_host,argument);
                        nameAndIP_map.put(argument,user_host);

                    } else continue;

                }catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    /**
     Iz user_host izreže IP naslov

     @param user_host niz, iz katerega se izreže ip naslov
     @return IP
     **/
    public String userhostToIP(String user_host){
        for(int i = user_host.length()-1; i > 0; i--){
            if(user_host.charAt(i) == '['){
                user_host = user_host.substring(i+1,user_host.length()-1);
                break;
            }
        }
        return user_host;
    }

    /**
     Funkcija preveri če je povezava vspostavljena in jo, če ni, vzpostavi
     **/
    private void checkConnection(){
        try {
            if(connection == null || connection.isClosed()) {
                try {

                    Class.forName("com.mysql.jdbc.Driver"); //če tole dela težave, spremeni iz com.mysql.cj.jdbc.Driver v com.mysql.jdbc.Driver (meni ne deluje, če vmes ni .cj.) <- Gašper Suhadolnik

                } catch (ClassNotFoundException ex) {
                    System.out.println("CLassNotFoundException: " + ex.getMessage());
                    System.exit(1);

                }

                connection = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/" + currentDatabase,"remote","remote");


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**Metoda vzpostavi povezavo s podatkovno bazo.
     *
     * @param username uporabniško ime
     * @param password geslo
     * @param serverIp ip serverja, na katerem je podatkovna baza
     * @param serverPort port serverja, na katerem je podatkovan baza
     * @param database podatkovna baza (shema), ki jo trenutno uporablja povezava
     * @return vrne vrednost true, če je povezava uspešna, drugače vrne false.
     */
    public boolean connect(String username, String password, String serverIp, String serverPort, String database){
        this.currentDatabase = database;
        try {
            if(connection == null || connection.isClosed()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("CLassNotFoundException: " + e.getMessage());
                    System.exit(1);
                }

                connection = DriverManager.getConnection("jdbc:mysql://"+ serverIp + ":"+ serverPort + "/"+currentDatabase, username, password);//"jdbc:mysql://193.2.190.23:3306/" + currentDatabase,"remote","remote");
                return true; //če je povezava uspešna, potem vrni true
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false; //če povezava ni uspešna, vrni false.
    }


    /**
     Metoda prekine povezavo s podatkovno bazo.
     @return vrni true, če je povezava uspešno prekinjena, drugače pa vrni false.
     **/
    public boolean disconnect(){ //preimenovana iz closeConnection()
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
            return true; //*-> tukaj
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false; //če pride do napake med izvajanjem zgornjega try-catch bloka, potem ne pride do vračanja true*, in potem vrni false.
    }

    /**
     Metoda zamenja trenutno bazo podatkov v tisto, ki je podana skozi parameter
     * @param database podatkovna baza, ki jo želimo uporabiti
     */

    public void changeDatabase(String database){
        currentDatabase = database;
        checkConnection();//preveri povezavo; če ni povezave, jo naredi in že v štartu spremeni podatkovno bazo

        Statement statement=null;
        ResultSet resultSet=null;
        try {
            statement = connection.createStatement();
            statement.execute("USE " + database + ";"); //

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


class Language{
    String all;
    String where;
    String distinct;
    String count;
    String as;
    String is_null;
    String and;
    String like;


    public Language(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));

        all = findInFile(in,"*");
        where = findInFile(in,"where");
        distinct = findInFile(in,"distinct");
        count = findInFile(in,"count");
        as = findInFile(in,"as");
        is_null = findInFile(in,"is_null");
        and = findInFile(in,"and");
        like = findInFile(in,"like");

    }

    String findInFile(BufferedReader in, String key){
        try {
            while (true) {
                String line = in.readLine();
                if(line.startsWith(key)){
                    return line.substring(line.indexOf(':')+1);

                }
            }
        }catch (IOException e){}

        return null;
    }

    @Override
    public String toString() {
        return "Language{" +
                "all='" + all + '\'' +
                ", where='" + where + '\'' +
                ", distinct='" + distinct + '\'' +
                ", count='" + count + '\'' +
                ", as='" + as + '\'' +
                ", is_null='" + is_null + '\'' +
                ", and='" + and + '\'' +
                ", like='" + like + '\'' +
                '}';
    }
}