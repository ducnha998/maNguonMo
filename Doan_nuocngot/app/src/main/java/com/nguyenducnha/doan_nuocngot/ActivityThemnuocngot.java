package com.nguyenducnha.doan_nuocngot;

import android.Manifest;
import android.annotation.SuppressLint;
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

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityThemnuocngot extends AppCompatActivity {
    private static final int requestCall=1;
    TextView txtTen,txtLoai,txtGia,txtNSX,txtXuatxu;
    Button btnLuu,btnChonhinh;
    NuocNgot nn;
    int resultCode=115;
    int requsetCodeHinh=113,resultCodeHinh=115;
    ImageView hinh;
    int hinhtrave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themnuocngot);
        addControls();
        getIntentData();
        addEvents();

    }
    private void addControls(){
        txtTen=findViewById(R.id.editText_ten);
        txtLoai=findViewById(R.id.editText_loai);
        txtGia=findViewById(R.id.editText_gia);
        txtNSX=findViewById(R.id.editText_nsx);
        txtXuatxu=findViewById(R.id.editText_xx);
        btnLuu=findViewById(R.id.btnLưu);
        btnChonhinh=findViewById(R.id.btnChonhinh);
        hinh=findViewById(R.id.imageChonhinh);
        hinh.setImageResource(hinhtrave);
    }
    private  void addEvents(){
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyLuu();
            }
        });
        btnChonhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityThemnuocngot.this,Activityhinhanh.class);
                startActivityForResult(intent,requsetCodeHinh);

            }
        });
    }
    private void xuLyLuu(){
        if(nn==null) {
            nn = new NuocNgot();
        }
        nn.setTenSP(txtTen.getText().toString());
        nn.setPhanLoai(txtLoai.getText().toString());
        nn.setGia(Integer.parseInt(txtGia.getText().toString()));
        nn.setNhaSX(txtNSX.getText().toString());
        nn.setXuatXu(txtXuatxu.getText().toString());
        nn.setHinhanh(hinhtrave);
        Intent intent=getIntent();
        intent.putExtra("TRA",nn);
        setResult(resultCode,intent);
        finish();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requsetCodeHinh==this.requsetCodeHinh){
            if(resultCodeHinh==this.resultCodeHinh){
                if(data.hasExtra("TRAHINH")){
                    hinhtrave=(Integer)data.getSerializableExtra("TRAHINH");
                    hinh.setImageResource(hinhtrave);
                }
            }
        }
    }
    private void getIntentData(){
        Intent intent=getIntent();
        if(intent.hasExtra("TRAINFO")){
            nn=(NuocNgot)intent.getSerializableExtra("TRAINFO");
            if(nn!=null) {
                txtTen.setText(nn.getTenSP());
                txtLoai.setText(nn.getPhanLoai());
                txtGia.setText(String.valueOf(nn.getGia()));
                txtNSX.setText(nn.getNhaSX());
                txtXuatxu.setText(nn.getXuatXu());
                hinh.setImageResource(nn.getHinhanh());
            }
            else resetView();
        }else resetView();
    }
    private void resetView(){
        txtTen.setText("");
        txtLoai.setText("");
        txtGia.setText("0");
        txtXuatxu.setText("");
        txtNSX.setText("");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ActivityThemnuocngot.this,MainActivity_nuocngot.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
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
                AlertDialog.Builder builder=new AlertDialog.Builder(ActivityThemnuocngot.this);
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
                Intent intent=new Intent(ActivityThemnuocngot.this,MapsActivityAbout.class);
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
    private void makephonecall(){
        if(ContextCompat.checkSelfPermission(ActivityThemnuocngot.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ActivityThemnuocngot.this,new String[]{Manifest.permission.CALL_PHONE}, requestCall);
        }
        else {
            String dial="tel:0983574113";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCodeCall, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCall==requestCodeCall){
            if (grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                makephonecall();
            }
            else {
                Toast.makeText(ActivityThemnuocngot.this,"Permission DENIED",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
