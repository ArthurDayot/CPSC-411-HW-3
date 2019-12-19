package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    StudentDB myStudentDB;
    LinearLayout myLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        myStudentDB = new StudentDB(this);
        setContentView(R.layout.student_list);
        myLinearLayout = findViewById(R.id.student_list);
        ArrayList<Student> studentList = myStudentDB.getStudentList();

        for(int i = 0; i < studentList.size(); i++) {

            Student myStudent = studentList.get(i);
            LayoutInflater inflater = LayoutInflater.from(this);
            View row_view = inflater.inflate(R.layout.student_row, myLinearLayout, false);
            ((TextView) row_view.findViewById(R.id.first_name)).setText(myStudent.getFirstName());
            ((TextView) row_view.findViewById(R.id.last_name)).setText(myStudent.getLastName());
            myLinearLayout.addView(row_view);
        }

    }

}
