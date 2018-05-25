package com.example.paul.fitme;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Paul on 25/05/2018.
 */

public class ApiBusiness {
    private OkHttpClient client;
    private Request request;
    private static final String TAG = "$CLASS_NAME$";
    SingletonEntity singleton = SingletonEntity.getInstance();

    public ApiBusiness(){
        client = new OkHttpClient();
    }

    public void GetRecipesWithParam(final int numberOfRecipes, int calories, String ingredient) {
        singleton.resetAllRecipe();
        if (numberOfRecipes > 0) {
            request = new Request.Builder().url("https://api.edamam.com/search?q="+ ingredient +"&app_id=72ddaba3&app_key=321e4ed28d51b4acf7ec7d9b8f4ee703&from=0&to=" + numberOfRecipes+"&calories="+ (calories-100) + (calories+100)).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG,"Erreur lors de l'import Json");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()){
                        try {
                            String jsonData = response.body().string();
                            JSONObject jsonObject = new JSONObject(jsonData);
                            JSONArray jsonArray = jsonObject.getJSONArray("hits");

                            for(int i=0; i<numberOfRecipes; i++){
                                JSONObject jsonRecipe = jsonArray.getJSONObject(i).getJSONObject("recipe");
                                String url     = jsonRecipe.getString("url");
                                String image = jsonRecipe.getString("image");
                                String name = jsonRecipe.getString("label");
                                singleton.addRecipe(new RecipeEntity(image,url, name));
                            }

                        } catch (JSONException e) {
                            Log.e(TAG,"Erreur lors de l'import Json");
                        }
                    }
                }
            });

        }
    }
}
