package edu.greenriver.edu.saasproject.controllers;
import edu.greenriver.edu.saasproject.models.Query;
import edu.greenriver.edu.saasproject.models.Raag;
import edu.greenriver.edu.saasproject.services.RaagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class establishes our WEB API to use RESTful services for Raag objects.
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/raag")
public class WebApiRaag {

    private RaagService service;

    /**
     * Constructor method for the WebApiRaag class
     *
     * @param service This method accepts the service field whenever a request method is called
     */
    public WebApiRaag(RaagService service)
    {
        this.service = service;
    }

    /**
     * Method returns all Raag objects
     *
     * @return Returns the response after requesting all objects
     */
    //GET request to http://localhost:8081/api/v1/raag
    @GetMapping("")
    public ResponseEntity<List<Raag>> allRaags()
    {
        //return the response using a constructor
        return new ResponseEntity<>(service.allRaags(), HttpStatus.OK);
    }

    // how do we get inputs through a request
    // **************************************

    /**
     * This method returns a single Raag object being searched for
     *
     * @param query Accepts the Raag object being searched for
     * @return Returns the response after requesting a specific object
     */
    //GET request to http://localhost:8081/api/v1/query
    @GetMapping("query")
    public ResponseEntity<Object> filterRaags(@RequestBody Query query)
    {
        //we won't allow this end-point to be used with an empty query
        if (query.getQueryValue() == null || query.getQueryValue().isEmpty())
        {
            return new ResponseEntity<>("The query string cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        //an alternative using factory methods
        return ResponseEntity.ok(service.searchRaags(query.getQueryValue()));
    }

    // **************************************

    /**
     * This method adds a new Raag object to the list
     *
     * @param tempRaag A temporary object to ensure an empty object doesn't get added
     * @return Returns a response after using a POST request
     */
    //POST request to http://localhost:8081/api/v1/raag
    @PostMapping("")
    public ResponseEntity<Object> addRaag(@RequestBody Raag tempRaag)
    {
        //don't add an empty joke
        if (tempRaag.getRaagName() == null || tempRaag.getRaagName().isEmpty())
        {
            return new ResponseEntity<>("The raag name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.addRaag(tempRaag.getRaagName(), tempRaag.getThaat(),
                tempRaag.getRaagTime(), tempRaag.getVaadi()), HttpStatus.CREATED);
    }

    /**
     * This method allows user to edit the Raag object's fields
     *
     * @param tempRaag Accepts a temporary object to ensure instrument is found
     * @return Returns response after editing the Raag using PUT request
     */
    @PutMapping("")
    public ResponseEntity<Object> editRaag(@RequestBody Raag tempRaag)
    {
        //make sure the id of the Raag is found
        if (!service.raagExists(tempRaag.getRaagID()))
        {
            return new ResponseEntity<>("Raag does not exist!", HttpStatus.NOT_FOUND);
        }
        //don't add an empty Raag
        else if (tempRaag.getRaagName() == null || tempRaag.getRaagName().isEmpty())
        {
            return new ResponseEntity<>("The Raag name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.updateRaag(tempRaag.getRaagID(), tempRaag.getRaagName(),
                tempRaag.getThaat(), tempRaag.getRaagTime(), tempRaag.getVaadi()), HttpStatus.OK);
    }

    /**
     * This method deletes a Raag object from the list
     *
     * @param tempRaag Accepts a temporary object to ensure correct Raag object is within list
     * @return Returns response after calling DELETE request
     */
    @DeleteMapping("")
    public ResponseEntity<Object> deleteRaag(@RequestBody Raag tempRaag)
    {
        // make sure the id of the Raag is found
        if (!service.raagExists(tempRaag.getRaagID()))
        {
            return new ResponseEntity<>("Raag does not exist!", HttpStatus.NOT_FOUND);
        }

        service.deleteRaag(tempRaag.getRaagID());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public String toString() {
        return "WebApiRaag{" +
                "service=" + service +
                '}';
    }
}
