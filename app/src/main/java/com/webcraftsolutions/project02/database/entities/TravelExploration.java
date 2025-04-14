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
    private String hikingRoute;
    private String outdoors;
    private String visitedPlaces;

    public TravelExploration(String hikingRoute, String outdoors, String visitedPlaces) {
        this.hikingRoute = hikingRoute;
        this.outdoors = outdoors;
        this.visitedPlaces = visitedPlaces;
        travelId = -1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TravelExploration that = (TravelExploration) o;
        return travelId == that.travelId && Objects.equals(hikingRoute, that.hikingRoute) && Objects.equals(outdoors, that.outdoors) && Objects.equals(visitedPlaces, that.visitedPlaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(travelId, hikingRoute, outdoors, visitedPlaces);
    }

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
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
}
