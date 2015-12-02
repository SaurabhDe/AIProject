package com.example.soumya.myscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DashboardFragment extends android.support.v4.app.Fragment {

    CheckBox mcompletedBox;
    View rootView;

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

        final TaskAdapter taskAdapter = new TaskAdapter(getActivity(), currentTasks);

        ListView listView = (ListView) rootView.findViewById(R.id.listview_tasks);
        listView.setAdapter(taskAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tasks task = (Tasks) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), "Clicked on Row: " + task.getName(), Toast.LENGTH_LONG).show();
            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item,
                                    int position, long id) {
                Tasks task = taskAdapter.getItem(position);
                task.toggleChecked();
                TaskAdapter.ViewHolder viewHolder = (TaskAdapter.ViewHolder) item.getTag();
                viewHolder.completedCheckBox.setChecked(task.isCompleted());
                for (int i = 0; i < taskAdapter.getCount(); i++) {
                    Tasks task1 = taskAdapter.getItem(i);
                    if (task1.isCompleted()) {
                        Toast.makeText(getActivity(), task1.getName() + " is Checked!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/



        return rootView;
    }

}
