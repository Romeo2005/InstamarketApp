package org.romeo.instamarketApp.instagram_working;

import org.apache.http.client.ClientProtocolException;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.romeo.instamarketApp.models.Add;
import org.romeo.instamarketApp.models.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MainInstagramWorker {
    //TODO: Check if working with one default account is faster

    public synchronized List<User> getUsersSuitableByFollowers(List<User> users, int minFollowersAmount) {
        List<User> result = new ArrayList<>();

        Instagram4j instagram =
                Instagram4j.builder()
                        .username(users.get(0).getUsername())
                        .password(users.get(0).getPassword())
                        .build();

        for (User u : users) {
            try {
                InstagramSearchUsernameResult currentUser =
                        instagram.sendRequest(
                                new InstagramSearchUsernameRequest(instagram.getUsername()));

                if (currentUser.getUser().getFollower_count() >= minFollowersAmount)
                    result.add(u);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
