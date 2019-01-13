package mac.yorum.android.app.activities;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import yorum.mac.com.macyorumandroid.R;

public class MainActivity extends BaseAppCompatActivitiy {

    private SharedPreferences prefs;
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

        Button  btn_match_reviews = (Button)findViewById(R.id.btn_match_reviews);
        Button btn_coupons =  (Button)findViewById(R.id.btn_coupons);

        txt_refno.setTypeface(type);
        btn_match_reviews.setTypeface(type);
        btn_coupons.setTypeface(type);
    }

    private void initButtons()
    {
        findViewById(R.id.btn_match_reviews).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new MatchReviewsListActivity());
            }
        });

        findViewById(R.id.btn_coupons).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new CouponListActivity());
            }
        });
    }
}
