package com.example.soumya.myscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DashboardFragment extends android.support.v4.app.Fragment {

    CheckBox mcompletedBox;
    View rootView;
    TaskAdapterForScheduling taskAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        SharedPreferences pref = this.getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        ArrayList<Tasks> currentTasks = new ArrayList<Tasks>();
        try{
            currentTasks = (ArrayList<Tasks>) Serializer.DeSerializerTasks(pref.getString("Uncompleted_Tasks",
                    Serializer.SerializerTasks(new ArrayList<Tasks>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        taskAdapter = new TaskAdapterForScheduling(getActivity(), currentTasks);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_tasksSchedule);
        listView.setAdapter(taskAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tasks task = (Tasks) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Clicked on Row: " + task.getName(), Toast.LENGTH_LONG).show();
            }
        });

        Button myButton = (Button) rootView.findViewById(R.id.runScheduleButton);
        final ArrayList<Tasks> finalCurrentTasks = currentTasks;
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");
                ArrayList<Tasks> tasksToBeScheduledList = finalCurrentTasks;
                for (int i = 0; i < tasksToBeScheduledList.size(); i++) {
                    Tasks task = tasksToBeScheduledList.get(i);
                    if (task.isCompleted()) {
                        responseText.append("\n" + task.getName());
                    }
                }

                Toast.makeText(getActivity(), responseText, Toast.LENGTH_LONG).show();

            }
        });
        return rootView;
    }
}
