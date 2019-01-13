package mac.yorum.android.app.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import yorum.mac.com.macyorumandroid.R;

public class MatchReviewsDetailActivity extends BaseAppCompatActivitiy {

    private String id;

    @Override
    protected void onResume() {
        super.onResume();
        GetList(id);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchreviewsdetailactivity);

        SetFont();
        initButtons();
        id  = getIntent().getStringExtra("Id");

        GetList(id);
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
        TextView txt_review_detail = (TextView) findViewById(R.id.txt_review);

        txt_league.setTypeface(type);
        txt_home.setTypeface(type);
        txt_date.setTypeface(type);
        txt_away.setTypeface(type);
        txt_score_prediction.setTypeface(type);
        txt_review.setTypeface(type);
        txt_review_detail.setTypeface(type);
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
    private void GetList(final String reviewId)
    {
        showLoadingPopup();

        hideLoadingPopup();

    }
}
