package com.webcraftsolutions.project02.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.R;
import com.webcraftsolutions.project02.database.entities.Outdoors;

public class OutdoorsViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView;
    private final TextView locationTextView;
    private final TextView activityTypeTextView;
    private final TextView durationTextView;
    private final TextView notesTextView;
    private final TextView ratingTextView;

    public OutdoorsViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.outdoorNameTextView);
        locationTextView = itemView.findViewById(R.id.outdoorLocationTextView);
        activityTypeTextView = itemView.findViewById(R.id.outdoorActivityTypeTextView);
        durationTextView = itemView.findViewById(R.id.outdoorDurationTextView);
        notesTextView = itemView.findViewById(R.id.outdoorNotesTextView);
        ratingTextView = itemView.findViewById(R.id.outdoorRatingTextView);
    }

    public void bind(Outdoors outdoors) {
        nameTextView.setText(outdoors.getName());
        locationTextView.setText(String.format("Location: %s", outdoors.getLocation()));
        activityTypeTextView.setText(String.format("Type: %s", outdoors.getActivityType()));
        durationTextView.setText(String.format("Duration: %s min", outdoors.getDurationMinutes()));
        notesTextView.setText(String.format("Notes: %s", outdoors.getNotes()));
        ratingTextView.setText(String.format("Rating: %s", outdoors.getRating()));
    }

    static OutdoorsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.outdoor_item, parent, false);
        return new OutdoorsViewHolder(view);
    }
}
