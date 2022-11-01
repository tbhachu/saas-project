package edu.greenriver.edu.saasproject.controllers;
import edu.greenriver.edu.saasproject.models.Query;
import edu.greenriver.edu.saasproject.models.Student;
import edu.greenriver.edu.saasproject.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class WebApiStudent
{
    @RequestMapping("/home")
    public String home()
    {
        return "index";
    }

    private StudentService service;

    public WebApiStudent(StudentService service)
    {
        this.service = service;
    }

    //GET request to http://localhost:8081/api/v1/student
    @GetMapping("")
    public ResponseEntity<List<Student>> allStudents()
    {
        //return the response using a constructor
        return new ResponseEntity<>(service.allStudents(), HttpStatus.OK);
    }

    // how do we get inputs through a request
    // **************************************

    @GetMapping("query")
    public ResponseEntity<Object> filterStudents(@RequestBody Query query)
    {
        if (query.getQueryValue() == null || query.getQueryValue().isEmpty())
        {
            return new ResponseEntity<>("The query string cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        //an alternative using factory methods
        return ResponseEntity.ok(service.searchStudents(query.getQueryValue()));
    }

    // **************************************

    //POST request to http://localhost:8081/api/v1/student
    @PostMapping("")
    public ResponseEntity<Object> addStudent(@RequestBody Student tempStudent)
    {
        //don't add an empty student
        if (tempStudent.getStudentFName() == null || tempStudent.getStudentFName().isEmpty())
        {
            return new ResponseEntity<>("The student first name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.addStudent(tempStudent.getStudentFName(), tempStudent.getStudentLName(),
                tempStudent.getStudentRaag(), tempStudent.getStudentInstrument()), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Object> editStudent(@RequestBody Student tempStudent)
    {
        //make sure the id of the Raag is found
        if (!service.studentExists(tempStudent.getStudentID()))
        {
            return new ResponseEntity<>("Student does not exist!", HttpStatus.NOT_FOUND);
        }
        //don't add an empty joke
        else if (tempStudent.getStudentFName() == null || tempStudent.getStudentFName().isEmpty())
        {
            return new ResponseEntity<>("The student first name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.updateStudent(tempStudent.getStudentID(),
                tempStudent.getStudentFName(), tempStudent.getStudentLName(), tempStudent.getStudentRaag(),
                tempStudent.getStudentInstrument()), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteStudent(@RequestBody Student tempStudent)
    {
        // make sure the id of the Instrument is found
        if (!service.studentExists(tempStudent.getStudentID()))
        {
            return new ResponseEntity<>("Student does not exist!", HttpStatus.NOT_FOUND);
        }

        service.deleteStudent(tempStudent.getStudentID());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
