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
    private final TextView ratingTextView;
    private final TextView notesTextView;

    public OutdoorsViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.outdoorsNameTextView);
        locationTextView = itemView.findViewById(R.id.outdoorsLocationTextView);
        activityTypeTextView = itemView.findViewById(R.id.outdoorsTypeTextView);
        durationTextView = itemView.findViewById(R.id.outdoorsDurationTextView);
        ratingTextView = itemView.findViewById(R.id.outdoorsRatingTextView);
        notesTextView = itemView.findViewById(R.id.outdoorsNotesTextView);
    }

    public void bind(Outdoors outdoors) {
        nameTextView.setText(outdoors.getName());
        locationTextView.setText(String.format("Location: %s", outdoors.getLocation()));
        activityTypeTextView.setText(String.format("Type: %s", outdoors.getActivityType()));
        durationTextView.setText(String.format("Duration: %s min", outdoors.getDurationMinutes()));
        ratingTextView.setText(String.format("Rating: %s", outdoors.getRating()));
        notesTextView.setText(String.format("Notes: %s", outdoors.getNotes()));
    }

    static OutdoorsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.outdoor_item, parent, false); // Replace with actual layout file if necessary
        return new OutdoorsViewHolder(view);
    }
}
