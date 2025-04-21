package com.webcraftsolutions.project02.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.webcraftsolutions.project02.database.ActivitiDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Entity(tableName = ActivitiDatabase.VISITED_PLACES_TABLE)
public class VisitedPlaces {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private String name;
    private String category;
    private String notes;
    private String photoUri;
    private long dateVisitedMillis;

    public VisitedPlaces(int userId, String name, String category, String notes, String photoUri, long dateVisitedMillis) {
        this.userId = userId;
        this.name = name;
        this.category = category;
        this.notes = notes;
        this.photoUri = photoUri;
        this.dateVisitedMillis = dateVisitedMillis;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VisitedPlaces that = (VisitedPlaces) o;
        return id == that.id && userId == that.userId && dateVisitedMillis == that.dateVisitedMillis && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(notes, that.notes) && Objects.equals(photoUri, that.photoUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, category, notes, photoUri, dateVisitedMillis);
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    // I learned this at https://www.geeksforgeeks.org/program-to-convert-milliseconds-to-a-date-format-in-java/
    // The following was inspired from the link above
    public String getDateVisited() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date(dateVisitedMillis);
        return sdf.format(date);
    }

    public long getDateVisitedMillis() {
        return dateVisitedMillis;
    }

    public void setDateVisitedMillis(long dateVisited) {
        this.dateVisitedMillis = dateVisited;
    }
}
