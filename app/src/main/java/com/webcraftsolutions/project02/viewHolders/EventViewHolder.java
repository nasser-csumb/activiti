/**
 * Title: CST-338 Project 02: Activiti - Event View Holder
 * @author Noah deFer
 * Date Created: 4/22/2025
 * Description: View Holder Class for Event Entities.
 */
package com.webcraftsolutions.project02.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.R;
import com.webcraftsolutions.project02.database.entities.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

    // FIELDS

    private final TextView nameTextView;

    private final TextView descTextView;

    private final TextView dateTextView;

    private final TextView locationTextView;

    // METHODS

    public EventViewHolder(View itemView) {
        super(itemView);

        // Initialize Variables
        nameTextView = itemView.findViewById(R.id.eventItemNameTextView);
        descTextView = itemView.findViewById(R.id.eventItemDescTextView);
        dateTextView = itemView.findViewById(R.id.eventItemDateTextView);
        locationTextView = itemView.findViewById(R.id.eventItemLocationTextView);
    }

    /**
     * Sets the text of the TextViews based on Event data.
     * @param event The event used to set text.
     */
    public void bind(Event event) {
        nameTextView.setText(event.getName());
        descTextView.setText(event.getDescription());
        dateTextView.setText(String.format("%s @ %s", event.getDate(), event.getTime()));
        locationTextView.setText(event.getLocation());
    }

    // STATIC METHODS

    /**
     * Creates and returns a new EventViewHolder.
     * @param parent The parent
     * @return A new EventViewHolder
     */
    static EventViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

}
