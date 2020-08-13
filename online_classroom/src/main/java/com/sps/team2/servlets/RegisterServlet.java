package com.sps.team2.servlets;

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

    if (mUserService.isUserLoggedIn()) {
      String userEmail = mUserService.getCurrentUser().getEmail();
      //if user not found, go to register
      if (mDatastore.getUser(userEmail) == null){
        response.sendRedirect("/register.html");
        // return;
      } else {
        response.sendRedirect("/dashboard.html");
      }
    } else {
      // Redirect to google log in
      String googleLoginUrl = mUserService.createLoginURL("/login");
      response.sendRedirect(googleLoginUrl);
    }
  }
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {    

    if (mUserService.isUserLoggedIn()) {
      final String userEmail = mUserService.getCurrentUser().getEmail();
      final String name = request.getParameter("name");
      if (name == null || name == "") {
        response.sendRedirect("/register.html");
      }
      final String identity = request.getParameter("identity");
      User user = new User(name, userEmail, identity);

      //store user to datastore
      mDatastore.storeUser(user);
      response.sendRedirect("/dashboard.html");
    } else {
      // Redirect to google log in
      String googleLoginUrl = mUserService.createLoginURL("/login");
      response.sendRedirect(googleLoginUrl);
    }
  }
}