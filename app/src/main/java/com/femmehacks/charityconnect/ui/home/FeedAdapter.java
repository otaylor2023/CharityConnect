package com.femmehacks.charityconnect.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.femmehacks.charityconnect.R;
import com.femmehacks.charityconnect.storage.EventPOJO;
import com.femmehacks.charityconnect.storage.OnEventsReceivedCallback;
import com.femmehacks.charityconnect.storage.ServerStorage;

import org.w3c.dom.Text;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<EventPOJO> eventList;

    public FeedAdapter(List<EventPOJO> dataSet) {
        eventList = dataSet;
        ServerStorage.attachEventPOJOListener(new OnEventsReceivedCallback() {
            @Override
            public void onReceived(@NonNull List<EventPOJO> eventPOJOList) {
                updateEventList(eventPOJOList);
            }
        });
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_info, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        EventPOJO eventData = eventList.get(position);
        viewHolder.getEventTitleBox().setText(eventData.getTitle());
        viewHolder.getEventTitleBox().setText(String.format("Date: %s", eventData.getDate()));
        viewHolder.getEventTitleBox().setText(String.format("Type: %s", eventData.getDriveType()));
        viewHolder.getEventTitleBox().setText(String.format("Location: %s", eventData.getLocation()));
        viewHolder.getEventTitleBox().setText(String.format("Home pickup? %s", eventData.hasPickup() ? "Yes" : "No"));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void updateEventList(List<EventPOJO> markerDataList) {
        this.eventList = markerDataList;
        notifyDataSetChanged();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView eventTitleBox;
        private final TextView dateBox;
        private final TextView eventTypeBox;
        private final TextView locationBox;
        private final TextView pickUpBox;
        private final TextView dropOffBox;
        private final View view;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            // Define click listener for the ViewHolder's View

            eventTitleBox = view.findViewById(R.id.event_title);
            dateBox = view.findViewById(R.id.event_date);
            eventTypeBox = view.findViewById(R.id.event_type);
            locationBox = view.findViewById(R.id.event_location);
            pickUpBox = view.findViewById(R.id.has_pick_up);
            dropOffBox = view.findViewById(R.id.has_drop_off);

        }

        public TextView getEventTitleBox() {
            return eventTitleBox;
        }

        public TextView getDateBox() {
            return dateBox;
        }

        public TextView getEventTypeBox() {
            return eventTypeBox;
        }

        public TextView getLocationBox() {
            return locationBox;
        }

        public TextView getPickUpBox() {
            return pickUpBox;
        }

        public TextView getDropOffBox() {
            return dropOffBox;
        }

    }



}

