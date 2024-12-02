/**
 * by Jakub Wawak
 * kubawawak@gmail.com
 * all rights reserved
 */
package com.jakubwawak.server.endpoints.health_endpoints;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jakubwawak.server.Response;

/**
 * Object for health check endpoint
 */
@RestController
@RequestMapping("/health")
public class HealthEndpoint {
    

    /**
     * Get health check
     * 
     * @return
     */
    @GetMapping
    public Response getHealthCheck(){
        Response response = new Response("/health", "GET");
        response.response_time = LocalDateTime.now().toString();
        response.status = "ok";
        response.status_code = "200";
        response.response_code = 200;
        response.message.put("message", "Server is running");
        return response;
    }
}
