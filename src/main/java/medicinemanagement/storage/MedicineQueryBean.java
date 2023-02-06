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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.elemMatch;

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

        //Crea il filtro
        Bson filter = Filters.eq("_id", new ObjectId(medicineId));

        //Recupera il documento del medicinale
        Document medicineDocument = collection.find(filter).first();

        //Aggiorna l'amount di package
        int amount = medicineDocument.getInteger("amount");
        collection.updateOne(medicineDocument, new Document("$set", new Document("amount", amount+1)));

        //Aggiorna l'id del package
        newPackage.setPackageId(String.valueOf(amount));

        //Crea il documento da inserire nella Collection
        Document packageDocument = createDocument(newPackage);

        medicineDocument = collection.find(filter).first();

        //Inserisci il documento nella collection
        collection.updateOne(medicineDocument, new Document("$push", packageDocument));

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
    public void updateDocument(String id, String valId, String key, Object valKey) {
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
            ArrayList<PackageBean> packageBeans = convertToArray(document.getList("package", Document.class));
            MedicineBean medicine = new MedicineBean(document.get(("_id")).toString(), document.getString("name"), document.getString("ingredients"), document.getInteger("amount"), packageBeans);
            medicines.add(medicine);
        }

        return medicines;
    }

    public ArrayList<MedicineBean> findDocument(ArrayList<String> key, ArrayList<Object> value) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = null;
        Bson finalFilter = null;
        Pattern regex;
        int i = 0;

        do {
            System.out.println("i: " + i + " filter: " + key.get(i));
            //Controllo di che tipo di valore si tratta
            switch (key.get(i)) {
                case "name" -> { //Nome
                    regex = Pattern.compile(Pattern.quote((String) value.get(i)), Pattern.CASE_INSENSITIVE);
                    if (i == 0)
                        finalFilter = Filters.eq(key.get(i), regex);
                    else
                        filter = Filters.eq(key.get(i), regex);
                }

                case "status" -> { //Stato
                    if((boolean) value.get(i)) { //Disponibile
                        if (i == 0)
                            finalFilter = Filters.gt("amount", 0);
                        else
                            filter = Filters.gt("amount", 0);
                    } else { //Esaurito
                        if (i == 0)
                            finalFilter = Filters.eq("amount", 0);
                        else
                            filter = Filters.eq("amount", 0);
                    }
                }

                case "expiryDate" -> { //Data scadenza: medicinali con almeno un package in scadenza entro quella data
                    if (i == 0)
                        finalFilter = Document.parse("{'package': {$elemMatch: { expiryDate: { $lt: ISODate('"+value.get(i)+"')}}}}");
                    else
                        filter = Document.parse("{'package': {$elemMatch: { expiryDate: { $lt: ISODate('"+value.get(i)+"')}}}}");
                }
            }

            if (i > 0)
                finalFilter = Filters.and(finalFilter, filter);

            i++;
        } while (i < key.size());

        System.out.println(finalFilter);

        //Cerca il documento
        FindIterable<Document> iterDoc = collection.find(finalFilter);

        Iterator<Document> it = iterDoc.iterator();
        ArrayList<MedicineBean> medicines = new ArrayList<>();

        while (it.hasNext()) {
            Document document = it.next();
            ArrayList<PackageBean> packageBeans = convertToArray(document.getList("package", Document.class));
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

        //Restituisce il medicinale
        return new MedicineBean(document.get(("_id")).toString(), document.getString("name"), document.getString("ingredients"), document.getInteger("amount"), convertToArray(document.getList("package", Document.class)));
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
            ArrayList<PackageBean> packageBeans = convertToArray(document.getList("package", Document.class));
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

    private ArrayList<PackageBean> convertToArray(List<Document> packages) {
        //Se non ci sono package restituisco null
        if (packages == null)
            return null;

        //Se ci sono package

        //Inserisco i package in un ArrayList
        ArrayList<PackageBean> packageArrayList = new ArrayList<>();

        for(Document d : packages)
            packageArrayList.add(new PackageBean(d.getBoolean("status"), d.getDate("expiryDate"), d.getInteger("capacity"), d.getString("packageId")));

        //Restituisco l'ArrayList
        return packageArrayList;
    }

}
