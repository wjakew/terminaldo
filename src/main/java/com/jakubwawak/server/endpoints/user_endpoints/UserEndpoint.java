/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all right reserved
 */
package com.jakubwawak.server.endpoints.user_endpoints;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jakubwawak.entity.TLDUser;
import com.jakubwawak.server.Response;

/**
 * Endpoint for user management
 */
@RestController
public class UserEndpoint {

    @GetMapping("/api/v1/user/register")
    public Response registerUser(@RequestBody Map<String,Object> payload){
        Response response = new Response("/api/v1/user/register", "GET");
        response.response_time = LocalDateTime.now().toString();
        
        try{
            if ( payload.containsKey("email") && payload.containsKey("telephone") && payload.containsKey("password") ){
                String email = (String) payload.get("email");
                String telephone = (String) payload.get("telephone");
                String password = (String) payload.get("password");

                TLDUser user = new TLDUser();
                user.email = email;
                user.telephone = telephone;
                user.setPassword(password);
    
            }
            else{
                response.status = "error";
                response.status_code = "400";
                response.response_code = 400;
                response.message.put("error", "Missing required fields");
            }
            return response;
        }catch(Exception e){
            response.status = "error";
            response.status_code = "500";
            response.response_code = 500;
            response.message.put("error", e.getMessage());
        }
        return response;
    }

    
}
