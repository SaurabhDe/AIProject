package com.example.soumya.myscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Soumya on 18-11-2015.
 */
public class TaskAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private ArrayList<Tasks> objects;

    private class ViewHolder {
        TextView nameTextView;
        TextView subjectTextView;
        TextView dateTextView;
        CheckBox completedCheckBox;
    }

    public TaskAdapter(Context context, ArrayList<Tasks> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
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
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_taskview, null);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            holder.subjectTextView = (TextView) convertView.findViewById(R.id.subjecTextView);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
            holder.completedCheckBox = (CheckBox) convertView.findViewById(R.id.completedCheckBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTextView.setText(objects.get(position).getName());
        holder.subjectTextView.setText(objects.get(position).getSubject());
        holder.dateTextView.setText(objects.get(position).getDueOnDate().toString());
        holder.completedCheckBox.setChecked(objects.get(position).isCompleted());
        return convertView;
    }
}
