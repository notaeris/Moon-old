package io.github.notaeris.moon.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.notaeris.moon.element.Element;
import lombok.Getter;
import org.bson.Document;

@Getter
public class MongoElement implements Element {

    private final MongoClient client;
    private final MongoDatabase database;

    private final MongoCollection<Document> profileCollection, rankCollection;

    public MongoElement() {
        this.client = new MongoClient("172.18.0.1", 27017);
        this.database = client.getDatabase("Moon");

        this.profileCollection = this.database.getCollection("profiles");
        this.rankCollection = this.database.getCollection("ranks");
    }
}
