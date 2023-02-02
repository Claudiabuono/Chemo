package userManagement.storage;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import connector.DatabaseConnector;
import userManagement.application.UserBean;

import java.util.ArrayList;
import java.util.Iterator;

public class UserQueryBean {
    //Inserimento singolo documento nella collection
    public void insertDocument(UserBean userBean){
        MongoCollection<Document> collection = getCollection();
        Document doc = createDocument(userBean);

        collection.insertOne(doc);
        System.out.println("Inserimento documento avvenuto con successo!");
    }

    //Inserimento collezione di documenti nella collection
    public void insertDocuments(ArrayList<UserBean> utenti){
        ArrayList<Document> documenti = new ArrayList<>();
        for(UserBean ut : utenti){
            Document doc = createDocument(ut);
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
    public ArrayList<UserBean> findDocument(String chiave, String valore){
        MongoCollection<Document> collection = getCollection();
        FindIterable<Document> iterDoc = collection.find(Filters.eq(chiave, valore));
        Iterator<Document> it = iterDoc.iterator();
        ArrayList<UserBean> users = new ArrayList<>();

        while(it.hasNext()){
            Document document = (Document) it.next();
            UserBean user = new UserBean(document.getString("id"), document.getString("name"), document.getString("surname"), document.getDate("birthDate"), document.getString("city"), document.getString("username"), document.getString("password"), document.getString("specialization"), document.getInteger("type"));
            users.add(user);
        }
        return users;
    }

    //Effettuo la connessione con il db, recupero la collection dal db e la restituisco
    private MongoCollection<Document> getCollection(){
        DatabaseConnector conn = new DatabaseConnector();
        MongoDatabase db = conn.getDatabase();

        MongoCollection<Document> coll = db.getCollection("user");
        System.out.println("Collection \'utente\' recuperata con successo");
        return coll;
    }

    private Document createDocument(UserBean userBean){
        Document doc = new Document("id", userBean.getId())
                .append("name", userBean.getName())
                .append("surname", userBean.getSurname())
                .append("city", userBean.getBirthplace())
                .append("birthDate", userBean.getBirthDate())
                .append("username", userBean.getUsername())
                .append("password", userBean.getPassword())
                .append("specialization", userBean.getSpecialization())
                .append("type", userBean.getType());

        return doc;
    }
}

