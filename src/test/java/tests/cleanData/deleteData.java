package tests.cleanData;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import config.AppConfig;
import org.aeonbits.owner.ConfigFactory;
import org.bson.Document;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class deleteData extends BaseTest {

    @Test(description = "Checking Admiral-Script on header")
    public void deleteData() {
        final AppConfig envConfig = ConfigFactory.create(AppConfig.class);
        MongoClientURI uri = new MongoClientURI("mongodb://" + envConfig.getDBUser() + ":" +
                envConfig.getDBPassword() + "@" + envConfig.getDBServer() + ":" +envConfig.getDBNewsServicePort()
                + "/api-news");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("api-news");
        MongoCollection<Document> collection = db.getCollection("news");
        collection.deleteMany(Filters.eq("title", ".elenium"));

        uri = new MongoClientURI("mongodb://" + envConfig.getDBUser() + ":" +
                envConfig.getDBPassword() + "@" + envConfig.getDBServer() + ":" +envConfig.getReviewsServicePort()
                + "/api-reviews");
        mongoClient = new MongoClient(uri);
         db = mongoClient.getDatabase("api-reviews");
        collection = db.getCollection("bookmakers");
        collection.deleteMany(Filters.regex("name", ".elenium"));
    }
}


