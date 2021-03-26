package ps.project.Models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "ADMINISTRATOR" )
public class Administrator extends User implements Serializable {

    private String name;
    private String address;
    private String dateOfEmployment;

    public Administrator(){
        // this form used by Hibernate
    }

    public Administrator(String username, String password,String name, String address, String dateOfEmployment) {
        super(username,password);
        this.name = name;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
