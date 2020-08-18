package com.sps.team2.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sps.team2.classes.*;

@WebServlet("/enroll-course")
public class EnrollCourseServlet extends HttpServlet {

  private final UserService mUserService = UserServiceFactory.getUserService();
  private final Datastore mDatastore = Datastore.getDatastore();

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String courseTitle = request.getParameter("course");
    if (mUserService.isUserLoggedIn()) {
        final String userEmail = mUserService.getCurrentUser().getEmail();
        mDatastore.addCourseToUser(courseTitle, userEmail);
        mDatastore.addStudentToCourse(userEmail, courseTitle);
        response.sendRedirect("/course.html?course=" + courseTitle);
    } else {
      response.sendRedirect("/index.html");
    }
  }
}