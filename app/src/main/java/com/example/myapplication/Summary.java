package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Summary extends AppCompatActivity {

    StudentDB myStudentDB;
    ListView myListView;
    SummaryList mySummaryList;
    Menu myMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        myStudentDB = new StudentDB(this);
        setContentView(R.layout.student_list_lv);
        myListView = findViewById(R.id.student_list_id);
        mySummaryList = new SummaryList();
        myListView.setAdapter(mySummaryList);

    }

    @Override
    protected void onStart() {

        mySummaryList.notifyDataSetChanged();
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu inputMenu) {

        getMenuInflater().inflate(R.menu.summary_menu, inputMenu);
        inputMenu.findItem(R.id.action_add).setVisible(true);
        inputMenu.findItem(R.id.action_submit).setVisible(false);
        myMenu = inputMenu;
        return super.onCreateOptionsMenu(inputMenu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem inputMenuItem) {

        if (inputMenuItem.getItemId() == R.id.action_add) {

            Intent myIntent = new Intent(this, AddStudent.class);
            startActivity(myIntent);
            inputMenuItem.setVisible(false);

        } else if (inputMenuItem.getItemId() == R.id.action_submit) {

            Intent myIntent = new Intent(getBaseContext(), Summary.class);
            startActivity(myIntent);
            inputMenuItem.setVisible(false);
            myMenu.findItem(R.id.action_add).setVisible(true);

        }

        return super.onOptionsItemSelected(inputMenuItem);
    }

}
