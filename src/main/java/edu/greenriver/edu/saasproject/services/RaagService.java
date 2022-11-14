package edu.greenriver.edu.saasproject.services;

import edu.greenriver.edu.saasproject.models.Raag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for supporting CRUD functionality methods for Raag objects
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@Service
public class RaagService
{
    private List<Raag> raags = new ArrayList<>(List.of(
            new Raag("None", "N/A", "N/A", "N/A"),
            new Raag("Kalyan", "Kalyaan", "Evening", "Ga"),
            new Raag("Bihag", "Bilaaval", "Night", "Ga"),
            new Raag("Desh", "Khamaj", "Morning", "Re")

    ));

    /**
     * Creates a new Raag object and adds it to the list
     *
     * @param raagName Name of the Raag
     * @param thaat Name of the thaat
     * @param raagTime Time of Raag
     * @param vaadi Name of the vaadi
     * @return Returns a Raag object
     */
    //CREATE
    public Raag addRaag(String raagName, String thaat,
                        String raagTime, String vaadi)
    {
        Raag added = new Raag(raagName, thaat, raagTime, vaadi);
        raags.add(added);
        return added;
    }

    /**
     * @return Returns the list of all Raag objects
     */
    //READ
    public List<Raag> allRaags()
    {
        return raags;
    }

    /**
     * @param queryValue Accepts specific Raag object being searched for
     * @return Returns list containing Raag objects
     */
    public List<Raag> searchRaags(String queryValue)
    {
        return raags.stream()
                .filter(raag -> raag.getRaagName().toLowerCase()
                        .contains(queryValue.toLowerCase()))
                .toList();
    }

    /**
     * @param uuid Unique ID of the Raag
     * @param raagName Name of the Raag
     * @param thaat Name of the Thaat
     * @param raagTime Time of the Raag
     * @param vaadi Name of the Thaat
     * @return Returns the Raag object
     */
    //UPDATE
    public Raag updateRaag(UUID uuid, String raagName, String thaat,
                           String raagTime, String vaadi)
    {
        Optional<Raag> foundRaag = raags.stream()
                .filter(raag -> raag.getRaagID().equals(uuid))
                .findFirst();

        if (foundRaag.isPresent())
        {
            //update it
            Raag raag = foundRaag.get();
            raag.setRaagName(raagName);
            raag.setThaat(thaat);
            raag.setRaagTime(raagTime);
            raag.setVaadi(vaadi);
            return raag;
        }
        else
        {
            //otherwise, return null
            return null;
        }
    }

    /**
     * @param uuid Unique ID of the Raag
     */
    //DELETE
    public void deleteRaag(UUID uuid)
    {
        raags = raags.stream()
                .filter(raag -> !raag.getRaagID().equals(uuid))
                .toList();
    }

    /**
     * @param uuid Unique ID for the Raag object
     * @return Returns boolean value of whether the Raag is in the list
     */
    public boolean raagExists(UUID uuid)
    {
        return raags.stream()
                .anyMatch(raag -> raag.getRaagID().equals(uuid));
    }

    @Override
    public String toString() {
        return "RaagService{" +
                "raags=" + raags +
                '}';
    }
}
