import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Communication with the SQL database log
 *
@author - Rok Mlinar Vahtar, Gašper Suhadolnik
@version - 25.1.2021
@since - 25.1.2021
**/
public class backend {

    Connection connection; //Povazava z SQL serverjem
    String currentDatabase; //Ime baze na kateri trenutno izvajamo operacije
    HashMap<String,String> nameAndIP_map = new HashMap<String,String>(); //Map imen in ip naslovov

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getTableInfo(String tableName){

        return null;
    }


    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhoInserted(String tableName){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhenInserted(String dataValue, String userName){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhatInserted(String userName){

        return null;
    }


    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhoSelected(String tableName){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhenSelected(String dataValue, String userName){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhatSelected(String userName){

        return null;
    }


    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhoUpdated(String tableName){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhenUpdated(String dataValue, String userName){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhatUpdated(String userName){

        return null;
    }


    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhoDeleted(String tableName){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhenDeleted(String dataValue, String userName){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getWhatDeleted(String userName){

        return null;
    }



    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getAllUsers(){

        return null;
    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public String[] getAllTables(){

        return null;
    }


    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public void setCurrentDatabase(String databaseName){


    }

    /**
     Opis

     @param
     @return Vracanje
     @thorws Exception
     **/
    public ResultSet getCustomQuery(String sqlSelectStatement){

        return null;
    }






    /**
     Funkcija IP pretvori v ime preko avtorjev ustvarjenih poimenovanih tabel

     @param IP
     @return name
     **/
    private String crossReferenceIP(String IP){
        if(nameAndIP_map.containsValue(IP)){
            return nameAndIP_map.get(IP);
        }
        return IP;
    }

    /**
     Ustvari hashmap iz IP-jev in avtorjev ustvarjenih poimenovanih tabel
     **/
    public void setupNameIP_map(){
        checkConnection();//preveri povezavo

        Statement statementmt=null;
        ResultSet resultSet=null;
        try {
            statementmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statementmt.executeQuery("CALL tableCreators();");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

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
                    if (argument.split(" ")[0].toUpperCase().equals("CREATE") && argument.split(" ")[1].toUpperCase().equals("TABLE")) {
                        argument = argument.split(" ")[2];

                        //Odstrani '' okoli imena
                        if (argument.charAt(0) == '`' || argument.charAt(0) == '\'') argument = argument.substring(1, argument.length() - 1);

                        //Dovoli le T+Priimek+Ime tabele
                        if (!(argument.charAt(0) == 'T'))continue;

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

     @param user_host
     @return IP
     **/
    private String userhostToIP(String user_host){
        for(int i = user_host.length()-1; i > 0; i--){
            if(user_host.charAt(i) == '['){
                user_host = user_host.substring(i+1,user_host.length()-1);
                break;
            }
        }
        return user_host;
    }

    /**
     Funkcija preveri če je povezava vspostavljena in jo, če ni, vspostavi
     **/
    private void checkConnection(){
        try {
            if(connection == null || connection.isClosed()) {
                try {

                    Class.forName("com.mysql.jdbc.Driver");

                } catch (ClassNotFoundException ex) {
                    System.out.println("CLassNotFoundException: " + ex.getMessage());
                    System.exit(1);

                }

                connection = DriverManager.getConnection("jdbc:mysql://193.2.190.23:3306/remote11","remote","remote");


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }






}
