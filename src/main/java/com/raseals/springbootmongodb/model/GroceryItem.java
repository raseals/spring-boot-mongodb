package com.raseals.springbootmongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("GroceryItem")
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroceryItem
{
    @Id
    private String id;

    private String name;
    private int quantity;
    private String category;
}
