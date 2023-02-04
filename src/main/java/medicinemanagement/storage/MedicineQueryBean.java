package medicinemanagement.storage;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import connector.DatabaseConnector;
import medicinemanagement.application.PackageBean;
import medicinemanagement.application.MedicineBean;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MedicineQueryBean {

    //Inserimento singolo documento nella Collection
    public void insertDocument(MedicineBean medicine) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il documento da inserire nella Collection
        Document document = createDocument(medicine);

        //Inserisci il documento nella collection
        collection.insertOne(document);

        System.out.println("Documento inserito con successo nella Collection");
    }

    //Inserimento una confezione in un medicinale
    public void insertDocument(PackageBean newPackage, String medicineId) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il documento da inserire nella Collection
        Document packageDocument = createDocument(newPackage);

        //Crea il filtro
        Bson filter = Filters.eq("_id", new ObjectId(medicineId));

        //Recupera il documento del medicinale
        Document medicineDocument = collection.find(filter).first();

        //Inserisci il documento nella collection
        collection.updateOne(medicineDocument, new Document("$set", packageDocument));

        System.out.println("Documento inserito con successo nella Collection");
    }

    //Inserimento collezione di documenti nella Collection
    public void insertDocuments(ArrayList<MedicineBean> medicines) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea un documento per ogni medicinale in medicines
        ArrayList<Document> docs = new ArrayList<>();
        for(MedicineBean medicine : medicines) {
            Document doc = createDocument(medicine);
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

    public ArrayList<MedicineBean> findDocument(String key, Object value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq(key, value);

        //Cerca il documento
        FindIterable<Document> iterDoc = collection.find(filter);

        Iterator<Document> it = iterDoc.iterator();
        ArrayList<MedicineBean> medicines = new ArrayList<>();

        while (it.hasNext()) {
            Document document = it.next();
            ArrayList<PackageBean> packageBeans = convertToArray(document.getList("package", PackageBean.class));
            MedicineBean medicine = new MedicineBean(document.get(("_id")).toString(), document.getString("name"), document.getString("ingredients"), document.getInteger("amount"), packageBeans);
            medicines.add(medicine);
        }

        return medicines;
    }

    public MedicineBean findDocumentById(String value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq("_id", new ObjectId(value));

        //Cerca il documento
        Document document = collection.find(filter).first();

        //Crea il medicinale da restituire
        MedicineBean medicine = new MedicineBean(document.get(("_id")).toString(), document.getString("name"), document.getString("ingredients"), document.getInteger("amount"), convertToArray(document.getList("package", PackageBean.class)));

        return medicine;
    }

    public ArrayList<MedicineBean> findAll() {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Cerca il documento
        FindIterable<Document> iterDoc = collection.find();

        Iterator<Document> it = iterDoc.iterator();
        ArrayList<MedicineBean> medicines = new ArrayList<>();

        while (it.hasNext()) {
            Document document = it.next();
            ArrayList<PackageBean> packageBeans = convertToArray(document.getList("package", PackageBean.class));
            MedicineBean medicine = new MedicineBean(document.get(("_id")).toString(), document.getString("name"), document.getString("ingredients"), document.getInteger("amount"), packageBeans);
            medicines.add(medicine);
        }

        return medicines;
    }


    //Metodi ausiliari
    private MongoCollection<Document> getCollection() {
        MongoDatabase mongoDatabase = DatabaseConnector.getDatabase();

        MongoCollection<Document> collection = mongoDatabase.getCollection("medicine");
        System.out.println("Collection 'medicinale' recuperata con successo");
        return collection;
    }

    private Document createDocument(MedicineBean medicine) {
        ObjectId objectId = new ObjectId();
        medicine.setId(objectId.toString());
        return new Document("_id", objectId)
                .append("name", medicine.getName())
                .append("ingredients", medicine.getIngredients())
                .append("amount", medicine.getAmount())
                .append("package", medicine.getPackages());
    }

    private Document createDocument(PackageBean box) {
        Document document = new Document("packageId", box.getPackageId())
                .append("status", box.getStatus())
                .append("capacity", box.getCapacity())
                .append("expiryDate", box.getExpiryDate());

        return new Document("package", document);
    }

    private ArrayList<PackageBean> convertToArray(List<PackageBean> packageBeans) {
        return new ArrayList<>(packageBeans);
    }

}
