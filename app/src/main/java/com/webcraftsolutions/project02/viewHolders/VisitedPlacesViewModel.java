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
