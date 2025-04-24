/**
 * Title: Project 02: Activiti - Visited Places
 * File: VisitedPlacesViewModel.java - Implementation of VisitedPlacesViewModel
 * @author Jian Mitchell
 * Professor: Dr. C
 * Date: 23 April 2025
 * Explanation/Abstract: The viewModel for visitedPlaces.
 */

package com.webcraftsolutions.project02.viewHolders;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.VisitedPlaces;

import java.util.List;

public class VisitedPlacesViewModel extends AndroidViewModel {

    private final ActivitiRepository repository;

    public VisitedPlacesViewModel(@NonNull Application application) {
        super(application);
        repository = ActivitiRepository.getRepository(application);
    }

    public void insertVisitedPlace(VisitedPlaces place) {
        repository.insertVisitedPlace(place);
    }

    public LiveData<List<VisitedPlaces>> getVisitedPlaces(int userId) {
        return repository.getVisitedPlacesByUserId(userId);
    }
}
