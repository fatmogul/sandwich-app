package com.udacity.sandwichclub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.net.URI;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView mAkaView;
    TextView mOriginView;
    TextView mIngredients;
    TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.makingsandwich)
                .error(R.drawable.nosandwich)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {


        mAkaView = findViewById(R.id.also_known_tv);
        mOriginView = findViewById(R.id.origin_tv);
        mIngredients = findViewById(R.id.ingredients_tv);
        mDescription = findViewById(R.id.description_tv);
        if (sandwich.getAlsoKnownAs().size() == 0){
            mAkaView.setVisibility(View.GONE);
            findViewById(R.id.aka_label).setVisibility(View.GONE);
        }
        else{
        List<String> sandwichAka = sandwich.getAlsoKnownAs();
        for(int i = 0; i < sandwichAka.size();i++){
            mAkaView.append(sandwichAka.get(i) + "\n");
        }}

        if(sandwich.getPlaceOfOrigin().equals("")){
            mOriginView.setVisibility(View.GONE);
            findViewById(R.id.origin_label).setVisibility(View.GONE);
        }
else {
            mOriginView.setText(sandwich.getPlaceOfOrigin() + "\n");
        }
        if(sandwich.getIngredients() == null){
            mIngredients.setVisibility(View.GONE);
            findViewById(R.id.ingredients_label).setVisibility(View.GONE);
        }
        else{
        List<String> sandwichIngredients = sandwich.getIngredients();
        for (int i = 0; i < sandwichIngredients.size(); i++){
            mIngredients.append(sandwichIngredients.get(i) + "\n");
        }}
if(sandwich.getDescription() == null){
            mDescription.setVisibility(View.GONE);
            findViewById(R.id.description_label).setVisibility(View.GONE);
}else {
    mDescription.setText(sandwich.getDescription() + "\n\n");
}



        }
}
