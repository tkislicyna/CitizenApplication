package ru.nsk.tkozlova.services;

import ru.nsk.tkozlova.model.Citizen;

import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
public interface CitizenService {
    Citizen findById(int id);

    void saveCitizen(Citizen citizen);

    void updateCitizen(Citizen citizen);

    void deleteCitizenById(Integer id);

    List<Citizen> findAllCitizens();
}
