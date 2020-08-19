package com.sps.team2.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sps.team2.classes.*;
import java.util.*;
import com.google.gson.Gson;

@WebServlet("/dashboard")
public class DashBoardServlet extends HttpServlet {

  private final UserService mUserService = UserServiceFactory.getUserService();
  private final Datastore mDatastore = Datastore.getDatastore();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    if (mUserService.isUserLoggedIn()) {
        final String userEmail = mUserService.getCurrentUser().getEmail();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        final ArrayList<String> courses = (ArrayList<String>) mDatastore.getCourseList(userEmail);
        final ArrayList<String> allCourses = (ArrayList<String>) mDatastore.getAllCoursesList();

        final Gson gson = new Gson();
        String json = gson.toJson(Arrays.asList(courses, allCourses));
        response.getWriter().println(json);
    } else {
      // Redirect to google log in
      String googleLoginUrl = mUserService.createLoginURL("/login");
      response.sendRedirect(googleLoginUrl);
    }
  }
}