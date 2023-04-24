package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private int id;
    private String username;
    private String saltedHash;
    private String salted;



    public static User fromResultSet(ResultSet res) {

        try {
            int id = res.getInt("Id");
            String username = res.getString("Username");
            String SaltedHash = res.getString("SaltedHash");
            String Salted = res.getString("Salted");
            return new User(id, username, SaltedHash, Salted);
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User createUser(
            int id, String username, String saltedHash, String salted
    ) {
        return new User(id,username,saltedHash,salted);
    }

    public User(int id, String username, String saltedHash, String salted) {
        super();
        this.id = id;
        this.username = username;
        this.saltedHash = saltedHash;
        this.salted = salted;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public String getSaltedHash() {
        return saltedHash;
    }



    public void setSaltedHash(String saltedHash) {
        this.saltedHash = saltedHash;
    }



    public String getSalted() {
        return salted;
    }



    public void setSalted(String salted) {
        this.salted = salted;
    }



}
