package com.webcraftsolutions.project02.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.webcraftsolutions.project02.database.ActivitiDatabase;

import java.util.Objects;

@Entity(tableName = ActivitiDatabase.HIKING_ROUTES_TABLE)
public class HikingRoutes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private String name;
    private String place;
    private int difficulty;
    private String timeEstimate;
    private String dangerLevel;
    private String safetyTips;
    private double rating;

    public HikingRoutes(double rating, String safetyTips, String dangerLevel, String timeEstimate, int difficulty, String place, String name, int userId) {
        this.rating = rating;
        this.safetyTips = safetyTips;
        this.dangerLevel = dangerLevel;
        this.timeEstimate = timeEstimate;
        this.difficulty = difficulty;
        this.place = place;
        this.name = name;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HikingRoutes that = (HikingRoutes) o;
        return id == that.id && userId == that.userId && difficulty == that.difficulty && Double.compare(rating, that.rating) == 0 && Objects.equals(name, that.name) && Objects.equals(place, that.place) && Objects.equals(timeEstimate, that.timeEstimate) && Objects.equals(dangerLevel, that.dangerLevel) && Objects.equals(safetyTips, that.safetyTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, place, difficulty, timeEstimate, dangerLevel, safetyTips, rating);
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public String getTimeEstimate() { return timeEstimate; }
    public void setTimeEstimate(String timeEstimate) { this.timeEstimate = timeEstimate; }

    public String getDangerLevel() { return dangerLevel; }
    public void setDangerLevel(String dangerLevel) { this.dangerLevel = dangerLevel; }

    public String getSafetyTips() { return safetyTips; }
    public void setSafetyTips(String safetyTips) { this.safetyTips = safetyTips; }

    public double getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
}
