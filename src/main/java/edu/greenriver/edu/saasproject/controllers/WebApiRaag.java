package edu.greenriver.edu.saasproject.controllers;
import edu.greenriver.edu.saasproject.models.Query;
import edu.greenriver.edu.saasproject.models.Raag;
import edu.greenriver.edu.saasproject.services.RaagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/raag")
public class WebApiRaag {

    @RequestMapping("/home")
    public String home()
    {
        return "index";
    }

    private RaagService service;

    public WebApiRaag(RaagService service)
    {
        this.service = service;
    }

    //GET request to http://localhost:8081/api/v1/raag
    @GetMapping("")
    public ResponseEntity<List<Raag>> allRaags()
    {
        //return the response using a constructor
        return new ResponseEntity<>(service.allRaags(), HttpStatus.OK);
    }

    // how do we get inputs through a request
    // **************************************

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

    @PutMapping("")
    public ResponseEntity<Object> editRaag(@RequestBody Raag tempRaag)
    {
        //make sure the id of the Raag is found
        if (!service.raagExists(tempRaag.getRaagID()))
        {
            return new ResponseEntity<>("Raag does not exist!", HttpStatus.NOT_FOUND);
        }
        //don't add an empty joke
        else if (tempRaag.getRaagName() == null || tempRaag.getRaagName().isEmpty())
        {
            return new ResponseEntity<>("The Raag name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.updateRaag(tempRaag.getRaagID(), tempRaag.getRaagName(),
                tempRaag.getThaat(), tempRaag.getRaagTime(), tempRaag.getVaadi()), HttpStatus.OK);
    }

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
}
