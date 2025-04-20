/**
 * Title: CST-338 Project 02: Activiti - Activiti Repository
 * @author Noah deFer
 * Date Created: 4/9/2025
 *
 * @author Jian Mitchell
 * Date Edited: 4/13/2025
 * Description: Repository class for Activiti database.
 */
package com.webcraftsolutions.project02.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.MainActivity;
import com.webcraftsolutions.project02.database.entities.Event;
import com.webcraftsolutions.project02.database.entities.HikingRoutes;
import com.webcraftsolutions.project02.database.entities.TravelExploration;
import com.webcraftsolutions.project02.database.entities.User;
import com.webcraftsolutions.project02.database.entities.WellnessJournal;
import com.webcraftsolutions.project02.database.entities.WellnessMood;
import com.webcraftsolutions.project02.database.entities.WellnessSleep;
import com.webcraftsolutions.project02.database.entities.CardioWorkout;
import com.webcraftsolutions.project02.database.entities.WeightLiftingWorkout;


import java.util.ArrayList;
import java.util.List;
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

    // Event DAO
    private final EventDAO eventDAO;
    private final TravelExplorationDAO travelExplorationDAO;
    private final HikingRoutesDAO hikingRoutesDAO;

    // Wellness DAO
    private final WellnessSleepDAO wellnessSleepDAO;
    private final WellnessMoodDAO wellnessMoodDAO;
    private final WellnessJournalDAO wellnessJournalDAO;

    // User DAO
    private final UserDAO userDAO;

    // CONSTRUCTORS
    private ActivitiRepository(Application application) {
        ActivitiDatabase db = ActivitiDatabase.getDatabase(application);

        // Init DAO
        this.eventDAO = db.eventDAO();
      
        this.travelExplorationDAO = db.travelExplorationDAO();
        this.hikingRoutesDAO = db.hikingRoutesDAO();

        this.wellnessSleepDAO = db.wellnessSleepDAO();
        this.wellnessMoodDAO = db.wellnessMoodDAO();
        this.wellnessJournalDAO = db.wellnessJournalDAO();

//        this.allEventLogs = getAllEvents();

        this.cardioWorkoutDAO = db.cardioWorkoutDAO();
        this.weightLiftingWorkoutDAO = db.weightLiftingWorkoutDAO();

        this.userDAO = db.userDAO();
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

    // METHODS

    // EVENT TABLE METHODS

    /**
     * Deletes all Event logs with the passed in userId.
     * @param userId The userId used to determine which event logs to delete.
     */
    public void deleteAllEventsByUserId(int userId) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            eventDAO.deleteAllEventsByUserId(userId);
        });
    }

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
    public LiveData<List<Event>> getAllEvents() {
        return eventDAO.getAllEvents();
    }

    /**
     * Returns an ArrayList containing all Event logs that were logged by the with userId.
     * @param userId Used to select Event logs that were logged by a specific user.
     * @return An ArrayList of Event objects.
     */
    public LiveData<List<Event>> getAllEventsByUserId(int userId) {
        return eventDAO.getAllEventsByUserId(userId);
    }

    /**
     * Returns an Event containing a specific id.
     * @param eventId Used to select a specific Event log.
     * @return An Event object.
     */
    public LiveData<Event> getEventByEventId(int eventId) {
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

    // Exercise DAO
    private final CardioWorkoutDAO cardioWorkoutDAO;
    private final WeightLiftingWorkoutDAO weightLiftingWorkoutDAO;



    // TRAVEL TABLE METHODS

    public LiveData<List<HikingRoutes>> getRoutesForUser(int userId) {
        return hikingRoutesDAO.getHikingRoutesForUser(userId);
    }

    public void insertRoute(HikingRoutes hikingRoutes) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            hikingRoutesDAO.insert(hikingRoutes);
        });
    }

    public void insertHikingRoute(HikingRoutes hikingRoute) {
        new Thread(() -> hikingRoutesDAO.insert(hikingRoute)).start();
    }

    /**
     * Inserts TravelExploration into the database
     * @param travelExploration the TravelExploration added
     */
    public void insertTravelExploration(TravelExploration travelExploration) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            travelExplorationDAO.insert(travelExploration);
        });
    }

    /**
     * Deletes TravelExploration from the database.
     * @param travelExploration The TravelExploration deleted
     */
    public void deleteTravelExploration(TravelExploration travelExploration) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> {
            travelExplorationDAO.delete(travelExploration);
        });
    }

    /**
     * returns a list of everything within TravelExploration
     * @return A list of TravelExploration
     */
    public List<TravelExploration> getAllTravelExplorations() {
        return travelExplorationDAO.getAllTravelExplorations();
    }

    public LiveData<List<TravelExploration>> getAllTravelExplorationsLive() {
        return travelExplorationDAO.getAllTravelExplorationsLive();
    }

    /**
     * Returns TravelExploration by its ID.
     * @param id The ID of TravelExploration.
     * @return The TravelExploration.
     */
    public TravelExploration getTravelExplorationById(int id) {
        return travelExplorationDAO.getTravelExplorationById(id);
    }

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

    // Cardio Workout Methods
    public void insertCardioWorkout(CardioWorkout... workouts) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> cardioWorkoutDAO.insert(workouts));
    }

    public ArrayList<CardioWorkout> getAllCardioWorkoutsByUserId(int userId) {
        return new ArrayList<>(cardioWorkoutDAO.getCardioWorkoutsByUserId(userId));
    }

    public void deleteAllCardioByUserId(int userId) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> cardioWorkoutDAO.deleteAllByUserId(userId));
    }

    public void deleteCardioWorkout(CardioWorkout workout) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> cardioWorkoutDAO.delete(workout));
    }

    // Weight Lifting Methods

    public void insertWeightLiftingWorkout(WeightLiftingWorkout... workouts) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> weightLiftingWorkoutDAO.insert(workouts));
    }

    public ArrayList<WeightLiftingWorkout> getAllWeightLiftingWorkoutsByUserId(int userId) {
        return new ArrayList<>(weightLiftingWorkoutDAO.getWeightLiftingWorkoutsByUserId(userId));
    }

    public void deleteAllWeightLiftingByUserId(int userId) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> weightLiftingWorkoutDAO.deleteAllByUserId(userId));
    }

    public void deleteWeightLiftingWorkout(WeightLiftingWorkout workout) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> weightLiftingWorkoutDAO.delete(workout));
    }

    // USER METHODS

    /**
     * Inserts a new user object into the user table.
     * @param user The user object to be inserted.
     */
    public void insertUser(User... user) {
        ActivitiDatabase.databaseWriteExecutor.execute(() -> userDAO.insert(user));
    }

    /**
     * Deletes a user from the user table.
     * @param user The user to be deleted.
     */
    public void deleteUser(User... user) {
        // Delete User
        ActivitiDatabase.databaseWriteExecutor.execute(() -> userDAO.delete(user));
    }

    /**
     * Deletes a user from the user table, and all logs made by that user.
     * @param user The user to be deleted.
     */
    public void wipeUser(User user) {
        // Delete User
        deleteUser(user);

        // Delete User's Event logs
        deleteAllEventsByUserId(user.getId());

        // Delete User's Exercise Logs
        deleteAllCardioByUserId(user.getId());
        deleteAllWeightLiftingByUserId(user.getId());

        // Delete User's Wellness Logs
        // TODO Delete User's Wellness Logs

        // Delete User's Travel Logs
        // TODO Delete User's Travel Logs
    }

    /** Returns all users in the user table. */
    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }

    /**
     * Returns a LiveData object containing a specific user.
     * @param userId The unique id for a specific user.
     * @return A LiveData object containing the user.
     */
    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

    /**
     * Returns a LiveData object containing a specific user.
     * @param username The unique username for a specific user.
     * @return A LiveData object containing the user.
     */
    public LiveData<User> getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
