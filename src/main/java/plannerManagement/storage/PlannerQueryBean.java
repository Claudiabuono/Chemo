package plannerManagement.storage;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import connector.DatabaseConnector;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlannerQueryBean {
    //Inserimento singolo documento nella collection
    public void insertDocument(PlannerBean plannerBean){
        MongoCollection<Document> collection = getCollection();
        Document doc = createDocument(plannerBean);

        collection.insertOne(doc);
        System.out.println("Inserimento documento avvenuto con successo!");
    }

    //Inserimento singolo documento nella collection, nel caso in cui l'agenda non sia popolata
    public void insertDocument(PlannerBean plannerBean, ArrayList<AppointmentBean> appuntamenti){
        MongoCollection<Document> collection = getCollection();
        for(AppointmentBean app : appuntamenti){
            plannerBean.getAppointments().add(app);
        }
        Document doc = createDocument(plannerBean);

        collection.insertOne(doc);
        System.out.println("Inserimento documento avvenuto con successo!");
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
    public Iterator<Document> findDocument(String chiave, String valore){
        MongoCollection<Document> collection = getCollection();
        FindIterable<Document> iterDoc = collection.find(Filters.eq(chiave, valore));
        Iterator<Document> doc = iterDoc.iterator();

        return doc;
    }

    private MongoCollection<Document> getCollection(){
        DatabaseConnector conn = new DatabaseConnector();
        MongoDatabase db = conn.getDatabase();

        MongoCollection<Document> coll = db.getCollection("agenda");
        System.out.println("Collection \'agenda\' recuperata con successo");
        return coll;
    }

    private Document createDocument(PlannerBean plannerBean){
        List<AppointmentBean> app = plannerBean.getAppointments();

        Document doc = new Document("id", plannerBean.getId())
                .append("dataInizio", plannerBean.startDate)
                .append("dataFine", plannerBean.endDate)
                .append("appuntamenti", app);

        return doc;
    }
}
