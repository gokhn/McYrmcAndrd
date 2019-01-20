package mac.yorum.android.app.activities;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import mac.yorum.android.app.adapters.CouponDetailListAdapter;
import mac.yorum.android.app.adapters.EmptyListAdapter;
import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.models.Result;
import mac.yorum.android.app.models.mainmodels.CouponMatch;
import mac.yorum.android.app.retrofit.RefrofitClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yorum.mac.com.macyorumandroid.R;

public class CouponDetailListActivity extends BaseAppCompatActivitiy {

    RecyclerView mRecyclerView;
    EmptyListAdapter mAdapyerEmpty;
    CouponDetailListAdapter mAdapter;
    private SharedPreferences prefs;
    String name;
    String id;
    String Token;

    @Override
    protected void onResume()
    {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        id  = getIntent().getStringExtra("Id");

        findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setRefreshing(true);
        GetList(id);
        findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setRefreshing(false);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_detail_list_activity);

        id  = getIntent().getStringExtra("Id");

        name = getIntent().getStringExtra("KUPONADI");

        findTextViewById(R.id.txt_coupon_name).setText(name);

        SetFont();

        initButtons();

        Token =  prefs.getString("Token","");

        findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setRefreshing(true);
         GetList(id);
        findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setRefreshing(false);

    }
    private void SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        TextView txt_header = (TextView) findViewById(R.id.txt_header);
        TextView txt_coupon_name = (TextView) findViewById(R.id.txt_coupon_name);

        txt_header.setTypeface(type);
        txt_coupon_name.setTypeface(type);
    }

    private void initButtons()
    {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                GetList(id);
                findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setRefreshing(false);
            }
        });
    }
    private void binData(final ArrayList<CouponMatch> items)
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.coupondetail_list_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CouponDetailListActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if (items.size() == 0) {
            mAdapyerEmpty = new EmptyListAdapter(this);
            mRecyclerView.setAdapter(mAdapyerEmpty);
        }
        else
        {
            mAdapter = new CouponDetailListAdapter(CouponDetailListActivity.this, items);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
    private void GetList(final String id)
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
        Call<LoginResponse> servicecall = apiservice.KuponListeListeDetay(Constants.API_KEY,Token, "text/json;charset=UTF-8", id);
        servicecall.enqueue(new Callback<LoginResponse>() {


            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response)
            {
                try
                {
                    if (response.isSuccessful() && response.body() != null)
                    {
                        final ArrayList<CouponMatch>   couponMatch = new ArrayList<CouponMatch>();
                        LoginResponse responseBody = response.body();
                        closeKeyboard();

                        if (!responseBody.Status.equals("200"))
                        {
                            if(responseBody.Status.equals("999"))
                            {
                                clearUser();
                                newActivity(new LoginActivity());
                                finish();
                                toastMessage(CouponDetailListActivity.this, getResources().getString(R.string.pleaserelogin));
                            }
                            else
                            {
                                toastMessage(CouponDetailListActivity.this, responseBody.Message.toString());
                            }
                        }
                        else
                        {
                            ArrayList<Result>  res = response.body().Result;

                            for(int i=0; i<res.size();i++)
                            {
                                CouponMatch item = new CouponMatch();
                                item.Id = res.get(i).getId();
                                item.MacTipi = res.get(i).getMacTipi();
                                item.IddaKodu = res.get(i).getIddaKodu();
                                item.EvSahibiTakim =res.get(i).getEvSahibiTakim();
                                item.KonukTakim = res.get(i).getKonukTakim();
                                item.Tahmin = res.get(i).getTahmin();
                                item.Oran = res.get(i).getOran();
                                item.Yorum = res.get(i).getYorum();
                                item.MacTarihi = res.get(i).getMacTarihi();
                                item.KayitTarihi = res.get(i).getKayitTarihi();
                                item.KuponAd = res.get(i).getKuponAd();

                                couponMatch.add(item);
                            }

                        }
                        hideLoadingPopup();

                        binData(couponMatch);
                    }
                    else
                        {
                        hideLoadingPopup();
                        toastMessage(CouponDetailListActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {
                    Log.e("exlogin", ex.toString());
                    hideLoadingPopup();
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(CouponDetailListActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });
    }

    public void callDetailActivity(String Id, String evssahibi,String konuktakım,String macsaati,String yorum ,String tahmin,String iddakodu,String mactipi,String kuponad)
    {
        newActivity(new CouponDetailActivity(),Id,evssahibi,konuktakım,macsaati,yorum,tahmin,iddakodu,mactipi,kuponad);
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

}
