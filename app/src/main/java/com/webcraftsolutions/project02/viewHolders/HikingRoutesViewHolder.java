package com.webcraftsolutions.project02.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.R;

public class HikingRoutesViewHolder extends RecyclerView.ViewHolder {

    public TextView hikingRouteNameTextView;
    public TextView hikingRoutePlaceTextView;
    public TextView difficultyRatingTextView;
    public TextView timeEstimateTextView;
    public TextView dangerLevelTextView;
    public TextView safetyTipsTextView;
    public TextView ratingTextView;

    public HikingRoutesViewHolder(@NonNull View itemView) {
        super(itemView);

        hikingRouteNameTextView = itemView.findViewById(R.id.hikingRouteNameTextView);
        hikingRoutePlaceTextView = itemView.findViewById(R.id.hikingRoutePlaceTextView);
        difficultyRatingTextView = itemView.findViewById(R.id.difficultyRatingTextView);
        timeEstimateTextView = itemView.findViewById(R.id.timeEstimateTextView);
        dangerLevelTextView = itemView.findViewById(R.id.dangerLevelTextView);
        safetyTipsTextView = itemView.findViewById(R.id.safetyTipsTextView);
        ratingTextView = itemView.findViewById(R.id.ratingTextView);
    }
}