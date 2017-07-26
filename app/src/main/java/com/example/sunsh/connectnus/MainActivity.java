package com.example.sunsh.connectnus;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by sunshengran on 5/7/2017.
 * This is the user login page.
 */

public class MainActivity extends AppCompatActivity {

    private static Button button_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_enter = (Button)findViewById(R.id.buttonEnter);

        EnterButton();

    }

    public void EnterButton() {
        button_enter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sunsh.connectnus.LoginActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}
