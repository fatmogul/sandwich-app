package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_AKA = "alsoKnownAs";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        List<String> emptyArray = new ArrayList<String>();
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameObject = sandwichJson.getJSONObject(KEY_NAME);
            String name = nameObject.optString(KEY_MAIN_NAME);
            JSONArray akaArray = nameObject.getJSONArray(KEY_AKA);
            List<String> akaList = new ArrayList<String>();
            for (int i=0; i < akaArray.length(); i++){
                akaList.add(akaArray.optString(i));

            }
            String imageUrl = sandwichJson.optString(KEY_IMAGE);
            String origin = sandwichJson.optString(KEY_PLACE_OF_ORIGIN);
            String description = sandwichJson.optString(KEY_DESCRIPTION);
            JSONArray ingredientArray = sandwichJson.getJSONArray(KEY_INGREDIENTS);
            List<String> ingredientList = new ArrayList<String>();
            for (int i=0; i < ingredientArray.length(); i++){
                ingredientList.add(ingredientArray.optString(i));
            }
            return new Sandwich(name, akaList, origin, description, imageUrl, ingredientList);

        }
        catch (JSONException e){
            e.printStackTrace();

        }
        return new Sandwich("", emptyArray, "", "", "", emptyArray);


    }
}
