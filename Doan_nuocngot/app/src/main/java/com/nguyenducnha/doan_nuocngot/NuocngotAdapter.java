package com.nguyenducnha.doan_nuocngot;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NuocngotAdapter extends ArrayAdapter<NuocNgot> {
    Activity context;
    int resource;
    List<NuocNgot> objects;

    public NuocngotAdapter(Activity context,
                           int resource,
                           List<NuocNgot> objects){
        super(context,resource,objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);
        TextView txtGia=item.findViewById(R.id.item_txtgia);
        TextView txtTen=item.findViewById(R.id.item_txtTen);
        TextView txtPhanloai=item.findViewById(R.id.item_txtLoai);
        ImageView imageNuocngot=item.findViewById(R.id.imageNuocngot);


        NuocNgot nn=this.objects.get(position);
        int hinh= (nn.getHinhanh());
        txtGia.setText(String.valueOf(nn.getGia()));
        txtTen.setText(nn.getTenSP());
        txtPhanloai.setText(nn.getPhanLoai());
        imageNuocngot.setImageResource(hinh);
        return item;
    }
}
