/**
 * Title: Project 02: Activiti - Outdoors
 * File: OutdoorsAdapter.java - Implementation of OutdoorsAdapter
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: The adapter for outdoors.
 */

package com.webcraftsolutions.project02.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.webcraftsolutions.project02.database.entities.Outdoors;

public class OutdoorsAdapter extends ListAdapter<Outdoors, OutdoorsViewHolder> {

    public OutdoorsAdapter(@NonNull DiffUtil.ItemCallback<Outdoors> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public OutdoorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return OutdoorsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull OutdoorsViewHolder holder, int position) {
        Outdoors current = getItem(position);
        holder.bind(current);
    }

    public static class OutdoorsDiff extends DiffUtil.ItemCallback<Outdoors> {
        @Override
        public boolean areItemsTheSame(@NonNull Outdoors oldItem, @NonNull Outdoors newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Outdoors oldItem, @NonNull Outdoors newItem) {
            return oldItem.equals(newItem);
        }
    }
}
