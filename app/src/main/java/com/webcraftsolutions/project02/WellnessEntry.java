package com.webcraftsolutions.project02;

import androidx.annotation.NonNull;

import com.webcraftsolutions.project02.database.entities.WellnessJournal;
import com.webcraftsolutions.project02.database.entities.WellnessMood;
import com.webcraftsolutions.project02.database.entities.WellnessSleep;

import java.util.ArrayList;
import java.util.Date;

public class WellnessEntry {
    String dateString;
    Date date;
    ArrayList<WellnessSleep> sleepEntries = new ArrayList<WellnessSleep>();
    ArrayList<WellnessMood> moodEntries = new ArrayList<WellnessMood>();
    ArrayList<WellnessJournal> journalEntries = new ArrayList<WellnessJournal>();

    public WellnessEntry(Date date) {
        this.date = date;
        this.dateString = DateWithoutTimeConverter.getDateAsString(date);
    }

    @NonNull
    @Override
    public String toString() {
        return "Entry\n" +
                "Date: " + dateString + "\n" +
                "Sleep Entries: " + sleepEntries.size() + "\n" +
                "Mood Entries: " + moodEntries.size() + "\n" +
                "Journal Entries: " + journalEntries.size() + "\n";
    }
}
