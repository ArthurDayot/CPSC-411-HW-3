package com.example.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.util.ArrayList;

public class StudentDB extends Activity {

    static SQLiteDatabase mySQLiteDB; //Must be static
    File databaseFile;

    public StudentDB(Context inputContext) {

        databaseFile = inputContext.getDatabasePath("student.db");
        mySQLiteDB = SQLiteDatabase.openOrCreateDatabase(databaseFile, null);
        String myQuery = "CREATE TABLE IF NOT EXISTS STUDENT (FirstName Text, LastName Text, CWID Text)";
        mySQLiteDB.execSQL(myQuery);
        myQuery = "CREATE TABLE IF NOT EXISTS COURSES (CWID Text, Course Text, Grade Text)";
        mySQLiteDB.execSQL(myQuery);

    }

    public static ArrayList<Student> getStudentList() {

        ArrayList<Student> listOfStudents = new ArrayList<>();
        Student myStudent;
        ArrayList<Course> listOfCourses = new ArrayList<>();
        Cursor myCursor = mySQLiteDB.query("STUDENT", null, null,
                null, null, null, null);

        if(myCursor.getCount() > 0) {

            while(myCursor.moveToNext()) {

                myStudent = new Student(myCursor.getString(0), myCursor.getString(1), myCursor.getString(2));
                Cursor myCursor2 = mySQLiteDB.query("COURSES", null, "CWID=?",
                        new String[]{myCursor.getString(2)}, null, null, null);

                if(myCursor2.getCount() > 0) {

                    listOfCourses = new ArrayList<>();

                    while(myCursor2.moveToNext()) {

                        listOfCourses.add(new Course(myCursor2.getString(1), myCursor2.getString(2)));

                    }

                }

                myStudent.setCourses(listOfCourses);
                listOfStudents.add(myStudent);

            }

        }

        return listOfStudents;

    }

    public static void addListToDB(ArrayList<Student> inputStudentList) {

        for(Student student:inputStudentList){

            mySQLiteDB.execSQL("INSERT INTO STUDENT VALUES (?, ?, ?)", new String[]{student.getFirstName(),
                    student.getLastName(), student.getCWID()});

            for(Course course:student.getCourses()) {

                mySQLiteDB.execSQL("INSERT INTO COURSES VALUES (?, ?, ?)", new String[]{student.getCWID(),
                        course.getCourseID(), course.getmGrade()});

            }

        }

    }
    public static void updateDB(Student inputStudent) {

        ContentValues cv = new ContentValues();
        cv.put("FirstName", inputStudent.getFirstName());
        cv.put("LastName", inputStudent.getLastName());
        mySQLiteDB.update("STUDENT", cv, "CWID=?", new String[]{inputStudent.getCWID()});

    }

}