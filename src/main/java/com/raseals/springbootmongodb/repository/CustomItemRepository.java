package com.raseals.springbootmongodb.repository;

import com.mongodb.client.result.UpdateResult;
import com.raseals.springbootmongodb.model.GroceryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomItemRepository implements ICustomItemRepository
{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateItemQuantity(String itemName, float newQuantity)
    {
        Query query = new Query(Criteria.where("name").is(itemName));
        Update update = new Update();
        update.set("quantity", newQuantity);

        UpdateResult result = mongoTemplate.updateFirst(query, update, GroceryItem.class);

        if (result != null)
        {
            log.info("Documents updated: {}", result.getModifiedCount());
        }
        else
        {
            log.info("No documents updated");
        }
    }
}
