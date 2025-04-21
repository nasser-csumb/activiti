package com.webcraftsolutions.project02.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.R;
import com.webcraftsolutions.project02.database.entities.HikingRoutes;

public class HikingRoutesViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView;
    private final TextView placeTextView;
    private final TextView difficultyTextView;
    private final TextView timeEstimateTextView;
    private final TextView dangerLevelTextView;
    private final TextView safetyTipsTextView;
    private final TextView ratingTextView;

    public HikingRoutesViewHolder(View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.hikingRouteNameTextView);
        placeTextView = itemView.findViewById(R.id.hikingRoutePlaceTextView);
        difficultyTextView = itemView.findViewById(R.id.difficultyRatingTextView);
        timeEstimateTextView = itemView.findViewById(R.id.timeEstimateTextView);
        dangerLevelTextView = itemView.findViewById(R.id.dangerLevelTextView);
        safetyTipsTextView = itemView.findViewById(R.id.safetyTipsTextView);
        ratingTextView = itemView.findViewById(R.id.ratingTextView);
    }

    public void bind(HikingRoutes hikingRoutes) {
        nameTextView.setText(hikingRoutes.getName());
        placeTextView.setText(hikingRoutes.getPlace());
        difficultyTextView.setText(String.format("Difficulty: %s", hikingRoutes.getDifficulty()));
        timeEstimateTextView.setText(String.format("Time: %s", hikingRoutes.getTimeEstimate()));
        dangerLevelTextView.setText(String.format("Danger Level: %s", hikingRoutes.getDangerLevel()));
        safetyTipsTextView.setText(String.format("Safety Tips: %s", hikingRoutes.getSafetyTips()));
        ratingTextView.setText(String.format("Rating: %s", hikingRoutes.getRating()));
    }

    static HikingRoutesViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hiking_route_item, parent, false);
        return new HikingRoutesViewHolder(view);
    }
}