package com.sps.team2.classes;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

import java.util.ArrayList;

/**
 * Provides access to the data stored in Datastore.
 */
public class Datastore {

    private final DatastoreService mDatastore = DatastoreServiceFactory.getDatastoreService();
    private static Datastore sDatastore;

    public static synchronized Datastore getDatastore() {
        if (sDatastore == null) {
            sDatastore = new Datastore();
        }
        return sDatastore;
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
        ArrayList<String> courses = (ArrayList<String>) userEntity.getProperty("courses");
        User user = new User(name, email, courses, identity);
        return user;
    }

    /*Stores the Course by course title. For course creation by prof. */
    public void storeCourse(Course course) {

        Entity courseEntity = new Entity("Course", course.getTitle());
        courseEntity.setProperty("title", course.getTitle());
        courseEntity.setProperty("professor", course.getProfessor());
        courseEntity.setProperty("students", course.getStudents());
        courseEntity.setProperty("videos", course.getVideos());
        courseEntity.setProperty("materials", course.getMaterials());
        mDatastore.put(courseEntity);
    }

    /*Returns list of all courses. For student browsing & search (to enroll) */
    public ArrayList<String> getAllCoursesList() {

        ArrayList<String> courseslist = new ArrayList<String>();

        Query query = new Query("Course");
        PreparedQuery results = mDatastore.prepare(query);
        if (results == null){
            return null;
        }
        for (Entity entity : results.asIterable()) {
            String title = (String) entity.getProperty("title");
            courseslist.add(title);
        }
        return courseslist;
    }

    /*Adds a course for a user. For student's enrollment or prof's course creation */
    public void addCourseToUser(String newCourseTitle, String email) {
        Query query = new Query("User")
        .setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
        PreparedQuery results = mDatastore.prepare(query);
        Entity userEntity = results.asSingleEntity();
        if (userEntity == null) {
            return;
        }
        ArrayList<String> courses = (ArrayList<String>) userEntity.getProperty("courses");
        if (courses == null) {
            courses = new ArrayList<>();
        } else {
            // Check if already added
            for (String course: courses) {
                if (course.equals(newCourseTitle) ){
                    return;
                }
            }
        }
        courses.add(newCourseTitle);
        userEntity.setProperty("courses", courses);
        mDatastore.put(userEntity);
    }

    public void addStudentToCourse(String email, String courseTitle) {
        Query query = new Query("Course")
        .setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
        PreparedQuery results = mDatastore.prepare(query);
        Entity courseEntity = results.asSingleEntity();
        if (courseEntity == null) {
            return;
        }
        ArrayList<String> students = (ArrayList<String>) courseEntity.getProperty("students");
        if (students == null) {
            students = new ArrayList<>();
        } else {
            // Check if already added
            for (String student: students) {
                if (student.equals(email)){
                    return;
                }
            }
        }
        students.add(email);
        courseEntity.setProperty("students", students);
        mDatastore.put(courseEntity);
    }

    /*Returns course list for a certain User. For Dashboard display.*/
    public ArrayList<String> getCourseList(String email) {

        Query query = new Query("User")
                .setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
        PreparedQuery results = mDatastore.prepare(query);
        Entity userEntity = results.asSingleEntity();
        if (userEntity == null) {
            return null;
        }
        ArrayList<String> courses = (ArrayList<String>) userEntity.getProperty("courses");
        if (courses == null) {
            courses = new ArrayList<>();
        }
        return courses;
    }

    /*Returns a course by course title. For Course page display.*/
    public Course getCourse(String title) {
        Query query = new Query("Course")
                .setFilter(new Query.FilterPredicate("title", FilterOperator.EQUAL, title));
        PreparedQuery results = mDatastore.prepare(query);
        Entity courseEntity = results.asSingleEntity();
        if (courseEntity == null) {
            return null;
        }
        final String professor = (String) courseEntity.getProperty("professor");
        ArrayList<String> students = (ArrayList<String>) courseEntity.getProperty("students");
        ArrayList<String> videos = (ArrayList<String>) courseEntity.getProperty("videos");
        ArrayList<String> materials = (ArrayList<String>) courseEntity.getProperty("materials");
        final Course course = new Course(title, professor, students, videos, materials);
        return course;
    }
}