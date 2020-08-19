package com.sps.team2.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sps.team2.classes.*;
import com.google.gson.Gson;

@WebServlet("/fetch-course")
public class FetchCourseServlet extends HttpServlet {

  private final Datastore mDatastore = Datastore.getDatastore();

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String courseTitle = request.getParameter("courseTitle");
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    final Course course = (Course) mDatastore.getCourse(courseTitle);
    final Gson gson = new Gson();
    String json = gson.toJson(course);
    response.getWriter().println(json);
  }
}