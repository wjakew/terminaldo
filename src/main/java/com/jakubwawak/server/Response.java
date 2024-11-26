/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.server;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

/**
 * Response class for creating responses to client requests
 */
public class Response {

    public String status;
    public String status_code;
    public String used_token;
    public int response_code;
    public String response_time;
    public Map<String,Object> message;

    public String url_endpoint;
    public String method;

    /**
     * Constructor
     */
    public Response( String url_endpoint, String method){
        this.status = "ok";
        this.status_code = "200";
        this.used_token = "";
        this.response_code = 200;
        this.response_time = "";
        this.message = new HashMap<>();
        this.url_endpoint = url_endpoint;
        this.method = method;
    }

    /**
     * Prepares a document for response
     * @return Document
     */
    public Document prepareDocument(){
        Document document = new Document();
        document.append("status", this.status);
        document.append("status_code", this.status_code);
        document.append("used_token", this.used_token);
        document.append("response_code", this.response_code);
        document.append("response_time", this.response_time);
        document.append("message", this.message);
        document.append("url_endpoint", this.url_endpoint);
        document.append("method", this.method);
        return document;
    }
    
}
