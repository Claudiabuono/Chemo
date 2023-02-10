package plannerManagement.storage;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import connector.DatabaseConnector;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import plannerManagement.application.AppointmentBean;
import plannerManagement.application.PlannerBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PlannerQueryBean {
    //Inserimento singolo documento nella collection
    public boolean insertDocument(PlannerBean plannerBean){
        MongoCollection<Document> collection = getCollection();
        Document doc = createDocument(plannerBean);
        collection.insertOne(doc);

        if(plannerBean.getAppointments().size() == 0){
            System.out.println("ERROR: No appointments selected to create the schedule");
            return false;
        }

        System.out.println("Inserimento documento avvenuto con successo!");
        return true;
    }

    //Inserimento singolo documento nella collection, nel caso in cui l'agenda non sia popolata
    public boolean insertDocument(PlannerBean plannerBean, ArrayList<AppointmentBean> appuntamenti){
        MongoCollection<Document> collection = getCollection();
        for(AppointmentBean app : appuntamenti){
            plannerBean.getAppointments().add(app);
        }
        Document doc = createDocument(plannerBean);

        collection.insertOne(doc);
        System.out.println("Inserimento documento avvenuto con successo!");
        return true;
    }

    //Inserimento collezione di documenti nella collection
    public void insertDocuments(ArrayList<PlannerBean> agende){
        ArrayList<Document> documenti = new ArrayList<>();
        for(PlannerBean ag : agende){
            Document doc = createDocument(ag);
            documenti.add(doc);
        }

        MongoCollection<Document> collection = getCollection();
        collection.insertMany(documenti);

        System.out.println("Inserimento documenti avvenuto con successo!");
    }

    //Eliminazione documento dalla collection
    public void deleteDocument(String chiave, String valore){
        MongoCollection<Document> collection = getCollection();
        collection.deleteOne(Filters.eq(chiave, valore));

        System.out.println("Eliminazione documento avvenuta con successo!");
    }

    //Modifica di un documento
    public void updateDocument(String id, String valId, String chiave, String valoreChiave){
        MongoCollection<Document> collection = getCollection();
        collection.updateOne(Filters.eq(id, valId), Updates.set(chiave, valoreChiave));

        System.out.println("Modifica documento avvenuta con successo!");
    }

    //Ricerca documento nella collection per una data coppia (chiave, valore)
    public ArrayList<PlannerBean> findDocument(String chiave, String valore){
        MongoCollection<Document> collection = getCollection();
        FindIterable<Document> iterDoc = collection.find(Filters.eq(chiave, valore));
        Iterator<Document> it = iterDoc.iterator();
        ArrayList<PlannerBean> p = new ArrayList<>();

        while(it.hasNext()){
            Document document = (Document) it.next();
            ArrayList<AppointmentBean> appointments = convertToArray(document.getList("appointments", Document.class));
            PlannerBean planner = new PlannerBean(document.get("_id").toString(), document.getDate("startDate"), document.getDate("endDate"), appointments);
            p.add(planner);
        }
        return p;
    }

    public ArrayList<PlannerBean> findAll(){
        MongoCollection<Document> collection = getCollection();
        FindIterable<Document> iterDoc = collection.find();
        Iterator<Document> it = iterDoc.iterator();
        ArrayList<PlannerBean> p = new ArrayList<>();

        while(it.hasNext()){
            Document document = (Document) it.next();
            ArrayList<AppointmentBean> appointments = convertToArray(document.getList("appointments", Document.class));
            PlannerBean planner = new PlannerBean(document.get("_id").toString(), document.getDate("startDate"), document.getDate("endDate"), appointments);
            System.out.println(planner);
            p.add(planner);
        }
        return p;
    }

    public PlannerBean findDocumentById(String id) {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Crea il filtro
        Bson filter = Filters.eq("_id", new ObjectId(id));

        //Cerca il documento
        Document document = collection.find(filter).first();


        return new PlannerBean(document.get("_id").toString(), document.getDate("start"), document.getDate("end"), convertToArray(document.getList("appointments", Document.class)));
    }

    public PlannerBean findLastDocument() {
        //Recupera la Collection
        MongoCollection<Document> collection = getCollection();

        //Cerca il documento
        Document document = collection.find().sort(new Document("_id", -1)).first();


        return new PlannerBean(document.get("_id").toString(), document.getDate("start"), document.getDate("end"), convertToArray(document.getList("appointments", Document.class)));
    }

    private MongoCollection<Document> getCollection(){
        DatabaseConnector conn = new DatabaseConnector();
        MongoDatabase db = conn.getDatabase();

        MongoCollection<Document> coll = db.getCollection("planner");
        System.out.println("Collection \'agenda\' recuperata con successo");
        return coll;
    }

    private Document createDocument(PlannerBean plannerBean){
        List<AppointmentBean> app = plannerBean.getAppointments();

        Document doc = new Document("_id", plannerBean.getId())
                .append("startDate", plannerBean.startDate)
                .append("endDate", plannerBean.endDate)
                .append("appointments", app);

        return doc;
    }

    private ArrayList<AppointmentBean> convertToArray(List<Document> list){
        if(list == null)
            return null;

        ArrayList<AppointmentBean> appointments = new ArrayList<>();

        for(Document d : list)
            appointments.add(new AppointmentBean(d.getString("patientId"), dateParser(d.getString("date")), d.getString("seat")));

        return appointments;
    }


    private Date dateParser(String date) {
        SimpleDateFormat pattern = new SimpleDateFormat("yyyy-MM-ddThh:mm:ss");
        try {
            return pattern.parse(date);
        }
        catch (Exception e) {
            return null;
        }
    }
}
