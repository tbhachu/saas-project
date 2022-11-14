package edu.greenriver.edu.saasproject.controllers;
import edu.greenriver.edu.saasproject.models.Query;
import edu.greenriver.edu.saasproject.models.Student;
import edu.greenriver.edu.saasproject.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class establishes our WEB API to use RESTful services for Student objects.
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/student")
public class WebApiStudent
{


    private StudentService service;

    /**
     * Constructor method for the WebApiStudent class
     *
     * @param service This method accepts the service field whenever a request method is called
     */
    public WebApiStudent(StudentService service)
    {
        this.service = service;
    }

    /**
     * Method returns all Student objects
     *
     * @return Returns the response after requesting all objects
     */
    //GET request to http://localhost:8081/api/v1/student
    @GetMapping("")
    public ResponseEntity<List<Student>> allStudents()
    {
        //return the response using a constructor
        return new ResponseEntity<>(service.allStudents(), HttpStatus.OK);
    }

    // how do we get inputs through a request
    // **************************************

    /**
     * This method returns a single Student object being searched for
     *
     * @param query Accepts the Student object being searched for
     * @return Returns the response after requesting a specific object
     */
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

    /**
     * This method adds a new Student object to the list
     *
     * @param tempStudent A temporary object to ensure an empty object doesn't get added
     * @return Returns a response after using a POST request
     */
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

    /**
     * This method allows user to edit the Student object's fields
     *
     * @param tempStudent Accepts a temporary object to ensure student is found
     * @return Returns response after editing the student's details using PUT request
     */
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

    /**
     * This method deletes a Student object from the list
     *
     * @param tempStudent Accepts a temporary object to ensure correct Student object is within list
     * @return Returns response after calling DELETE request
     */
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

    @Override
    public String toString() {
        return "WebApiStudent{" +
                "service=" + service +
                '}';
    }
}
