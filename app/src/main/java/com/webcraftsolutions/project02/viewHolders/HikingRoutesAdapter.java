package com.webcraftsolutions.project02.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.R;
import com.webcraftsolutions.project02.database.entities.TravelExploration;

import java.util.ArrayList;
import java.util.List;

public class HikingRoutesAdapter extends RecyclerView.Adapter<HikingRoutesAdapter.HikingRoutesViewHolder> {

    private List<TravelExploration> hikingRoutes = new ArrayList<>();

    @NonNull
    @Override
    public HikingRoutesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hiking_route_item, parent, false);
        return new HikingRoutesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikingRoutesViewHolder holder, int position) {
        TravelExploration currentRoute = hikingRoutes.get(position);
        holder.bind(currentRoute);
    }

    @Override
    public int getItemCount() {
        return hikingRoutes.size();
    }

    public void setHikingRoutes(List<TravelExploration> hikingRoutes) {
        this.hikingRoutes = hikingRoutes;
        notifyDataSetChanged();
    }

    public static class HikingRoutesViewHolder extends RecyclerView.ViewHolder {

        private final TextView hikingRouteName;
        private final TextView hikingRoutePlace;
        private final TextView difficultyRating;
        private final TextView timeEstimate;
        private final TextView dangerLevel;

        public HikingRoutesViewHolder(@NonNull View itemView) {
            super(itemView);

            hikingRouteName = itemView.findViewById(R.id.hikingRouteNameTextView);
            hikingRoutePlace = itemView.findViewById(R.id.hikingRoutePlaceTextView);
            difficultyRating = itemView.findViewById(R.id.difficultyRatingTextView);
            timeEstimate = itemView.findViewById(R.id.timeEstimateTextView);
            dangerLevel = itemView.findViewById(R.id.dangerLevelTextView);
        }

        public void bind(TravelExploration travelExploration) {
            hikingRouteName.setText(travelExploration.getHikingRoute());
            hikingRoutePlace.setText(travelExploration.getVisitedPlaces());

            difficultyRating.setText("Difficulty: " + "Easy");
            timeEstimate.setText("Time: " + "2 hours");
            dangerLevel.setText("Danger: " + "Moderate");
        }
    }
}
