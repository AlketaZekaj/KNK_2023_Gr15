package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBConnection;
import model.Drejtimi;

public class DrejtimiRepository {

    private DBConnection connection;

    public DrejtimiRepository() {
        this.connection = DBConnection.getConnection();
    }

    public ArrayList<Drejtimi> findAll() throws SQLException{
        String query = "SELECT * FROM DREJTIMI";
        ResultSet res = this.connection.executeQuery(query);
        ArrayList<Drejtimi> drejtimi = new ArrayList<Drejtimi>();

        while(res.next()) {
            drejtimi.add(Drejtimi.fromResultSet(res));
        }
        res.close();
        return drejtimi;
    }

    public String[] getDrejtimet() throws SQLException {
        ArrayList<Drejtimi> d = findAll();

        String[] drejtimet = new String[d.size()];

        for (int i = 0; i < d.size(); i++) {
            drejtimet[i] = d.get(i).getEmri();
        }

        return drejtimet;
    }

    public int getIdByEmri(String drejtimi) throws SQLException {
        String query = "SELECT * FROM DREJTIMI WHERE EMRI = '" + drejtimi + "'";
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        int i = Drejtimi.fromResultSet(res).getId();
        res.close();
        return i;
    }

    public String getEmriById(int id) throws SQLException {
        String query = "SELECT * FROM DREJTIMI WHERE ID = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        String s = Drejtimi.fromResultSet(res).getEmri();
        res.close();
        return s;
    }

    public String getEmriBySId(String id) throws SQLException {
        String query = "SELECT * FROM DREJTIMI WHERE ID = " + id;
        ResultSet res = this.connection.executeQuery(query);
        res.next();
        String s = Drejtimi.fromResultSet(res).getEmri();
        res.close();
        return s;
    }

}
