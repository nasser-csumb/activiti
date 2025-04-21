package com.webcraftsolutions.project02.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.webcraftsolutions.project02.R;
import com.webcraftsolutions.project02.database.entities.CardioWorkout;

import java.util.ArrayList;

public class CardioAdapter extends RecyclerView.Adapter<CardioAdapter.CardioViewHolder> {

    private ArrayList<CardioWorkout> cardioList = new ArrayList<>();
    private final OnDeleteClickListener deleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(CardioWorkout workout);
    }

    public CardioAdapter(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    public void setData(ArrayList<CardioWorkout> list) {
        this.cardioList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardio_list_item, parent, false);
        return new CardioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardioViewHolder holder, int position) {
        CardioWorkout item = cardioList.get(position);
        holder.text.setText(item.getType() + " - " + item.getDurationMinutes() + " minutes - " + item.getIntensity());

        holder.deleteIcon.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION && deleteClickListener != null) {
                deleteClickListener.onDeleteClick(cardioList.get(adapterPosition));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardioList.size();
    }

    static class CardioViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView deleteIcon;

        CardioViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.cardioItemText);
            deleteIcon = itemView.findViewById(R.id.cardioDeleteIcon);
        }
    }
}
