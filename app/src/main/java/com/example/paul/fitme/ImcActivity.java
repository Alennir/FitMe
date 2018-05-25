package com.example.paul.fitme;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImcActivity extends AppCompatActivity implements View.OnClickListener {
    SingletonEntity singleton = SingletonEntity.getInstance();
    private static final String TAG = "$className$";
    UserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnSearch2 = findViewById(R.id.btnSearch2);
        Button btnSearch3 = findViewById(R.id.btnSearch3);
        TextView tvImc = findViewById(R.id.tvImc);
        ProgressBar progressBar = findViewById(R.id.pbChargement);

        userEntity = singleton.getUser();
        Log.d(TAG,"UserEntity login with:" + userEntity.getTaille()+ userEntity.getPoids()+ userEntity.getEmail()+ userEntity.getAge());
        String imc = userEntity.calculImc();
        tvImc.setText(imc);
        progressBar.setVisibility(View.INVISIBLE);

        btnSearch.setOnClickListener(this);
        btnSearch2.setOnClickListener(this);
        btnSearch3.setOnClickListener(this);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(ImcActivity.this);
        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_alert);
        mBuilder.setContentTitle("FitMe");
        mBuilder.setContentText("Connecté avec le compte" + userEntity.getEmail());
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(0, mBuilder.build());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.disconnect_btn:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void onClick(View v) {
        EditText etSearch = findViewById(R.id.etSearch);

        String ingredient = etSearch.getText().toString();
        ApiBusiness api = new ApiBusiness();
        int age = userEntity.getAge();
        if(!ingredient.isEmpty()) {
            etSearch.onEditorAction(EditorInfo.IME_ACTION_DONE);
            switch (v.getId()) {
                case R.id.btnSearch:
                    if (age > 40)
                        api.GetRecipesWithParam(3, 650, ingredient);
                     else
                        api.GetRecipesWithParam(3, 700, ingredient);

                    break;
                case R.id.btnSearch2:
                    if(age>40)
                        api.GetRecipesWithParam( 3, 450, ingredient);
                    else
                        api.GetRecipesWithParam( 3, 550, ingredient);
                    break;
                case R.id.btnSearch3:
                    if(age >40)
                        api.GetRecipesWithParam( 3, 800, ingredient);
                    else
                        api.GetRecipesWithParam( 3, 900, ingredient);
                    break;
                default:
                    break;

            }


            Toast.makeText(getApplicationContext(), "Chargement en cours...", Toast.LENGTH_LONG).show();

            final ProgressBar progressBar = findViewById(R.id.pbChargement);

            progressBar.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(ImcActivity.this, RecipeActivity.class);
                    startActivity(intent);
                }
            }, 7000);
        }
        else{
            Toast.makeText(getApplicationContext(), "Veuillez renseigner un ingredient", Toast.LENGTH_LONG).show();
        }
    }

    public void clickDisconnect(){

    }
}
