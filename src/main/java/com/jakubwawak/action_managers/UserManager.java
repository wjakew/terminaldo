/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all right reserved
 */
package com.jakubwawak.action_managers;

import com.jakubwawak.database.DatabaseUser;
import com.jakubwawak.entity.TLDToken;
import com.jakubwawak.entity.TLDUser;
import com.jakubwawak.terminaldo.TerminaldoApplication;

/**
 * Object for user management - logic for user login, register, etc.
 */
public class UserManager {

    DatabaseUser databaseUser;

    /**
     * Constructor
     */
    public UserManager(){
        databaseUser = new DatabaseUser();
    }

    /**
     * Function for login user
     * @param email
     * @param password
     * @return String - token
     */
    public String loginUser(String email, String password){
        try{
            UserManager userManager = new UserManager();
            String token = userManager.loginUser(email, password);
            if (token != null){
                if ( token.equals("user-not-found") ){
                    return "user-not-found";
                } else if ( token.equals("wrong-password") ){
                    return "wrong-password";
                } else {
                    TerminaldoApplication.database.log("USER-LOGGED-RP", "User ("+email+") logged in");
                    return token;
                }
            } else{
                TerminaldoApplication.database.log("USER-LOGGED-FAILED", "User ("+email+") login failed");
                return null;
            }
        } catch (Exception e){
            TerminaldoApplication.database.log("USER-LOGGED-FAILED", "User ("+email+") login failed ("+e.getMessage()+")");
            return null;
        }
    }

    
}
