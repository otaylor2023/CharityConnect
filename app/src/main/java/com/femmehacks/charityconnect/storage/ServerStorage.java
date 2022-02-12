package com.femmehacks.charityconnect.storage;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerStorage {

    private static final String TAG = "mercury.markerstorage";

    public static void addEvent(EventPOJO eventPOJO) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        String key = reference.child(Storage.EVENT_STORAGE.path).push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put(key, eventPOJO);
        reference.child(Storage.EVENT_STORAGE.path).updateChildren(map);

    }


    public static void getEvents(OnEventsReceivedCallback eventsReceivedCallback, boolean isRetry) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        reference.child(Storage.EVENT_STORAGE.path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventsReceivedCallback.onReceived(extractEventData(snapshot));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                if (!isRetry) {
                    getEvents(eventsReceivedCallback, true);
                }
            }
        });
    }

    private static List<EventPOJO> extractEventData(DataSnapshot dataSnapshot) {
        List<EventPOJO> eventPOJOList = new ArrayList<>();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Log.d(TAG, snapshot.getKey() + " " + snapshot.getValue().toString());

            EventPOJO eventPOJO = snapshot.getValue(EventPOJO.class);
            eventPOJOList.add(new EventPOJO(eventPOJO));

//                    eventPOJOList.addAll(data.values());
//                    System.out.println(eventPOJOList);
        }
        return eventPOJOList;
    }

    public static void attachEventPOJOListener(OnEventsReceivedCallback eventsReceivedCallback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        reference.child(Storage.EVENT_STORAGE.path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eventsReceivedCallback.onReceived(extractEventData(snapshot));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static void attemptSignIn(String username, String unhashedPassword, OnLoginCheckedCallback loginCheckedCallback) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        Query query = reference.child(Storage.USER_LOGIN_STORAGE.path).orderByKey();
        Task<DataSnapshot> snapshotTask = query.get();
        snapshotTask.addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, UserPOJO> userDataList;
                Log.d(TAG, "success");
                GenericTypeIndicator<HashMap<String, UserPOJO>> typeIndicator = new GenericTypeIndicator<HashMap<String, UserPOJO>>() {};
                userDataList = dataSnapshot.getValue(typeIndicator);
                UserPOJO match = ServerStorage.checkForMatch(username, unhashedPassword, userDataList);
                loginCheckedCallback.onLoginChecked(match);
            }
        });
    }

    /**
     * Checks the user data list for a match and returns the match or null if there isnt one
     * @param username the username to check
     * @param unhashedPassword the unhashed password
     * @param userDataList the user data list
     * @return the match if a match is found, otherwise a null object
     */
    private static UserPOJO checkForMatch(String username, String unhashedPassword, HashMap<String, UserPOJO> userDataList) {
        if (userDataList != null) {
            UserPOJO match = userDataList.get(username);
            if (match != null && match.checkPassword(unhashedPassword)) {
                return match;
            }
        }
        return null;
    }
//
    public static void addUser(UserPOJO userPOJO) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();
        String key = userPOJO.getEmail();
        Map<String, Object> map = new HashMap<>();
        map.put(key, userPOJO);
        reference.child(Storage.USER_LOGIN_STORAGE.path).updateChildren(map);
    }

    public enum Storage {
        EVENT_STORAGE("eventStorage"),
        USER_LOGIN_STORAGE("userLoginStorage");

        public final String path;

        Storage(String path) {
            this.path = path;
        }
    }
}

