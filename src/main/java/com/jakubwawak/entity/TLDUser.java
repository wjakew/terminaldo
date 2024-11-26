/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all right reserved
 */
package com.jakubwawak.entity;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.jakubwawak.terminaldo.TerminaldoApplication;

/**
 * Class for storing user data
 */
public class TLDUser {

    public ObjectId id;
    public String email;
    public String telephone;

    private String password;
    private String salt;

    public boolean active;
    public boolean public_profile;

    public String token;

    /**
     * Constructor
     */
    public TLDUser(){
        this.id = null;
        this.email = "";
        this.telephone = "";
        this.password = "";
        this.salt = "";
        this.active = true;
        this.public_profile = false;
    }

    /**
     * Constructor
     * @param document
     */
    public TLDUser(Document document){
        this.id = document.getObjectId("_id");
        this.email = document.getString("email");
        this.telephone = document.getString("telephone");
        this.password = document.getString("password");
        this.salt = document.getString("salt");
        this.active = document.getBoolean("active");
        this.public_profile = document.getBoolean("public_profile");
    }

    /**
     * Prepares a document for user
     * @return Document
     */
    public Document prepareDocument(){
        Document document = new Document();
        document.append("email", this.email);
        document.append("telephone", this.telephone);
        document.append("password", this.password);
        document.append("salt", this.salt);
        document.append("active", this.active);
        document.append("public_profile", this.public_profile);
        return document;
    }

    /**
     * Function for setting user password
     * @param password
     */
    public void setPassword(String password){
        try{
            String salt = BCrypt.gensalt();
            this.salt = salt;
            this.password = BCrypt.hashpw(password, salt);
        }catch(Exception e){
            TerminaldoApplication.database.log("PASSWORD-HASHING-FAILED","Failed to hash password: "+e.toString());
        }
    }

    /**
     * Function for validating password
     * @param password
     * @return boolean
     */
    public boolean validatePassword(String password){
        try{
            TerminaldoApplication.database.log("PASSWORD-VALIDATION","id: "+id.toString()+" - trying to validate password");
            return BCrypt.checkpw(password,this.password);
        }catch (Exception e){
            TerminaldoApplication.database.log("PASSWORD-VALIDATION-FAILED","Failed to validate password: "+e.toString());
            return false;
        }
    }
}
