package com.sps.team2.classes;
import java.util.HashSet;


/** A user class. */
public class User {

    private String mName;
    private String mEmail;
    private HashSet<String> mCourses;
    private String mIdentity; // prof or student

    public User(String name, String email, String identity) {
        this.mName = name;
        this.mEmail = email;
        this.mCourses = new HashSet<String> ();
        this.mIdentity = identity;
    }

    public User(String name, String email, HashSet<String> courses, String identity) {
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
    public HashSet<String> getCourses(){
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
    public void setCourses(HashSet<String>courses){
        this.mCourses = courses;
    }
    public void setIdentity(String identity){
        this.mIdentity = identity;
    }
  }