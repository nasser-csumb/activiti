/**
 * Title: Project 02: Activiti - WellnessJournal POJO
 * Author: Nasser Akhter
 * Date Created: 4/11/2025
 * Description: A POJO that stores wellness journal entries.
 */
package com.webcraftsolutions.project02.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.webcraftsolutions.project02.database.ActivitiDatabase;
import com.webcraftsolutions.project02.database.DateConverter;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = ActivitiDatabase.WELLNESS_JOURNAL_TABLE)
public class WellnessJournal {
    // FIELDS
    @PrimaryKey(autoGenerate = true)
    private int entryId;

    private int userId;

    @TypeConverters(DateConverter.class)
    private Date date;

    private String title;

    private String content;

    // CONSTRUCTOR
    public WellnessJournal(int userId, Date date, String title, String content) {
        this.userId = userId;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    // GETTERS
    public int getEntryId() {
        return entryId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    // SETTERS
    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return title + "\n\n" +
               content + "\n----------------\n";
    }

    // EQUALS & HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WellnessJournal that = (WellnessJournal) o;
        return entryId == that.entryId &&
                userId == that.userId &&
                Objects.equals(date, that.date) &&
                Objects.equals(title, that.title) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, userId, date, title, content);
    }
}
