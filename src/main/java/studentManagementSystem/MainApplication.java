package studentManagementSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private StudentDAO dao;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        List<StudentObj> students = dao.findAll();

        for (StudentObj student : students) {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
        }
    }
}