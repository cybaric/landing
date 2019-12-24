package com.vehicle.machine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class penghitung extends AppCompatActivity {
    EditText tgls, blns, thns, d, mg, bln, thn;
    Button ma, mu, refres;
    TextView hl, Htgl, Hbln, Hthn, hasil, hasil1, Htgl1,Hbln1,Hthn1,e3,ee3;
    InterstitialAd in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penghitung);

        MobileAds.initialize(this,"ca-app-pub-9560479013051071~6919702773");
        in = new InterstitialAd(penghitung.this);
        in.setAdUnitId(getString(R.string.inter));
        AdRequest minta = new AdRequest.Builder().build();
        in.loadAd(minta);
        in.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded(){
                displayInterstitial();
            }
        });

        d = findViewById(R.id.hr);
        mg = findViewById(R.id.ed11);
        bln = findViewById(R.id.ed21);
        thn = findViewById(R.id.ed31);
        hl = findViewById(R.id.hariawal);
        tgls = findViewById(R.id.ed1);
        blns = findViewById(R.id.ed2);
        thns = findViewById(R.id.ed3);
        Htgl = findViewById(R.id.e2);
        Hbln = findViewById(R.id.e1);
        Hthn = findViewById(R.id.e4);
        Htgl1 = findViewById(R.id.ee2);
        Hbln1 = findViewById(R.id.ee1);
        Hthn1 = findViewById(R.id.ee4);
        hasil = findViewById(R.id.tbl1);
        hasil1 = findViewById(R.id.hsil1);
        e3 = findViewById(R.id.e3);
        ee3 = findViewById(R.id.ee3);

        Calendar ca = Calendar.getInstance(); // --------
        int h = ca.get(Calendar.DAY_OF_WEEK);
        String dhs = namahari(h);
        hl.setText(dhs);
        int ts = ca.get(Calendar.DATE);
        String ou = String.valueOf(ts);
        tgls.setText(ou);
        int t = 1 + ca.get(Calendar.MONTH);
        String o = String.valueOf(t);
        blns.setText(o);
        int s = ca.get(Calendar.YEAR);
        String osi = String.valueOf(s);
        thns.setText(osi);

        refres = findViewById(R.id.ref);
        refres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(penghitung.this, penghitung.class);
                penghitung.this.startActivity(intent);
                penghitung.this.finish();
            }
        });


        ma = findViewById(R.id.maju);
        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (d.length() == 0 || mg.length() == 0 || bln.length() == 0 || thn.length() == 0 || tgls.length() == 0 || blns.length() == 0 || thns.length() == 0) {
                    Toast.makeText(getApplication(), "Please Input The Empty Blank\n(  Fill With Zero If It Ignored )", Toast.LENGTH_LONG).show();
                }
                else {
                    String ax = tgls.getText().toString();
                    String bx = blns.getText().toString();
                    String cx = thns.getText().toString();
                    int tgls = Integer.parseInt(ax);
                    int blns = Integer.parseInt(bx);
                    int thns = Integer.parseInt(cx);
                    String ay = d.getText().toString();
                    String by = mg.getText().toString();
                    String vx = bln.getText().toString();
                    String cy = thn.getText().toString();
                    int d = Integer.parseInt(ay);
                    int mg = Integer.parseInt(by);
                    int bln = Integer.parseInt(vx);
                    int thn = Integer.parseInt(cy);
                    Calendar ca = Calendar.getInstance(); // --------
                    int h = ca.get(Calendar.DAY_OF_WEEK);
                    int ttl = d + (7 * mg) + tgls;
                    int tl = ttl - tgls;
                    int jbl = bln + 12*thn; // mengubah tahun dan bulan, ke jml bulan
                    int jhr = ubah (blns,jbl,thns); // mengubah jml bulan ke hari
                    int smua = tl + jhr;
                    String sting = b(h,smua); // mencari nama hari
                    e3.setText(sting);

                    thns = thns + thn;
                    blns = blns + bln;
                    while (blns > 12) { // menghitung hasil tahun
                        blns = blns - 12;
                        thns = thns + 1;
                    }

                    while (ttl > fun(blns, thns)) {
                        ttl = ttl - fun(blns, thns);
                        blns++;

                        while (blns > 12) {
                            blns = blns - 12;
                            thns = thns + 1;
                        }
                    }
                    String p = String.valueOf(ttl);
                    Htgl.setText(p);
                    String q = nmbln(blns);
                    Hbln.setText(q);
                    String r = String.valueOf(thns);
                    Hthn.setText(r);
                    String s = ay + " Days "+ by + " Weeks " + vx + " Months " + cy + " Years To The Future Is";
                    hasil.setText(s);
                }
            }
        });

        mu = findViewById(R.id.mundur);
        mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (d.length() == 0 || mg.length() == 0 || bln.length() == 0 || thn.length() == 0 || tgls.length() == 0 || blns.length() == 0 || thns.length() == 0) {
                    Toast.makeText(getApplication(), "Please Input The Empty Blank\n(  Fill With Zero If It Ignored  )", Toast.LENGTH_LONG).show();
                }
                else {
                    String ax = tgls.getText().toString();
                    String bx = blns.getText().toString();
                    String cx = thns.getText().toString();
                    int tgls = Integer.parseInt(ax);
                    int blns = Integer.parseInt(bx);
                    int thns = Integer.parseInt(cx);
                    String ay = d.getText().toString();
                    String by = mg.getText().toString();
                    String vx = bln.getText().toString();
                    String cy = thn.getText().toString();
                    int d = Integer.parseInt(ay);
                    int mg = Integer.parseInt(by);
                    int bln = Integer.parseInt(vx);
                    int thn = Integer.parseInt(cy);
                    int ttl;

                    if (thn >= 10000) {
                        Toast.makeText(getApplication(), " Sorry, Out Of Range", Toast.LENGTH_LONG).show();
                    }
                    else {
                        ttl = d + (7 * mg);
                        Calendar ca = Calendar.getInstance(); // --------
                        int h = ca.get(Calendar.DAY_OF_WEEK); // mengambil no hari
                        int jbl = bln + 12 * thn; // mengubah tahun dan bulan, ke jml bulan
                        int jhr = ubih(blns, jbl, thns); // mengubah jml bulan ke hari mundur
                        String sting = a(h, ttl + jhr); // mencari nama hari mundur
                        ee3.setText(sting);
                        int j = ttl + jhr;
                        while (j > 0) {

                            while (blns < 2) {
                                blns = blns + 12;
                                thns--;
                            }

                            while (tgls < 2) {
                                tgls = tgls + fun(blns - 1, thns);
                                blns--;
                            }

                            tgls--;
                            j--;
                        }
                        while (blns > 12) {
                            blns = blns - 12;
                            thns++;
                        }

                        String p = String.valueOf(tgls);
                        Htgl1.setText(p);
                        String q = nmbln(blns);
                        Hbln1.setText(q);
                        String r = String.valueOf(thns);
                        Hthn1.setText(r);

                        String s = ay + " Days " + by + " Weeks " + vx + " Months " + cy + " Years To The Past Is";
                        hasil1.setText(s);
                    }

                }
            }
        });


    }

    private static  String nmbln(int a){
        if (a==1){
            return "Jan" ;
        }
        if (a==2){
            return "Feb" ;
        }
        if (a==3){
            return "Mar" ;
        }
        if (a==4){
            return "Apr" ;
        }
        if (a==5){
            return "May" ;
        }
        if (a==6){
            return "Jun" ;
        }
        if (a==7){
            return "Jul" ;
        }
        if (a==8){
            return "Aug" ;
        }
        if (a==9){
            return "Sep" ;
        }
        if (a==10){
            return "Oct" ;
        }
        if (a==11){
            return "Nov" ;
        }
        if (a==12){
            return "Dec" ;
        }
        return "Error";
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

    private static String b (int h, int ttl){
        //maju
        int jml = (h - 1) + (ttl % 7);
        System.out.println(jml);
        while (jml >=7){
            jml = jml % 7;
        }
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

    private static int ubah (int blns, int j, int thns) {
        // mengubah bulan ke hari maju
        int hr = 0;
        while (j > 0) {
            hr = hr + fun(blns, thns);
            blns++;
            j--;
            while (blns > 12) {
                blns = blns - 12;
                thns++;
            }
        }
        return hr;
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

    private static int fun(int a, int b){ // ---------------------
        if ( a==0){
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
            return "Friday";
        }
        if (a == 7){
            return "Saturday";
        }
        return  "error";

    }

    public void  displayInterstitial(){
        if (in.isLoaded()){
            in.show();
        }
    }
}
