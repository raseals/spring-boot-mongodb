package com.raseals.springbootmongodb.service;

import com.raseals.springbootmongodb.model.GroceryItem;
import com.raseals.springbootmongodb.repository.CustomItemRepository;
import com.raseals.springbootmongodb.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GroceryService implements IGroceryService
{
    @Autowired
    private ItemRepository groceryItemRepo;

    @Autowired
    private CustomItemRepository customRepo;

    private List<GroceryItem> itemList = new ArrayList<GroceryItem>();

    @Override
    public void execute() throws Exception
    {
        // Clean up any previous data
        groceryItemRepo.deleteAll();

        log.info("CREATE GROCERY ITEMS");
        createGroceryItems();

        log.info("SHOW ALL GROCERY ITEMS");
        showAllGroceryItems();

        log.info("GET ITEM BY NAME");
        getGroceryItemByName("Whole Wheat Biscuit");

        log.info("GET ITEMS BY CATEGORY");
        getItemsByCategory("millets");

        log.info("UPDATE CATEGORY NAME OF ALL GROCERY ITEMS");
        updateCategoryName("snacks");

        log.info("UPDATE QUANTITY OF A GROCERY ITEM");
        updateItemQuantity("Bonny Cheese Crackers Plain", 10);

        log.info("DELETE A GROCERY ITEM");
        deleteGroceryItem("Kodo Millet");

        log.info("FINAL COUNT OF GROCERY ITEMS");
        findCountOfGroceryItems();
    }

    //
    // CRUD operations
    //

    // CREATE
    private void createGroceryItems()
    {
        log.info("Data creation started...");

        groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
        groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
        groceryItemRepo.save(new GroceryItem("Dried Red Chili", "Dried Whole Red Chili", 2, "spices"));
        groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
        groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));

        log.info("Data creation complete...");
    }

    // READ
    // 1. Show all the data
    private void showAllGroceryItems()
    {
        itemList = groceryItemRepo.findAll();

        itemList.forEach(item -> log.info(item.toString()));
    }

    // 2. Get item by name
    public void getGroceryItemByName(String name)
    {
        log.info("Getting item by name: {}", name);
        GroceryItem item = groceryItemRepo.findItemByName(name);
        log.info(item.toString());
    }

    // 3. Get name and items of a all items of a particular category
    public void getItemsByCategory(String category)
    {
        log.info("Getting items for the category {}", category);
        List<GroceryItem> list = groceryItemRepo.findAll(category);

        list.forEach(item -> log.info("Name: {}, Quantity: {}", item.getName(), item.getQuantity()));
    }

    // 4. Get count of documents in the collection
    private void findCountOfGroceryItems()
    {
        long count = groceryItemRepo.count();
        log.info("Number of documents in the collection = {}", count);
    }

    // UPDATE APPROACH 1: Using MongoRepository
    public void updateCategoryName(String category)
    {
        // Change to this new value
        String newCategory = "munchies";

        // Find all the items with the category
        List<GroceryItem> list = groceryItemRepo.findAll(category);

        list.forEach(item -> {
            // Update the category in each document
            item.setCategory(newCategory);
        });

        // Save all the items in database
        List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);

        if (itemsUpdated != null)
        {
            log.info("Successfully updated {} items", itemsUpdated.size());
        }
    }

    // UPDATE APPROACH 2: Using MongoTemplate
    private void updateItemQuantity(String name, float newQuantity)
    {
        log.info("Updating quantity for {}", name);
        customRepo.updateItemQuantity(name, newQuantity);
    }

    // DELETE
    private void deleteGroceryItem(String id)
    {
        groceryItemRepo.deleteById(id);
        log.info("Item with id {} deleted");
    }

    // print item details
    private String getItemDetails(GroceryItem item)
    {

        log.info(item.toString());

        return "";
    }
}
