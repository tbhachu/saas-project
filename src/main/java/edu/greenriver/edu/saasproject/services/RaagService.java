package edu.greenriver.edu.saasproject.services;

import edu.greenriver.edu.saasproject.models.Raag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RaagService
{
    private List<Raag> raags = new ArrayList<>(List.of(
            new Raag("Kalyan", "Kalyaan", "Evening", "Ga"),
            new Raag("Bihag", "Bilaaval", "Night", "Ga"),
            new Raag("Desh", "Khamaj", "Morning", "Re")
            //new Raag("Maajh", "Khamaj", "Night", "Ma")

    ));

    //CREATE
    public Raag addRaag(String raagName, String thaat,
                        String raagTime, String vaadi)
    {
        Raag added = new Raag(raagName, thaat, raagTime, vaadi);
        raags.add(added);
        return added;
    }

    //READ
    public List<Raag> allRaags()
    {
        return raags;
    }

    public List<Raag> searchRaags(String queryValue)
    {
        return raags.stream()
                .filter(raag -> raag.getRaagName().toLowerCase()
                        .contains(queryValue.toLowerCase()))
                .toList();
    }

    //UPDATE
    public Raag updateRaag(UUID id, String raagName, String thaat,
                           String raagTime, String vaadi)
    {
        Optional<Raag> foundRaag = raags.stream()
                .filter(raag -> raag.getRaagID().equals(id))
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

    //DELETE
    public void deleteRaag(UUID id)
    {
        raags = raags.stream()
                .filter(raag -> !raag.getRaagID().equals(id))
                .toList();
    }

    public boolean raagExists(UUID id)
    {
        return raags.stream()
                .anyMatch(raag -> raag.getRaagID().equals(id));
    }

    @Override
    public String toString() {
        return "RaagService{" +
                "raags=" + raags +
                '}';
    }
}
