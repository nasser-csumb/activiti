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

import com.webcraftsolutions.project02.MainActivity;
import com.webcraftsolutions.project02.database.entities.Event;
import com.webcraftsolutions.project02.database.entities.TravelExploration;

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

    private final EventDAO eventDAO;
    private final TravelExplorationDAO travelExplorationDAO;

    // CONSTRUCTORS
    private ActivitiRepository(Application application) {
        ActivitiDatabase db = ActivitiDatabase.getDatabase(application);
        this.eventDAO = db.eventDAO();
        this.travelExplorationDAO = db.travelExplorationDAO();
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

    /**
     * Returns TravelExploration by its ID.
     * @param id The ID of TravelExploration.
     * @return The TravelExploration.
     */
    public TravelExploration getTravelExplorationById(int id) {
        return travelExplorationDAO.getTravelExplorationById(id);
    }

    // WELLNESS TABLE METHODS
}
