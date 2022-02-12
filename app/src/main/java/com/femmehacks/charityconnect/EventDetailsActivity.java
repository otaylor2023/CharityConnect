package com.femmehacks.charityconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.femmehacks.charityconnect.databinding.ActivityMainBinding;
import com.femmehacks.charityconnect.storage.EventPOJO;
import com.femmehacks.charityconnect.storage.OrgPOJO;

public class EventDetailsActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static String TAG = "femme.hacks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//

        Log.d(TAG, "eventPOJO.toString()");

        final View rootView = getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {

                        //by now all views will be displayed with correct values
                        EventPOJO eventPOJO = (EventPOJO) getIntent().getSerializableExtra("event");
                        OrgPOJO orgPOJO = eventPOJO.getOrgPOJO();

                        TextView locationDetail = rootView.findViewById(R.id.detail_location);
                        locationDetail.setText("Location: " + eventPOJO.getLocation());

                        TextView phoneNumberDetail = rootView.findViewById(R.id.org_phone);
                        phoneNumberDetail.setText("Phone #: " + orgPOJO.getPhoneNumber());

                        TextView emailDetail = rootView.findViewById(R.id.org_email);
                        emailDetail.setText("Email: " + orgPOJO.getEmail());

                        TextView websiteDetail = rootView.findViewById(R.id.org_site);
                        websiteDetail.setText("Website: " + orgPOJO.getWebsite());

                        TextView dateDetail = rootView.findViewById(R.id.detail_date);
                        dateDetail.setText("Date: " + eventPOJO.getDate());

                        TextView driveTypeDetail = rootView.findViewById(R.id.detail_driveType);
                        driveTypeDetail.setText("Drive Type: " + eventPOJO.getDriveType());

                        TextView descriptionDetail = rootView.findViewById(R.id.detail_desc);
                        descriptionDetail.setText("Event Description: " + eventPOJO.getDescription());
                    }
                });


    }



}