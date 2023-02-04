package medicinemanagement.storage;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import connector.DatabaseConnector;
import medicinemanagement.application.BoxBean;
import medicinemanagement.application.MedicineBean;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MedicineQueryBean {

    //Inserimento singolo documento nella Collection
    public boolean insertDocument(MedicineBean medicine) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il documento da inserire nella Collection
        Document document = createDocument(medicine);
        if(medicine.getName().length() < 0 || medicine.getName().length() > 32){
            System.out.println("ERROR: name length incorrect!");
            return false;
        }else if(medicine.getIngredients().length() > 100){
            System.out.println("ERROR: ingredients length out of range!");
            return false;
        }
        //Inserisci il documento nella collection
        collection.insertOne(document);
        System.out.println("Documento inserito con successo nella Collection");
        return true;
    }

    //Inserimento singolo documento in un medicinale
    public void insertDocument(BoxBean box, String medicineId) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il documento da inserire nella Collection
        Document document = createDocument(box);

        //Crea il filtro
        Bson filter = Filters.eq("id", medicineId);

        //Inserisci il documento nella collection
        collection.findOneAndUpdate(filter, document);

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

    public ArrayList<MedicineBean> findDocument(String key, String value) {
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
            ArrayList<BoxBean> boxBeans = convertToArray(document.getList("box", BoxBean.class));
            MedicineBean medicine = new MedicineBean(document.getString("id"), document.getString("name"), document.getString("ingredients"), document.getInteger("amount"), boxBeans);
            medicines.add(medicine);
        }
        return medicines;
    }


    //Metodi ausiliari
    private MongoCollection<Document> getCollection() {
        MongoDatabase mongoDatabase = DatabaseConnector.getDatabase();

        MongoCollection<Document> collection = mongoDatabase.getCollection("medicinale");
        System.out.println("Collection 'medicinale' recuperata con successo");
        return collection;
    }

    private Document createDocument(MedicineBean medicine) {
        return new Document("id", medicine.getId())
                .append("name", medicine.getName())
                .append("ingredients", medicine.getIngredients())
                .append("amount", medicine.getAmount())
                .append("box", medicine.getBox());
    }

    private Document createDocument(BoxBean box) {
        return new Document("id", box.getBoxId())
                .append("status", box.getStatus())
                .append("capacity", box.getCapacity())
                .append("expiryDate", box.getExpiryDate());
    }

    private ArrayList<BoxBean> convertToArray(List<BoxBean> boxBeans) {
        return new ArrayList<>(boxBeans);
    }

}
