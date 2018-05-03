package ru.nsk.tkozlova.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import ru.nsk.tkozlova.model.IdentityDocument;

import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
public class DocumentDaoImpl extends AbstractDao<Integer, IdentityDocument> implements DocumentDao{
    @Override
    public IdentityDocument findById(int id) {
        return getByKey(id);
    }

    @Override
    public void saveDocument(IdentityDocument document) {
        persist(document);
    }

    @Override
    public void deleteDocumentById(int id) {
        Query query = getSession().createSQLQuery("delete from documents where id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<IdentityDocument> findAllDocumentByHolderId(Integer holderId) {
        Criteria criteria = getSession().createCriteria(IdentityDocument.class);
        return criteria.add(Restrictions.eq("holder_id", holderId)).list();
    }
}
