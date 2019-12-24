package com.vehicle.machine;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    FrameLayout nue;
    AnimationDrawable animationDrawable;
    Button tu, dua, tiga, penghitung, exit;
    InterstitialAd in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        MobileAds.initialize(this,"ca-app-pub-9560479013051071~6919702773");
        in = new InterstitialAd(MainActivity.this);
        in.setAdUnitId(getString(R.string.inter));
        AdRequest minta = new AdRequest.Builder().build();
        in.loadAd(minta);
        in.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded(){
                displayInterstitial();
            }
        });

        nue = findViewById(R.id.s);
        animationDrawable = (AnimationDrawable) nue.getBackground();
        animationDrawable.setEnterFadeDuration(4000);
        animationDrawable.setExitFadeDuration(500);
        animationDrawable.start();

        tu = findViewById((R.id.ls));
        tu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext() ,maju.class);
                startActivity(a);
            }
        });

        dua = findViewById((R.id.button3));
        dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext() ,Future.class);
                startActivity(b);
            }
        });

        tiga = findViewById((R.id.lm));
        tiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(getApplicationContext() ,judur.class);
                startActivity(b);
            }
        });

        penghitung = findViewById((R.id.penghitung));
        penghitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(getApplicationContext() ,penghitung.class);
                startActivity(a);
            }
        });

        exit = findViewById(R.id.button2);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }); //kode untuk exit dari applikasi jika tombol exit ditekan

    }


    public void  displayInterstitial(){
        if (in.isLoaded()){
            in.show();
        }
    }
}
