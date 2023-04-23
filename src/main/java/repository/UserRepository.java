package repository;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DBConnection;
import database.InsertQueryBuilder;
import model.User;
import processor.SaltedHash;

public class UserRepository {

    private DBConnection connection;
    private SaltedHash saltedHash = new SaltedHash();

    public UserRepository() {
        this.connection = DBConnection.getConnection();
    }

    // VALIDATION

    public boolean validateLogin(String username, String password) throws SQLException, NoSuchAlgorithmException {

        User useri = findByUsername(username);
        String salted = useri.getSalted();
        String databaseSaltedHash = useri.getSaltedHash();
        String inputSaltedHash = saltedHash.generatehash(password, salted);

        if(databaseSaltedHash.equals(inputSaltedHash)) {
            return true;
        }
        return false;
    }

//	public boolean validateChangePassword(String id, String password) throws SQLException, NoSuchAlgorithmException {
////		String username = findUsernameById(id);
////		User useri = findByUsername(username);
//		User useri = findById(id);
//		String salted = useri.getSalted();
//		String databaseSaltedHash = useri.getSaltedHash();
//		String inputSaltedHash = saltedHash.generatehash(password, salted);
//
//		if(databaseSaltedHash.equals(inputSaltedHash)) {
//			return true;
//			}
//		return false;
//	}

    public User findByUsername(String Username) throws SQLException {

        String a = "'" + Username + "'";
        String query = "SELECT * FROM User WHERE Username = " + a;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        User u = User.fromResultSet(res);
        res.close();
        return u;
    }

    public User findById(String id) throws SQLException {

        String query = "SELECT * FROM USER WHERE ID = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        User u = User.fromResultSet(res);
        res.close();
        return u;
    }

    public String findUsernameById(String id) throws SQLException {
        String query = "SELECT * FROM USER WHERE ID = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        String s = User.fromResultSet(res).getUsername();
        res.close();
        return s;
    }

    public int findIdByUsername(String Username) throws SQLException {

        String a = "'" + Username + "'";
        String query = "SELECT * FROM User WHERE Username = " + a;
        ResultSet res = connection.executeQuery(query);
        res.next();
        int i = User.fromResultSet(res).getId();
        res.close();
        return i;
    }

    public boolean IsThereOne(String username) throws SQLException {
        String a = "'" + username + "'";
        String query = "SELECT * FROM User WHERE Username = " + a;
        ResultSet res = this.connection.executeQuery(query);
        boolean b = res.next();
        res.close();
        if(b) {
            return true;
        }
        return false;
    }

    //REGISTRATION

    public void create(User user) throws Exception {
        InsertQueryBuilder query = (InsertQueryBuilder)
                InsertQueryBuilder.create("user")
                        .add("id", user.getId(), "i")
                        .add("username", user.getUsername(), "s")
                        .add("saltedhash", user.getSaltedHash(), "s")
                        .add("salted", user.getSalted(), "s");

        int lastInsertedId = this.connection.execute(query);

    }





    //OTHER METHODS

    public User findById(int id) throws SQLException {
        String query = "SELECT * FROM USER WHERE ID = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        User u = User.fromResultSet(res);
        res.close();
        return u;
    }


    public boolean checkIfUsernameExists(String username) throws SQLException {
        String query = "SELECT * FROM USER WHERE USERNAME = '" + username + "'";
        ResultSet res = this.connection.executeQuery(query);
        boolean b = res.next();
        res.close();
        if(b) {
            return true;
        }else {
            return false;
        }
    }

    //update


    public void changePassword(String a, String cid) throws NoSuchAlgorithmException, SQLException {
        SaltedHash s = new SaltedHash();
        String salted = s.generateSalted();
        String SaltedHash = s.generatehash(a, salted);
        String querysh = "Update User set SaltedHash = '" + SaltedHash + "' where id = " + cid;
        this.connection.executeU(querysh);
        String querys = "UPDATE USER SET Salted = '" + salted + "' where id = " + cid;
        this.connection.executeU(querys);
    }

}
