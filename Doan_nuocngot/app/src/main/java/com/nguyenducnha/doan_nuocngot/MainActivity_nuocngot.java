package com.nguyenducnha.doan_nuocngot;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity_nuocngot extends AppCompatActivity {
    private static final int requestCall=1;
    private long backPressedTime;
    private Toast backToast;
    FloatingActionButton btnThem;
    NuocNgotViewModel nuocNgotViewModel;
    NuocngotAdapter adapter;
    ListView lvDSNuocngot;
    int requestCode=113,resultCode=115;
    int requestCodeinfo=123,resultCodeinfo=125;
    NuocNgot chon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuocngot);
        addControls();
        addEvents();
        /*nuocNgotViewModel.insert(new NuocNgot(1,"Pepsi","co gas",120000,"PEPSICO","VN", R.drawable.pepsi));
        nuocNgotViewModel.insert(new NuocNgot(2,"Coca cola","co gas",120000,"Coca","VN", (R.drawable.coca330ml)));
        nuocNgotViewModel.insert(new NuocNgot(3,"0 do","ko co gas",120000,"abc","VN",(R.drawable.kodo) ));
        nuocNgotViewModel.insert(new NuocNgot(4,"Sting","co gas",120000,"cde","VN", (R.drawable.sting)));*/
        adapter.notifyDataSetChanged();

    }

    private void addControls(){
        nuocNgotViewModel= ViewModelProviders.of(this).get(NuocNgotViewModel.class);
        nuocNgotViewModel.getDsNuocNgot().observe(this,dsNuocngot->{adapter.clear();adapter.addAll(dsNuocngot);});
        btnThem=findViewById(R.id.fabThem);
        adapter=new NuocngotAdapter(MainActivity_nuocngot.this,
                R.layout.item_nuocngot,new ArrayList<>()
                );
        lvDSNuocngot=findViewById(R.id.lvDSNuocngot);
        lvDSNuocngot.setAdapter(adapter);
    }
    private void addEvents(){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_nuocngot.this,ActivityThemnuocngot.class);
                startActivityForResult(intent,requestCode);
            }
        });
        lvDSNuocngot.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity_nuocngot.this);
                builder.setTitle("CANH BAO!");
                builder.setMessage("BAN CO MUON XOA KHONG");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(position>=0&&position<adapter.getCount()){
                            nuocNgotViewModel.delete(adapter.getItem(position));
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.create().show();
                return true;
            }
        });
        lvDSNuocngot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l) {
                if(positon>=0&&positon<adapter.getCount()) {
                    Intent intent = new Intent(MainActivity_nuocngot.this,ActivityThemnuocngot.class );
                    chon = adapter.getItem(positon);
                    intent.putExtra("TRAINFO",chon);
                    startActivityForResult(intent, requestCodeinfo);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.requestCode) {
            if (resultCode == this.resultCode) {
                if (data.hasExtra("TRA")) {
                    NuocNgot nn = (NuocNgot) data.getSerializableExtra("TRA");
                    nuocNgotViewModel.insert(nn);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (requestCodeinfo == this.requestCodeinfo) {
            if (resultCodeinfo == this.resultCodeinfo) {
                if (data.hasExtra("TRAINFO")) {
                    NuocNgot nn = (NuocNgot) data.getSerializableExtra("TRAINFO");
                    chon.setTenSP(nn.getTenSP());
                    chon.setPhanLoai(nn.getPhanLoai());
                    chon.setGia(nn.getGia());
                    chon.setNhaSX(nn.getNhaSX());
                    chon.setXuatXu(nn.getXuatXu());
                    chon.setHinhanh(nn.getHinhanh());
                    nuocNgotViewModel.update(chon);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Sửa thành công!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_info,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnmenu:{
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity_nuocngot.this);
                builder.setTitle("Thông tin");
                builder.setMessage("Nguyễn Đức Nhã" +
                        "\n"+"DH51601289"+'\n'+"SĐT: 09835745XX");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.setNegativeButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        makephonecall();
                    }
                });
                builder.create().show();
                return true;
            }
            case R.id.btnmenu1:{
                Intent intent=new Intent(MainActivity_nuocngot.this,MapsActivityAbout.class);
                startActivity(intent);
                return true;
            }
            case R.id.btnmenu2: {
                finish();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast=Toast.makeText(getBaseContext(),"Press BACK again to exit",Toast.LENGTH_LONG);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }
    private void makephonecall(){
        if(ContextCompat.checkSelfPermission(MainActivity_nuocngot.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity_nuocngot.this,new String[]{Manifest.permission.CALL_PHONE}, requestCall);
        }
        else {
            String dial="tel:0983574113";
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCodeCall, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCall==requestCodeCall){
            if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                makephonecall();
            }
            else {
                Toast.makeText(MainActivity_nuocngot.this,"Permission DENIED",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
