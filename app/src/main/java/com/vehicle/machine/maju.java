package com.vehicle.machine;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import java.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class maju extends AppCompatActivity {
    EditText tgl, bln, thn, tgls, blns, thns;
    LinearLayout nue;
    Button refres, hitung;
    TextView htn, hbl, htg, hari, hl, hr, mng, shr;
    AnimationDrawable animationDrawable;
    AdView adBanner;
    AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maju);

        MobileAds.initialize(this,"ca-app-pub-9560479013051071~6919702773");
        adBanner=findViewById(R.id.adBanner);
        adRequest=new AdRequest.Builder().addTestDevice("").build();
        adBanner.loadAd(adRequest);

        nue = findViewById(R.id.ani);
        animationDrawable = (AnimationDrawable) nue.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(800);
        animationDrawable.start();

        tgls = findViewById(R.id.d1); // sekarang
        blns = findViewById(R.id.d2);
        thns = findViewById(R.id.d3);

        tgl = findViewById(R.id.ed1); // lampau
        bln = findViewById(R.id.ed2);
        thn = findViewById(R.id.ed3);

        htg = findViewById(R.id.e1); // hasil perhitungan tgl, bln, tahun
        hbl = findViewById(R.id.e2);
        htn = findViewById(R.id.e3);
        refres = findViewById(R.id.ref);

        hari = findViewById(R.id.hari); // total selisih waktu dalam hari
        hl = findViewById(R.id.hariawal);
        hr = findViewById(R.id.hariakhir);
        mng = findViewById(R.id.mg);
        shr = findViewById(R.id.m1);

        Calendar ca = Calendar.getInstance(); // --------
        int h = ca.get(Calendar.DAY_OF_WEEK);
        String dhs = namahari(h);
        hr.setText(dhs);

        int ts = ca.get(Calendar.DATE);
        String ou = String.valueOf(ts);
        tgls.setText(ou);

        int bs = 1 + ca.get(Calendar.MONTH); // mengambil tanggal dari sistem dan memasukkan
        String oi = String.valueOf(bs);      // ke editeks kolom sekarang
        blns.setText(oi);

        int Ts = ca.get(Calendar.YEAR);
        String iu = String.valueOf(Ts);
        thns.setText(iu);                   // --------

        refres = findViewById(R.id.ref);
        refres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(maju.this, maju.class);
                maju.this.startActivity(intent);
                maju.this.finish();
            }
        });

        hitung = findViewById(R.id.tbl2);
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tgl.length() == 0 || bln.length() == 0 || thn.length() == 0) {
                    Toast.makeText(getApplication(), "Fill in The Empty Blank", Toast.LENGTH_LONG).show();
                } else {
                    String ax = tgl.getText().toString();
                    String bx = bln.getText().toString();
                    String cx = thn.getText().toString();
                    int tgl = Integer.parseInt(ax);
                    int bln = Integer.parseInt(bx);
                    int thn = Integer.parseInt(cx);
                    String ay = tgls.getText().toString();
                    String by = blns.getText().toString();
                    String cy = thns.getText().toString();
                    int tgls = Integer.parseInt(ay);
                    int blns = Integer.parseInt(by);
                    int thns = Integer.parseInt(cy);
                    int cthns = thns;
                    int bawal = blns-1;

                    if (((tgl == 0) || (bln == 0) || (thn == 0)) || ((tgls == 0) || (blns == 0) || (thns == 0))) {
                        Toast.makeText(getApplication(), "(Date/ Month / Year) Invalid", Toast.LENGTH_LONG).show();
                    }
                    int K = fun(bln, thn);
                    if (K < tgl && bln == 2 && tgl == 29) {
                        Toast.makeText(getApplication(), "February In That Time Only 28 Day ", Toast.LENGTH_LONG).show();
                    } else if (K < tgl) {
                        Toast.makeText(getApplication(), "Date Or Mont Is Too Big", Toast.LENGTH_LONG).show();
                    } else if (thn > thns) {
                        Toast.makeText(getApplication(), "Past Year Must Be Smaller", Toast.LENGTH_LONG).show();
                    } else if (bln > 12 || bln < 1) {
                        Toast.makeText(getApplication(), "Month Invalid", Toast.LENGTH_LONG).show();
                    } else if ((thn == thns) && (bln > blns)) {
                        Toast.makeText(getApplication(), "Past Mont Must Be Smaller", Toast.LENGTH_LONG).show();
                    } else if ((thn == thns) && (bln == blns) && (tgl > tgls)) {
                        Toast.makeText(getApplication(), "Past Date Must Be Smaller", Toast.LENGTH_LONG).show();
                    } else {
                        if (tgls < tgl) { // ---------------mulai 1
                            int J = fun(blns-1, thns);
                            int Ht = tgls + J - tgl;
                            blns = blns - 1;
                            if (blns < bln) {
                                thns = thns - 1;
                                int Hbl = 12 + blns - bln;
                                int Htn = thns - thn;
                                String p = String.valueOf(Hbl);
                                hbl.setText(p);
                                String q = String.valueOf(Htn);
                                htn.setText(q);
                            }
                            if (blns >= bln) {
                                int Hbl = blns - bln;
                                int Htn = thns - thn;
                                String p = String.valueOf(Hbl);
                                hbl.setText(p);
                                String q = String.valueOf(Htn);
                                htn.setText(q);
                            }
                            String o = String.valueOf(Ht);
                            htg.setText(o);

                        }   // ++++++++ end 1

                        if (tgls >= tgl) {  // ---------------mulai 2
                            int Ht = tgls - tgl;
                            if (blns < bln) {
                                thns = thns - 1;
                                int Hbl = 12 + blns - bln;
                                int Htn = thns - thn;
                                String p = String.valueOf(Hbl);
                                hbl.setText(p);
                                String q = String.valueOf(Htn);
                                htn.setText(q);
                            }
                            if (blns >= bln) {
                                int Hbl = blns - bln;
                                int Htn = thns - thn;
                                String p = String.valueOf(Hbl);
                                hbl.setText(p);
                                String q = String.valueOf(Htn);
                                htn.setText(q);
                            }
                            String o = String.valueOf(Ht);
                            htg.setText(o);
                            // ++++++++ end 2
                        } // end total loop

                        String jhr = htg.getText().toString();
                        String jbl = hbl.getText().toString();
                        String jtn = htn.getText().toString();
                        // mengambil nilai hasi hari/tgl/thn dari textview hasil
                        int jj = Integer.parseInt(jhr); // hasil tanggal
                        int jk = Integer.parseInt(jbl); // hasil bulan
                        int jl = Integer.parseInt(jtn); // hasil tahun

                        Calendar ca = Calendar.getInstance(); // --------
                        int h = ca.get(Calendar.DAY_OF_WEEK); // mengambil no hari
                        int jbln = jk + 12*jl; // mengubah tahun dan bulan, ke jml bulan
                        int jhri = ubih (bawal,jbln,cthns-1); // mengubah jml bulan ke hari mundur
                        String sting = a(h,jj+jhri); // mencari nama hari mundur
                        hl.setText(sting);

                        String s1 = String.valueOf(jhri+jj);
                        hari.setText(s1);
                        int mngu = (jj + jhri)/7;
                        String minggu = String.valueOf(mngu);
                        mng.setText(minggu);

                        int sisa = (jj + jhri)%7;
                        String sis = String.valueOf(sisa);
                        shr.setText(sis);
                    }
                }
            }
        });
    }


    private static int ubih (int blns, int j, int thns) {
        // mengubah bulan ke hari mundur
        int hr = 0;
        while (j > 0) {
            hr = hr + fun(blns-1, thns);
            blns--;
            j--;
            while (blns <= 0) {
                blns = blns + 12;
                thns--;
            }
        }
        return hr;
    }

    private static String a (int h, int ttl){
        //mundur
        int jml = (h - 1) - (ttl % 7);
        if (jml == 0){
            return "Sunday";
        }
        if ((jml == 1) || (jml == -6)){
            return "Monday";
        }
        if ((jml == 2) || (jml == -5)){
            return "Tuesday";
        }
        if ((jml == 3) || (jml == -4)){
            return "Wednesday";
        }
        if ((jml == 4) || (jml == -3)){
            return "Thursday";
        }
        if ((jml == 5) || (jml == -2)){
            return "Friday";
        }
        if ((jml == 6) || (jml == -1)){
            return "Saturday";
        }
        return  "error";
    }

    private static String namahari(int a ){
        if (a == 1){
            return "Sunday";
        }
        if (a == 2){
            return "Monday";
        }
        if (a == 3){
            return "Tuesday";
        }
        if (a == 4){
            return "Wednesday";
        }
        if (a == 5){
            return "Thursday";
        }
        if (a == 6){
            return "Fryday";
        }
        if (a == 7){
            return "Saturday";
        }
        return  "error";

    }

    private static int fun(int a, int b){ // ---------------------
        if ( a == 0 ){
            return 31;
        }
        if ( a == 1 ){
            return 31;
        }
        if ( a == 2 ){
            if ( b % 100 == 0){
                if ( b % 400 == 0 ){
                    return 29;
                }
                return 28;
            }
            if (b%4 == 0) {
                return 29;
            }
            return 28;
        }
        if ( a == 3 ) {
            return 31;
        }
        if ( a == 4 ) {
            return 30;
        }
        if ( a == 5 ) {
            return 31;
        }
        if ( a == 6 ) {
            return 30;
        }
        if ( a == 7 ) {
            return 31;
        }
        if ( a == 8 ) {
            return 31;
        }
        if ( a == 9 ) {
            return 30;
        }
        if ( a == 10 ) {
            return 31;
        }
        if ( a == 11 ) {
            return 30;
        }
        if ( a == 12 ) {
            return 31;
        }
        return 0;
    }
}

