package com.example.soumya.myscheduler;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Soumya on 02-12-2015.
 */
public class TaskAdapterForScheduling extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Tasks> objects;
    private Context mContext;

    public class ViewHolder {
        TextView nameTextView;
        TextView subjectTextView;
        TextView dateTextView;
        CheckBox chooseBox;
    }

    public TaskAdapterForScheduling(Context context, ArrayList<Tasks> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
        this.mContext = context;
    }

    public int getCount() {
        return objects.size();
    }

    public Tasks getItem(int position) {
        return objects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Tasks to display
        ViewHolder holder = null;
        Log.v("Extra", String.valueOf(position));

        if(convertView == null) {
            holder = new ViewHolder();
            final Tasks rowItem = (Tasks) getItem(position);
            convertView = inflater.inflate(R.layout.list_item_taskview, null);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            holder.subjectTextView = (TextView) convertView.findViewById(R.id.subjecTextView);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
            holder.chooseBox = (CheckBox) convertView.findViewById(R.id.completedCheckBox);
            convertView.setTag(holder);

            // If CheckBox is toggled, update the planet it is tagged with.
            holder.chooseBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v.findViewById(R.id.completedCheckBox);
//                    Tasks task = (Tasks) cb.getTag();
                    Log.v("Extra", rowItem.toString());
                    rowItem.setCompleted(cb.isChecked());
                    Toast.makeText(mContext, "Selected: " + rowItem.getName() + " is: " + cb.isChecked(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTextView.setText(objects.get(position).getName());
        holder.subjectTextView.setText(objects.get(position).getSubject());
        holder.dateTextView.setText(objects.get(position).getDueOnDate().toString());
        holder.chooseBox.setChecked(objects.get(position).isCompleted());
        return convertView;
    }
}
