package com.nguyenducnha.doan_nuocngot;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Activityhinhanh extends AppCompatActivity {
    ArrayList<Integer> dshinhanh;
    hinhAdapter adapterHinh;
    int resultCodeHinh = 115;
    ListView lvhinhanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityhinhanh);
        addControls();
        addEvents();
    }

    private void addControls() {
        dshinhanh = new ArrayList<>();
        dshinhanh.add(R.drawable.mirindaokem);
        dshinhanh.add(R.drawable.camtwister);
        dshinhanh.add(R.drawable.carabao);
        dshinhanh.add(R.drawable.coca330ml);
        dshinhanh.add(R.drawable.mirindacam);
        dshinhanh.add(R.drawable.mirindaxaxi);
        dshinhanh.add(R.drawable.c2);
        dshinhanh.add(R.drawable.pepsi);
        dshinhanh.add(R.drawable.redbull);
        dshinhanh.add(R.drawable.sprite);
        dshinhanh.add(R.drawable.sting);
        dshinhanh.add(R.drawable.drthanh);
        dshinhanh.add(R.drawable.kodo);
        dshinhanh.add(R.drawable.bayup);
        lvhinhanh = findViewById(R.id.lvhinhanh);
        adapterHinh = new hinhAdapter(Activityhinhanh.this,R.layout.item_hinhnuocngot,dshinhanh);
        lvhinhanh.setAdapter(adapterHinh);
    }

    private void addEvents() {
        lvhinhanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int hinh=(dshinhanh.get(position));
                Intent intent=new Intent(Activityhinhanh.this,ActivityThemnuocngot.class);
                intent.putExtra("TRAHINH",hinh);
                setResult(resultCodeHinh,intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Activityhinhanh.this,ActivityThemnuocngot.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
