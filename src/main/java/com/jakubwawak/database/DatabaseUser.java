/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all right reserved
 */
package com.jakubwawak.database;

import org.bson.Document;

import com.jakubwawak.entity.TLDUser;
import com.jakubwawak.terminaldo.TerminaldoApplication;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;

/**
 * Object for user management in database
 */
public class DatabaseUser {

    Database database;

    /**
     * Constructor
     */
    public DatabaseUser() {
        database = TerminaldoApplication.database;
    }

    /**
     * Function for registering user in database
     * 
     * @param user
     * @return int
     */
    public int registerUser(TLDUser user) {
        try {
            MongoCollection<Document> collection = database.getCollection("users");
            if (checkIfUserExists(user)) {
                TerminaldoApplication.database.log("USER-CREATION", "User already exists");
                return 1;
            } else {
                InsertOneResult result = collection.insertOne(user.prepareDocument());
                if (result.getInsertedId() != null) {
                    TerminaldoApplication.database.log("USER-CREATION", "User created");
                    return 0;
                } else {
                    TerminaldoApplication.database.log("USER-CREATION", "User creation failed");
                    return 2;
                }
            }
        } catch (Exception e) {
            TerminaldoApplication.database.log("USER-CREATION", "User creation failed");
            return 2;
        }
    }

    /**
     * Function for checking if user exists in database
     * 
     * @param user
     * @return boolean
     */
    public boolean checkIfUserExists(TLDUser user) {
        try {
            MongoCollection<Document> collection = database.getCollection("users");
            Document query = new Document("email", user.email);
            Document result = collection.find(query).first();
            return result != null;
        } catch (Exception e) {
            return false;
        }
    }

}
