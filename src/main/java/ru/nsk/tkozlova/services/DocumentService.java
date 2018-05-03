package ru.nsk.tkozlova.services;

import ru.nsk.tkozlova.model.IdentityDocument;

import java.util.List;

/**
 * @project CitizenApplication
 * @autor Toma on 4/26/2018.
 */
public interface DocumentService {
    IdentityDocument findById(int id);

    void saveDocument(IdentityDocument document);

    void updateDocument(IdentityDocument Document);

    void deleteDocumentById(Integer id);

    List<IdentityDocument> findAllDocumentsByHolderId(Integer holder);
}
