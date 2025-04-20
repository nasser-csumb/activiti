package com.webcraftsolutions.project02.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.R;
import com.webcraftsolutions.project02.database.entities.WeightLiftingWorkout;

import java.util.ArrayList;

public class LiftingAdapter extends RecyclerView.Adapter<LiftingAdapter.LiftingViewHolder> {

    private ArrayList<WeightLiftingWorkout> liftList = new ArrayList<>();
    private final OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(WeightLiftingWorkout workout);
    }

    public LiftingAdapter(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    public void setData(ArrayList<WeightLiftingWorkout> list) {
        this.liftList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LiftingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lifting_list_item, parent, false);
        return new LiftingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LiftingViewHolder holder, int position) {
        WeightLiftingWorkout item = liftList.get(position);
        holder.text.setText(item.getExerciseName() + " - " + item.getSets() + " sets - " + item.getTotalReps() + " reps - " + item.getDurationMinutes() + " minutes");

        holder.deleteIcon.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && deleteClickListener != null) {
                deleteClickListener.onDeleteClick(liftList.get(adapterPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return liftList.size();
    }

    static class LiftingViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView deleteIcon;

        LiftingViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.liftingItemText);
            deleteIcon = itemView.findViewById(R.id.liftingDeleteIcon);
        }
    }
}
