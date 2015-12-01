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
    public static final java.text.DateFormat outputFormat= new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");

    public static String Serializer(ArrayList<Tasks> list) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        return jsonString;
    }

    public static ArrayList<Tasks> DeSerializer(String jsonString) throws JSONException, ParseException {
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
}
