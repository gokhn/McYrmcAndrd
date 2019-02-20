package mac.yorum.android.app.activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import mac.yorum.android.app.adapters.EmptyListAdapter;
import mac.yorum.android.app.adapters.MatchReviewsListAdapter;
import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.models.Result;
import mac.yorum.android.app.models.mainmodels.MatchReviewsList;
import mac.yorum.android.app.retrofit.RefrofitClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yorum.mac.com.macyorumandroid.R;

public class MatchReviewsListActivity extends  BaseAppCompatActivitiy
{

    private SharedPreferences prefs;
    RecyclerView mRecyclerView;
    EmptyListAdapter mAdapyerEmpty;
    MatchReviewsListAdapter mAdapter;
    String Token;

    @Override
    protected void onResume()
    {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Token =  prefs.getString("Token","");

        TextView txt_search = (TextView)findViewById(R.id.txt_search);
        txt_search.setText("");

        if(findTextViewById(R.id.txt_date).getText().length() >0)
        {
            GetList(findTextViewById(R.id.txt_date).getText().toString());
        }
        else
        {
            String curDate = getCurrentDate();
            findTextViewById(R.id.txt_date).setText(curDate);
            GetList(curDate);
        }
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchreviewslistactivity);

        Token =  prefs.getString("Token","");

        SetFont();
        String curDate = getCurrentDate();
        findTextViewById(R.id.txt_date).setText(curDate);

        initButtons();

