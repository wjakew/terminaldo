/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all right reserved
 */
package com.jakubwawak.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.jakubwawak.database.DatabaseToken;
import com.jakubwawak.terminaldo.TerminaldoApplication;

/**
 * TLDToken entity - for storing token data
 */
public class TLDToken {

    public ObjectId _id;
    public String token;
    public LocalDateTime created_at;
    public LocalDateTime expires_at;
    public ObjectId user_id;

    /**
     * Default constructor
     */
    public TLDToken() {
        this._id = null;
        this.token = null;
        this.created_at = null;
        this.expires_at = null;
        this.user_id = null;
    }

    /**
     * Constructor for TLDToken
     * @param document
     */
    public TLDToken(Document document){
        this._id = document.getObjectId("_id");
        this.token = document.getString("token");
        this.created_at = document.getDate("created_at").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.expires_at = document.getDate("expires_at").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.user_id = document.getObjectId("user_id");
    }

    /**
     * Constructor for TLDToken
     * @param user_id
     */
    public TLDToken(ObjectId user_id){
        this.user_id = user_id;
        this.token = generateRandomToken();
        this.created_at = LocalDateTime.now();
        this.expires_at = LocalDateTime.now().plusDays(1);
    }

    /**
     * Function for generating random token
     * @return String - token
     */
    private String generateRandomToken() {
        Random random = new Random();
        int token = random.nextInt(9000000) + 1000000; // Generates a number between 1,000,000 and 9,999,999
        String tokenString = String.valueOf(token);
        DatabaseToken databaseToken = new DatabaseToken();
        if (databaseToken.checkIfTokenExists(tokenString) == null){
            return tokenString;
        } else {
            return generateRandomToken();
        }
    }

    /**
     * Function for converting TLDToken to Document
     * @return Document
     */
    public Document prepareDocument(){
        Document document = new Document();
        document.put("_id", _id);
        document.put("token", token);
        document.put("created_at", created_at);
        document.put("expires_at", expires_at);
        document.put("user_id", user_id);
        return document;
    }
}
