package repository;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection dbLink;

    public Connection getConn(){
        String dbName = "konsultimet_test";
        String dbuser = "root";
        String dbpass = "AhaHxG12@S*&";

        String url = "jdbc:mysql://localhost:3306/" + dbName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbLink = DriverManager.getConnection(url,dbuser,dbpass);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dbLink;
    }
}

