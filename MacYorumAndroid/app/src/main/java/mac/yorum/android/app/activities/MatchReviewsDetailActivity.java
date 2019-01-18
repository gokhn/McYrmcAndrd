package mac.yorum.android.app.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mac.yorum.android.app.helpers.Converter;
import yorum.mac.com.macyorumandroid.R;

public class MatchReviewsDetailActivity extends BaseAppCompatActivitiy {


    private String id;
    private String evsahibi;
    private String konuktakim;
    private String iddiaKodu;
    private String mactarihi;
    private String macsonucu;
    private String yorum;
    private String oran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchreviewsdetailactivity);

        id  = getIntent().getStringExtra("Id");
        evsahibi = getIntent().getStringExtra("EVSAHIBI");
        konuktakim = getIntent().getStringExtra("KONUKTAKIM");
        iddiaKodu = getIntent().getStringExtra("IDDIAKODU");
        mactarihi = getIntent().getStringExtra("MACTARIHI");
        macsonucu = getIntent().getStringExtra("MACSONUCU");
        yorum = getIntent().getStringExtra("YORUM");
        oran =  getIntent().getStringExtra("ORAN");

        SetFont();
        initButtons();
    }

    private void SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

         TextView txt_bedding_code = (TextView)findViewById(R.id.txt_bedding_code);
        TextView txt_league = (TextView) findViewById(R.id.txt_league);
             TextView txt_home = (TextView) findViewById(R.id.txt_home);
              TextView txt_date = (TextView) findViewById(R.id.txt_date);
               TextView txt_away = (TextView) findViewById(R.id.txt_away);
                 TextView txt_score_prediction = (TextView) findViewById(R.id.txt_score_prediction);
                 TextView txt_review = (TextView) findViewById(R.id.txt_review);
              TextView txt_review_detail = (TextView) findViewById(R.id.txt_review_detail);

        txt_league.setTypeface(type);
        txt_home.setTypeface(type);
        txt_date.setTypeface(type);
        txt_away.setTypeface(type);
        txt_score_prediction.setTypeface(type);
        txt_review.setTypeface(type);
        txt_review_detail.setTypeface(type);


        txt_league.setText(getResources().getString(R.string.rate)+" " + oran);

        txt_home.setText(evsahibi);
        txt_away.setText(konuktakim);
        txt_date.setText(Converter.stringToShortDate(mactarihi));
        txt_review_detail.setText(yorum);
        txt_score_prediction.setText(macsonucu);
        txt_bedding_code.setText(getResources().getString(R.string.code)+" " +iddiaKodu);
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
