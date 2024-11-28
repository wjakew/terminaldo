/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all right reserved
 */
package com.jakubwawak.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

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
}
