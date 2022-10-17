package edu.greenriver.edu.saasproject.services;

import edu.greenriver.edu.saasproject.models.Instrument;
import edu.greenriver.edu.saasproject.models.Raag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstrumentService
{

    private List<Instrument> instruments = new ArrayList<>(List.of(
            new Instrument("Tabla", "Percussion", "Shisham", true),
            new Instrument("Esraj", "Stringed", "Tun", true),
            new Instrument("Rabab", "Stringed", "Mulberry", false)

    ));

    //CREATE
    public Instrument addInstrument(String name, String type,
                        String material, boolean carryBag)
    {
        Instrument added = new Instrument(name, type, material, carryBag);
        instruments.add(added);
        return added;
    }

    //READ
    public List<Instrument> allInstruments()
    {
        return instruments;
    }

    public List<Instrument> searchInstruments(String queryValue)
    {
        return instruments.stream()
                .filter(instrument -> instrument.getName().toLowerCase()
                        .contains(queryValue.toLowerCase()))
                .toList();
    }

    //UPDATE
    public Instrument updateInstrument(UUID id, String name, String type,
                                       String material, boolean carryBag)
    {
        Optional<Instrument> foundInstrument = instruments.stream()
                .filter(instrument -> instrument.getInstrumentID().equals(id))
                .findFirst();

        if (foundInstrument.isPresent())
        {
            //update it
            Instrument instrument = foundInstrument.get();
            instrument.setName(name);
            instrument.setType(type);
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

    //DELETE
    public void deleteInstrument(UUID id)
    {
        instruments = instruments.stream()
                .filter(instrument -> !instrument.getInstrumentID().equals(id))
                .toList();
    }

    public boolean instrumentExists(UUID id)
    {
        return instruments.stream()
                .anyMatch(instrument -> instrument.getInstrumentID().equals(id));
    }

}
