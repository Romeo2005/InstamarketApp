package org.romeo.instamarketApp.messaging;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class PostResultMessage {
    public static final String SAVED = "SAVED";
    public static final String ERROR = "ERROR";
    public static final String SUITABLE_CLIENT_FOUND = "SUITABLE_CLIENT_FOUND";

    @Getter
    @Setter
    private String status;

    public PostResultMessage(String status) {
        this.status = status;
    }
}
