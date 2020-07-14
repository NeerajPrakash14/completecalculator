package com.name.neeraj.urcalculator;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.material.navigation.NavigationView;

import static com.name.neeraj.urcalculator.StandardCal.getApplicationName;

public class ScientificCal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = ScientificCal.class.getSimpleName();
    private EditText e2;
    TextView e1;
    private int count = 0;
    private String expression = "";
    private String text = "";
    private Double result = 0.0;
    private DBHelper dbHelper;
    private Button mode, toggle, square, xpowy, log, sin, cos, tan, sqrt, fact;
    private int toggleMode = 1;
    private int angleMode = 1;

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
        setContentView(R.layout.activity_scientific_cal);

        initMethod();
        admobinitialize();
        faninitialize();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle_actionbar = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle_actionbar);
        toggle_actionbar.syncState();

        e2.setText("0");

        //tags to change the mode from degree to radian and vice versa
        mode.setTag(1);
        //tags to change the names of the buttons performing different operations
        toggle.setTag(1);
    }


    private void initMethod() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        dbHelper = new DBHelper(this);


        e1 = (TextView) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        mode = (Button) findViewById(R.id.mode);
        toggle = (Button) findViewById(R.id.toggle);
        square = (Button) findViewById(R.id.square);
        xpowy = (Button) findViewById(R.id.xpowy);
        log = (Button) findViewById(R.id.log);
        sin = (Button) findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tan = (Button) findViewById(R.id.tan);
        sqrt = (Button) findViewById(R.id.sqrt);
        fact = (Button) findViewById(R.id.factorial);
        dbHelper = new DBHelper(this);


        View headView = navigationView.getHeaderView(0);
        TextView name_header = headView.findViewById(R.id.username);
        name_header.setText(getApplicationName(this));
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

        MobileAds.initialize(this);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2007637783238354/3078000696");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        /*mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideAd();
        mRewardedVideoAd.show();*/

    }

    public void onClick(View v) {
        toggleMode = (int) toggle.getTag();
        angleMode = ((int) mode.getTag());
        switch (v.getId()) {

            case R.id.toggle:
                //change the button text if switch button is clicked
                if (toggleMode == 1) {
                    toggle.setTag(2);
                    square.setText(R.string.cube);
                    xpowy.setText(R.string.tenpow);
                    log.setText(R.string.naturalLog);
                    sin.setText(R.string.sininv);
                    cos.setText(R.string.cosinv);
                    tan.setText(R.string.taninv);
                    sqrt.setText(R.string.cuberoot);
                    fact.setText(R.string.Mod);
                } else if (toggleMode == 2) {
                    toggle.setTag(3);
                    square.setText(R.string.square);
                    xpowy.setText(R.string.epown);
                    log.setText(R.string.log);
                    sin.setText(R.string.hyperbolicSine);
                    cos.setText(R.string.hyperbolicCosine);
                    tan.setText(R.string.hyperbolicTan);
                    sqrt.setText(R.string.inverse);
                    fact.setText(R.string.factorial);
                } else if (toggleMode == 3) {
                    toggle.setTag(1);
                    sin.setText(R.string.sin);
                    cos.setText(R.string.cos);
                    tan.setText(R.string.tan);
                    sqrt.setText(R.string.sqrt);
                    xpowy.setText(R.string.xpown);
                }
                break;

            case R.id.mode:
                //change the angle property for trignometric operations if mode button is clicked
                if (angleMode == 1) {
                    mode.setTag(2);
                    mode.setText(R.string.mode2);
                } else {
                    mode.setTag(1);
                    mode.setText(R.string.mode1);
                }
                break;

            case R.id.num0:
                e2.setText(e2.getText() + "0");
                break;

            case R.id.num1:
                e2.setText(e2.getText() + "1");
                break;

            case R.id.num2:
                e2.setText(e2.getText() + "2");
                break;

            case R.id.num3:
                e2.setText(e2.getText() + "3");
                break;


            case R.id.num4:
                e2.setText(e2.getText() + "4");
                break;

            case R.id.num5:
                e2.setText(e2.getText() + "5");
                break;

            case R.id.num6:
                e2.setText(e2.getText() + "6");
                break;

            case R.id.num7:
                e2.setText(e2.getText() + "7");
                break;

            case R.id.num8:
                e2.setText(e2.getText() + "8");
                break;

            case R.id.num9:
                e2.setText(e2.getText() + "9");
                break;

            case R.id.pi:
                e2.setText(e2.getText() + "pi");
                break;

            case R.id.dot:
                if (count == 0 && e2.length() != 0) {
                    e2.setText(e2.getText() + ".");
                    count++;
                }
                break;

            case R.id.clear:
                e1.setText("");
                e2.setText("");
                count = 0;
                expression = "";
                break;

            case R.id.backSpace:
                text = e2.getText().toString();
                if (text.length() > 0) {
                    if (text.endsWith(".")) {
                        count = 0;
                    }
                    String newText = text.substring(0, text.length() - 1);
                    //to delete the data contained in the brackets at once
                    if (text.endsWith(")")) {
                        char[] a = text.toCharArray();
                        int pos = a.length - 2;
                        int counter = 1;
                        //to find the opening bracket position
                        for (int i = a.length - 2; i >= 0; i--) {
                            if (a[i] == ')') {
                                counter++;
                            } else if (a[i] == '(') {
                                counter--;
                            }
                            //if decimal is deleted b/w brackets then count should be zero
                            else if (a[i] == '.') {
                                count = 0;
                            }
                            //if opening bracket pair for the last bracket is found
                            if (counter == 0) {
                                pos = i;
                                break;
                            }
                        }
                        newText = text.substring(0, pos);
                    }
                    //if e2 edit text contains only - sign or sqrt or any other text functions
                    // at last then clear the edit text e2
                    if (newText.equals("-") || newText.endsWith("sqrt") || newText.endsWith("log") || newText.endsWith("ln")
                            || newText.endsWith("sin") || newText.endsWith("asin") || newText.endsWith("asind") || newText.endsWith("sinh")
                            || newText.endsWith("cos") || newText.endsWith("acos") || newText.endsWith("acosd") || newText.endsWith("cosh")
                            || newText.endsWith("tan") || newText.endsWith("atan") || newText.endsWith("atand") || newText.endsWith("tanh")
                            || newText.endsWith("cbrt")) {
                        newText = "";
                    }
                    //if pow sign is left at the last or divide sign
                    else if (newText.endsWith("^") || newText.endsWith("/"))
                        newText = newText.substring(0, newText.length() - 1);
                    else if (newText.endsWith("pi") || newText.endsWith("e^"))
                        newText = newText.substring(0, newText.length() - 2);
                    e2.setText(newText);
                }
                break;

            case R.id.plus:
                operationClicked("+");
                break;

            case R.id.minus:
                operationClicked("-");
                break;

            case R.id.divide:
                operationClicked("/");
                break;

            case R.id.multiply:
                operationClicked("*");
                break;

            case R.id.sqrt:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    toggleMode = (int) toggle.getTag();
                    if (toggleMode == 1)
                        e2.setText("sqrt(" + text + ")");
                    else if (toggleMode == 2)
                        e2.setText("cbrt(" + text + ")");
                    else
                        e2.setText("1/(" + text + ")");
                }
                break;

            case R.id.square:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if (toggleMode == 2)
                        e2.setText("(" + text + ")^3");
                    else
                        e2.setText("(" + text + ")^2");
                }
                break;

            case R.id.xpowy:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if (toggleMode == 1)
                        e2.setText("(" + text + ")^");
                    else if (toggleMode == 2)
                        e2.setText("10^(" + text + ")");
                    else
                        e2.setText("e^(" + text + ")");
                }
                break;

            case R.id.log:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if (toggleMode == 2)
                        e2.setText("ln(" + text + ")");
                    else
                        e2.setText("log(" + text + ")");
                }
                break;

            case R.id.factorial:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if (toggleMode == 2) {
                        e1.setText("(" + text + ")%");
                        e2.setText("");
                    } else {
                        String res = "";
                        try {
                            CalculateFactorial cf = new CalculateFactorial();
                            int[] arr = cf.factorial((int) Double.parseDouble(String.valueOf(new ExtendedDoubleEvaluator().evaluate(text))));
                            int res_size = cf.getRes();
                            if (res_size > 20) {
                                for (int i = res_size - 1; i >= res_size - 20; i--) {
                                    if (i == res_size - 2)
                                        res += ".";
                                    res += arr[i];
                                }
                                res += "E" + (res_size - 1);
                            } else {
                                for (int i = res_size - 1; i >= 0; i--) {
                                    res += arr[i];
                                }
                            }
                            e2.setText(res);
                        } catch (Exception e) {
                            if (e.toString().contains("ArrayIndexOutOfBoundsException")) {
                                e2.setText("Result too big!");
                            } else
                                e2.setText("Invalid!!");
                            e.printStackTrace();
                        }
                    }
                }
                break;

            case R.id.sin:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if (angleMode == 1) {
                        double angle = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                        if (toggleMode == 1)
                            e2.setText("sin(" + angle + ")");
                        else if (toggleMode == 2)
                            e2.setText("asind(" + text + ")");
                        else
                            e2.setText("sinh(" + text + ")");
                    } else {
                        if (toggleMode == 1)
                            e2.setText("sin(" + text + ")");
                        else if (toggleMode == 2)
                            e2.setText("asin(" + text + ")");
                        else
                            e2.setText("sinh(" + text + ")");
                    }
                }
                break;

            case R.id.cos:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if (angleMode == 1) {
                        double angle = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                        if (toggleMode == 1)
                            e2.setText("cos(" + angle + ")");
                        else if (toggleMode == 2)
                            e2.setText("acosd(" + text + ")");
                        else
                            e2.setText("cosh(" + text + ")");
                    } else {
                        if (toggleMode == 1)
                            e2.setText("cos(" + text + ")");
                        else if (toggleMode == 2)
                            e2.setText("acos(" + text + ")");
                        else
                            e2.setText("cosh(" + text + ")");
                    }
                }
                break;

            case R.id.tan:
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    if (angleMode == 1) {
                        double angle = Math.toRadians(new ExtendedDoubleEvaluator().evaluate(text));
                        if (toggleMode == 1)
                            e2.setText("tan(" + angle + ")");
                        else if (toggleMode == 2)
                            e2.setText("atand(" + text + ")");
                        else
                            e2.setText("tanh(" + text + ")");
                    } else {
                        if (toggleMode == 1)
                            e2.setText("tan(" + text + ")");
                        else if (toggleMode == 2)
                            e2.setText("atan(" + text + ")");
                        else
                            e2.setText("tanh(" + text + ")");
                    }
                }
                break;

            case R.id.posneg:
                if (e2.length() != 0) {
                    String s = e2.getText().toString();
                    char arr[] = s.toCharArray();
                    if (arr[0] == '-')
                        e2.setText(s.substring(1, s.length()));
                    else
                        e2.setText("-" + s);
                }
                break;

            case R.id.equal:
                /*loadRewardedVideAd();
                startVideoAd();*/
                if (e2.length() != 0) {
                    text = e2.getText().toString();
                    expression = e1.getText().toString() + text;
                }
                e1.setText("");
                if (expression.length() == 0)
                    expression = "0.0";
                try {
                    //evaluate the expression
                    result = new ExtendedDoubleEvaluator().evaluate(expression);
                    //insert expression and result in sqlite database if expression is valid and not 0.0
                    if (String.valueOf(result).equals("6.123233995736766E-17")) {
                        result = 0.0;
                        e2.setText(result + "");
                    } else if (String.valueOf(result).equals("1.633123935319537E16"))
                        e2.setText("infinity");
                    else
                        e2.setText(result + "");
                    if (!expression.equals("0.0"))
                        dbHelper.insert("SCIENTIFIC", expression + " = " + result);
                } catch (Exception e) {
                    e2.setText("Invalid Expression");
                    e1.setText("");
                    expression = "";
                    e.printStackTrace();
                }
                break;

            case R.id.openBracket:
                e1.setText(e1.getText() + "(");
                break;

            case R.id.closeBracket:
                if (e2.length() != 0)
                    e1.setText(e1.getText() + e2.getText().toString() + ")");
                else
                    e1.setText(e1.getText() + ")");
                break;
        }
    }

    private void operationClicked(String op) {
        if (e2.length() != 0) {
            String text = e2.getText().toString();
            e1.setText(e1.getText() + text + op);
            e2.setText("");
            count = 0;
        } else {
            String text = e1.getText().toString();
            if (text.length() > 0) {
                String newText = text.substring(0, text.length() - 1) + op;
                e1.setText(newText);
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_standard:
                startActivity(new Intent(ScientificCal.this, StandardCal.class));
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(ScientificCal.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

                /*mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
                mRewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) this);*/
                break;
            case R.id.nav_scientific:
                //startActivity(new Intent(ScientificCal.this, ScientificCal.class));
                break;
            case R.id.nav_converter:
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(ScientificCal.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

               /* mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
                mRewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) this);*/
                startActivity(new Intent(ScientificCal.this, Converter_MainActivity.class));

                break;
            case R.id.nav_matrix:
                startActivity(new Intent(ScientificCal.this, Matrixcalculator_main.class));
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    //Toast.makeText(ScientificCal.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
                }
                if (interstitialAd.isAdLoaded()) {
                    interstitialAd.show();
                }

                /*mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
                mRewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) this);*/
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
                    //Toast.makeText(ScientificCal.this, "The Ad wasn't loaded yet.", Toast.LENGTH_SHORT).show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.standard_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.history) {
            if (interstitialAd.isAdLoaded()) {
                interstitialAd.show();
            }

            Intent i = new Intent(this, History.class);
            i.putExtra("calcName", "SCIENTIFIC");
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
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

    }*/

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