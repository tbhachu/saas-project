package edu.greenriver.edu.saasproject.controllers;
import edu.greenriver.edu.saasproject.models.Instrument;
import edu.greenriver.edu.saasproject.models.Query;
import edu.greenriver.edu.saasproject.services.InstrumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instrument")
public class WebApiInstrument
{
    @RequestMapping("/home")
    public String home()
    {
        return "index";
    }

    private InstrumentService service;

    public WebApiInstrument(InstrumentService service)
    {
        this.service = service;
    }

    //GET request to http://localhost:8081/api/v1/instrument
    @GetMapping("")
    public ResponseEntity<List<Instrument>> allInstruments()
    {
        //return the response using a constructor
        return new ResponseEntity<>(service.allInstruments(), HttpStatus.OK);
    }

    // how do we get inputs through a request
    // **************************************

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

    //POST request to http://localhost:8081/api/v1/instrument
    @PostMapping("")
    public ResponseEntity<Object> addInstrument(@RequestBody Instrument tempInstrument)
    {
        //don't add an empty joke
        if (tempInstrument.getInstrumentName() == null || tempInstrument.getInstrumentName().isEmpty())
        {
            return new ResponseEntity<>("The instrument name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.addInstrument(tempInstrument.getInstrumentName(), tempInstrument.getInstrumentType(),
                tempInstrument.getMaterial(), tempInstrument.isCarryBag()), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Object> editInstrument(@RequestBody Instrument tempInstrument)
    {
        //make sure the id of the Raag is found
        if (!service.instrumentExists(tempInstrument.getInstrumentID()))
        {
            return new ResponseEntity<>("Instrument does not exist!", HttpStatus.NOT_FOUND);
        }
        //don't add an empty joke
        else if (tempInstrument.getInstrumentName() == null || tempInstrument.getInstrumentName().isEmpty())
        {
            return new ResponseEntity<>("The instrument name cannot be empty/null", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(service.updateInstrument(tempInstrument.getInstrumentID(),
                tempInstrument.getInstrumentName(), tempInstrument.getInstrumentType(), tempInstrument.getMaterial(),
                tempInstrument.isCarryBag()), HttpStatus.OK);
    }

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
}
