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
    private ArrayList<Event> allEventLogs;

    private final EventDAO eventDAO;

    // CONSTRUCTORS
    private ActivitiRepository(Application application) {
        ActivitiDatabase db = ActivitiDatabase.getDatabase(application);
        this.eventDAO = db.eventDAO();
        this.allEventLogs = this.eventDAO.getAllEvents();
    }

    // STATIC METHODS

    /**
     * Returns an ActivitiRepository instance.
     * Attempts to create an ActivitiRepository instance with multi-threading.
     * @param application
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

    // EXERCISE TABLE METHODS

    // TRAVEL TABLE METHODS

    // WELLNESS TABLE METHODS
}
