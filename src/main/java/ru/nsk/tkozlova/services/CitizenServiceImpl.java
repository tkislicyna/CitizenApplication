package ru.nsk.tkozlova.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsk.tkozlova.dao.CitizenDao;
import ru.nsk.tkozlova.model.Citizen;

import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
@Service("сitizenService")
@Transactional
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenDao dao;

    @Override
    public Citizen findById(int id) {
            return dao.findById(id);
    }

    @Override
    public void saveCitizen(Citizen citizen) {
        dao.saveCitizen(citizen);
    }

    @Override
    public void updateCitizen(Citizen citizen) {
        Citizen entity = dao.findById(citizen.getId());
        if(entity!=null){
            entity.setFirstName(citizen.getFirstName());
            entity.setMiddleName(citizen.getMiddleName());
            entity.setLastName(citizen.getLastName());
            entity.setBirthDay(citizen.getBirthDay());
            entity.setAddress(citizen.getAddress());
        }
    }

    @Override
    public void deleteCitizenById(Integer id) {
        dao.deleteCitizenById(id);
    }

    @Override
    public List<Citizen> findAllCitizens() {
        return dao.findAllCitizens();
    }
}
