package com.example.myapplication;

import java.util.ArrayList;

public class Student {

    String firstName;
    String lastName;
    String studentID;
    ArrayList<Course> listOfCourses;

    public Student(String inputFirstName, String inputLastName, String inputStudentID){

        this.firstName = inputFirstName;
        this.lastName = inputLastName;
        this.studentID = inputStudentID;

    }


    public String getFirstName (){

        return firstName;

    }

    public void setFirstName(String inputFirstName) {

        firstName = inputFirstName;

    }

    public String getLastName() {

        return lastName;

    }
    public void setLastName(String inputLastName) {

        lastName = inputLastName;

    }

    public String getCWID () {

        return studentID;

    }

    public void setCWID( String inputStudentID) {

        studentID = inputStudentID;

    }

    public ArrayList<Course> getCourses() {

        return listOfCourses;

    }

    public void setCourses(ArrayList<Course> inputCourses){

        listOfCourses = inputCourses;

    }

    public void addCourses(Course inputCourse) {

        listOfCourses.add(inputCourse);

    }

}