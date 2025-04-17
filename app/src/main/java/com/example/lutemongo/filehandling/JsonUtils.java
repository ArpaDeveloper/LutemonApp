package com.example.lutemongo.filehandling;

//Imports
import android.content.Context;
import android.util.Log;

import com.example.lutemongo.Lutemon;
import com.example.lutemongo.ui.ErrorHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

//This class handles errors and JSON files

public class JsonUtils {
    //This tag is used in error messages
    private static final String TAG = "JsonUtils";

    //This methods handles the loading of the file contents
    public static List<Lutemon> loadLutemonsFromJson(Context context, int resourceId) throws IOException, JSONException {
        //Declare a ArrayList of movies
        List<Lutemon> lutemonList = new ArrayList<>();
        //String to store the contents of JSON file
        String jsonContent = readJsonFile(context, resourceId);
        Log.d("JSON Debug", "Loaded JSON: " + jsonContent);

        //Lets Parse the JSON data
        //Lets create JSON array from the string
        JSONArray jsonArray = new JSONArray(jsonContent);

        //Loop through array to get the details
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject lutemonObject = jsonArray.getJSONObject(i);
            //Get all the details and if they are incorrect/missing put a placeholder
            String name = lutemonObject.has("name") && !lutemonObject.isNull("name")
                    ? lutemonObject.optString("name", "-")
                    : "-";

            String color = lutemonObject.has("color") && !lutemonObject.isNull("color")
                    ? lutemonObject.optString("color", "lutemonball")
                    : "lutemonball";

            int attack = lutemonObject.has("attack") ? lutemonObject.optInt("attack", -1) : -1;
            int health = lutemonObject.has("health") ? lutemonObject.optInt("health", -1) : -1;
            int max_health = lutemonObject.has("max_health") ? lutemonObject.optInt("max_health", -1) : -1;
            int defence = lutemonObject.has("defence") ? lutemonObject.optInt("defence", -1) : -1;
            int experience = lutemonObject.has("experience") ? lutemonObject.optInt("experience", -1) : -1;

            //Finally add the Lutemon Object to list
            lutemonList.add(new Lutemon(name,color,attack,health,max_health,defence,experience));
        }
        //Return the list
        return lutemonList;
    }

    //This method reads the JSON file and returns a string
    private static String readJsonFile(Context context, int resourceId) throws IOException {
        //Declare a String builder, which allows for better string manipulation
        StringBuilder stringBuilder = new StringBuilder();
        //Read the JSON file using buffered reader
        try (InputStream inputStream = context.getResources().openRawResource(resourceId);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            //Loop through as long as there is a line
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            //Catch IOException and throw it
        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON file", e);
            throw e;
        }
        //Return the full string
        return stringBuilder.toString();
    }

    //This method handles exceptions
    private static void handleJsonException(Exception e, Context context){
        //Declare message (This will be returned to MainActivity and printed)
        String message;
        //Different message for different errors
        if (e instanceof FileNotFoundException) {
            Log.e(TAG, "Error: File was not found", e);
            message = "File was not found";
        } else if (e instanceof JSONException) {
            Log.e(TAG, "Error: Movie data format is invalid", e);
            message = "Error movie data format is invalid";
        } else if (e instanceof IOException) {
            Log.e(TAG, "Error: Accessing the movies data", e);
            message = "Error accessing the movies data";
        } else {
            Log.e(TAG, "Error: Unexpected, Try again", e);
            message = "Unexpected error, Try again";
        }

        ErrorHandler.showError(context, message);
    }

    //Getter to call the error handling
    public static void handleError(Exception e, Context context) {
        handleJsonException(e, context);
    }

}