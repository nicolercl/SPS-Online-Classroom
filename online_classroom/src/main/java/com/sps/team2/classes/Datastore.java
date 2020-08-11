package com.sps.team2.classes;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

import java.util.*;

/**
 * Provides access to the data stored in Datastore.
 */
public class Datastore {

    private final DatastoreService mDatastore;

    public Datastore() {
        mDatastore = DatastoreServiceFactory.getDatastoreService();
    }
    /* Stores the User in Datastore.*/
    public void storeUser(User user) {
        Entity userEntity = new Entity("User", user.getEmail());
        userEntity.setProperty("email", user.getEmail());
        userEntity.setProperty("name", user.getName());
        userEntity.setProperty("courses", user.getCourses());
        userEntity.setProperty("identity", user.getIdentity());
        mDatastore.put(userEntity);
    }

    /*Returns User with the email address, or null if no matching User was found.*/
    public User getUser(String email) {

        Query query = new Query("User")
                .setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
        PreparedQuery results = mDatastore.prepare(query);
        Entity userEntity = results.asSingleEntity();
        if (userEntity == null) {
            return null;
        }

        String name = (String) userEntity.getProperty("name");
        String identity = (String) userEntity.getProperty("identity");
        HashSet<String> courses = (HashSet<String>) userEntity.getProperty("courses");
        User user = new User(name, email, courses, identity);
        return user;
    }
}