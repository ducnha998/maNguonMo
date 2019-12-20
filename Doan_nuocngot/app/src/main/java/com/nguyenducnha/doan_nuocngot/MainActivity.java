package com.nguyenducnha.doan_nuocngot;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtTaikhoan,txtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    private void addControls(){
        txtTaikhoan=findViewById(R.id.txtTaikhoan);
        txtPass=findViewById(R.id.txtPass);
        btnLogin=findViewById(R.id.btnLogin);
    }
    private void addEvents(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk=txtTaikhoan.getText().toString();
                String pass=txtPass.getText().toString();
                if(tk.equals("admin")&&pass.equals("admin")){
                    Intent intent=new Intent(MainActivity.this,MainActivity_nuocngot.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
