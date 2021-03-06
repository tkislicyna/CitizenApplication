package ru.nsk.tkozlova.dao;

import ru.nsk.tkozlova.model.Citizen;
import ru.nsk.tkozlova.model.IdentityDocument;

import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
public interface DocumentDao {
    IdentityDocument findById(int id);

    void saveDocument(IdentityDocument document);

    void deleteDocumentById(int id);

    List<IdentityDocument> findAllDocumentByHolderId(Integer holderId);
}
