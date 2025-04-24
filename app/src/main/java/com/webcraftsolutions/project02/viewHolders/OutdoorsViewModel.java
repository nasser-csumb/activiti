package com.webcraftsolutions.project02.viewHolders;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.Outdoors;

import java.util.List;

public class OutdoorsViewModel extends AndroidViewModel {
    private final ActivitiRepository repository;
    private LiveData<List<Outdoors>> outdoorsList;

    public OutdoorsViewModel(@NonNull Application application) {
        super(application);
        repository = ActivitiRepository.getRepository(application);
    }

    public LiveData<List<Outdoors>> getOutdoors(int userId) {
        if (outdoorsList == null) {
            outdoorsList = repository.getOutdoorsForUser(userId);
        }
        return outdoorsList;
    }

    public void insertOutdoor(Outdoors outdoor) {
        repository.insertOutdoors(outdoor);
    }

    public void setOutdoorsList(List<Outdoors> outdoors) {
        for (Outdoors item : outdoors) {
            repository.insertOutdoors(item);
        }
    }
}
