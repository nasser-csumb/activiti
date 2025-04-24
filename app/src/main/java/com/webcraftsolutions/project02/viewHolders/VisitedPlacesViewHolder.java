/**
 * Title: Project 02: Activiti - Visited Places
 * File: VisitedPlacesViewHolder.java - Implementation of VisitedPlacesViewHolder
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: The viewHolder for visitedPlaces.
 */

package com.webcraftsolutions.project02.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webcraftsolutions.project02.R;
import com.webcraftsolutions.project02.database.entities.VisitedPlaces;

public class VisitedPlacesViewHolder extends RecyclerView.ViewHolder {
    private TextView placeNameTextView;
    private TextView placeCategoryTextView;
    private TextView placeNotesTextView;
    private TextView placeDateVisitedTextView;
    private ImageView placePhotoImageView;

    public VisitedPlacesViewHolder(@NonNull View itemView) {
        super(itemView);
        placeNameTextView = itemView.findViewById(R.id.placeNameTextView);
        placeCategoryTextView = itemView.findViewById(R.id.placeCategoryTextView);
        placeNotesTextView = itemView.findViewById(R.id.placeNotesTextView);
        placeDateVisitedTextView = itemView.findViewById(R.id.placeDateVisitedTextView);
        placePhotoImageView = itemView.findViewById(R.id.placePhotoImageView);
    }

    public void bind(VisitedPlaces visitedPlace) {
        placeNameTextView.setText(visitedPlace.getName());
        placeCategoryTextView.setText(String.format("Category: %s", visitedPlace.getCategory()));
        placeNotesTextView.setText(String.format("Notes: %s", visitedPlace.getNotes()));
        placeDateVisitedTextView.setText(String.format("Visited: %s", visitedPlace.getDateVisited()));

        // I learned this at https://stackoverflow.com/questions/41324132/how-set-image-into-imageview-using-glide-and-a-uri
        // The following was inspired by the above link to implement a way to get the image.
        String imageUri = visitedPlace.getPhotoUri();
        if (imageUri != null && !imageUri.isEmpty()) {
            Glide.with(itemView.getContext())
                    .load(imageUri)
                    .into(placePhotoImageView);
        }
    }

    public static VisitedPlacesViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.visited_place_item, parent, false);
        return new VisitedPlacesViewHolder(view);
    }
}