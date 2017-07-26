package com.example.sunsh.connectnus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AroundMeActivity extends AppCompatActivity {

    private static ListView list_view;

    public static String date = "";
    public static String title = "";
    public static String desc = "";
    public static String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_around_me);

        try {
            listView();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void listView() throws ExecutionException, InterruptedException {

        list_view = (ListView)findViewById(R.id.lvAM);

        String type = "aroundme_info";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type);
        String result = backgroundWorker.get();
        final String[] info_list = result.split("\t");

        final ArrayList<Integer> al_id = new ArrayList<Integer>();
        for (int i = 0;i<info_list.length;i=i+5) al_id.add(Integer.parseInt(info_list[i]));

        final ArrayList<String> al_date = new ArrayList<String>();
        for (int i = 1;i<info_list.length;i=i+5) al_date.add(info_list[i]);

        final ArrayList<String> al_title = new ArrayList<String>();
        for (int i = 2;i<info_list.length;i=i+5) al_title.add(info_list[i]);

        final ArrayList<String> al_description = new ArrayList<String>();
        for (int i = 3;i<info_list.length;i=i+5) al_description.add(info_list[i]);

        final ArrayList<String> al_url = new ArrayList<String>();
        for (int i = 4;i<info_list.length;i=i+5) al_url.add(info_list[i]);

        String[] show_list = new String[al_id.size()];
        for (int i=al_id.size()-1;i>=0;i--) show_list[al_id.size()-1-i]="\n"+al_title.get(i)+"\n\n"+al_date.get(i)+"\n";

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.aroundme_list,show_list);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        date = al_date.get(al_id.size()-position-1);
                        title = al_title.get(al_id.size()-position-1);
                        desc = al_description.get(al_id.size()-position-1);
                        url = al_url.get(al_id.size()-position-1);

                        Intent intent = new Intent("com.example.sunsh.connectnus.EventActivity");
                        startActivity(intent);

                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_aroundme,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id==R.id.action_backfrom_am) {
            Intent intent = new Intent("com.example.sunsh.connectnus.MenuActivity");
            startActivity(intent);
        }
        else if (res_id==R.id.action_refresh_aroundme) {
            try {
                listView();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
