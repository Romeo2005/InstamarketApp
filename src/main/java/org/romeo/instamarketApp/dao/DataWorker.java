package org.romeo.instamarketApp.dao;

import org.romeo.instamarketApp.messaging.PostResultMessage;
import org.romeo.instamarketApp.models.Add;
import org.romeo.instamarketApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataWorker {
    private UserDAO userDAO;
    private InFilterDAO inFilterDAO;
    private AddDAO addDAO;

    @Autowired
    public DataWorker(UserDAO userDAO, InFilterDAO inFilterDAO, AddDAO addDAO) {
        this.userDAO = userDAO;
        this.inFilterDAO = inFilterDAO;
        this.addDAO = addDAO;
    }

    public PostResultMessage saveUser(User user) {
        try {
            userDAO.create(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new PostResultMessage(PostResultMessage.ERROR);
        }

        return new PostResultMessage(PostResultMessage.SAVED);
    }


    public PostResultMessage processAdd(Add add) {
        
        return null;
    }
}