        GetList(curDate);

    }

    private void SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        TextView txt_matchestoday = (TextView) findViewById(R.id.txt_matchestoday);
        TextView txt_date = (TextView) findViewById(R.id.txt_date);
        TextView txt_matchreviews = (TextView) findViewById(R.id.txt_matchreviews);
        TextView txt_search = (TextView)findViewById(R.id.txt_search);

        txt_matchestoday.setTypeface(type);
        txt_date.setTypeface(type);
        txt_matchreviews.setTypeface(type);
        txt_search.setTypeface(type);

    }

    private void initButtons()
    {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        findSwipeRefreshLayoutById(R.id.swipe_refresh_layout).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
               GetList(findTextViewById(R.id.txt_date).getText().toString());
                findSwipeRefreshLayoutById(R.id.swipe_refresh_layout).setRefreshing(false);
            }
        });


        findTextViewById(R.id.txt_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(MatchReviewsListActivity.this, new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();

            }
        });

        findViewById(R.id.btn_searchteamname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView txt_search = (TextView)findViewById(R.id.txt_search);

                if(txt_search != null && txt_search.length() >0 && txt_search.getText().toString().length() >0
                        &&!txt_search.getText().toString().equals(null) && !txt_search.getText().toString().equals(""))
                {
                    GetListTeamName(txt_search.getText().toString());
                }
            }
        });
    }

    private void GetListTeamName(final String teamName)
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
        Call<LoginResponse> servicecall = apiservice.MacYorumAraListe(Constants.API_KEY,Token, "text/json;charset=UTF-8", teamName);
        servicecall.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response)
            {
                try
                {
                    if (response.isSuccessful() && response.body() != null)
                    {
                        final  ArrayList<MatchReviewsList> lists = new ArrayList<MatchReviewsList>();
                        LoginResponse responseBody = response.body();
                        closeKeyboard();

                        if (!responseBody.Status.equals("200"))
                        {
                            if(response.body().Status.equals("999"))
                            {
                                clearUser();
                                newActivity(new LoginActivity());
                                finish();
                                toastMessage(MatchReviewsListActivity.this, getResources().getString(R.string.pleaserelogin));
                            }
                            else
                            {
                                toastMessage(MatchReviewsListActivity.this, response.body().Message.toString());
                            }
                        }
                        else
                        {
                            if(response.body().Result == null)
                            {
                                toastMessage(MatchReviewsListActivity.this, responseBody.Message.toString());
                            }
                            else
                            {
                                ArrayList<Result>  res = response.body().Result;

                                for(int i=0; i<res.size();i++)
                                {
                                    MatchReviewsList item = new MatchReviewsList();
                                    item.setId(res.get(i).getId());
                                    item.setRefKullanici(res.get(i).getRefKullanici());
                                    item.setIddaKodu(res.get(i).getIddaKodu());
                                    item.setYorumOlusturan(res.get(i).getYorumOlusturan());
                                    item.setKonukTakim(res.get(i).getKonukTakim());
                                    item.setEvSahibiTakim(res.get(i).getEvSahibiTakim());
                                    item.setOran(res.get(i).getOran());

                                    item.setMacTarihi(res.get(i).getMacTarihi());
                                    item.setMacYorumu(res.get(i).getMacYorumu());
                                    item.setMacSonucu(res.get(i).getMacSonucu());
                                    item.setOkunmaSayisi(res.get(i).getOkunmaSayisi());
                                    item.setKayitTarihi(res.get(i).getKayitTarihi());

                                    lists.add(item);
                                }
                            }


                        }
                        hideLoadingPopup();

                        binData(lists);
                    }
                    else
                    {
                        hideLoadingPopup();
                        toastMessage(MatchReviewsListActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {
                    Log.e("exlogin", ex.toString());
                    hideLoadingPopup();
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(MatchReviewsListActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });
    }




    private void binData(final ArrayList<MatchReviewsList> items)
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.matchreviews_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MatchReviewsListActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if (items.size() == 0) {
            mAdapyerEmpty = new EmptyListAdapter(this);
            mRecyclerView.setAdapter(mAdapyerEmpty);
        }
        else
        {
            mAdapter = new MatchReviewsListAdapter(MatchReviewsListActivity.this, items);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
    private void GetList(final String currentDate)
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
        Call<LoginResponse> servicecall = apiservice.MacYorumListe(Constants.API_KEY,Token, "text/json;charset=UTF-8", currentDate);
        servicecall.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response)
            {
                try
                {
                    if (response.isSuccessful() && response.body() != null)
                    {
                        final  ArrayList<MatchReviewsList> lists = new ArrayList<MatchReviewsList>();
                        LoginResponse responseBody = response.body();
                        closeKeyboard();

                        if (!responseBody.Status.equals("200"))
                        {
                            if(response.body().Status.equals("999"))
                            {
                                clearUser();
                                newActivity(new LoginActivity());
                                finish();
                                toastMessage(MatchReviewsListActivity.this, getResources().getString(R.string.pleaserelogin));
                            }
                            else
                            {
                                toastMessage(MatchReviewsListActivity.this, response.body().Message.toString());
                            }
                        }
                        else
                        {
                            if(response.body().Result == null)
                            {
                                toastMessage(MatchReviewsListActivity.this, responseBody.Message.toString());
                            }
                            else
                            {
                                ArrayList<Result>  res = response.body().Result;

                                for(int i=0; i<res.size();i++)
                                {
                                    MatchReviewsList item = new MatchReviewsList();
                                    item.setId(res.get(i).getId());
                                    item.setRefKullanici(res.get(i).getRefKullanici());
                                    item.setIddaKodu(res.get(i).getIddaKodu());
                                    item.setYorumOlusturan(res.get(i).getYorumOlusturan());
                                    item.setKonukTakim(res.get(i).getKonukTakim());
                                    item.setEvSahibiTakim(res.get(i).getEvSahibiTakim());
                                    item.setOran(res.get(i).getOran());

                                    item.setMacTarihi(res.get(i).getMacTarihi());
                                    item.setMacYorumu(res.get(i).getMacYorumu());
                                    item.setMacSonucu(res.get(i).getMacSonucu());
                                    item.setOkunmaSayisi(res.get(i).getOkunmaSayisi());
                                    item.setKayitTarihi(res.get(i).getKayitTarihi());

                                    lists.add(item);
                                }
                            }


                        }
                        hideLoadingPopup();

                        binData(lists);
                    }
                    else
                    {
                        hideLoadingPopup();
                        toastMessage(MatchReviewsListActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {
                    Log.e("exlogin", ex.toString());
                    hideLoadingPopup();
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(MatchReviewsListActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });



       //binData(matchReviewsListArrayList);
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
    private String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        c.set(mYear, mMonth, mDay );
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = sdf.format(c.getTime());

        return formattedDate;
    }
    public class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub

            Calendar c = Calendar.getInstance();
            c.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = sdf.format(c.getTime());
            findTextViewById(R.id.txt_date).setText(formattedDate);

            GetList(formattedDate);

        }
    }

    public void callMatchReviewsDetailActivity(String id)
    {
        newActivity(new MatchReviewsDetailActivity(),id);
    }

    public void callMatchReviewsDetailActivity(String id,String evsahibi,String konuk,String iddiaKodu,
                                               String mactarihi,String macsonucu,String yorum,String oran)
    {
        newActivity(new MatchReviewsDetailActivity(),id,evsahibi,konuk,iddiaKodu,mactarihi,macsonucu,yorum,oran);
    }

}
