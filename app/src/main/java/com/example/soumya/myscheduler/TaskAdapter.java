package com.example.soumya.myscheduler;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Soumya on 18-11-2015.
 */
public class TaskAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private ArrayList<Tasks> objects;
    private Context mContext;

    public class ViewHolder {
        TextView nameTextView;
        TextView subjectTextView;
        TextView dateTextView;
        CheckBox completedCheckBox;
    }

    public TaskAdapter(Context context, ArrayList<Tasks> objects) {
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


    public static int getIndexOfTask(ArrayList<Tasks> current , Tasks task)
    {
        for(Tasks t : current)
            if(t.compareTo(task) == 0 )
                return current.indexOf(t);
        return -1;
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
            holder.completedCheckBox = (CheckBox) convertView.findViewById(R.id.completedCheckBox);
            convertView.setTag(holder);

            // If CheckBox is toggled, update the planet it is tagged with.
            holder.completedCheckBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v.findViewById(R.id.completedCheckBox);
//                    Tasks task = (Tasks) cb.getTag();
                    SharedPreferences pref = mContext.getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    ArrayList<Tasks> currentUncompletedTasks = new ArrayList<Tasks>();
                    ArrayList<Tasks> currentCompletedTasks = new ArrayList<Tasks>();
                    try{
                        currentUncompletedTasks = (ArrayList<Tasks>) Serializer.DeSerializerTasks(pref.getString("Uncompleted_Tasks",
                                Serializer.SerializerTasks(new ArrayList<Tasks>())));
                        currentCompletedTasks = (ArrayList<Tasks>) Serializer.DeSerializerTasks(pref.getString("Completed_Tasks",
                                Serializer.SerializerTasks((new ArrayList<Tasks>()))));
                        Log.v("Extra", rowItem.toString());
                        int indexOfTask;
                        if(cb.isChecked()) {
                             indexOfTask = getIndexOfTask(currentUncompletedTasks, rowItem);
                            if(indexOfTask != -1) {
                                Log.v("Extra", "removal: " + currentUncompletedTasks.remove(getIndexOfTask(currentUncompletedTasks, rowItem)));
                            } else {
                                Log.v("Extra", "removal: Object not found");
                            }
                            rowItem.setCompleted(cb.isChecked());
                            currentCompletedTasks.add(rowItem);
                        } else {
                            indexOfTask = getIndexOfTask(currentCompletedTasks, rowItem);
                            if(indexOfTask != -1) {
                                Log.v("Extra", "removal: " + currentCompletedTasks.remove(getIndexOfTask(currentCompletedTasks, rowItem)));
                            } else {
                                Log.v("Extra", "removal: Object not found");
                            }
                            rowItem.setCompleted(cb.isChecked());
                            currentUncompletedTasks.add(rowItem);
                        }
                        Log.v("Extra", "Current Completed Tasks");
                        for (int i = 0;i<currentCompletedTasks.size();i++) {
                            Log.v("Extra", currentCompletedTasks.get(i).toString());
                        }
                        Log.v("Extra", "Current Uncompleted Tasks;");
                        for (int i = 0;i<currentUncompletedTasks.size();i++) {
                            Log.v("Extra", currentUncompletedTasks.get(i).toString());
                        }
                        editor.putString("Uncompleted_Tasks", Serializer.SerializerTasks(currentUncompletedTasks));
                        editor.putString("Completed_Tasks", Serializer.SerializerTasks(currentCompletedTasks));
                        editor.commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(mContext, "Selected: " + rowItem.getName() + " is: " + cb.isChecked(), Toast.LENGTH_SHORT).show();
                }
            });

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
