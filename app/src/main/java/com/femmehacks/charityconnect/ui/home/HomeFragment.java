package com.femmehacks.charityconnect.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.femmehacks.charityconnect.EventDetailsActivity;
import com.femmehacks.charityconnect.R;
import com.femmehacks.charityconnect.databinding.FragmentHomeBinding;
import com.femmehacks.charityconnect.storage.DriveType;
import com.femmehacks.charityconnect.storage.EventPOJO;
import com.femmehacks.charityconnect.storage.OnEventsReceivedCallback;
import com.femmehacks.charityconnect.storage.OrgPOJO;
import com.femmehacks.charityconnect.storage.ServerStorage;

import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private final String TAG = "driveconnect.home";

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private FeedAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recyclerview);
        ServerStorage.getEvents(new OnEventsReceivedCallback() {
            @Override
            public void onReceived(@NonNull List<EventPOJO> eventPOJOList) {

                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new FeedAdapter(eventPOJOList);
                recyclerView.setAdapter(adapter);
                Log.d(TAG, "item number: " + adapter.getItemCount());
            }
        }, false);

        binding.testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Test button pressed");
                testButtonPressed();
//                Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
//                startActivity(intent);
                //dataList = LocalStorage.getContactList(root)
            }
        });

        return root;
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void testButtonPressed() {
        OrgPOJO orgPOJO = new OrgPOJO("org", "982", "t333@gmail.com", "kff.com", "kfjf.d.");
        EventPOJO eventPOJO = new EventPOJO("Test 1", new Date(), DriveType.CLOTHES, true, "street", "description",orgPOJO);
        ServerStorage.addEvent(eventPOJO);

        ServerStorage.getEvents(new OnEventsReceivedCallback() {
            @Override
            public void onReceived(@NonNull List<EventPOJO> eventPOJOList) {
                Log.d(TAG, eventPOJOList.toString());
            }
        }, false);

    }


}