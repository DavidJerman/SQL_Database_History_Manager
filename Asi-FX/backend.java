import java.sql.*;
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
     Funkcija preveri če je povezava vspostavljena in jo, če ni, vspostavi
     **/
    public void checkConnection(){
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
