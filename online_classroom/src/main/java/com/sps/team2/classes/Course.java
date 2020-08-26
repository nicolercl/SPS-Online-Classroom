/** A course. */
package com.sps.team2.classes;
import java.util.ArrayList;

public class Course {

    private String mTitle;
    private String mProfessor;
    private ArrayList<String> mStudents;
    private ArrayList<String> mVideos;
    private ArrayList<String> mMaterials;

    public Course(String title, String professeor, ArrayList<String> students, ArrayList<String> videos, 
                ArrayList<String> materials) {
      this.mTitle = title;
      this.mProfessor = professeor;
      this.mStudents = students == null ? new ArrayList<>() : students;
      this.mVideos = videos == null? new ArrayList<>() : videos;
      this.mMaterials = materials == null? new ArrayList<>() : materials;
    }

    public String getTitle(){
        return this.mTitle;
    }
    public String getProfessor(){
        return this.mProfessor;
    }
    public ArrayList<String> getStudents(){
        return this.mStudents;
    }
    public ArrayList<String> getVideos(){
        return this.mVideos;
    }
    public ArrayList<String> getMaterials(){
        return this.mMaterials;
    }
    public void setStudents(ArrayList<String> students){
        this.mStudents = students;
    }
    public void setVideos(ArrayList<String> videos){
        this.mVideos = videos;
    }
    public void setCourses(ArrayList<String> materials){
        this.mMaterials = materials;
    }
  }