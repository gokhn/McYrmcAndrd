package mac.yorum.android.app.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mac.yorum.android.app.helpers.Converter;
import yorum.mac.com.macyorumandroid.R;

public class CouponDetailActivity extends BaseAppCompatActivitiy {

    private String id;

    String name;
    String evsahibi;
    String konuktakim;
    String macssati;
    String yorum;
    String tahmin;
    String iddiakodu;
    String mactipi;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_detail_activity);

        id  = getIntent().getStringExtra("Id");

        evsahibi = getIntent().getStringExtra("EVSAHIBI");
        konuktakim = getIntent().getStringExtra("KONUKTAKIM");
        macssati = getIntent().getStringExtra("MACSAATI");
        yorum = getIntent().getStringExtra("YORUM");
        tahmin =  getIntent().getStringExtra("TAHMIN");
        iddiakodu = getIntent().getStringExtra("IDDIAKODU");
        mactipi =  getIntent().getStringExtra("MACTIPI");

        id  = getIntent().getStringExtra("Id");

        name = getIntent().getStringExtra("KUPONADI");

        SetFont();
        initButtons();


    }

    private void SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        TextView txt_league = (TextView) findViewById(R.id.txt_league);
        TextView txt_home = (TextView) findViewById(R.id.txt_home);
        TextView txt_date = (TextView) findViewById(R.id.txt_date);
        TextView txt_away = (TextView) findViewById(R.id.txt_away);
        TextView txt_score_prediction = (TextView) findViewById(R.id.txt_score_prediction);
        TextView txt_review = (TextView) findViewById(R.id.txt_review);
        TextView txt_review_detail = (TextView) findViewById(R.id.txt_review_detail);
        TextView txt_bedding_code = (TextView) findViewById(R.id.txt_bedding_code);

        txt_league.setTypeface(type);
        txt_home.setTypeface(type);
        txt_date.setTypeface(type);
        txt_away.setTypeface(type);
        txt_score_prediction.setTypeface(type);
        txt_review.setTypeface(type);
        txt_review_detail.setTypeface(type);
        txt_bedding_code.setTypeface(type);

        txt_league.setText(mactipi);
        txt_home.setText(evsahibi);
        txt_away.setText(konuktakim);
        txt_date.setText(Converter.stringToShortDate(macssati));
        txt_score_prediction.setText(tahmin);
        txt_review_detail.setText(yorum);
        txt_bedding_code.setText(iddiakodu);

    }

    private void  initButtons()
    {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

}
