package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Studenti {

    private int id;
    private String emri;
    private String mbiemri;
    private String studentId;
    private int drejtimi;
    private int viti;


    public static Studenti fromResultSet(ResultSet res) {
        try {
            int id = res.getInt("Id");
            String emri = res.getString("Emri");
            String mbiemri = res.getString("Mbiemri");
            String studentId = res.getString("StudentId");
            int drejtimi = res.getInt("Drejtimi");
            int viti = res.getInt("Viti");
            return new Studenti(id,emri,mbiemri,studentId,drejtimi,viti);
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Studenti createStudenti(
            int id, String emri, String mbiemri,String studentId, int drejtimi, int viti
    ) {
        return new Studenti(id,emri,mbiemri,studentId,drejtimi,viti;
    }

    public Studenti(int id, String emri, String mbiemri,String studentId, int drejtimi, int viti) {
        this.id = id;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.studentId = studentId;
        this.drejtimi = drejtimi;
        this.viti = viti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getDrejtimi() {
        return drejtimi;
    }

    public void setDrejtimi(int drejtimi) {
        this.drejtimi = drejtimi;
    }

    public int getViti() {
        return viti;
    }

    public void setViti(int viti) {
        this.viti = viti;
    }



}
