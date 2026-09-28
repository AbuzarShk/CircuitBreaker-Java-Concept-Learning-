package studentManagementSystem;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<StudentObj> getStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentObj getStudentByID(@PathVariable int id) {
        System.out.println(id);
        return service.getStudentID(id);
    }

    @PostMapping
    public StudentObj addStudentByID(@RequestBody StudentObj student) {
        return service.addStudentID(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentByID(@PathVariable int id) {
        service.deleteStudentID(id);
    }
    
    
    @GetMapping("/test")
    public String test() {
        return "Controller is working";
    }
    @PutMapping("/{id}")
    public StudentObj udpateStudent(
            @PathVariable int id,
            @RequestBody StudentObj student) {

        return service.updateStudent(id, student);
    }

    @GetMapping("/{id}/external")
    public CompletableFuture<String> getExternalStudentData(
            @PathVariable Long id) {

        return service.getExternalStudentData(id);
    }
}