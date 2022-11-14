package edu.greenriver.edu.saasproject.services;
import edu.greenriver.edu.saasproject.models.Instrument;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for supporting CRUD functionality methods for instrument objects
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@Service
public class InstrumentService
{
    private List<Instrument> instruments = new ArrayList<>(List.of(
            new Instrument("None", "N/A", "N/A", false),
            new Instrument("Taus", "Stringed", "Tun", true),
            new Instrument("Esraj", "Stringed", "Tun", true),
            new Instrument("Rabab", "Stringed", "Mulberry", false)
    ));

    /**
     * This method helps create a new Instrument object and adds it to the list.
     *
     * @param instrumentName The name of the instrument
     * @param instrumentType The instrument type
     * @param material The material of the instrument
     * @param carryBag True or false value indicates whether instrument comes with carry bag
     * @return Returns an Instrument object
     */
    //CREATE
    public Instrument addInstrument(String instrumentName, String instrumentType,
                        String material, boolean carryBag)
    {
        Instrument added = new Instrument(instrumentName, instrumentType, material, carryBag);
        instruments.add(added);
        return added;
    }

    /**
     * This method reads from the list and returns all instrument objects
     *
     * @return Returns the list which includes all instrument objects
     */
    //READ
    public List<Instrument> allInstruments()
    {
        return instruments;
    }

    /**
     * This method searches for a specific instrument object in the list
     *
     * @param queryValue Accepts specific instrument user is looking for
     * @return Returns the list which includes all instrument objects
     */
    public List<Instrument> searchInstruments(String queryValue)
    {
        return instruments.stream()
                .filter(instrument -> instrument.getInstrumentName().toLowerCase()
                        .contains(queryValue.toLowerCase()))
                .toList();
    }

    /**
     * This method is for updated a specific instrument object
     *
     * @param uuid Unique ID of the instrument
     * @param instrumentName Name of the instrument
     * @param instrumentType Type if instrument
     * @param material Material of the instrument
     * @param carryBag True or false value indicating if instrument includes carry bag
     * @return Returns an instrument object
     */
    //UPDATE
    public Instrument updateInstrument(UUID uuid, String instrumentName, String instrumentType,
                                       String material, boolean carryBag)
    {
        Optional<Instrument> foundInstrument = instruments.stream()
                .filter(instrument -> instrument.getInstrumentID().equals(uuid))
                .findFirst();

        if (foundInstrument.isPresent())
        {
            //update it
            Instrument instrument = foundInstrument.get();
            instrument.setInstrumentName(instrumentName);
            instrument.setInstrumentType(instrumentType);
            instrument.setMaterial(material);
            instrument.setCarryBag(carryBag);
            return instrument;
        }
        else
        {
            //otherwise, return null
            return null;
        }
    }

    /**
     * This method deletes an instrument object from the list
     *
     * @param uuid Unique instrument object ID
     */
    //DELETE
    public void deleteInstrument(UUID uuid)
    {
        instruments = instruments.stream()
                .filter(instrument -> !instrument.getInstrumentID().equals(uuid))
                .toList();
    }

    /**
     * @param uuid Unique instrument object ID
     * @return Returns true or false value verifying if instrument exists or not
     */
    public boolean instrumentExists(UUID uuid)
    {
        return instruments.stream()
                .anyMatch(instrument -> instrument.getInstrumentID().equals(uuid));
    }

    @Override
    public String toString() {
        return "InstrumentService{" +
                "instruments=" + instruments +
                '}';
    }
}
