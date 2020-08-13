package com.sps.team2.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sps.team2.classes.*;

@WebServlet("/create-course")
public class CreateCourseServlet extends HttpServlet {

  private final UserService mUserService = UserServiceFactory.getUserService();
  private final Datastore mDatastore = new Datastore();

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String courseTitle = request.getParameter("courseTitle");

    if (mUserService.isUserLoggedIn()) {
      final String userEmail = mUserService.getCurrentUser().getEmail();
      final User creator = mDatastore.getUser(userEmail);
      if (creator.getIdentity().equals("professor")){
        Course course = new Course(courseTitle, creator.getName());
        mDatastore.storeCourse(course);
        mDatastore.addCourseToUser(courseTitle, userEmail);
        response.sendRedirect("/course.html?course=" + courseTitle);
      }
      else {
        response.sendRedirect("/dashboard.html");
      }
    } else {
      response.sendRedirect("/index.html");
    }
  }
}