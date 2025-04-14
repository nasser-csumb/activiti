/**
 * Title: Project 02: Activiti - Travel & Exploration
 * File: TravelExploration.java - Implementation of TravelExploration
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 13 April 2025
 * Explanation/Abstract: This stores the travelExploration data.
 */

package com.webcraftsolutions.project02.database.entities;

import androidx.room.PrimaryKey;
import androidx.room.Entity;

import com.webcraftsolutions.project02.database.ActivitiDatabase;

@Entity(tableName = ActivitiDatabase.TravelExploration_Table)
public class TravelExploration {

    @PrimaryKey(autoGenerate = true)
    private int travelID;
    private String destination;
    private String startDate;
    private String endDate;
    private String description;

    public TravelExploration(String destination, String startDate, String endDate, String description) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getTravelID() {
        return travelID;
    }

    public void setTravelID(int travelID) {
        this.travelID = travelID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
