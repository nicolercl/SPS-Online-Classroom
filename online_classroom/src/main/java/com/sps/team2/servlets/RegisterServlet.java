package com.sps.team2.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sps.team2.classes.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

  private final UserService mUserService = UserServiceFactory.getUserService();
  private final Datastore mDatastore = new Datastore();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");

    if (mUserService.isUserLoggedIn()) {
      String userEmail = mUserService.getCurrentUser().getEmail();
      //if user not found, save user
      if (mDatastore.getUser(userEmail) == null){
        User user = new User(userEmail);
        mDatastore.storeUser(user);
      }
      response.sendRedirect("/dashboard.html?user=" + userEmail);
    } else {
      // Redirect to google log in
      String googleLoginUrl = mUserService.createLoginURL("/login");
      response.sendRedirect(googleLoginUrl);
    }
  }
}