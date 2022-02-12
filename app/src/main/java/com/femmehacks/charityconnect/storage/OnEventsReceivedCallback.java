package com.femmehacks.charityconnect.storage;

import androidx.annotation.NonNull;


import java.util.List;

public interface OnEventsReceivedCallback {

    void onReceived(@NonNull List<EventPOJO> eventPOJOList);
}
