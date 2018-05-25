package com.example.paul.fitme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountCreationActivity extends AppCompatActivity {
    private boolean isAllParamOk = false;
    private EditText etEmail, etPassword, etAge, etTaille, etPoids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        etEmail = findViewById(R.id.etEmailAcc);
        etPassword = findViewById(R.id.etPasswordAcc);
        etAge = findViewById(R.id.etAge);
        etTaille = findViewById(R.id.etTaille);
        etPoids = findViewById(R.id.etPoids);

        Button btnValidate = findViewById(R.id.btnValidateAcc);
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAllParam();

                if(isAllParamOk) {
                    TinyDB db = new TinyDB(getApplicationContext());
                    ArrayList<UserEntity> userEntities = new ArrayList<>();
                    UserEntity userEntityToAdd = new UserEntity(etEmail.getText().toString(), etPassword.getText().toString(),Integer.parseInt(etAge.getText().toString()),Double.parseDouble(etTaille.getText().toString()),Integer.parseInt(etPoids.getText().toString()));
                    ArrayList<Object> userObjects = new ArrayList<>();
                    userEntities.add(userEntityToAdd);

                    for (UserEntity a : userEntities) userObjects.add(a);

                    db.getListObject("UserEntity", UserEntity.class).addAll(userObjects);
                    db.putListObject("UserEntity", userObjects);

                    showDialogBox();



                }
            }
        });
    }

    private void showDialogBox() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(AccountCreationActivity.this);
        builder1.setMessage("Etes-vous surs ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Oui",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(AccountCreationActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "Non",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void CheckAllParam() {
        if (etAge.getText().toString().isEmpty() || etTaille.getText().toString().isEmpty() || etPoids.getText().toString().isEmpty() || etEmail.getText().toString().matches("") || etPassword.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "Complete all fields please", Toast.LENGTH_LONG).show();
            isAllParamOk = false;
        }
        else
            isAllParamOk = true;
    }
}
