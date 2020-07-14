package com.name.neeraj.urcalculator;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
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
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.material.navigation.NavigationView;

import static com.name.neeraj.urcalculator.StandardCal.getApplicationName;

public class Converter_MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    NavigationView navigationView;
    private DrawerLayout drawer;
    FrameLayout navigation_framelayout;
    Toolbar toolbar;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;

    private com.facebook.ads.AdView adView;
    private com.facebook.ads.InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter__main);


        faninitialize();
        initMethod();
        admobinitialize();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


    }

    private void faninitialize() {

        AudienceNetworkAds.initialize(this);

        //adView = new AdView(Converter_MainActivity.this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50);
        adView = new com.facebook.ads.AdView(Converter_MainActivity.this, "253012682465759_253661875734173", AdSize.BANNER_HEIGHT_50);
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

       /* mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideAd();
        mRewardedVideoAd.show();*/

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            //Toast.makeText(Converter_MainActivity.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
        }

    }

    private void initMethod() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headView = navigationView.getHeaderView(0);
        TextView name_header = headView.findViewById(R.id.username);
        name_header.setText(getApplicationName(this));
    }


    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.area:

                startActivity(new Intent(this, UnitArea.class));
                break;
            case R.id.length:

                startActivity(new Intent(this, UnitLength.class));
                break;
            case R.id.weight:

                startActivity(new Intent(this, UnitWeight.class));
                break;
            case R.id.temperature:
                startActivity(new Intent(this, UnitTemperature.class));
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_standard:
                startActivity(new Intent(Converter_MainActivity.this, StandardCal.class));
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(Converter_MainActivity.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

                break;
            case R.id.nav_scientific:
                startActivity(new Intent(Converter_MainActivity.this, ScientificCal.class));
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(Converter_MainActivity.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

                break;
            case R.id.nav_converter:
                // startActivity(new Intent(Converter_MainActivity.this, Converter_MainActivity.class));

                break;
            case R.id.nav_matrix:
                startActivity(new Intent(Converter_MainActivity.this, Matrixcalculator_main.class));
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    // Toast.makeText(Converter_MainActivity.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

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
                    //Toast.makeText(Converter_MainActivity.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
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
/*


    private void loadRewardedVideAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd("ca-app-pub-2007637783238354/1830619781", new AdRequest.Builder().build());
        }
    }

    private void startVideoAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }


    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideAd();
        mRewardedVideoAd.show();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
*/

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
