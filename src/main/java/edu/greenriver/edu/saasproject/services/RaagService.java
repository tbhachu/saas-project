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
            new Raag("Kalyan", "Kalyaan", "Evening",
                    "Sa Re Ga ma Pa Da Ni Sa", "Sa Ni Da Pa ma Ga Re Sa"),
            new Raag("Bihag", "Bilaaval", "Night",
                    "Sa Ga Ma Pa Ni Sa", "Sa Ni Pa Ga Ma Ga  Re Sa"),
            new Raag("Desh", "Khamaj", "Morning",
                    "Na Sa Re Ma Pa Ni Sa", "Sa ni Dha Pa Da Ma Ga Re Pa Ma Ga Re Ga Ni Sa"),
            new Raag("Maajh", "Khamaj", "Night",
                    "Sa Re Ma Pa Ni Sa", "Sa ni Da Pa Da Ma Ga Ma Re Pa ga Re Ni Sa")

    ));

    //CREATE
    public Raag addRaag(String raagName, String thaat,
                        String time, String aroh, String avroh)
    {
        Raag added = new Raag(raagName, thaat, time, aroh, avroh);
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
    public Raag updateRaag(UUID id, String raagName)
    {
        Optional<Raag> foundRaag = raags.stream()
                .filter(raag -> raag.getRaagID().equals(id))
                .findFirst();

        if (foundRaag.isPresent())
        {
            //update it
            Raag raag = foundRaag.get();
            raag.setRaagName(raagName);
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
