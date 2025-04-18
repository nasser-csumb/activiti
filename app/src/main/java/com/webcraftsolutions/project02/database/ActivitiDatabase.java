/**
 * Title: Project 02: Activiti - Activiti Database
 * @author Noah deFer
 * Date Created: 4/9/2025
 *
 * @author Jian Mitchell
 * Date Edited: 4/13/2025
 * Description: Database object for Activiti databse.
 */
package com.webcraftsolutions.project02.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.webcraftsolutions.project02.MainActivity;
import com.webcraftsolutions.project02.database.entities.Event;

import com.webcraftsolutions.project02.database.entities.TravelExploration;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.database.entities.WellnessSleep;
import com.webcraftsolutions.project02.database.entities.WellnessJournal;
import com.webcraftsolutions.project02.database.entities.WellnessMood;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Event.class, WellnessJournal.class, WellnessMood.class, WellnessSleep.class, TravelExploration.class, User.class}, version = 5, exportSchema = false)
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

    // ID for the travel exploration table
    public static final String TRAVEL_EXPLORATION_TABLE = "TravelExploration_Table";

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
    static ActivitiDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (ActivitiDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ActivitiDatabase.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /** Adds to default users, admin1 and testuser1, to the user table. */
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Log Message
            Log.i(MainActivity.TAG, "ACTIVITI DATABASE CREATED");

            // Add default users to database.
            databaseWriteExecutor.execute(() -> {
                // Get userDAO
                UserDAO userDAO = INSTANCE.userDAO();
                userDAO.deleteAll();

                // Add admin
                User admin = new User("admin1", "admin1");
                admin.setId(1);
                admin.setAdmin(true);
                userDAO.insert(admin);

                // Add test user
                User testUser = new User("testuser1", "testuser1");
                testUser.setId(2);
                userDAO.insert(testUser);

            });
        }
    };


    // ABSTRACT METHODS

    public abstract EventDAO eventDAO();

    public abstract TravelExplorationDAO travelExplorationDAO();
    public abstract UserDAO userDAO();
    // Wellness
    public abstract WellnessSleepDAO wellnessSleepDAO();
    public abstract WellnessMoodDAO wellnessMoodDAO();
    public abstract WellnessJournalDAO  wellnessJournalDAO();
}
