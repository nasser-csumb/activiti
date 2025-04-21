package com.webcraftsolutions.project02.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.webcraftsolutions.project02.database.entities.VisitedPlaces;

public class VisitedPlacesAdapter extends ListAdapter<VisitedPlaces, VisitedPlacesViewHolder> {

    public VisitedPlacesAdapter(@NonNull DiffUtil.ItemCallback<VisitedPlaces> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public VisitedPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VisitedPlacesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitedPlacesViewHolder holder, int position) {
        VisitedPlaces place = getItem(position);
        holder.bind(place);
    }

    public static class VisitedPlacesDiff extends DiffUtil.ItemCallback<VisitedPlaces> {
        @Override
        public boolean areItemsTheSame(@NonNull VisitedPlaces oldItem, @NonNull VisitedPlaces newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull VisitedPlaces oldItem, @NonNull VisitedPlaces newItem) {
            return oldItem.equals(newItem);
        }
    }
}
