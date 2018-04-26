package ru.nsk.tkozlova.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import ru.nsk.tkozlova.model.Citizen;

import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
public interface CitizenDao {

    Citizen findById(int id);

    void saveCitizen(Citizen citizen);

    void deleteCitizenById(int id);

    List<Citizen> findAllCitizens();

}
