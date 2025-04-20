package com.webcraftsolutions.project02.viewHolders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.HikingRoutes;

import java.util.List;

public class HikingRoutesViewModel extends AndroidViewModel {
    private final ActivitiRepository repository;
    private LiveData<List<HikingRoutes>> hikingRoutes;

    public HikingRoutesViewModel(Application application) {
        super(application);
        repository = ActivitiRepository.getRepository(application);
    }

    public void insertHikingRoute(HikingRoutes hikingRoute) {
        repository.insertHikingRoute(hikingRoute);
    }

    // Get all hiking routes for a specific user
    public LiveData<List<HikingRoutes>> getHikingRoutes(int userId) {
        if (hikingRoutes == null) {
            hikingRoutes = repository.getRoutesForUser(userId);
        }
        return hikingRoutes;
    }

    // Set hiking routes manually (not typical, but useful for testing or replacing all data)
    public void setHikingRoutes(List<HikingRoutes> hikingRoutes) {
        for (HikingRoutes hikingRoute : hikingRoutes) {
            repository.insertRoute(hikingRoute);
        }
    }

    // Optionally allow single insert
    public void insert(HikingRoutes hikingRoutes) {
        repository.insertRoute(hikingRoutes);
    }
}
