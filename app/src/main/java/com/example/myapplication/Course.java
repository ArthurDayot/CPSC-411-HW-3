package com.example.myapplication;

public class Course {

    String courseID;
    String grade;

    public Course(String inputID, String inputGrade){

        courseID = inputID;
        grade = inputGrade;
    }

    public String getCourseID(){

        return courseID;

    }

    public void setCourseID (String inputID){

        courseID = inputID;

    }

    public String getmGrade () {

        return grade;

    }

    public void setGrade (String inputGrade) {

        grade = inputGrade;

    }


}