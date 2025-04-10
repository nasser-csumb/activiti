/**
 * Title: Project 02: Activiti - User POJO
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: A POJO that stores event data.
 */
package com.webcraftsolutions.project02.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.webcraftsolutions.project02.database.ActivitiDatabase;

import java.util.Objects;

@Entity(tableName = ActivitiDatabase.EVENT_TABLE)
public class Event {
    // FIELDS
    @PrimaryKey(autoGenerate = true)
    private int eventId;

    // The name of the event.
    private String name;

    // The description of the event.
    private String description;

    // The date of the event.
    private String date;

    // The time of the event.
    private String time;

    // The user that saved this event.
    private int userId;

    // CONSTRUCTOR
    public Event(String name, String description, String date, String time, int userId) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.userId = userId;
    }

    // METHODS

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId && userId == event.userId && Objects.equals(name, event.name) && Objects.equals(description, event.description) && Objects.equals(date, event.date) && Objects.equals(time, event.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, name, description, date, time, userId);
    }


    // GETTERS AND SETTERS

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getUserId() {
        return userId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
