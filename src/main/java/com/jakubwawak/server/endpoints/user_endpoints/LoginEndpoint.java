/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all right reserved
 */
package com.jakubwawak.server.endpoints.user_endpoints;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubwawak.action_managers.UserManager;
import com.jakubwawak.server.Response;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Login endpoint
 */
@RestController
public class LoginEndpoint {

    /**
     * Login user endpoint
     * 
     * @param payload - payload from client
     * @return response
     *         payload example:
     *         {
     *         "email": "example@example.com",
     *         "password": "password"
     *         }
     */
    @GetMapping("/api/v1/user/login")
    public Response loginUser(@RequestBody Map<String, Object> payload) {
        Response response = new Response("/api/v1/user/login", "GET");
        response.response_time = LocalDateTime.now().toString();

        try {
            if (payload.containsKey("email") && payload.containsKey("password")) {
                String email = (String) payload.get("email");
                String password = (String) payload.get("password");
                UserManager userManager = new UserManager();
                String token = userManager.loginUser(email, password);
                if (token != null) {
                    if (!token.contains("user")) {
                        response.message.put("token", token);
                    } else {
                        response.status = "auth-error";
                        response.status_code = "600";
                        response.response_code = 600;
                        response.message.put("error", token);
                    }
                } else {
                    response.status = "error";
                    response.status_code = "401";
                    response.response_code = 401;
                    response.message.put("error", "Invalid credentials");
                }
            } else {
                response.status = "error";
                response.status_code = "400";
                response.response_code = 400;
                response.message.put("error", "Missing required fields");
            }
        } catch (Exception e) {
            response.status = "error";
            response.status_code = "500";
            response.response_code = 500;
            response.message.put("error", e.getMessage());
        }
        return response;
    }
}
