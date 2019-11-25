package com.example.firsttime;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.view.CameraView;
import androidx.core.app.ActivityCompat;

import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class scanima extends AppCompatActivity {
    private Button btnpromo, btnrecet;

    SurfaceView camara;
    private String texto;
    TextView txtResult;
    Detector detector;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    final int RequestCameraPermissionID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanima);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //camara en surfaceView
        camara = findViewById(R.id.cameraPreview);
        txtResult = findViewById(R.id.textView);

        barcodeDetector=new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        camara=new CameraSource.Builder(this,barcodeDetector).setAutoFocusEnabled(true).setRequestedPreviewSize(640,480).build();
        //cam preview
        camara.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //revisa permisos
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(scanima.this, new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        detector.setProcessor(new Detector.Processor() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections detections) {
                final SparseArray<Detector> sparseArray=detections.getDetectedItems();
                if(sparseArray.size()!= 0){
                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator= (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            txtResult.setText(sparseArray.valueAt(0).toString());
                        }
                    });
                }
            }
        });

        btnpromo = (Button) findViewById(R.id.buttonpromos);
        btnpromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent promo = new Intent(v.getContext(), promoactivity.class);
                startActivity(promo);
            }
        });

        btnrecet = (Button) findViewById(R.id.buttonRecetas);
        btnrecet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recets = new Intent(v.getContext(), recetas.class);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(view.getContext(), MainActivity.class);
                startActivity(home);
            }
        });
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case RequestCameraPermissionID:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        return;
                    }try{
                        cameraSource.start(camara.getHolder());
                    }catch (Exception e){
                        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
    }
}


}
