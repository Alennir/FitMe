package com.example.paul.fitme;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    SingletonEntity singleton = SingletonEntity.getInstance();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        List<RecipeEntity> recipeEntity = singleton.getRecipeEntity();

        recyclerView = findViewById(R.id.rvRecipe);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        RecipeAdapter recipeAdapter = new RecipeAdapter(recipeEntity);

        recyclerView.setAdapter(recipeAdapter);


    }

}
