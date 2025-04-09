/**
 * Title: Project 02: Activiti - Activiti Database
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: Database object for Activiti databse.
 */
package com.webcraftsolutions.project02.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.webcraftsolutions.project02.database.entities.Event;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
public abstract class ActivitiDatabase extends RoomDatabase {

    // CLASS FIELDS

    // This ActivitiDatabase instance
    public static ActivitiDatabase INSTANCE;

    // ID for the database
    private static final String DATABASE_NAME = "Activiti_Database";

    // ID for event table
    public static final String EVENT_TABLE = "Event_Table";

    // CONSTRUCTOR

    // METHODS

}
