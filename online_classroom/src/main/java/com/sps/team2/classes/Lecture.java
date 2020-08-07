/** A lecture video in a course. */
package com.sps.team2.classes;
public class Lecture {

    private long mId;
    private String mTitle;
    private String mAccessCode;

    public Lecture(long id, String title, String accessCode) {
      this.mId = id;
      this.mTitle = title;
      this.mAccessCode = accessCode;
    }

    public long getId(){
        return this.mId;
    }
    public String getTitle(){
        return this.mTitle;
    }
    public String getAccessCode(){
        return this.mAccessCode;
    }
    public void setId(long id){
        this.mId = id;
    }
    public void setTitle(String title){
        this.mTitle = title;
    }
    public void setAccessCode(String accessCode){
        this.mAccessCode = accessCode;
    }
  }