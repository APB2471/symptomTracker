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
        final String uriString = "mongodb+srv://testryan:omegalul123@cluster0.6tuhw.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";
        final MongoClient mongoClient = MongoClients.create(uriString);
        // swtich to the test Database
        MongoDatabase mongoDB = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = mongoDB.getCollection("inventory");

        MongoDatabase db = mongoClient.getDatabase("test");
        collection = db.getCollection("inventory");
        Document symptom = new Document("symptom", name);
        Document date = new Document("time", time);
        Document r8 = new Document("severity", severity);

        Document symptomLog = new Document("symptom", symptom)
                .append("date", date)
                .append("severity", r8);

        collection.insertOne(symptomLog);

        mongoClient.close();
    }
    


}
