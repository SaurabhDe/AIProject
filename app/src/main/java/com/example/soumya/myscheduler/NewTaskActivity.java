package com.example.soumya.myscheduler;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    private static EditText DateEdit;
    private Button DoneButton;
    private EditText nameTask;
    private EditText subjectTask;
    private Spinner priorityTask;
    private EditText noOfHoursTask;
    private EditText dueOnTask;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Spinner spinner = (Spinner) findViewById(R.id.priorityTask);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priority_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        DateEdit = (EditText) findViewById(R.id.dateTimeEditText);
        DateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTruitonTimePickerDialog(v);
                showTruitonDatePickerDialog(v);
            }
        });


        DoneButton = (Button) findViewById(R.id.doneButton);
        nameTask = (EditText) findViewById(R.id.nameTask);
        subjectTask = (EditText) findViewById(R.id.subjectTask);
        priorityTask = (Spinner) findViewById(R.id.priorityTask);
        noOfHoursTask = (EditText) findViewById(R.id.noOfHoursTask);
        dueOnTask = (EditText) findViewById(R.id.dateTimeEditText);

        final java.text.DateFormat formatter= new SimpleDateFormat("dd/MM/yyyy -HH:mm");

        DoneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Tasks newTask = null;
                try {
                    newTask = new Tasks(nameTask.getText().toString(), subjectTask.getText().toString(),
                            (Date)formatter.parse(dueOnTask.getText().toString()), Integer.parseInt(priorityTask.getSelectedItem().toString()), Integer.parseInt(noOfHoursTask.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(newTask == null) {
                    Toast.makeText(getApplicationContext(), "Please enter Date in correct Format", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Task Made", Toast.LENGTH_SHORT).show();
                    SharedPreferences pref = getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    ArrayList<Tasks> currentTasks = new ArrayList<Tasks>();
                    try{
                        currentTasks = (ArrayList<Tasks>) Serializer.DeSerializer(pref.getString("Task Made",
                                Serializer.Serializer(new ArrayList<Tasks>())));
                        currentTasks.add(newTask);
                        System.out.println(editor.toString());
                        for (int i = 0;i<currentTasks.size();i++) {
                            Log.v("Extra", currentTasks.get(i).toString());
                        }
                        editor.putString("Task Made", Serializer.Serializer(currentTasks));
                        editor.commit();
                        System.out.println(editor.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }

    public void showTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            DateEdit.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public void showTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            DateEdit.setText(DateEdit.getText() + " -" + hourOfDay + ":" + minute);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_new_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
