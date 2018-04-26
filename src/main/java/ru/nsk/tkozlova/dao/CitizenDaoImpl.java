package ru.nsk.tkozlova.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import ru.nsk.tkozlova.model.Citizen;

import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */

@Repository("citizenDaoImpl")
public class CitizenDaoImpl extends AbstractDao<Integer, Citizen> implements CitizenDao {
    @Override
    public Citizen findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveCitizen(Citizen citizen) {
        persist(citizen);
    }

    @Override
    public void deleteCitizenById(int id) {
        Query query = getSession().createSQLQuery("delete from Employee where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Citizen> findAllCitizens() {
        Criteria criteria = createEntityCriteria();
        return (List<Citizen>) criteria.list();
    }
}
