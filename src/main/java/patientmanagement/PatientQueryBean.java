package patientmanagement;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import connector.DatabaseConnector;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;

public class PatientQueryBean {

    //Inserimento singolo documento nella Collection
    public void insertDocument(PatientBean patient) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il documento da inserire nella Collection
        Document document = createDocument(patient);

        //Inserisci il documento nella collection
        collection.insertOne(document);

        System.out.println("Documento inserito con successo nella Collection");
    }

    //Inserimento collezione di documenti nella Collection
    public void insertDocuments(ArrayList<PatientBean> patients) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea un documento per ogni medicinale in medicines
        ArrayList<Document> docs = new ArrayList<>();
        for (PatientBean patient : patients) {
            Document doc = createDocument(patient);
            docs.add(doc);
        }

        //Inserisci i documenti nella collection
        collection.insertMany(docs);

        System.out.println("Documenti inseriti con successo nella Collection");
    }

    //Elimina documento dalla Collection
    public void deleteDocument(String key, String value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq(key, value);

        //Cancella il documento
        collection.deleteOne(filter);

        System.out.println("Documento eliminato con successo nella Collection");
    }

    //Modifica di un documento
    public void updateDocument(String id, String valId, String key, String valKey) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq(id, valId);

        //Aggiorna il documento
        collection.updateOne(filter, Updates.set(key, valKey));

        System.out.println("Documento aggiornato con successo nella Collection");
    }

    //Ricerca di un documento nella Collection data una coppia (key, value)
    public Iterator<Document> findDocument(String key, String value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq(key, value);

        //Cerca il documento
        FindIterable<Document> iterDoc = collection.find(filter);

        return iterDoc.iterator();
    }


    //Metodi ausiliari
    private MongoCollection<Document> getCollection() {
        MongoDatabase mongoDatabase = DatabaseConnector.getDatabase();

        MongoCollection<Document> collection = mongoDatabase.getCollection("paziente");
        System.out.println("Collection 'paziente' recuperata con successo");
        return collection;
    }

    private Document createDocument(PatientBean patient) {
        return new Document("codiceFiscale", patient.getCodiceFiscale())
                .append("nome", patient.getNome())
                .append("cognome", patient.getCognome())
                .append("dataNascita", patient.getDataNascita())
                .append("cittaNascita", patient.getCittaNascita())
                .append("numTelefono", patient.getNumTelefono())
                .append("stato", patient.getStato())
                .append("patologia", patient.getPatologia());
    }
}