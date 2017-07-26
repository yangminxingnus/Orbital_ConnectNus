package com.example.sunsh.connectnus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sunshengran on 6/7/2017.
 * This is the user register page.
 * New users can register here and the data will be stored into the SQL database
 */

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    EditText editName, editEmail, editPword, editCP;
    Button btnRegister, btnDelete, btnCancel;
    String username,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = (EditText)findViewById(R.id.editTextName);
        editEmail = (EditText)findViewById(R.id.editTextEmail);
        editPword = (EditText)findViewById(R.id.editTextPword);
        editCP = (EditText)findViewById(R.id.editTextCP);
        btnRegister = (Button)findViewById(R.id.buttonRegister);
        btnDelete = (Button)findViewById(R.id.buttonDelete);
        btnCancel = (Button)findViewById(R.id.buttonCancel);

        DeleteButton();
        CancelButton();
    }

    //Click the button to check the integrity of the information entered, and store the information to the table
    public void OnRegister(View view) throws ExecutionException, InterruptedException {
        username = editName.getText().toString();
        email = editEmail.getText().toString();
        password = editPword.getText().toString();
        if (editName.getText().toString().equals("")||editPword.getText().toString().equals("")||editCP.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Information is not completed!", Toast.LENGTH_SHORT).show();
        }
        else if (!editPword.getText().toString().equals(editCP.getText().toString()))
            Toast.makeText(RegisterActivity.this, "Passwords entered are defferent", Toast.LENGTH_SHORT).show();
        else {
            String type = "register";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type,username,email,password);

            String result = backgroundWorker.get();
            if (result.equals("register success")) {
                Toast.makeText(RegisterActivity.this, "Information Registered!", Toast.LENGTH_SHORT).show();
                finish();
            }
            else if (result.equals("register fail")) {
                Toast.makeText(RegisterActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Click to delete everything the user has entered
    public void DeleteButton() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editName.setText("");
                        editEmail.setText("");
                        editPword.setText("");
                        editCP.setText("");
                    }
                }
        );
    }

    //Click to cancel the registration and return to the login page
    public void CancelButton() {
        btnCancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_help,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.action_help) {
            AlertDialog.Builder alt = new AlertDialog.Builder(this);
            String text = "Please enter your prefered username, email(optional) and password into the textboxes.\n\nEmpty username/password or different confirm password will not be accepted.\n\nClick 'Register' to create a new account. Click 'Delete' to clear all the information entered. Click 'Cancel' to close the registration page and return to login page.";
            alt.setMessage(text)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            alt.show();
        }
        return true;
    }

}
