package com.webcraftsolutions.project02.viewHolders;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.webcraftsolutions.project02.database.ActivitiRepository;
import com.webcraftsolutions.project02.database.entities.TravelExploration;

import java.util.ArrayList;
import java.util.List;

public class HikingRoutesViewModel extends AndroidViewModel {

    private final MutableLiveData<Integer> selectedDifficulty = new MutableLiveData<>();
    private final LiveData<List<TravelExploration>> allExplorations;
    private final LiveData<List<TravelExploration>> filteredRoutes;

    public HikingRoutesViewModel(@NonNull Application application) {
        super(application);
        ActivitiRepository repository = ActivitiRepository.getRepository(application);

        allExplorations = repository.getAllTravelExplorationsLive();

        filteredRoutes = Transformations.switchMap(selectedDifficulty, difficulty ->
                Transformations.map(allExplorations, routes -> {
                    List<TravelExploration> filtered = new ArrayList<>();
                    if (routes != null) {
                        for (TravelExploration route : routes) {
                            if (route.getHikingRoute() != null && !route.getHikingRoute().isEmpty()) {
                                // You can apply more filtering logic here using difficulty
                                filtered.add(route);
                            }
                        }
                    }
                    return filtered;
                })
        );
    }

    public LiveData<List<TravelExploration>> getFilteredRoutes() {
        return filteredRoutes;
    }

    public void setSelectedDifficulty(int difficulty) {
        selectedDifficulty.setValue(difficulty);
    }

    public LiveData<List<TravelExploration>> getHikingRoutes() {
        return filteredRoutes;
    }
}
