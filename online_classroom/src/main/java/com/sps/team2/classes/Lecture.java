/** A lecture video in a course. */
package com.sps.team2.classes;
public class Lecture {

    private String mTitle;
    private String mAccessCode;

    public Lecture(String title, String accessCode) {
      this.mTitle = title;
      this.mAccessCode = accessCode;
    }

    public String getTitle(){
        return this.mTitle;
    }
    public String getAccessCode(){
        return this.mAccessCode;
    }
    public void setTitle(String title){
        this.mTitle = title;
    }
    public void setAccessCode(String accessCode){
        this.mAccessCode = accessCode;
    }
  }