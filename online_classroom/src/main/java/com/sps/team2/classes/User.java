package com.sps.team2.classes;
import java.util.HashSet;


/** A user class. */
public class User {

    private long mId;
    private String mName;
    private String mEmail;
    private HashSet<String> mCourses;

    public User(long id, String name, String email, HashSet<String> courses) {
      this.mId = id;
      this.mName = name;
      this.mEmail = email;
      this.mCourses = courses;
    }

    public long getId(){
        return this.mId;
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
    public void setId(long id){
        this.mId = id;
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