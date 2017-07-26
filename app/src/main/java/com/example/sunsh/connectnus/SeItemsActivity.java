package com.example.sunsh.connectnus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class SeItemsActivity extends AppCompatActivity {

    public static String number = "";

    TextView tvdate,tvcate,tvdesc,tvprice,tvlink,tvaddr,tvcont,tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_items);

        tvdate = (TextView)findViewById(R.id.textViewSEDate);
        tvcate = (TextView)findViewById(R.id.textViewSECategory);
        tvdesc = (TextView)findViewById(R.id.textViewSEDescription);
        tvprice = (TextView)findViewById(R.id.textViewSEPrice);
        tvlink = (TextView)findViewById(R.id.textViewSELink);
        tvaddr = (TextView)findViewById(R.id.textViewSEAddress);
        tvcont = (TextView)findViewById(R.id.textViewSEContact);
        tvemail = (TextView)findViewById(R.id.textViewSEEmail);

        tvdate.setText("\n\n"+StuffExchangeActivity.date);
        tvcate.setText("\nCategory: " + StuffExchangeActivity.category);
        tvdesc.setText("\n"+StuffExchangeActivity.desc);
        tvprice.setText("\nPrice: " + StuffExchangeActivity.price);
        tvaddr.setText("\nAddress of owner:\n"+StuffExchangeActivity.address);
        tvcont.setText("\nContact: "+StuffExchangeActivity.contact);

        String type = "get_email";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, StuffExchangeActivity.username);
        try {
            String result = backgroundWorker.get();
            String[] info_list = result.split("\t");
            tvemail.setText("\nEmail: "+info_list[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        tvlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(StuffExchangeActivity.pic_link));
                startActivity(intent);
            }
        });
    }

    public void DeletePost () throws ExecutionException, InterruptedException {
        String number = StuffExchangeActivity.str_id;
        String type = "delete_se_post";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, number);
        String result = backgroundWorker.get();
        if (result.equals("delete success")) {
            Toast.makeText(SeItemsActivity.this, "Your post has been deleted!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(SeItemsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_se_items,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.action_backfrom_se_items) {
            Intent intent = new Intent("com.example.sunsh.connectnus.StuffExchangeActivity");
            startActivity(intent);
        }
        else if (res_id==R.id.action_delete_se_post) {
            if (LoginActivity.curr_user.equals(StuffExchangeActivity.username)) {
                AlertDialog.Builder alt = new AlertDialog.Builder(SeItemsActivity.this);
                String text = "Are you sure you want to delete the post?";
                alt.setMessage(text)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Connect to MySQL database and delete the current post
                                try {
                                    DeletePost();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                alt.show();
            }
            else {
                Toast.makeText(SeItemsActivity.this, "You cannot delete other user's post!", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}
