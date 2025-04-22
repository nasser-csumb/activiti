/**
 * Title: CST-338 Project 02: Activiti - Event Adapter
 * @author Noah deFer
 * Date Created: 4/22/2025
 * Description: Adapater class for Event entities.
 */
package com.webcraftsolutions.project02.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.webcraftsolutions.project02.database.entities.Event;
import com.webcraftsolutions.project02.viewHolders.EventViewHolder;

public class EventAdapter extends ListAdapter<Event, EventViewHolder> {

    // METHODS

    public EventAdapter(@NonNull DiffUtil.ItemCallback<Event> diffCallBack) {
        super(diffCallBack);
    }

    /**
     * Calls EventViewHolder.create method and returns the result.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new EventViewHolder.
     */
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return EventViewHolder.create(parent);
    }

    /**
     * Calls the bind method of the passed in EventViewHolder.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event current = getItem(position);
        holder.bind(current);
    }

    /**
     * Used to compare different Event instances to each other.
     */
    public static class EventDiff extends DiffUtil.ItemCallback<Event> {

        /**
         * Compares the eventId fields of two Event instances.
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return Whether the Events have the same eventId (true) or not (false).
         */
        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getEventId() == newItem.getEventId();
        }

        /**
         * Compares two Event instances.
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return Whether the Events are the same (true) or not (false).
         */
        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.equals(newItem);
        }
    }
}
