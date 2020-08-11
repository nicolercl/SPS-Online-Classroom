package com.sps.team2.classes;
import java.util.HashSet;


/** A user class. */
public class User {

    private String mName;
    private String mEmail;
    private HashSet<String> mCourses;

    public User(String email) {
        this.mName = "";
        this.mEmail = email;
        this.mCourses = new HashSet<String> ();
    }

    public User(String name, String email, HashSet<String> courses) {
      this.mName = name;
      this.mEmail = email;
      this.mCourses = courses;
    }
    public String getName(){
        return this.mName;
    }
    public String getEmail(){
        return this.mEmail;
    }
    public HashSet<String> getCourses(){
        return this.mCourses;
    }
    public void setName(String name){
        this.mName = name;
    }
    public void setEmail(String email){
        this.mEmail = email;
    }
    public void setCourses(HashSet<String>courses){
        this.mCourses = courses;
    }
  }