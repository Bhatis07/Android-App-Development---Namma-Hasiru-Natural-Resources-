package com.internshipproject;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SaplingViewModel extends AndroidViewModel {
    private final SaplingDao saplingDao;
    private final LiveData<List<Sapling>> allSaplings;
    private final ExecutorService executorService;

    public SaplingViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        saplingDao = db.saplingDao();
        allSaplings = saplingDao.getAllSaplings();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Sapling>> getAllSaplings() {
        return allSaplings;
    }

    public void insert(Sapling sapling) {
        executorService.execute(() -> saplingDao.insert(sapling));
    }

    public void update(Sapling sapling) {
        executorService.execute(() -> saplingDao.update(sapling));
    }
}
