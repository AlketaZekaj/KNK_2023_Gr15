package Modelet;

public class Student {
    private String id;
    private String username;
    
    public Student(String id,String username) {
       this.id=id;
       this.username=username;
    }


public void setId(String id) {
    this.id = id;
}

public String getId() {
    return id;
}

public void setName(String username) {
    this.username=username;
}

public String getName() {
    return username;
}
}
