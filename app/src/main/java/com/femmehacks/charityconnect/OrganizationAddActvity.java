package com.femmehacks.charityconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.femmehacks.charityconnect.storage.EventPOJO;
import com.femmehacks.charityconnect.storage.OrgPOJO;
import com.femmehacks.charityconnect.storage.ServerStorage;

public class OrganizationAddActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_add_actvity);

        final View rootView = getWindow().getDecorView().getRootView();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        rootView.findViewById(R.id.addEventButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String title = ((EditText)rootView.findViewById(R.id.editTitle)).getText().toString();
                                String date = ((EditText)rootView.findViewById(R.id.editDate)).getText().toString();
                                String type = ((EditText)rootView.findViewById(R.id.editType)).getText().toString();
                                String location = ((EditText)rootView.findViewById(R.id.editLocation)).getText().toString();
                                String description = ((EditText)rootView.findViewById(R.id.editDescription)).getText().toString();
                                OrgPOJO orgPojo = (OrgPOJO) getIntent().getSerializableExtra("org");

                                EventPOJO eventPOJO = new EventPOJO(title, date, type, false, location, description, orgPojo);
                                ServerStorage.addEvent(eventPOJO);

                            }
                        });
                    }
                });
    }

}