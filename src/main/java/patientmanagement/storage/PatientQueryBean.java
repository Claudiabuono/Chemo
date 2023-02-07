package patientmanagement.storage;

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
import patientmanagement.application.TherapyMedicineBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

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

    //Inserimento terapia in un paziente
    public void insertDocument(TherapyBean therapy, String patientId) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il documento da inserire nella Collection
        Document therapyDocument = createDocument(therapy);

        //Crea il filtro
        Bson filter = Filters.eq("_id", new ObjectId(patientId));

        //Recupera il documento del paziente
        Document patientDoc = collection.find(filter).first();

        //Inserisci il documento della terapia nel documento del paziente
        collection.updateOne(patientDoc, new Document("$set", therapyDocument));

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
    public void updateDocument(String id, String valId, String key, Object valKey) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq(id, new ObjectId(valId));

        //Aggiorna il documento
        collection.updateOne(filter, Updates.set(key, valKey));

        System.out.println("Documento aggiornato con successo nella Collection");
    }

    //Ricerca di un documento nella Collection data una coppia (key, value)
    public ArrayList<PatientBean> findDocument(String key, Object value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq(key, value);

        //Cerca il documento
        FindIterable<Document> iterDoc = collection.find(filter);

        Iterator<Document> it = iterDoc.iterator();
        ArrayList<PatientBean> patients = new ArrayList<>();

        //Itero su ogni documento restituito dalla query
        while(it.hasNext()) {
            Document document = it.next();
            //ArrayList<TherapyBean> therapies = convertToArray(document.getList("therapy", TherapyBean.class));
            PatientBean patient = new PatientBean(document.getString("taxCode"), document.getString("name"), document.getString("surname"), document.getDate("birthDate"),
                    document.getString("city"), document.getString("phoneNumber"), document.getBoolean("status"), document.getString("condition"), document.getString("notes") ,therapyParser((Document) document.get("therapy")));
            patient.setPatientId(document.get("_id").toString());
            patients.add(patient);
        }

        return patients;
    }

    public ArrayList<PatientBean> findDocument(ArrayList<String> key, ArrayList<Object> value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson finalFilter = null;
        Bson filter = null;
        Pattern regex;
        int i = 0;

        //Ciclo sull'array di filtri
        do {
            switch (key.get(i)) {
                case "name", "surname" -> { //Nome e Cognome lavorano con le regex
                    regex = Pattern.compile(Pattern.quote((String) value.get(i)), Pattern.CASE_INSENSITIVE);
                    if (i == 0)
                        finalFilter = Filters.eq(key.get(i), regex);
                    else
                        filter = Filters.eq(key.get(i), regex);
                }

                case "status" -> { //Stato paziente
                    if (i == 0)
                        finalFilter = Filters.eq(key.get(i), value.get(i));
                    else
                        filter = Filters.eq(key.get(i), value.get(i));
                }

                case "medicine" -> { //Medicinale nella terapia
                    if (i == 0)
                        finalFilter = Document.parse("{'therapy.medicines': {$elemMatch: {medicineId: '"+value.get(i)+"'}}}");
                    else
                        filter = Document.parse("{'therapy.medicines': {$elemMatch: {medicineId: '"+value.get(i)+"'}}}");
                }
            }

            if (i > 0)
                finalFilter = Filters.and(finalFilter, filter);

            i++;
        } while(i < key.size());

        //Cerca il documento
        FindIterable<Document> iterDoc = collection.find(finalFilter);

        Iterator<Document> it = iterDoc.iterator();
        ArrayList<PatientBean> patients = new ArrayList<>();

        //Itero su ogni documento restituito dalla query
        while(it.hasNext()) {
            Document document = it.next();
            PatientBean patient = new PatientBean(document.getString("taxCode"), document.getString("name"), document.getString("surname"), document.getDate("birthDate"),
                    document.getString("city"), document.getString("phoneNumber"), document.getBoolean("status"), document.getString("condition"), document.getString("notes") ,therapyParser((Document) document.get("therapy")));
            patient.setPatientId(document.get("_id").toString());
            patients.add(patient);
        }

        return patients;
    }

    public ArrayList<PatientBean> findAll() {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Cerca il documento
        FindIterable<Document> iterDoc = collection.find();

        Iterator<Document> it = iterDoc.iterator();
        ArrayList<PatientBean> patients = new ArrayList<>();

        //Itero su ogni documento restituito dalla query
        while(it.hasNext()) {
            Document document = it.next();
            PatientBean patient = new PatientBean(document.getString("taxCode"), document.getString("name"), document.getString("surname"), document.getDate("birthDate"),
                    document.getString("city"), document.getString("phoneNumber"), document.getBoolean("status"), document.getString("condition"), document.getString("notes") ,therapyParser((Document) document.get("therapy")));
            patient.setPatientId(document.get("_id").toString());
            patients.add(patient);
        }

        return patients;
    }

    //Ricerca di un documento nella Collection in base al suo ObjectId
    public PatientBean findDocumentById(String value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq("_id", new ObjectId(value));

        //Cerca il documento
        Document document = collection.find(filter).first();

        //ArrayList<TherapyBean> therapies = convertToArray(document.getList("therapy", TherapyBean.class));
        PatientBean patient = new PatientBean(document.getString("taxCode"), document.getString("name"), document.getString("surname"), document.getDate("birthDate"),
                document.getString("city"), document.getString("phoneNumber"), document.getBoolean("status"), document.getString("condition"), document.getString("notes"), therapyParser((Document) document.get("therapy")));
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
        ObjectId objectId = new ObjectId();
        patient.setPatientId(objectId.toString());
        return new Document("_id", objectId)
                .append("taxCode", patient.getTaxCode())
                .append("name", patient.getName())
                .append("surname", patient.getSurname())
                .append("birthDate", patient.getBirthDate())
                .append("city", patient.getCity())
                .append("phoneNumber", patient.getPhoneNumber())
                .append("status", patient.getStatus())
                .append("notes", patient.getNotes());
    }

   private Document createDocument(TherapyBean therapyBean) {
        //Creo il documento con le informazioni della terapia
        Document therapyDocument = new Document()
                .append("sessions", therapyBean.getSessions())
                .append("duration", therapyBean.getDuration())
                .append("frequency", therapyBean.getFrequency())
                .append("medicines", therapyBean.getMedicines());

       //Restituisco il documento della terapia
        return new Document("therapy", therapyDocument);
    }

    private TherapyBean therapyParser(Document document) {
        //Se non c'è una terapia restituisco null
        if(document == null) {
            return null;
        }

        //Se c'è una terapia

        //Recupero i medicinali e li inserisco in un ArrayList
        List<Document> medicinesDocument = document.getList("medicines", Document.class);
        ArrayList<TherapyMedicineBean> medicines = new ArrayList<>();

        for (Document d : medicinesDocument) {
            medicines.add(new TherapyMedicineBean(d.getString("medicineId"), d.getInteger("dose")));
        }


        //Restituisco il documento della terapia
        return new TherapyBean(document.getInteger("sessions"), medicines,
                document.getInteger("duration"), document.getInteger("frequency"));
    }

    private ArrayList<TherapyMedicineBean> convertToArray(List<TherapyMedicineBean> list) {

        return new ArrayList<>(list);

    }
}