/**
 * Title: Project 02: Activiti - Outdoors
 * File: Outdoors.java - Implementation of Outdoors
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: This stores the outdoors data.
 */

package com.webcraftsolutions.project02.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.webcraftsolutions.project02.database.ActivitiDatabase;

import java.util.Objects;

@Entity(tableName = ActivitiDatabase.OUTDOORS_TABLE)
public class Outdoors {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private String name;
    private String location;
    private String activityType;
    private int durationMinutes;
    private String notes;
    private double rating;

    public Outdoors( String name, String location, String activityType, int durationMinutes, String notes, double rating, int userId) {
        this.name = name;
        this.location = location;
        this.activityType = activityType;
        this.durationMinutes = durationMinutes;
        this.notes = notes;
        this.rating = rating;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Outdoors outdoors = (Outdoors) o;
        return id == outdoors.id && userId == outdoors.userId && durationMinutes == outdoors.durationMinutes && Double.compare(rating, outdoors.rating) == 0 && Objects.equals(name, outdoors.name) && Objects.equals(location, outdoors.location) && Objects.equals(activityType, outdoors.activityType) && Objects.equals(notes, outdoors.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, location, activityType, durationMinutes, notes, rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public double getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
