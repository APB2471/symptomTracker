package st.symptomtracker;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.Iterator;

/**
 * Class containing methods to retrieve symptom data from the
 * mongoDB. Methods will be called from the DisplaySymptoms class.
 *
 * @author Andrew Bush (apb2471@rit.edu)
 * @author Ryan Ong (rto9185@rit.edu)
 */
public class RecieveFromDB {

    public void displayCollection() {
        final String uriString = "mongodb://testryan:$[password]@localhost:27017/Cluster0?authSource=myFirstDatabase?retryWrites=true&w=majority";
        MongoClient mongoClient = MongoClients.create(uriString);

        MongoDatabase mongoDB = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = mongoDB.getCollection("inventory");

        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        mongoClient.close();

    }


}
