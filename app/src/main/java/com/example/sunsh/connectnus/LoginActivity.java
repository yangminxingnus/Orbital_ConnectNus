package com.example.sunsh.connectnus;

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

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private static EditText username;
    private static EditText password;
    private static Button button_sbm;
    private static Button button_rgt;
    int attempt_counter = 5;
    public static String curr_user = "";

    //Click the button to open the register page
    public void RegisterButton() {
        button_rgt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sunsh.connectnus.RegisterActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.editTextName);
        password = (EditText)findViewById(R.id.editTextPword);
        button_sbm = (Button)findViewById(R.id.buttonLogin);
        button_rgt = (Button)findViewById(R.id.buttonRegister);

        RegisterButton();
    }

    //Click to check the information from database and turn to the menu page
    public void OnLogin(View view) throws ExecutionException, InterruptedException {
        String name = username.getText().toString();
        String pword = password.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name, pword);
        String result = backgroundWorker.get();
        if (result.equals("login fail")) {
            Toast.makeText(LoginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
        }
        else {

            curr_user = result;
            Toast.makeText(LoginActivity.this, "Welcome, "+result+"!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("com.example.sunsh.connectnus.MenuActivity");
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.action_aboutus) {
            Toast.makeText(LoginActivity.this, "Welcome to Connect!NUS", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("com.example.sunsh.connectnus.AboutUsActivity");
            startActivity(intent);
        }
        return true;
    }
}
