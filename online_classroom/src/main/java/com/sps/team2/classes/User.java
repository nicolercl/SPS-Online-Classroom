package com.sps.team2.classes;
import java.util.ArrayList;


/** A user class. */
public class User {

    private String mName;
    private String mEmail;
    private ArrayList<String> mCourses;
    private String mIdentity; // prof or student

    public User(String name, String email, String identity) {
        this.mName = name;
        this.mEmail = email;
        this.mCourses = new ArrayList<String> ();
        this.mIdentity = identity;
    }

    public User(String name, String email, ArrayList<String> courses, String identity) {
      this.mName = name;
      this.mEmail = email;
      this.mCourses = courses;
      this.mIdentity = identity;
    }
    public String getName(){
        return this.mName;
    }
    public String getEmail(){
        return this.mEmail;
    }
    public ArrayList<String> getCourses(){
        return this.mCourses;
    }
    public String getIdentity(){
        return this.mIdentity;
    }
    public void setName(String name){
        this.mName = name;
    }
    public void setEmail(String email){
        this.mEmail = email;
    }
    public void setCourses(ArrayList<String>courses){
        this.mCourses = courses;
    }
    public void setIdentity(String identity){
        this.mIdentity = identity;
    }
  }