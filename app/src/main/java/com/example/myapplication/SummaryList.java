package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryList extends BaseAdapter {

    @Override
    public int getCount() {

        return StudentDB.getStudentList().size();

    }

    @Override
    public Object getItem(int inputItem) {

        return StudentDB.getStudentList().get(inputItem);

    }

    @Override
    public long getItemId(int inputItemID) {

        return inputItemID;

    }

    @Override
    public View getView(int i, View inputView, ViewGroup inputViewGroup) {

        View myView;

        if (inputView == null) {

            LayoutInflater myLayoutInflater = LayoutInflater.from(inputViewGroup.getContext());
            myView = myLayoutInflater.inflate(R.layout.student_row, inputViewGroup, false);

        } else {

            myView = inputView;

        }

        final Student myStudent = StudentDB.getStudentList().get(i);
        ((TextView) myView.findViewById(R.id.first_name)).setText(myStudent.getFirstName());
        ((TextView) myView.findViewById(R.id.last_name)).setText(myStudent.getLastName());
        myView.setTag((new Integer(i)));

        myView.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {

                        Toast.makeText(v.getContext(), "User Selected " + myStudent.getFirstName(), Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(v.getContext(), StudentDetail.class);
                        myIntent.putExtra("StudentIndex", ((Integer)v.getTag()).intValue());
                        v.getContext().startActivity(myIntent);

                    }
                }

        );

        return myView;

    }

}
