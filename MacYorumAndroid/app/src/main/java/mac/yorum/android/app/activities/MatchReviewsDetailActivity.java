package mac.yorum.android.app.activities;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.helpers.Converter;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.retrofit.RefrofitClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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
    private SharedPreferences prefs;
    private String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchreviewsdetailactivity);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Token =  prefs.getString("Token","");

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

        GetList();
    }


    private void GetList()
    {
        showLoadingPopup();


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(UrlConfig.API_RETROFIT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient).build();
        RefrofitClass apiservice = retrofit.create(RefrofitClass.class);
        Call<LoginResponse> servicecall = apiservice.MacYorumOkuyan(Constants.API_KEY,Token, "text/json;charset=UTF-8", id);
        servicecall.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response)
            {
                if(response.isSuccessful() && response.body() != null && !response.body().Status.equals("200"))
                {
                    if(response.body().Status.equals("999"))
                    {
                        clearUser();
                        newActivity(new LoginActivity());
                        finish();
                        toastMessage(MatchReviewsDetailActivity.this, getResources().getString(R.string.pleaserelogin));
                    }
                    else
                    {
                        toastMessage(MatchReviewsDetailActivity.this, response.body().Message.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
        hideLoadingPopup();
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
