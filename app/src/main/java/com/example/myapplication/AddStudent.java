package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class AddStudent extends AppCompatActivity {

    StudentDB myStudentDB;
    EditText firstName;
    EditText lastName;
    EditText studentID;
    EditText courseName;
    EditText courseGrade;
    List<String[]> listOfCourses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        myStudentDB = new StudentDB(this);
        setContentView(R.layout.student_add);
        firstName = findViewById(R.id.add_fname_id);
        lastName = findViewById(R.id.add_lname_id);
        studentID = findViewById(R.id.add_cwid_id);
        courseName = findViewById(R.id.add_course_name);
        courseGrade = findViewById(R.id.add_course_grade);
        listOfCourses = new ArrayList<String[]>();
        Button addC = findViewById(R.id.add_course_btn);
        addC.setOnClickListener(new View.OnClickListener() {

            int rows = 0;

            @Override
            public void onClick(View v) {

                if(courseName.getText().toString() != "" && courseGrade.getText().toString() != "") {

                    listOfCourses.add(new String[] {courseName.getText().toString(), courseGrade.getText().toString()});
                    Toast.makeText(getApplicationContext(), courseName.getText().toString() + " and " + courseGrade.getText().toString() + " added", Toast.LENGTH_LONG).show();
                    courseName.setText("");
                    courseGrade.setText("");
                    rows++;

                } else {

                    Toast.makeText( getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu inputMenu) {

        getMenuInflater().inflate(R.menu.summary_menu, inputMenu);
        inputMenu.findItem(R.id.action_submit).setVisible(true);
        inputMenu.findItem(R.id.action_add).setVisible(false);
        return super.onCreateOptionsMenu(inputMenu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem inputMenuItem) {

        if(inputMenuItem.getItemId() == R.id.action_submit) {

            ArrayList<Student> studentList = new ArrayList<Student>();
            ArrayList<Course> courseList = new ArrayList<>();
            Student temptStudent = new Student(firstName.getText().toString(), lastName.getText().toString(), studentID.getText().toString());

            for(String[]course : listOfCourses) {

                courseList.add(new Course(course[0],course[1]));

            }

            temptStudent.setCourses(courseList);
            studentList.add(temptStudent);
            myStudentDB.addListToDB(studentList);
            finish();

        }

        return super.onOptionsItemSelected(inputMenuItem);

    }

}