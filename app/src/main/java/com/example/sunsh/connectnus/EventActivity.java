package com.example.sunsh.connectnus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    TextView tvDate, tvTitle, tvDesc, tvUrl;
    private String date, title, desc, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        date = AroundMeActivity.date;
        title = AroundMeActivity.title;
        desc = AroundMeActivity.desc;
        url = AroundMeActivity.url;

        tvDate = (TextView)findViewById(R.id.textViewAMDate);
        tvTitle = (TextView)findViewById(R.id.textViewAMTitle);
        tvDesc = (TextView)findViewById(R.id.textViewAMDescription);
        tvUrl = (TextView)findViewById(R.id.textViewAMUrl);

        tvDate.setText("\n\n"+date);
        tvTitle.setText("\n"+title);
        tvDesc.setText("\n"+desc);
        tvUrl.setText("\n"+url);

        tvUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_share,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.action_backto_aroundme) {
            Intent intent = new Intent("com.example.sunsh.connectnus.AroundMeActivity");
            startActivity(intent);
        }
        else if (res_id==R.id.action_share_event) {

            String shareBody = title+"\n"+url;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "My App");
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, "Share via"));
        }
        return true;
    }
}
