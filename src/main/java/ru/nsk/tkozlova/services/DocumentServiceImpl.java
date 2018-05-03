package ru.nsk.tkozlova.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.nsk.tkozlova.dao.DocumentDao;
import ru.nsk.tkozlova.model.Citizen;
import ru.nsk.tkozlova.model.IdentityDocument;

import javax.swing.text.Document;
import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDao documentDao;

    @Override
    public IdentityDocument findById(int id) {
        return documentDao.findById(id);
    }

    @Override
    public void saveDocument(IdentityDocument document) {
        documentDao.saveDocument(document);
    }

    @Override
    public void updateDocument(IdentityDocument document) {
        IdentityDocument entry = documentDao.findById(document.getId());
        if (document != null) {
            entry.setType(document.getType());
            entry.setIssueDate(document.getIssueDate());
            entry.setExpiryDate(document.getExpiryDate());
            entry.setAuthority(document.getAuthority());
        }
    }

    @Override
    public void deleteDocumentById(Integer id) {
        documentDao.deleteDocumentById(id);
    }

    @Override
    public List<IdentityDocument> findAllDocumentsByHolderId(Integer holder) {
        return documentDao.findAllDocumentByHolderId(holder);
    }
}
