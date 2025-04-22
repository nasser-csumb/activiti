/**
 * Title: CST-338 Project 02: Activiti - Event View Model
 * @author Noah deFer
 * Date Created: 4/22/2025
 * Description: View Model class for Event Recycler.
 */
package com.webcraftsolutions.project02.viewHolders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.Event;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    // FIELDS

    // The Repository.
    private ActivitiRepository repository;

    // CONSTRUCTOR

    /**
     * Initializes repository.
     * @param application The application.
     */
    public EventViewModel(Application application) {
        super(application);
        repository = ActivitiRepository.getRepository(application);
    }

    // METHODS

    /**
     * Gets all Events with the passed in userId.
     * @param userId The id of the logged in user.
     * @return A LiveData object containing a List of Event entities.
     */
    public LiveData<List<Event>> getAllLogsByUserId(int userId) {
        return repository.getAllEventsByUserId(userId);
    }

    /**
     * Inserts a new event into the repository.
     * @param event The event to be inserted.
     */
    public void insert(Event event) { repository.insertEvent(event); }
}
