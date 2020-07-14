package com.name.neeraj.urcalculator;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.facebook.ads.AdSize;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import static com.name.neeraj.urcalculator.StandardCal.getApplicationName;


public class Matrixcalculator_main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String operation;

    Button b2, b0, b8, b9;
    ImageButton b1, b3, b4, b5, b6, b7, b10, b11, b12;


    NavigationView navigationView;
    private DrawerLayout drawer;
    FrameLayout navigation_framelayout;
    Toolbar toolbar;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    private com.facebook.ads.AdView adView;
    private com.facebook.ads.InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrixcalculator_main);

        initMethod();
        admobinitialize();
        faninitialize();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        clickListener();

    }


    private void faninitialize() {

        AudienceNetworkAds.initialize(this);

        //adView = new AdView(Converter_MainActivity.this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
        adView = new com.facebook.ads.AdView(this, "253012682465759_253661875734173", AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        adView.loadAd();

        interstitialAd = new com.facebook.ads.InterstitialAd(this, "253012682465759_253660982400929");
        interstitialAd.loadAd();

    }

    private void admobinitialize() {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2007637783238354/3078000696");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }

    private void initMethod() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        b0 = (Button) findViewById(R.id.button0);
        b1 = (ImageButton) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (ImageButton) findViewById(R.id.button3);
        b4 = (ImageButton) findViewById(R.id.button4);
        b5 = (ImageButton) findViewById(R.id.button5);
        b6 = (ImageButton) findViewById(R.id.button6);
        b7 = (ImageButton) findViewById(R.id.tranpose);
        b8 = (Button) findViewById(R.id.adjoint);
        b9 = (Button) findViewById(R.id.minor);
        b10 = (ImageButton) findViewById(R.id.ainverse_b);
        b11 = (ImageButton) findViewById(R.id.ainverse_binverse);
        b12 = (ImageButton) findViewById(R.id.a_b_inverse);

        View headView = navigationView.getHeaderView(0);
        TextView name_header = headView.findViewById(R.id.username);
        name_header.setText(getApplicationName(this));
    }


    private void nextActivity(int key) {
        Intent intent = new Intent(Matrixcalculator_main.this, final_result.class);
        intent.putExtra("key1", key);
        //  Toast.makeText((MainActivity.this),"Fill the Matrix",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    private void clickListener() {

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(0);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(3);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(4);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(5);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(6);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(7);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(8);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(9);
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(10);
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(11);
            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity(12);
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_standard:
                startActivity(new Intent(Matrixcalculator_main.this, StandardCal.class));
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(Matrixcalculator_main.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

                break;
            case R.id.nav_scientific:
                startActivity(new Intent(Matrixcalculator_main.this, ScientificCal.class));
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(Matrixcalculator_main.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

                break;
            case R.id.nav_converter:
                startActivity(new Intent(Matrixcalculator_main.this, Converter_MainActivity.class));
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(Matrixcalculator_main.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

                break;
            case R.id.nav_matrix:
                //startActivity(new Intent(Matrixcalculator_main.this, Matrixcalculator_main.class));
                break;
            case R.id.nav_invite:

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "    *" + getApplicationName(this) + "*\nAll in One calculator App\nDownload the app here:\n" + "http://play.google.com/store/apps/details?id=" + this.getPackageName();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getApplicationName(this));
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

                break;
            case R.id.nav_feedback:
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(Matrixcalculator_main.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "neerajprakashreddy@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback for 'Your Calculator App' ");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;
            case R.id.nav_Rate:
                Uri uri1 = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                goToMarket1.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket1);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }

        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}