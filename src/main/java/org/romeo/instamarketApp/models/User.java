package org.romeo.instamarketApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    String username;
    String password;
    String location;
    String profession;
    String FCM_TOKEN;
    int followers;
}
