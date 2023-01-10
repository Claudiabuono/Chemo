package connector;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class DatabaseConnector {
    public static synchronized MongoDatabase getDatabase(){
        //Viene creato il client
        MongoClient mongo = new MongoClient("localhost", 27017);
        //Configurazione del CodecRegistru per gestire la traduzione da bson e pojo e viceversa
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        //Accediamo al db
        MongoDatabase db = mongo.getDatabase("Chemo").withCodecRegistry(pojoCodecRegistry);

        System.out.println("Connessione al db avvenuta con successo");

        return db;
    }
}