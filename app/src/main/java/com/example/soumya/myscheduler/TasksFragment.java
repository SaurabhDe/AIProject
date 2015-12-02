package com.example.soumya.myscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.soumya.myscheduler.GeneticAlgorithm.main.Schedule;

import java.util.ArrayList;
import java.util.Date;

public class TasksFragment extends android.support.v4.app.Fragment {

    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        SharedPreferences pref = getSharedPreferences("DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        ArrayList<Tasks> currentTasks = new ArrayList<Tasks>();
        try{
            currentTasks = (ArrayList<Tasks>) Serializer.DeSerializer(pref.getString("Task Made",
                    Serializer.Serializer(new ArrayList<Tasks>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        TaskAdapter taskAdapter = new TaskAdapter(getActivity(), currentTasks);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_tasks);
        listView.setAdapter(taskAdapter);

        return rootView;
    }
}
