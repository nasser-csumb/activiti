package com.webcraftsolutions.project02.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.webcraftsolutions.project02.database.entities.HikingRoutes;

public class HikingRoutesAdapter extends ListAdapter<HikingRoutes, HikingRoutesViewHolder> {

    public HikingRoutesAdapter(@NonNull DiffUtil.ItemCallback<HikingRoutes> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public HikingRoutesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return HikingRoutesViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull HikingRoutesViewHolder holder, int position) {
        HikingRoutes current = getItem(position);
        holder.bind(current);
    }

    public static class HikingRoutesDiff extends DiffUtil.ItemCallback<HikingRoutes> {
        @Override
        public boolean areItemsTheSame(@NonNull HikingRoutes oldItem, @NonNull HikingRoutes newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull HikingRoutes oldItem, @NonNull HikingRoutes newItem) {
            return oldItem.equals(newItem);
        }
    }
}
