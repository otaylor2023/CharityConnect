package com.femmehacks.charityconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.femmehacks.charityconnect.databinding.ActivityMainBinding;
import com.femmehacks.charityconnect.storage.EventPOJO;

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

                        TextView locationDetail = rootView.findViewById(R.id.detail_location);
                        locationDetail.setText("Location: " + eventPOJO.getLocation());
                    }
                });


    }



}