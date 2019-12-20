package com.nguyenducnha.doan_nuocngot;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NuocNgotResposity {
    private NuocNgotDao nuocNgotDao;
    private LiveData<List<NuocNgot>> dsNuocNgot;
    public NuocNgotResposity(Context context){
        nuocNgotDao=AppDatabase.getInstance(context).nuocNgotDao();
        dsNuocNgot=nuocNgotDao.getAll();
    }
    public void insert(NuocNgot nn){
        AppDatabase.executor.execute(()->nuocNgotDao.insert(nn));
    }
    public void update(NuocNgot nn){
        AppDatabase.executor.execute(()->nuocNgotDao.update(nn));
    }
    public void delete(NuocNgot nn){
        AppDatabase.executor.execute(()->nuocNgotDao.delete(nn));
    }
    public LiveData<List<NuocNgot>> getDsNuocNgot(){
        return dsNuocNgot;
    }
}
