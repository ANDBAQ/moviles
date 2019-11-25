package com.example.firsttime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnscan, btnrecetas, btnpromos, btnAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnpromos =(ImageButton) findViewById(R.id.btnPromosM);
        btnpromos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent promo = new Intent(v.getContext(),promoactivity.class);
                startActivity(promo);
            }

        });
        btnrecetas = (ImageButton) findViewById(R.id.btnRecetasM);
        btnrecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recetas =new Intent(v.getContext(),recetas.class);
                startActivity(recetas);
            }
        });
        btnscan = (ImageButton) findViewById(R.id.btnScanM);
        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent escan =new Intent(v.getContext(),scanima.class);
                startActivity(escan);
            }
        });
        btnAbout=(ImageButton) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recetas =new Intent(v.getContext(),recetas.class);
                startActivity(recetas);
            }
        });

    }
}
