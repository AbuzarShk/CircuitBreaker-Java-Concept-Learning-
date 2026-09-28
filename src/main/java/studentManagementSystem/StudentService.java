package studentManagementSystem;


import java.util.concurrent.CompletableFuture;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
@Service
public class StudentService {
	
	
	@Autowired 
	private StudentDAO dao;
	
private final RestClient restClient;
	
	public StudentService(RestClient.Builder builder)
	{
		this.restClient = builder.baseUrl("http://localhost:8081").build();
	}
	
	
	public List<StudentObj> getAllStudents() {
		return dao.findAll();
	}

	public void deleteStudentID(int id) {
		dao.deleteById(id);;
	}

	public StudentObj addStudentID(StudentObj student) {
		
		return dao.save(student);
	}

	public StudentObj updateStudent(int id, StudentObj student) {
		StudentObj existingStudent = dao.findById(id).orElse(null);
		
		if(existingStudent!= null)
		{
			existingStudent.setId(student.getId());
			existingStudent.setName(student.getName());
			
			return dao.save(existingStudent);
		}
		
		
		return null;
	}

	public StudentObj getStudentID(int id) {
		
		return dao.findById(id).orElse(null);
	}
	
	
	 @CircuitBreaker(
	            name = "studentService",
	            fallbackMethod = "studentFallback"
	    )
	    @Retry(name = "studentService")
	 public CompletableFuture<String> getExternalStudentData(Long id) {

		    return CompletableFuture.supplyAsync(() -> {

		        System.out.println("Calling external student service...");

		        return restClient.get()
		                .uri("/external/students/" + id)
		                .retrieve()
		                .body(String.class);
		    });
		}

	    // -----------------------------
	    // FALLBACK
	    // -----------------------------

	 public CompletableFuture<String> studentFallback(
		        Long id,
		        Exception exception) {

		    System.out.println(
		            "Fallback executed for student ID: " + id
		    );

		    System.out.println(
		            "Reason: " + exception.getMessage()
		    );

		    return CompletableFuture.completedFuture(
		            "Student service is temporarily unavailable. "
		            + "Please try again later."
		    );
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
