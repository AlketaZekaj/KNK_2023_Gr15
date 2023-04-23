package repository;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DBConnection;
import database.InsertQueryBuilder;
import model.Studenti;
import model.User;
import processor.SaltedHash;

public class StudentRepository {

    private DBConnection connection;
    private SaltedHash saltedHash = new SaltedHash();
    private DrejtimiRepository drejtimiRepository = new DrejtimiRepository();
    private GrupiRepository grupiRepository = new GrupiRepository();
    private VitiAkademikRepository vitiAkademikRepository = new VitiAkademikRepository();

    public StudentRepository() {
        this.connection = DBConnection.getConnection();
    }

    public boolean validateLogin(String username,String password) throws SQLException, NoSuchAlgorithmException {
        User useri = findByUsername(username);
        String salted = useri.getSalted();
        String databaseSaltedHash = useri.getSaltedHash();
        String inputSaltedHash = saltedHash.generatehash(password,salted);

        if(databaseSaltedHash.equals(inputSaltedHash)) {
            return true;
        }
        return false;
    }

    public User findByUsername(String username) throws SQLException {
        String a = "'" + username + "'";
        String query = "SELECT * FROM USER WHERE USERNAME = " + a;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        User u = User.fromResultSet(res);
        res.close();
        return u;
    }

    public Studenti findById(int id) throws SQLException {
        String query = "SELECT * FROM STUDENTI WHERE ID = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        Studenti s = Studenti.fromResultSet(res);
        res.close();
        return s;
    }

    public Studenti findById2(String id) throws SQLException {
        String query = "SELECT * FROM STUDENTI WHERE ID = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        Studenti s = Studenti.fromResultSet(res);
        res.close();
        return s;
    }

    public String findUsernameById(String id) throws SQLException {

        String query = "SELECT * FROM USER WHERE ID = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        String s = User.fromResultSet(res).getUsername();
        res.close();
        return s;
    }

    public void changePassword(String a, String cid) throws NoSuchAlgorithmException, SQLException {
        SaltedHash s = new SaltedHash();
        String salted = s.generateSalted();
        String SaltedHash = s.generatehash(a, salted);
        String querysh = "Update User set SaltedHash = '" + SaltedHash + "' where id = " + cid;
        this.connection.executeU(querysh);
        String querys = "UPDATE USER SET Salted = '" + salted + "' where id = " + cid;
        this.connection.executeU(querys);
    }


    public void create(Studenti student) throws Exception {
        InsertQueryBuilder query = (InsertQueryBuilder)
                InsertQueryBuilder.create("studenti")
                        .add("emri", student.getEmri(), "s")
                        .add("mbiemri", student.getMbiemri(), "s")
                        .add("studentId", student.getStudentId(), "s")
                        .add("drejtimi", student.getDrejtimi(), "i")
                        .add("viti", student.getViti(), "i")
                        .add("grupi", student.getGrupi(), "i");

        int lastInsertedId = this.connection.execute(query);

    }


    public ArrayList<Studenti> findAll() throws SQLException{
        String query = "SELECT * FROM STUDENTI";
        ResultSet res = this.connection.executeQuery(query);
        ArrayList<Studenti> student = new ArrayList<Studenti>();

        while(res.next()) {
            student.add(Studenti.fromResultSet(res));
        }
        res.close();
        return student;
    }

    public int getIdByStudentId(String studentId) throws SQLException {
        String query = "SELECT * FROM STUDENTI WHERE STUDENTID = '" + studentId + "'";
        ResultSet res = this.connection.executeQuery(query);
        if(res.next()) {
            int i = Studenti.fromResultSet(res).getId();
            res.close();
            return i;
        }else {
            return 0;
        }
    }

    public boolean checkIfSIDExists(String studentId) throws SQLException {
        String query = "SELECT * FROM STUDENTI WHERE STUDENTID = '" + studentId + "'";
        ResultSet res = this.connection.executeQuery(query);
        boolean b = res.next();
        res.close();
        if(b) {
            return true;
        }else {
            return false;
        }
    }

    public int getGrupiById(String id) throws SQLException {

        String query = "select * from studenti where id = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        int i = Studenti.fromResultSet(res).getGrupi();
        res.close();
        return i;
    }

    public int getDrejtimiById(String id) throws SQLException {
        String query = "select * from studenti where id = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        int i = Studenti.fromResultSet(res).getDrejtimi();
        res.close();
        return i;
    }

    public int getVitiById(String id) throws SQLException {
        String query = "select * from studenti where id = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        int i = Studenti.fromResultSet(res).getViti();
        res.close();
        return i;
    }

    public int getIdBySid(String StudentId) throws SQLException {

        String query = "Select * from studenti where StudentId = '"+StudentId+"'";
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        int i = Studenti.fromResultSet(res).getId();
        res.close();
        return i;
    }

    public void RemoveBySId(String StudentId) throws SQLException {

        int id = getIdBySid(StudentId);

        String query = "Call deleteStudent("+id+")";
        this.connection.executeU(query);

    }

    public void UpdateBySId(String StudentId, String drejtimi, String grupi,String viti) throws SQLException {

        int id = getIdBySid(StudentId);
        int did = drejtimiRepository.getIdByEmri(drejtimi);
        int vid = vitiAkademikRepository.getIdByEmri(viti);
        String grup = grupiRepository.getGrupiPjesa1(grupi);
        String gr = grupiRepository.getNenGrupi(grupi);
        int gid = grupiRepository.getIdByEmri(grup, gr, vid);

        String query = "call updateStudent("+id+","+gid+","+did+")";
        this.connection.executeU(query);
    }

}
