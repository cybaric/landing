package com.vehicle.machine;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Calendar;

public class Future extends AppCompatActivity {
    EditText tgl, bln, thn, tgls, blns, thns ;
    LinearLayout nue;
    Button refres, hitung;
    TextView htn, hbl, htg, hari, hl, hr, mng,shr;
    AnimationDrawable animationDrawable;
    AdView adBanner;
    AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);adView.setAdUnitId("ca-app-pub-9560479013051071~6919702773");

        //MobileAds.initialize(this,"ca-app-pub-9560479013051071~9056176871");
        adBanner=findViewById(R.id.adBanner);
        adRequest=new AdRequest.Builder().build();
        adBanner.loadAd(adRequest);

        nue = findViewById(R.id.ani);
        animationDrawable = (AnimationDrawable) nue.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(800);
        animationDrawable.start();

        tgls = findViewById(R.id.d1); // masa depan
        blns = findViewById(R.id.d2);
        thns = findViewById(R.id.d3);

        tgl = findViewById(R.id.ed1); // sekarang (diisi otomatis oleh sistem)
        bln = findViewById(R.id.ed2);
        thn = findViewById(R.id.ed3);

        htg = findViewById(R.id.e1); // hasil selisih
        hbl = findViewById(R.id.e2);
        htn = findViewById(R.id.e3);

        hari = findViewById(R.id.hari);
        hl = findViewById(R.id.hariawal);
        hr = findViewById(R.id.hariakhir);
        mng = findViewById(R.id.mg);
        shr = findViewById(R.id.m1);

        Calendar ca = Calendar.getInstance();

       // int hsek = ca.get(Calendar.DAY_OF_WEEK) - 1 ;
       // String oo = String.valueOf(hsek);
       // hl.setText(oo);

        int ts = ca.get(Calendar.DATE);
        String ou = String.valueOf(ts);
        tgl.setText(ou);

        int bs = 1 + ca.get(Calendar.MONTH);
        String oi = String.valueOf(bs);
        bln.setText(oi);

        int Ts = ca.get(Calendar.YEAR);
        String iu = String.valueOf(Ts);
        thn.setText(iu);

        refres = findViewById(R.id.ref);
        refres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Future.this, Future.class);
                Future.this.startActivity(intent);
                Future.this.finish();
            }
        });
        hitung = findViewById(R.id.tbl2);
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tgls.length() == 0 || blns.length() == 0 || thns.length() == 0) {
                    Toast.makeText(getApplication(), "Fill In Empty Blank", Toast.LENGTH_LONG).show();
                } else {
                    //String ao = hl.getText().toString();
                    String ax = tgl.getText().toString();
                    String bx = bln.getText().toString();
                    String cx = thn.getText().toString();
                    //int hh = Integer.parseInt(ao);
                    int tgl = Integer.parseInt(ax);
                    int bln = Integer.parseInt(bx);
                    int thn = Integer.parseInt(cx);
                    String ay = tgls.getText().toString();
                    String by = blns.getText().toString();
                    String cy = thns.getText().toString();
                    int tgls = Integer.parseInt(ay);
                    int blns = Integer.parseInt(by);
                    int thns = Integer.parseInt(cy);
                    //int bawal = bln;
                    //int cthns = thn;


                    int K = fun (blns,thns);
                    if (K < tgls && blns == 2 && tgls == 29 ){
                        Toast.makeText(getApplication(), "February In That Time Only 28 Day", Toast.LENGTH_LONG).show();
                    }
                    else if (K < tgls ) {
                        Toast.makeText(getApplication(), "Date Or Mont Is Too Big", Toast.LENGTH_LONG).show();
                    }
                    else if (tgls == 0 ) {
                        Toast.makeText(getApplication(), "Invalid Date", Toast.LENGTH_LONG).show();
                    }
                    else if (thn > thns ) {
                        Toast.makeText(getApplication(), "Next Year Must Be Greather", Toast.LENGTH_LONG).show();
                    }
                    else if (blns > 12 || blns < 1) {
                        Toast.makeText(getApplication(), "Invalid Month", Toast.LENGTH_LONG).show();
                    }
                    else if ((thn == thns ) && (bln > blns)) {
                       Toast.makeText(getApplication(), "Next Month Must Be Greather", Toast.LENGTH_LONG).show();
                    }
                    else if ((thn == thns ) && (bln == blns) && (tgl>tgls)) {
                        Toast.makeText(getApplication(), "Next Date Must be Greather", Toast.LENGTH_LONG).show();
                    }

                        else {

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
                        int jj = Integer.parseInt(jhr); // hasil hari
                        int jk = Integer.parseInt(jbl); // hasil bulan
                        int jl = Integer.parseInt(jtn); // hasil tahun

                        Calendar ca = Calendar.getInstance(); // --------
                        int h = ca.get(Calendar.DAY_OF_WEEK); // mengambil no hari dari sistem
                        int jbln = jk + 12*jl; // mengubah tahun dan bulan, ke jml bulan
                        int jhri = ubah (bln,jbln,thn); // mengubah jml bulan ke hari maju
                        String sting = b(h,jj+jhri); // mencari nama hari maju
                        hr.setText(sting);

                        String s1 = String.valueOf(jj + jhri); // jumlah hari total
                        hari.setText(s1);

                        int hsek = ca.get(Calendar.DAY_OF_WEEK) - 1 ;
                        String rr = day(hsek);
                        hl.setText(rr);

                        int mngu = (jj + jhri)/7;
                        String minggu = String.valueOf(mngu);
                        mng.setText(minggu);

                        int sisa = (jj + jhri)%7;
                        String sis = String.valueOf(sisa);
                        shr.setText(sis);


                        } // batas eksekusi tombol

                    }
                }
        });

    } // penutup listener


    private static String day(int b) {

        if (b % 7 == 0) {
            return "Sunday";
        }
        if (b % 7 == 1) {
            return "Monday";
        }
        if (b % 7 == 2) {
            return "Tuesday";
        }
        if (b % 7 == 3) {
            return "Wednesday";
        }
        if (b % 7 == 4) {
            return "Thursday";
        }
        if (b % 7 == 5) {
            return "Friday";
        }
        if (b % 7 == 6) {
            return "Saturday";
        }
        return "error";
    }

    private static String b (int h, int ttl) {

        // mencari nama hari dr ttl (maju)
        int jml = (h - 1) + (ttl % 7);
        System.out.println(jml);
        while (jml >= 7) {
            jml = jml % 7;
        }
        if (jml == 0) {
            return "Sunday";
        }
        if ((jml == 1) || (jml == -6)) {
            return "Monday";
        }
        if ((jml == 2) || (jml == -5)) {
            return "Tuesday";
        }
        if ((jml == 3) || (jml == -4)) {
            return "Wednesday";
        }
        if ((jml == 4) || (jml == -3)) {
            return "Thursday";
        }
        if ((jml == 5) || (jml == -2)) {
            return "Friday";
        }
        if ((jml == 6) || (jml == -1)) {
            return "Saturday";
        }
        return "error";
    }

    private static int ubah (int blns, int j, int thns){
        // mengubah bulan ke hari (maju)
        int hr = 0;
        while (j>0){
            hr = hr + fun(blns,thns);
            blns++;
            j--;
            while (blns>12){
                blns = blns - 12;
                thns++;
            }
        }
        return  hr;
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
    }  //-------------------------------------------
}





