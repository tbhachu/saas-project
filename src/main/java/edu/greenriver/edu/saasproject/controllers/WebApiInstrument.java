package edu.greenriver.edu.saasproject.controllers;
import edu.greenriver.edu.saasproject.models.Instrument;
import edu.greenriver.edu.saasproject.models.Query;
import edu.greenriver.edu.saasproject.services.InstrumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class establishes our WEB API to use RESTful services for instrument objects.
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/instrument")
@CrossOrigin(origins = "*")
public class WebApiInstrument
{

    private InstrumentService service;

    /**
     * Constructor method for the WebApiInstrument class
     *
     * @param service This method accepts the service field whenever a request method is called
     */
    public WebApiInstrument(InstrumentService service)
    {
        this.service = service;
    }

    /**
     * This method returns all instrument objects
     *
     * @return Returns the response after requesting all objects
     */
    //GET request to http://localhost:8081/api/v1/instrument
    @GetMapping("")
    public ResponseEntity<List<Instrument>> allInstruments()
    {
        //return the response using a constructor
        return new ResponseEntity<>(service.allInstruments(), HttpStatus.OK);
    }

    // how do we get inputs through a request
    // **************************************

    /**
     * This method returns a single instrument object being searched for
     *
     * @param query Accepts the instrument object being searched for
     * @return Returns the response after requesting a specific object
     */
    @GetMapping("query")
    public ResponseEntity<Object> filterInstruments(@RequestBody Query query)
    {
        if (query.getQueryValue() == null || query.getQueryValue().isEmpty())
        {
            return new ResponseEntity<>("The query string cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        //an alternative using factory methods
        return ResponseEntity.ok(service.searchInstruments(query.getQueryValue()));
    }

    // **************************************

    /**
     * This method adds a new instrument object to the list
     *
     * @param tempInstrument A temporary object to ensure an empty object doesn't get added
     * @return Returns a response after using a POST request
     */
    //POST request to http://localhost:8081/api/v1/instrument
    @PostMapping("")
    public ResponseEntity<Object> addInstrument(@RequestBody Instrument tempInstrument)
    {
        //don't add an empty instrument
        if (tempInstrument.getInstrumentName() == null || tempInstrument.getInstrumentName().isEmpty())
        {
            return new ResponseEntity<>("The instrument name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.addInstrument(
                tempInstrument.getInstrumentName(),
                tempInstrument.getInstrumentType(),
                tempInstrument.getMaterial(),
                tempInstrument.isCarryBag()),
                HttpStatus.CREATED);
    }

    /**
     * This method allows user to edit the instrument object's fields
     *
     * @param tempInstrument Accepts a temporary object to ensure instrument is found
     * @return Returns response after editing the instrument using PUT request
     */
    @PutMapping("")
    public ResponseEntity<Object> editInstrument(@RequestBody Instrument tempInstrument)
    {
        //make sure the id of the Instrument is found
        if (!service.instrumentExists(tempInstrument.getInstrumentID()))
        {
            return new ResponseEntity<>("Instrument does not exist!", HttpStatus.NOT_FOUND);
        }
        //don't add an empty instrument
        else if (tempInstrument.getInstrumentName() == null || tempInstrument.getInstrumentName().isEmpty())
        {
            return new ResponseEntity<>("The instrument name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.updateInstrument(tempInstrument.getInstrumentID(),
                tempInstrument.getInstrumentName(), tempInstrument.getInstrumentType(), tempInstrument.getMaterial(),
                tempInstrument.isCarryBag()), HttpStatus.OK);
    }

    /**
     * This method deletes an instrument object from the list
     *
     * @param tempInstrument Accepts a temporary object to ensure correct instrument object is within list
     * @return Returns response after calling DELETE request
     */
    @DeleteMapping("")
    public ResponseEntity<Object> deleteInstrument(@RequestBody Instrument tempInstrument)
    {
        // make sure the id of the Instrument is found
        if (!service.instrumentExists(tempInstrument.getInstrumentID()))
        {
            return new ResponseEntity<>("Instrument does not exist!", HttpStatus.NOT_FOUND);
        }

        service.deleteInstrument(tempInstrument.getInstrumentID());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public String toString() {
        return "WebApiInstrument{" +
                "service=" + service +
                '}';
    }
}
