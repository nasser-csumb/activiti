/**
 * Title: Project 02: Activiti - Activiti Database
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: Database object for Activiti databse.
 */
package com.webcraftsolutions.project02.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
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

    // ID for user table
    public static final String USER_TABLE = "User_Table";

    // METHODS
    /**
     * Returns a GymLogDatabase instance.
     * Creates a new instance only if one doesn't exist.
     * @param context The application context.
     * @return A GymLogDatabase instance.
     */
    //TODO Enable add addCallback call once addDefaultValues is added.
    static ActivitiDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ActivitiDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ActivitiDatabase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
//                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // TODO Add addDefaultValues method here after User and UserDAO are setup.


    // ABSTRACT METHODS

    public abstract EventDAO eventDAO();
}
