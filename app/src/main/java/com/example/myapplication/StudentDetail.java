package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class StudentDetail extends AppCompatActivity {

    StudentDB myStudentDB;
    Student myStudent;
    Menu myMenu;
    int studentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        myStudentDB = new StudentDB(this);
        setContentView(R.layout.student_details);
        studentIndex = getIntent().getIntExtra("StudentIndex", 0);
        myStudent = StudentDB.getStudentList().get(studentIndex);
        EditText myEditText = findViewById(R.id.p_fname_id);
        myEditText.setText(myStudent.getFirstName());
        myEditText.setEnabled(false);
        myEditText = findViewById(R.id.p_lname_id);
        myEditText.setText(myStudent.getLastName());
        myEditText.setEnabled(false);
        TextView myTextView = findViewById((R.id.p_cwid_id));
        myTextView.setText(myStudent.getCWID().toString());
        myTextView.setEnabled(false);
        ArrayList<Course> listOfCourses = myStudent.getCourses();

        for(int i = 0; i < myStudent.getCourses().size(); i++) {

            Course myCourse = listOfCourses.get(i);
            TextView myTextView2 = findViewById(R.id.p_course_name_id);
            myTextView2.append(myCourse.getCourseID());
            myTextView2.append("\n");
            myTextView2.setEnabled(false);
            myTextView2 = findViewById(R.id.p_course_grade_id);
            myTextView2.append(myCourse.getmGrade());
            myTextView2.append("\n");
            myTextView2.setEnabled(false);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu inputMenu) {

        getMenuInflater().inflate(R.menu.detail_menu, inputMenu);
        inputMenu.findItem(R.id.action_edit).setVisible(true);
        inputMenu.findItem(R.id.action_done).setVisible(false);
        myMenu = inputMenu;
        return super.onCreateOptionsMenu(inputMenu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem inputMenuItem) {

        Student tempStudent = new Student("", "", "");

        if (inputMenuItem.getItemId() == R.id.action_edit) {

            EditText myEditText = findViewById(R.id.p_fname_id);
            myEditText.setEnabled(true);
            myEditText = findViewById(R.id.p_lname_id);
            myEditText.setEnabled(true);
            inputMenuItem.setVisible(false);
            myMenu.findItem(R.id.action_done).setVisible(true);

        } else if (inputMenuItem.getItemId() == R.id.action_done) {

            EditText myEditText = findViewById(R.id.p_fname_id);
            tempStudent.setFirstName(myEditText.getText().toString());
            myEditText.setEnabled(false);
            myEditText = findViewById(R.id.p_lname_id);
            tempStudent.setLastName(myEditText.getText().toString());
            myEditText.setEnabled(false);
            myEditText = findViewById(R.id.p_cwid_id);
            tempStudent.setCWID(myEditText.getText().toString());
            inputMenuItem.setVisible(false);
            myMenu.findItem(R.id.action_edit).setVisible(true);
            myStudentDB.updateDB(tempStudent);

        }

        return super.onOptionsItemSelected(inputMenuItem);

    }

}