package com.raseals.springbootmongodb.controller;

import com.raseals.springbootmongodb.service.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/grocery")
@Slf4j
public class GroceryController
{
    @Autowired
    private GroceryService groceryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String execute()
    {
        try
        {
            groceryService.execute();
        }
        catch (Exception e)
        {
            log.error("Error executing Grocery Process", e);
        }

        return "{\"success\": \"true\"}";
    }
}
