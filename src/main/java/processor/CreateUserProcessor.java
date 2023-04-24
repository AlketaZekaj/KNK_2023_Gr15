package processor;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import model.Studenti;
import model.User;
import repository.DrejtimiRepository;
import repository.StudentRepository;
import repository.UserRepository;
import repository.VitiAkademikRepository;

public class CreateUserProcessor {

    private SaltedHash saltedHash = new SaltedHash();
    private UserRepository userRepository = new UserRepository();
    private StudentRepository studentRepository = new StudentRepository();
    private DrejtimiRepository drejtimiRepository = new DrejtimiRepository();
    private VitiAkademikRepository vitiAkademikRepository = new VitiAkademikRepository();

    //student
    private int id;
    private String emri;
    private String mbiemri;
    private String studentId;
    private String drejtimi;
    private int drejtimiId;
    private String viti;
    private int vitiId;
    private String igrupi;
    private String igrupinr;
    private String igrupia_b;
    private String grupi;
    private int grupiId;
    //user
    private String username;
    private String password;
    //SaltedHash
    private String salted;
    private String SaltedHash;

    public void Create(String emri, String mbiemri,String studentId, String drejtimi,String viti, String username, String password) throws Exception {
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.studentId = studentId;
        this.drejtimi = drejtimi;
        this.viti = viti;
        this.username = username;
        this.password = password;
        this.insertimi();
    }

    private void defineGrupi() {
        this.igrupinr = this.igrupi.substring(0,1);
        this.igrupia_b = this.igrupi.substring(1,2);
    }



    private void insertimi() throws Exception {
        this.insertStudent();
        this.insertUser();
    }

    private void setIds() throws SQLException {
        this.setDrejtimiId();
        this.setVitiId();
    }

    private void setDrejtimiId() throws SQLException {
        this.drejtimiId = drejtimiRepository.getIdByEmri(this.drejtimi);
    }

    private void setVitiId() throws SQLException {
        this.vitiId = vitiAkademikRepository.getIdByEmri(this.viti);
    }


    private void setId() throws SQLException {
        this.id = studentRepository.getIdByStudentId(studentId);
    }

    private void insertStudent() throws Exception {
        this.setIds();
        Studenti student = new Studenti(0,this.emri,this.mbiemri,this.studentId, this.drejtimiId,this.vitiId);
        this.studentRepository.create(student);
        this.setId();
    }

    private void SaltedLogic() throws NoSuchAlgorithmException {
        this.salted = processor.SaltedHash.generateSalted();
        this.SaltedHash = saltedHash.generatehash(this.password, this.salted);
    }

    private void insertUser() throws Exception {
        this.SaltedLogic();
        User user = new User(this.id,this.username,this.SaltedHash,this.salted);
        this.userRepository.create(user);
    }

}
