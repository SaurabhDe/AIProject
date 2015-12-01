package com.example.soumya.myscheduler;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class TasksFragment extends android.support.v4.app.Fragment {

    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<Tasks> objects = new ArrayList<Tasks>();
        objects.add(new Tasks("Assignment", "DMG", new Date(10000)));
        objects.add(new Tasks("Project", "DMG", new Date(20000)));
        objects.add(new Tasks("Assignment", "PSOSM", new Date(30000)));
        objects.add(new Tasks("Project", "PSOSM", new Date(40000)));
        objects.add(new Tasks("Assignment", "Program Analysis", new Date(50000)));
        objects.add(new Tasks("Project", "Program Analysis", new Date(60000)));
        objects.add(new Tasks("Assignment", "Artificial Intelligence", new Date(70000)));
        objects.add(new Tasks("Project", "Artificial Intelligence", new Date(80000)));
        TaskAdapter taskAdapter = new TaskAdapter(getActivity(), objects);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_tasks);
        listView.setAdapter(taskAdapter);

        return rootView;
    }
}
