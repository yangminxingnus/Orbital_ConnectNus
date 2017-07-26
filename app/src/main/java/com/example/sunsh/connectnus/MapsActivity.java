package com.example.sunsh.connectnus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private String date, category, desc, location, contact, username;

    TextView tvdate, tvcate, tvdesc, tvCont,tvEmail;

    Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        date = LostAndFoundActivity.date;
        category = LostAndFoundActivity.category;
        desc = LostAndFoundActivity.desc;
        location = LostAndFoundActivity.location;
        contact = LostAndFoundActivity.contact;
        username = LostAndFoundActivity.username;

        tvdate = (TextView)findViewById(R.id.textViewItemDate);
        tvcate = (TextView)findViewById(R.id.textViewItemCategory);
        tvdesc = (TextView)findViewById(R.id.textViewItemDesc);
        tvCont = (TextView)findViewById(R.id.textViewItemContact);
        tvEmail = (TextView)findViewById(R.id.textViewItemEmail);

        btn_delete = (Button)findViewById(R.id.buttonDeleteLnfPost);

        tvdate.setText("\nDate: "+date);
        tvcate.setText("\nItem category: "+category);
        tvdesc.setText("\nDetailed description: "+desc);
        tvCont.setText("\nContact: "+contact);

        String type = "get_email";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, LostAndFoundActivity.username);
        try {
            String result = backgroundWorker.get();
            String[] info_list = result.split("\t");
            tvEmail.setText("\nEmail: "+info_list[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapLNF);
        mapFragment.getMapAsync(this);

        DeleteButton();
    }

    public void DeleteButton() {
        if (LostAndFoundActivity.username.equals(LoginActivity.curr_user)) {
            btn_delete.setEnabled(true);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alt = new AlertDialog.Builder(MapsActivity.this);
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
            });
        }
    }

    public void DeletePost() throws ExecutionException, InterruptedException {
        String number = LostAndFoundActivity.str_id;
        String type = "delete_lnf_post";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, number);
        String result = backgroundWorker.get();
        if (result.equals("delete success")) {
            Toast.makeText(MapsActivity.this, "Your post has been deleted!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(MapsActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String type = "location_info";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, location);
        String result = null;
        try {
            result = backgroundWorker.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        final String[] coordinate = result.split("\t");
        Double latitude = Double.parseDouble(coordinate[0]);
        Double longtitude = Double.parseDouble(coordinate[1]);

        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(latitude,longtitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in "+location));
        mMap.addMarker(new MarkerOptions().position(latLng)).setVisible(true);

        // Move the camera instantly to location with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50));

        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
    }


}
