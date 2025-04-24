/**
 * Title: Project 02: Activiti - Visited Places
 * File: VisitedPlacesAdapter.java - Implementation of VisitedPlacesAdapter
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: The adapter for visited places.
 */

package com.webcraftsolutions.project02.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.webcraftsolutions.project02.database.entities.VisitedPlaces;

import java.util.ArrayList;
import java.util.List;

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
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull VisitedPlaces oldItem, @NonNull VisitedPlaces newItem) {
            return oldItem.equals(newItem);
        }
    }
}
