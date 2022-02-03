package com.raseals.springbootmongodb.repository;

public interface ICustomItemRepository
{
    public void updateItemQuantity(String itemName, float newQuantity);
}
