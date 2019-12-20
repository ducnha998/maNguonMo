package com.nguyenducnha.doan_nuocngot;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NuocNgotViewModel extends AndroidViewModel {
    private NuocNgotResposity resposity;
    private LiveData<List<NuocNgot>> dsNuocNgot;
    public NuocNgotViewModel(Application app){
        super(app);
        resposity=new NuocNgotResposity(app);
        dsNuocNgot=resposity.getDsNuocNgot();
    }
    public void insert(NuocNgot nn){
        resposity.insert(nn);
    }
    public void update(NuocNgot nn){
        resposity.update(nn);
    }
    public void delete(NuocNgot nn){
        resposity.delete(nn);
    }
    public LiveData<List<NuocNgot>> getDsNuocNgot(){
        return dsNuocNgot;
    }
}
