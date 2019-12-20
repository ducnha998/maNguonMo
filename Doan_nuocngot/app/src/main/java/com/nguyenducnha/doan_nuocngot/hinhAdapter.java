package com.nguyenducnha.doan_nuocngot;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class hinhAdapter extends ArrayAdapter {

    Activity context;
    int resource;
    List<Integer> objects;

    public hinhAdapter( Activity context, int resource,List<Integer> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);
        ImageView imageItemhinh=item.findViewById(R.id.imageView_itemhinh);

        int hinh=this.objects.get(position);
        imageItemhinh.setImageResource(hinh);
        return item;
    }
}


