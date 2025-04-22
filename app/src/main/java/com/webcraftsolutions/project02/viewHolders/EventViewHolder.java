/**
 * Title: CST-338 Project 02: Activiti - Event View Holder
 * @author Noah deFer
 * Date Created: 4/22/2025
 * Description: View Holder Class for Event Entities.
 */
package com.webcraftsolutions.project02.viewHolders;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.EventActivity;
import com.webcraftsolutions.project02.EventCreateActivity;
import com.webcraftsolutions.project02.R;
import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

    // FIELDS

    // The EventActivity activity
    private final EventActivity eventActivity;

    // The repository.
    private final ActivitiRepository repository;

    // TEXTVIEW

    private final TextView nameTextView;

    private final TextView descTextView;

    private final TextView dateTextView;

    private final TextView locationTextView;

    private final TextView editTextView;

    private final TextView deleteTextView;

    // METHODS

    public EventViewHolder(View itemView, EventActivity eventActivity) {
        super(itemView);

        // Initialize Variables
        this.eventActivity = eventActivity;
        nameTextView = itemView.findViewById(R.id.eventItemNameTextView);
        descTextView = itemView.findViewById(R.id.eventItemDescTextView);
        dateTextView = itemView.findViewById(R.id.eventItemDateTextView);
        locationTextView = itemView.findViewById(R.id.eventItemLocationTextView);
        editTextView = itemView.findViewById(R.id.eventItemEditTextView);
        deleteTextView = itemView.findViewById(R.id.eventItemDeleteTextView);

        // Get Repository
        repository = ActivitiRepository.getRepository(eventActivity.getApplication());
    }

    /**
     * Sets the text of the TextViews based on Event data.
     * @param event The event used to set text.
     */
    public void bind(Event event) {

        // Set TextView text.
        nameTextView.setText(event.getName());
        descTextView.setText(event.getDescription());
        dateTextView.setText(String.format("%s @ %s", event.getDate(), event.getTime()));
        locationTextView.setText(event.getLocation());

        // Set OnClickListener for 'Edit Text'
        editTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EventCreateActivity.eventCreateActivityIntentFactory(
                        eventActivity.getApplicationContext(), event.getUserId(), event.getEventId());
                eventActivity.startActivity(intent);
            }
        });

        // Set OnClickListener for 'Delete Text'
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete Event from Repository
                assert repository != null;
                repository.deleteEvent(event);
            }
        });
    }

    // STATIC METHODS

    /**
     * Creates and returns a new EventViewHolder.
     * @param parent The parent
     * @param eventActivity The EventActivity activity
     * @return A new EventViewHolder
     */
    static EventViewHolder create(ViewGroup parent, EventActivity eventActivity) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view, eventActivity);
    }

}
