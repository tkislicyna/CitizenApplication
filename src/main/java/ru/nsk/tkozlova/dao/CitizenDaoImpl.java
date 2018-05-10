package ru.nsk.tkozlova.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nsk.tkozlova.model.Citizen;

import java.awt.print.Book;
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
        Query query = getSession().createSQLQuery("delete from citizens where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Citizen> findAllCitizens() {
        Criteria criteria = createEntityCriteria();
        return (List<Citizen>) criteria.list();
    }

    @Override
    public List<Citizen> findCitizensByKeyword(String word) {
        Criteria cr = getSession().createCriteria(Citizen.class);
        cr.add(Restrictions.disjunction().add(Restrictions.or(
                Restrictions.or(Restrictions.ilike("firstName", word, MatchMode.ANYWHERE),
                        Restrictions.ilike("middleName", word, MatchMode.ANYWHERE)),
                Restrictions.ilike("lastName", word, MatchMode.ANYWHERE))));

        cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Citizen>) cr.list();
    }

}
