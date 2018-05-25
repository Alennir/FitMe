package com.example.paul.fitme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected TinyDB tinyDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnValidate = findViewById(R.id.btnValidate);
        Button btnCreate = findViewById(R.id.btnCreate);
        btnValidate.setOnClickListener(this);
        btnCreate.setOnClickListener(this);



        tinyDb = new TinyDB(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreate:
                CreateAccount();
                break;
            case R.id.btnValidate:
                ValidateAccount();
                break;
            default:
                break;
        }
    }

    private void ValidateAccount() {
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        ArrayList<Object> listUsers;
        ArrayList<UserEntity> userEntityFinal = new ArrayList<>();

        listUsers = tinyDb.getListObject("UserEntity", UserEntity.class);

        for (Object a : listUsers) {
            userEntityFinal.add((UserEntity) a);
        }

        for (UserEntity userEntity : userEntityFinal) {
            if (userEntity.getEmail().equals(etEmail.getText().toString()) && userEntity.getPassword().equals(etPassword.getText().toString())) {

                    SingletonEntity singleton = SingletonEntity.getInstance();
                    singleton.addUser(userEntity);

                    Intent intent = new Intent(MainActivity.this, ImcActivity.class);
                    startActivity(intent);
            }
        }
    }

    private void CreateAccount() {
        Intent intent = new Intent(MainActivity.this, AccountCreationActivity.class);
        startActivity(intent);
    }
}
