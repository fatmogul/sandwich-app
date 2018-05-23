package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        List<String> emptyArray = new ArrayList<String>();
        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameObject = sandwichJson.getJSONObject("name");
            String name = nameObject.getString("mainName");
            JSONArray akaArray = nameObject.getJSONArray("alsoKnownAs");
            List<String> akaList = new ArrayList<String>();
            for (int i=0; i < akaArray.length(); i++){
                akaList.add(akaArray.getString(i));

            }
            String imageUrl = sandwichJson.getString("image");
            String origin = sandwichJson.getString("placeOfOrigin");
            String description = sandwichJson.getString("description");
            JSONArray ingredientArray = sandwichJson.getJSONArray("ingredients");
            List<String> ingredientList = new ArrayList<String>();
            for (int i=0; i < ingredientArray.length(); i++){
                ingredientList.add(ingredientArray.getString(i));
            }
            return new Sandwich(name, akaList, origin, description, imageUrl, ingredientList);

        }
        catch (JSONException e){
            e.printStackTrace();

        }
        return new Sandwich("", emptyArray, "", "", "", emptyArray);


    }
}
