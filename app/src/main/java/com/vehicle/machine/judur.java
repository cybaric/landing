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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class judur extends AppCompatActivity {
    EditText tgl, bln, thn, tgls, blns, thns ;
    LinearLayout nue;
    Button refres, hitung;
    TextView htn, hbl, htg, hari, hl, hr, mng, shr;
    AnimationDrawable animationDrawable;
    InterstitialAd in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judur);

        MobileAds.initialize(this,"ca-app-pub-9560479013051071~6919702773");
        in = new InterstitialAd(judur.this);
        in.setAdUnitId(getString(R.string.inter));
        AdRequest minta = new AdRequest.Builder().build();
        in.loadAd(minta);
        in.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded(){
                displayInterstitial();
            }
        });

        nue = findViewById(R.id.ani);
        animationDrawable = (AnimationDrawable) nue.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(800);
        animationDrawable.start();

        tgls = findViewById(R.id.d1); // akhir
        blns = findViewById(R.id.d2);
        thns = findViewById(R.id.d3);

        tgl = findViewById(R.id.ed1); // awal
        bln = findViewById(R.id.ed2);
        thn = findViewById(R.id.ed3);

        htg = findViewById(R.id.e1); // hasil
        hbl = findViewById(R.id.e2);
        htn = findViewById(R.id.e3);

        hari = findViewById(R.id.hari);
        hl = findViewById(R.id.hariawal);
        hr = findViewById(R.id.hariakhir);
        mng = findViewById(R.id.mg);
        shr = findViewById(R.id.m1);

        refres = findViewById(R.id.ref);
        refres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(judur.this, judur.class);
                judur.this.startActivity(intent);
                judur.this.finish();
            }
        });

        hitung = findViewById(R.id.tbl2);
        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tgl.length() == 0 || bln.length() == 0 || thn.length() == 0 || tgls.length() == 0 || blns.length() == 0 || thns.length() == 0) {
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
                    int K = fun (bln,thn);
                    int L = fun(blns, thns);
                    if (K < tgl && bln == 2 && tgl == 29 ){
                        Toast.makeText(getApplication(), "February " + cx +" Only 28 Days", Toast.LENGTH_LONG).show();
                    }
                    else if (L < tgls && blns == 2 && tgls == 29 ){
                        Toast.makeText(getApplication(), "February " + cy +" Only 28 Days", Toast.LENGTH_LONG).show();
                    }
                    else if (K < tgl || L < tgls ) {
                        Toast.makeText(getApplication(), "Date Or Month Is Too Big", Toast.LENGTH_LONG).show();
                    }
                    else if (thn > thns ) {
                        Toast.makeText(getApplication(), "Start Year Must Be Smaller", Toast.LENGTH_LONG).show();
                    }
                    else if ((tgl == 0 || tgls == 0) || (thn == 0 || thns == 0) || (bln == 0 || blns == 0))  {
                        Toast.makeText(getApplication(), "Invalid date", Toast.LENGTH_LONG).show();
                    }
                    else if ((bln > 12 || bln < 1) || (blns > 12 || blns < 1)) {
                        Toast.makeText(getApplication(), "Invalid Month", Toast.LENGTH_LONG).show();
                    }
                    else if ((thn == thns ) && (bln > blns)) {
                        Toast.makeText(getApplication(), "Start Month Must Be Smaller Than The Next Month", Toast.LENGTH_LONG).show();
                    }
                    else if ((thn == thns ) && (bln == blns) && (tgl>tgls)) {
                        Toast.makeText(getApplication(), "Start Date Must Be Smaller Than The Next Date", Toast.LENGTH_LONG).show();
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
                        // menghitung hari dari tgl 1, bln 1, thn 1
                        int d = tgl -1, selisihbln = bln -1, selisihthn = thn-1;
                        int jbln = selisihbln + 12*selisihthn; // mengubah tahun dan bulan, ke jml bulan
                        int jhri = ubah (1,jbln,1); // mengubah jml bulan ke hari maju
                        String string = b(2,d+jhri); // mencari nama hari maju
                        hl.setText(string);

                        String jhr = htg.getText().toString();
                        String jbl = hbl.getText().toString();
                        String jtn = htn.getText().toString();
                        String hariawal =  hl.getText().toString();
                        // mengambil nilai hasi hari/tgl/thn dari textview hasil
                        int hhr = f(hariawal);
                        int jj = Integer.parseInt(jhr); // hasil tanggal
                        int jk = Integer.parseInt(jbl); // hasil bulan
                        int jl = Integer.parseInt(jtn); // hasil tahun

                        int jbln2= jk + 12*jl; // mengubah tahun dan bulan, ke jml bulan
                        int jhri2 = ubah (bln,jbln2,thn); // mengubah jml bulan ke hari maju
                        String sting = b(hhr,jj+jhri2); // mencari nama hari maju
                        hr.setText(sting);

                        String s1 = String.valueOf(jj + jhri2); // jumlah hari total
                        hari.setText(s1);
                        int mngu = (jj + jhri2)/7;
                        String minggu = String.valueOf(mngu);
                        mng.setText(minggu);

                        int sisa = (jj + jhri2)%7;
                        String sis = String.valueOf(sisa);
                        shr.setText(sis);
                    }
                }
            }
        });

    } // penutup listener

    private static int f (String a){
        if (a.equals("Sunday")){
            return 1;
        }
        if (a.equals("Monday")){
            return 2;
        }
        if (a.equals("TuesDay")){
            return 3;
        }
        if (a.equals("Wednesday")){
            return 4;
        }
        if (a.equals("Thursday")){
            return 5;
        }
        if (a.equals("Friday")){
            return 6;
        }
        if (a.equals("Saturday")){
            return 7;
        }
        return 0;
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
        /*if (j==0){
            return 0;
        }*/
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
        if (( a == 0 ) && (b==1)){
            return 0;
        }
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
            }else {
                return 28;
            }
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

    public void  displayInterstitial(){
        if (in.isLoaded()){
            in.show();
        }
    }

    }

