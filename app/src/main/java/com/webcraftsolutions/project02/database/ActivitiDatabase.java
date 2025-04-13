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
import com.webcraftsolutions.project02.database.entities.WellnessSleep;
import com.webcraftsolutions.project02.database.entities.WellnessJournal;
import com.webcraftsolutions.project02.database.entities.WellnessMood;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Event.class, WellnessJournal.class, WellnessMood.class, WellnessSleep.class}, version = 2, exportSchema = false)
public abstract class ActivitiDatabase extends RoomDatabase {

    // CLASS FIELDS

    // The number of threads used by the ExecutorService
    private static final int NUMBER_OF_THREADS = 4;

    // The ExecutorService instance.
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // This ActivitiDatabase instance
    public static ActivitiDatabase INSTANCE;

    // ID for the database
    private static final String DATABASE_NAME = "Activiti_Database";

    // ID for event table
    public static final String EVENT_TABLE = "Event_Table";

    // ID for user table
    public static final String USER_TABLE = "User_Table";

    // Wellness
    public static final String WELLNESS_SLEEP_TABLE = "Wellness_Sleep_Table";
    public static final String WELLNESS_MOOD_TABLE = "Wellness_Mood_Table";
    public static final String WELLNESS_JOURNAL_TABLE = "Wellness_Journal_Table";

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

    // TODO Add addDefaultValues, which adds default users to database, method here after User and UserDAO are setup.


    // ABSTRACT METHODS

    public abstract EventDAO eventDAO();

    // Wellness
    public abstract WellnessSleepDAO wellnessSleepDAO();
    public abstract WellnessMoodDAO wellnessMoodDAO();
    public abstract WellnessJournalDAO  wellnessJournalDAO();
}
