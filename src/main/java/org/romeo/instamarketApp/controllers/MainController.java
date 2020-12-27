package org.romeo.instamarketApp.controllers;

import org.romeo.instamarketApp.dao.AddDAO;
import org.romeo.instamarketApp.dao.DataWorker;
import org.romeo.instamarketApp.dao.InFilterDAO;
import org.romeo.instamarketApp.dao.UserDAO;
import org.romeo.instamarketApp.messaging.PostResultMessage;
import org.romeo.instamarketApp.models.Add;
import org.romeo.instamarketApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private DataWorker dataWorker;

    @Autowired
    public MainController(DataWorker dataWorker) {
        this.dataWorker = dataWorker;
    }

    @PostMapping("/new_user")
    @ResponseBody
    public PostResultMessage processUser(@ModelAttribute User user) {
        return dataWorker.saveUser(user);
    }

    @PostMapping("/new_add")
    @ResponseBody
    public PostResultMessage newAdd(@ModelAttribute Add add) {
        return dataWorker.processAdd(add);
    }
}
