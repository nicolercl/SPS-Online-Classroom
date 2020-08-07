/** A course. */
package com.sps.team2.classes;
import java.util.HashSet;
import java.util.ArrayList;

public class Course {

    private long mId;
    private String mTitle;
    private String mProfessor;
    private HashSet<String> mStudents;
    private ArrayList<String> mVideos;
    private ArrayList<String> mMaterials;
    

    public Course(long id, String title, String professeor, HashSet<String> students, ArrayList<String> videos, 
                ArrayList<String> materials) {
      this.mId = id;
      this.mTitle = title;
      this.mProfessor = professeor;
      this.mStudents = students;
      this.mVideos = videos;
      this.mMaterials = materials;
    }

    public long getId(){
        return this.mId;
    }
    public String getTitle(){
        return this.mTitle;
    }
    public String getProfessor(){
        return this.mProfessor;
    }
    public HashSet<String> getStudents(){
        return this.mStudents;
    }
    public ArrayList<String> getVideos(){
        return this.mVideos;
    }
    public ArrayList<String> getMaterials(){
        return this.mMaterials;
    }
    public void setId(long id){
        this.mId = id;
    }
    public void setStudents(HashSet<String> students){
        this.mStudents = students;
    }
    public void setVideos(ArrayList<String> videos){
        this.mVideos = videos;
    }
    public void setCourses(ArrayList<String> materials){
        this.mMaterials = materials;
    }
  }