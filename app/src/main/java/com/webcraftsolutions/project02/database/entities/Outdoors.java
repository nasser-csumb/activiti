package com.webcraftsolutions.project02.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "outdoors_activities")
public class Outdoors {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public String location;
    public String activityType;
    public int durationMinutes;
    public String notes;
    public float rating;

    public Outdoors(@NonNull String name, String location, String activityType,
                            int durationMinutes, String notes, float rating) {
        this.name = name;
        this.location = location;
        this.activityType = activityType;
        this.durationMinutes = durationMinutes;
        this.notes = notes;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Outdoors outdoors = (Outdoors) o;
        return id == outdoors.id && durationMinutes == outdoors.durationMinutes && Float.compare(rating, outdoors.rating) == 0 && Objects.equals(name, outdoors.name) && Objects.equals(location, outdoors.location) && Objects.equals(activityType, outdoors.activityType) && Objects.equals(notes, outdoors.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, activityType, durationMinutes, notes, rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
