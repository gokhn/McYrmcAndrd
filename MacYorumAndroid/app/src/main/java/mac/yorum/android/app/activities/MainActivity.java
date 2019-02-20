package mac.yorum.android.app.activities;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import mac.yorum.android.app.widgets.YesNoPopup;
import yorum.mac.com.macyorumandroid.R;

public class MainActivity extends BaseAppCompatActivitiy {

    private SharedPreferences prefs;
    private long mLastPress = 0;
    int TOAST_DURATION = 5000;
    Toast onBackPressedToast;

    @Override
    public void onBackPressed() {
        closeKeyboard();

        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastPress > TOAST_DURATION) {
            onBackPressedToast = Toast.makeText(this, R.string.pressexitbutton, Toast.LENGTH_SHORT);
            onBackPressedToast.show();
            mLastPress = currentTime;
        } else {
            if (onBackPressedToast != null) {
                onBackPressedToast.cancel();  //Difference with previous answer. Prevent continuing showing toast after application exit.
                onBackPressedToast = null;
            }
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        SetFont();
        initButtons();

        String RefNo = prefs.getString("ReferansKodu","");
        findTextViewById(R.id.txt_refno).setText(RefNo);
    }

    private void  SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        TextView txt_refno = (TextView) findViewById(R.id.txt_refno);

        TextView  btn_match_reviews = (TextView)findViewById(R.id.btn_match_reviews);
        TextView btn_coupons =  (TextView)findViewById(R.id.btn_coupons);
        TextView btn_won_coupons = (TextView)findViewById(R.id.btn_won_coupons);

        txt_refno.setTypeface(type);
        btn_match_reviews.setTypeface(type);
        btn_coupons.setTypeface(type);
        btn_won_coupons.setTypeface(type);
    }

    public void clearUser()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("Token","");
        edit.putString("ReferansKodu", "");
        edit.putString("KullaniciAdi","");
        edit.putString("Parola","");
        edit.putString("AdSoyad","");
        edit.putString("Email","");
        edit.putString("Telefon","");
        edit.commit();
    }

    private void initButtons()
    {
        findViewById(R.id.btn_user_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new SettingsActivity());
            }
        });
        findViewById(R.id.btn_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new YesNoPopup(MainActivity.this, getResources().getString(R.string.do_you_want_to_quit)) {
                    @Override
                    protected void OnApplyConfirmation() {
                       clearUser();
                       newActivity(new LauncherActivity());
                       finish();
                    }
                };
            }
        });
        findTextViewById(R.id.btn_match_reviews).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new MatchReviewsListActivity());
            }
        });

        findTextViewById(R.id.btn_coupons).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new CouponListActivity());
            }
        });

        findTextViewById(R.id.btn_won_coupons).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new WonCouponListActivity());
            }
        });
    }
}
