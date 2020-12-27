package org.romeo.instamarketApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Add {
    int id;
    int price;
    int minFollowers;
    String addType;
    String mediaType;
    String description;
    String location;
    User user;
}
