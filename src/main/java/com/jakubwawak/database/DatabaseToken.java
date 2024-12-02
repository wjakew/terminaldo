/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all right reserved
 */
package com.jakubwawak.database;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.jakubwawak.entity.TLDToken;
import com.jakubwawak.terminaldo.TerminaldoApplication;

/**
 * Object for database token management
 */
public class DatabaseToken {

    Database database;

    /**
     * Constructor
     */
    public DatabaseToken(){
        database = TerminaldoApplication.database;
    }   
    
    /**
     * Function for checking if token exists
     * @param token
     * @return ObjectId - user id
     */
    public ObjectId checkIfTokenExists(String token){
        try{
            MongoCollection<Document> collection = database.getCollection("tokens");
            Document query = new Document("token", token);
            Document result = collection.find(query).first();
            if (result != null){
                TLDToken tldToken = new TLDToken(result);
                TerminaldoApplication.database.log("TOKEN-CHECK-DATABASE", "Token found for user: " + tldToken.user_id);
                return tldToken.user_id;
            } else {
                TerminaldoApplication.database.log("TOKEN-CHECK-DATABASE", "Token not found (" + token + ")");
                return null;
            }
        } catch (Exception e){
            TerminaldoApplication.database.log("TOKEN-CHECK-DATABASE", "Token check failed");
            return null;
        }
    }

}

