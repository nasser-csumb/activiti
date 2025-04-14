/**
 * Title: CST-338 Project 02: Activiti - Activiti Repository
 * @author Noah deFer
 * Date Created: 4/9/2025
 * Description: Repository class for Activiti database.
 */
package com.webcraftsolutions.project02.database;

import android.app.Application;
import android.util.Log;

import com.webcraftsolutions.project02.MainActivity;
import com.webcraftsolutions.project02.database.entities.Event;
import com.webcraftsolutions.project02.database.entities.WellnessJournal;
import com.webcraftsolutions.project02.database.entities.WellnessMood;
import com.webcraftsolutions.project02.database.entities.WellnessSleep;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ActivitiRepository {
    // CLASS FIELDS

    // Stores the repository instance.
    private static ActivitiRepository repository;

    // INSTANCE FIELDS

    // Stores the EventDAO instance.

    // Stores an array of Event instances.
    public ArrayList<Event> allEventLogs;

    private final EventDAO eventDAO;

    // Wellness DAO
    private final WellnessSleepDAO wellnessSleepDAO;
    private final WellnessMoodDAO wellnessMoodDAO;
    private final WellnessJournalDAO wellnessJournalDAO;

    // CONSTRUCTORS
    private ActivitiRepository(Application application) {
        ActivitiDatabase db = ActivitiDatabase.getDatabase(application);

        // Init DAO
        this.eventDAO = db.eventDAO();

        this.wellnessSleepDAO = db.wellnessSleepDAO();
        this.wellnessMoodDAO = db.wellnessMoodDAO();
        this.wellnessJournalDAO = db.wellnessJournalDAO();

        this.allEventLogs = getAllEvents();
    }

    // STATIC METHODS

    /**
     * Returns an ActivitiRepository instance.
     * Attempts to create an ActivitiRepository instance with multi-threading.
     * @param application The current application.
     * @return The ActivitiRepository instance.
     */
    public static ActivitiRepository getRepository(Application application) {
        Future<ActivitiRepository> future = ActivitiDatabase.databaseWriteExecutor.submit(
                new Callable<ActivitiRepository>() {
                    @Override
                    public ActivitiRepository call() throws Exception {
                        repository = new ActivitiRepository(application);
                        return repository;
                    }
                });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.d(MainActivity.TAG, "Problem getting ActivitiRepository, thread error.");
        }
        return null;
    }

    // EVENT TABLE METHODS

    /**
     * Deletes an Event log from the database.
     * @param event The Event log to be deleted.
     */
    public void deleteEvent(Event event) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            eventDAO.delete(event);
        });
    }

    /**
     * Deletes an Event log from the database.
     * @param event The Event log to be deleted.
     */
    public void deleteEvent(Event... event) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            eventDAO.delete(event);
        });
    }

    /**
     * Returns an ArrayList containing all Event logs in the repository.
     * @return An ArrayList of Event objects.
     */
    public ArrayList<Event> getAllEvents() {
        return (ArrayList<Event>) eventDAO.getAllEvents();
    }

    /**
     * Returns an ArrayList containing all Event logs that were logged by the with userId.
     * @param userId Used to select Event logs that were logged by a specific user.
     * @return An ArrayList of Event objects.
     */
    public ArrayList<Event> getAllEventsByUserId(int userId) {
        return (ArrayList<Event>) eventDAO.getAllEventsByUserId(userId);
    }

    /**
     * Returns an Event containing a specific id.
     * @param eventId Used to select a specific Event log.
     * @return An Event object.
     */
    public Event getEventByEventId(int eventId) {
        return eventDAO.getEventByEventId(eventId);
    }

    /**
     * Inserts a new Event into the database.
     * If two events have the same id, the new event will override it.
     * @param event The event to be added to the database.
     */
    public void insertEvent(Event event) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            eventDAO.insert(event);
        });
    }

    /**
     * Inserts new Events into the database.
     * If two events have the same id, the new event will override it.
     * @param event The events to be added to the database.
     */
    public void insertEvent(Event... event) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            eventDAO.insert(event);
        });
    }

    // EXERCISE TABLE METHODS

    // TRAVEL TABLE METHODS

    // ===============
    // Wellness
    // ===============

    // Sleep
    /** Deletes one or more WellnessSleep entries. */
    public void deleteSleep(WellnessSleep... entries) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> wellnessSleepDAO.delete(entries));
    }

    /** Inserts one or more WellnessSleep entries. Replaces on conflict. */
    public void insertSleep(WellnessSleep... entries) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> wellnessSleepDAO.insert(entries));
    }

    /** Returns all WellnessSleep entries. */
    public ArrayList<WellnessSleep> getAllSleep() {
        return new ArrayList<>(wellnessSleepDAO.getAllEntries());
    }

    /** Returns WellnessSleep entries filtered by userId. */
    public ArrayList<WellnessSleep> getAllSleepByUserId(int userId) {
        return new ArrayList<>(wellnessSleepDAO.getAllEntriesByUserId(userId));
    }

    /** Returns a specific WellnessSleep entry by ID. */
    public WellnessSleep getSleepById(int entryId) {
        return wellnessSleepDAO.getEntryById(entryId);
    }

    // Mood

    /** Deletes one or more Mood entries. */
    public void deleteMood(WellnessMood... moods) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> wellnessMoodDAO.delete(moods));
    }

    /** Inserts one or more Mood entries. Replaces on conflict. */
    public void insertMood(WellnessMood... moods) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> wellnessMoodDAO.insert(moods));
    }

    /** Returns all Mood entries. */
    public ArrayList<WellnessMood> getAllMoods() {
        return new ArrayList<>(wellnessMoodDAO.getAllEntries());
    }

    /** Returns Mood entries filtered by userId. */
    public ArrayList<WellnessMood> getAllMoodsByUserId(int userId) {
        return new ArrayList<>(wellnessMoodDAO.getAllEntriesByUserId(userId));
    }

    /** Returns a specific Mood entry by ID. */
    public WellnessMood getMoodById(int entryId) {
        return wellnessMoodDAO.getEntryById(entryId);
    }

    // Journal

    /** Deletes one or more Journal entries. */
    public void deleteJournal(WellnessJournal... entries) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> wellnessJournalDAO.delete(entries));
    }

    /** Inserts one or more Journal entries. Replaces on conflict. */
    public void insertJournal(WellnessJournal... entries) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> wellnessJournalDAO.insert(entries));
    }

    /** Returns all Journal entries. */
    public ArrayList<WellnessJournal> getAllJournals() {
        return new ArrayList<>(wellnessJournalDAO.getAllEntries());
    }

    /** Returns Journal entries filtered by userId. */
    public ArrayList<WellnessJournal> getAllJournalsByUserId(int userId) {
        return new ArrayList<>(wellnessJournalDAO.getAllEntriesByUserId(userId));
    }

    /** Returns a specific Journal entry by ID. */
    public WellnessJournal getJournalById(int entryId) {
        return wellnessJournalDAO.getEntryById(entryId);
    }
}
