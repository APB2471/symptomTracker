package st.symptomtracker;

import com.mongodb.DB;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;

public class AddToDB {

    public void insert(String name, LocalDateTime time, int severity) {
    // connect to mongoDB instance
        final String uriString = "mongodb://testryan:omegalul123@localhost:27017/Cluster0?authSource=Cluster0?retryWrites=true&w=majority";
        final MongoClient mongoClient = MongoClients.create(uriString);
        // swtich to the test Database
        MongoDatabase mongoDB = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = mongoDB.getCollection("inventory");

        MongoDatabase db = mongoClient.getDatabase("test");
        collection = db.getCollection("inventory");
//        Document symptom = new Document("symptom", name);
//        Document time = new Document("time", new LocalDateTime[]);

        Document canvas = new Document("item", "canvas")
                .append("qty", 100);

        Document size = new Document("h", 28)
                .append("w", 35.5)
                .append("uom", "cm");
        canvas.put("size", size);

        collection.insertOne(canvas);


        mongoClient.close();
    }
    


}
