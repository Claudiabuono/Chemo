package patientmanagement.storage;

import com.mongodb.Mongo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import connector.DatabaseConnector;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import patientmanagement.application.PatientBean;
import patientmanagement.application.TherapyBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public ArrayList<PatientBean> findDocument(String key, String value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq(key, value);

        //Cerca il documento
        FindIterable<Document> iterDoc = collection.find(filter);

        Iterator<Document> it = iterDoc.iterator();
        ArrayList<PatientBean> patients = new ArrayList<>();

        while(it.hasNext()) {
            Document document = (Document) it.next();
            ArrayList<TherapyBean> therapies = convertToArray(document.getList("therapy", TherapyBean.class));
            PatientBean patient = new PatientBean(document.getString("taxCode"), document.getString("name"), document.getString("surname"), document.getDate("birthDate"),
                    document.getString("city"), document.getString("phoneNumber"), document.getBoolean("status"), document.getString("condition"), document.getString("notes") ,therapies);

            patients.add(patient);
        }

        return patients;
    }

    public PatientBean findDocumentsById(String value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq("_id", new ObjectId(value));

        //Cerca il documento
        Document document = (Document) collection.find(filter).first();

        ArrayList<TherapyBean> therapies = convertToArray(document.getList("therapy", TherapyBean.class));
        PatientBean patient = new PatientBean(document.getString("taxCode"), document.getString("name"), document.getString("surname"), document.getDate("birthDate"),
                document.getString("city"), document.getString("phoneNumber"), document.getBoolean("status"), document.getString("condition"), document.getString("notes") ,therapies);
        patient.setPatientId(value);

        return patient;
    }


    //Metodi ausiliari
    private MongoCollection<Document> getCollection() {
        MongoDatabase mongoDatabase = DatabaseConnector.getDatabase();

        MongoCollection<Document> collection = mongoDatabase.getCollection("patient");
        System.out.println("Collection 'patient' recuperata con successo");
        return collection;
    }

    private Document createDocument(PatientBean patient) {
        return new Document("_id", new ObjectId(patient.getPatientId()))
                .append("taxCode", patient.getTaxCode())
                .append("name", patient.getName())
                .append("surname", patient.getSurname())
                .append("birthDate", patient.getBirthDate())
                .append("city", patient.getCity())
                .append("phoneNumber", patient.getPhoneNumber())
                .append("status", patient.getStatus())
                .append("condition", patient.getCondition())
                .append("notes", patient.getNotes())
                .append("therapy", patient.getTherapy());
    }

    private ArrayList<TherapyBean> convertToArray(List<TherapyBean> list) {

        return new ArrayList<>(list);

    }
}