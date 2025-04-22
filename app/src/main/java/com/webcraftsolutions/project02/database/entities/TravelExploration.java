/**
 * Title: Project 02: Activiti - Travel & Exploration
 * File: TravelExploration.java - Implementation of TravelExploration
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 14 April 2025
 * Explanation/Abstract: This stores the travelExploration data.
 */

package com.webcraftsolutions.project02.database.entities;

import androidx.room.PrimaryKey;
import androidx.room.Entity;

import com.webcraftsolutions.project02.database.ActivitiDatabase;

import java.util.Objects;

@Entity(tableName = ActivitiDatabase.TRAVEL_EXPLORATION_TABLE)
public class TravelExploration {

    @PrimaryKey(autoGenerate = true)
    private int travelId;

    private int userId;
    private String hikingRoute;
    private String outdoors;
    private String visitedPlaces;

    private long timeStamp;

    public TravelExploration(int userId, String hikingRoute, String outdoors, String visitedPlaces, long timeStamp) {
        this.userId = userId;
        this.hikingRoute = hikingRoute;
        this.outdoors = outdoors;
        this.visitedPlaces = visitedPlaces;
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TravelExploration that = (TravelExploration) o;
        return travelId == that.travelId && userId == that.userId && timeStamp == that.timeStamp && Objects.equals(hikingRoute, that.hikingRoute) && Objects.equals(outdoors, that.outdoors) && Objects.equals(visitedPlaces, that.visitedPlaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelId, userId, hikingRoute, outdoors, visitedPlaces, timeStamp);
    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userrId) {
        this.userId = userrId;
    }

    public String getHikingRoute() {
        return hikingRoute;
    }

    public void setHikingRoute(String hikingRoute) {
        this.hikingRoute = hikingRoute;
    }

    public String getOutdoors() {
        return outdoors;
    }

    public void setOutdoors(String outdoors) {
        this.outdoors = outdoors;
    }

    public String getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(String visitedPlaces) {
        this.visitedPlaces = visitedPlaces;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timesstamp) {
        this.timeStamp = timesstamp;
    }
}
