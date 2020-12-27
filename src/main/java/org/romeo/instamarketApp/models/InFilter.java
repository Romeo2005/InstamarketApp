package org.romeo.instamarketApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InFilter {
    int id;
    int price;
    String addType;
    String mediaType;
    User user;
}
