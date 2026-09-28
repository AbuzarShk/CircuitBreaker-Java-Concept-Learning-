package studentManagementSystem;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class StudentObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // Default Constructor
    public StudentObj() {
    }

    // Parameterized Constructor
    public StudentObj(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter for ID
    public Integer getId() {
        return id;
    }

    // Setter for ID
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentObj{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}