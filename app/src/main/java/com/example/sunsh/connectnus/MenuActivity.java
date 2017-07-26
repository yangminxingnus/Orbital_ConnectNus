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
import android.widget.Toast;

/**
 * Created by sunshengran on 5/7/2017.
 * This is the menu page. There are three functions displayed on this page: around me, lost and found, stuff exchange.
 */

public class MenuActivity extends AppCompatActivity {

    private static Button button_AM;
    private static Button button_LF;
    private static Button button_SE;
    private static Button button_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        button_AM = (Button)findViewById(R.id.buttonAM);
        button_LF = (Button)findViewById(R.id.buttonLF);
        button_SE = (Button)findViewById(R.id.buttonSE);
        button_info = (Button)findViewById(R.id.buttonInfo);

        ButtonAroundMe();
        ButtonLostAndFound();
        ButtonStuffExchange();
        ButtonUserInfo();
    }

    public  void ButtonAroundMe() {
        button_AM.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sunsh.connectnus.AroundMeActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    public  void ButtonLostAndFound() {
        button_LF.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sunsh.connectnus.LostAndFoundActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    public  void ButtonStuffExchange() {
        button_SE.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sunsh.connectnus.StuffExchangeActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    //Click to show the detailed description of each feature so that the users can easily know how to use them
    public void ButtonUserInfo() {

        button_info.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.sunsh.connectnus.UserInformationActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.action_backtologin) {
            Intent intent = new Intent("com.example.sunsh.connectnus.LoginActivity");
            startActivity(intent);
        }
        else if (res_id==R.id.action_user) {
            AlertDialog.Builder alt = new AlertDialog.Builder(this);
            String text = "You have login as \""+LoginActivity.curr_user+"\".\n\nClick 'Logout' to logout the current account.";
            alt.setMessage(text)
                    .setNegativeButton("Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent("com.example.sunsh.connectnus.LoginActivity");
                            startActivity(intent);
                        }
                    })
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
