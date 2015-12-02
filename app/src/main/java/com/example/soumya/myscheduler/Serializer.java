package com.example.soumya.myscheduler;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Soumya on 01-12-2015.
 */
public class Serializer {

    public static final java.text.DateFormat inputFormat= new SimpleDateFormat("dd/MM/yyyy -HH:mm");
//    public static final java.text.DateFormat outputFormat= new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
    public static final java.text.DateFormat outputFormat= new SimpleDateFormat("MMM d, yyyy HH:mm:ss aaa");

//    public static final java.text.DateFormat outputFormat= new SimpleDateFormat("L dd, yyyy H:m:s a");

    public static String SerializerTasks(ArrayList<Tasks> list) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        return jsonString;
    }

    public static ArrayList<Tasks> DeSerializerTasks(String jsonString) throws JSONException, ParseException {
        JSONArray js = new JSONArray(jsonString);
        ArrayList<Tasks> answer = new ArrayList<Tasks>();
        JSONObject j;
        for (int i=0;i<js.length();i++) {
            j = js.getJSONObject(i);
            answer.add(new Tasks(j.getString("name"),
                    j.getString("subject"),
                    (Date)outputFormat.parse(j.getString("dueOnDate")),
                            Integer.parseInt(j.getString("priority")),
                    Integer.parseInt(j.getString("noOfHoursRequired")),
                    Boolean.parseBoolean(j.getString("completed")))
                    );
        }
        return answer;
    }


    public static String SerializerBlock(ArrayList<Block> list) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        return jsonString;
    }

    public static ArrayList<Block> DeSerializerBlock(String jsonString) throws JSONException, ParseException {
        JSONArray js = new JSONArray(jsonString);
        ArrayList<Block> answer = new ArrayList<Block>();
        JSONObject j;
        for (int i=0;i<js.length();i++) {
            j = js.getJSONObject(i);
//            Log.d("asg", (Date)outputFormat.parse(j.getString("startTime"));

            Date startDate = new Date();
            String startDateS = j.getString("startTime");
            String[] temp = startDateS.split(" ");
            startDate.setDate(Integer.parseInt(temp[1].substring(0, temp[1].length() - 1)));
            String[] temp1 = temp[3].split(":");
            startDate.setHours(Integer.parseInt(temp1[0]));
            startDate.setMinutes(Integer.parseInt(temp1[1]));
            startDate.setSeconds(Integer.parseInt(temp[2]));

            Date endDate = new Date();
            String endDateS = j.getString("endTime");
            temp = endDateS.split(" ");
            endDate.setDate(Integer.parseInt(temp[1].substring(0, temp[1].length() - 1)));
            temp1 = temp[3].split(":");
            endDate.setHours(Integer.parseInt(temp1[0]));
            endDate.setMinutes(Integer.parseInt(temp1[1]));
            endDate.setSeconds(Integer.parseInt(temp[2]));


            answer.add(new Block(j.getString("name"),
                   //         (Date) outputFormat.parse(j.getString("startTime")),
                   //         (Date) outputFormat.parse(j.getString("endTime"))
                            startDate,
                            endDate
                    )
            );

            Log.d("Block Start" , j.getString("startTime"));
            Log.d("Block End" , j.getString("endTime"));
        }
        return answer;
    }
}
