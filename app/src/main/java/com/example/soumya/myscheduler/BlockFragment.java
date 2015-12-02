package com.example.soumya.myscheduler;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class BlockFragment extends android.support.v4.app.Fragment {

    private Button DoneButton;
    private EditText nameText;
    private static EditText startTime;
    private static EditText endTime;

    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_block, container, false);

        startTime = (EditText) rootView.findViewById(R.id.startTimeBlock);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStartTruitonTimePickerDialog(v);
                showStartTruitonDatePickerDialog(v);
            }
        });
        endTime = (EditText) rootView.findViewById(R.id.endDateBlock);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEndTruitonTimePickerDialog(v);
                showEndTruitonDatePickerDialog(v);
            }
        });
        DoneButton = (Button) rootView.findViewById(R.id.doneButtonBlock);
        nameText = (EditText) rootView.findViewById(R.id.nameBlock);

        final java.text.DateFormat formatter= new SimpleDateFormat("dd/MM/yyyy -HH:mm");

        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Block newBlock = null;
                try {
                    newBlock = new Block(nameText.getText().toString().trim(), (Date) formatter.parse(startTime.getText().toString().trim()), (Date) formatter.parse(endTime.getText().toString().trim()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (newBlock == null) {
                    Toast.makeText(getActivity(), "Please enter Date in correct Format", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Block Made", Toast.LENGTH_SHORT).show();
                    SharedPreferences pref = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    ArrayList<Block> currentTasks = new ArrayList<Block>();
                    try {
                        currentTasks = (ArrayList<Block>) Serializer.DeSerializerBlock(pref.getString("Blocked_Tasks",
                                Serializer.SerializerBlock(new ArrayList<Block>())));
                        currentTasks.add(newBlock);

                        for (int i = 0; i < currentTasks.size(); i++) {
                            Log.v("Extra", currentTasks.get(i).toString());
                        }

                        System.out.println(editor.toString());
                        for (int i = 0; i < currentTasks.size(); i++) {
                            Log.v("Extra", currentTasks.get(i).toString());
                        }
                        editor.putString("Blocked_Tasks", Serializer.SerializerBlock(currentTasks));
                        editor.commit();
                        System.out.println(editor.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        });
        return rootView;
    }

    public static class startDatePickerFragment extends DialogFragment implements
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
            startTime.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public static class endDatePickerFragment extends DialogFragment implements
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
            endTime.setText(day + "/" + (month + 1) + "/" + year);
        }
    }

    public static class endTimePickerFragment extends DialogFragment implements
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
            endTime.setText(endTime.getText() + " -" + hourOfDay + ":" + minute);
        }
    }

    public static class startTimePickerFragment extends DialogFragment implements
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
            startTime.setText(startTime.getText() + " -" + hourOfDay + ":" + minute);
        }
    }

    public void showStartTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new startDatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    public void showEndTruitonDatePickerDialog(View v) {
        DialogFragment newFragment = new endDatePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    public void showStartTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new startTimePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }
    public void showEndTruitonTimePickerDialog(View v) {
        DialogFragment newFragment = new endTimePickerFragment();
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

}
