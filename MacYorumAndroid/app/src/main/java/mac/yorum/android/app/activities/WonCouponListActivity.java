package mac.yorum.android.app.activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
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
import mac.yorum.android.app.adapters.WonCouponListAdapter;
import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.models.Result;
import mac.yorum.android.app.models.mainmodels.Coupon;
import mac.yorum.android.app.retrofit.RefrofitClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yorum.mac.com.macyorumandroid.R;

public class WonCouponListActivity extends BaseAppCompatActivitiy {

    RecyclerView mRecyclerView;
    EmptyListAdapter mAdapyerEmpty;
    WonCouponListAdapter mAdapter;
    private SharedPreferences prefs;
    String Token;

    boolean IsCouponGuarantee = true;

    //1 bnko
    //2 misli

    @Override
    protected void onResume()
    {
        String curDate = getCurrentDate();
        findTextViewById(R.id.txt_date).setText(curDate);

        findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(true);
        if(IsCouponGuarantee)
        {
            setActiveGuaranteeColor();
            setPassiveFoldColor();
            GetList("1",curDate);
            findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));
        }
        else
        {
            setActiveFoldColor();
            setPassiveGuaranteeColor();
            GetList("2",curDate);
            findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.fold));
        }

        findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(false);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.couponlist_activity);


        SetFont();
        initButtons();

        String curDate = getCurrentDate();
        findTextViewById(R.id.txt_date).setText(curDate);

        Token =  prefs.getString("Token","");

        findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(true);
        if(IsCouponGuarantee)
        {
            setActiveGuaranteeColor();
            setPassiveFoldColor();
            GetList("1",curDate);
            findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));
        }
        else
        {
            setActiveFoldColor();
            setPassiveGuaranteeColor();
            GetList("2",curDate);
            findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.fold));
        }
    }

    public void GetList(final String couponType,String date)
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
        Call<LoginResponse> servicecall = apiservice.KazananKuponListe(Constants.API_KEY,Token, "text/json;charset=UTF-8", couponType,date);
        servicecall.enqueue(new Callback<LoginResponse>() {


            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                try
                {
                    if (response.isSuccessful() && response.body() != null) {

                        LoginResponse responseBody = response.body();
                        closeKeyboard();

                        if (!responseBody.Status.equals("200"))
                        {
                            toastMessage(WonCouponListActivity.this, responseBody.Result.toString());
                        }
                        else
                        {
                            ArrayList<Result> res = response.body().Result;
                            ArrayList<Coupon> couponList = new ArrayList<>();
                            for(int i=0; i<res.size();i++)
                            {
                                Coupon item = new Coupon();
                                item.Id = res.get(i).getId();
                                item.Aciklama = res.get(i).getAciklama();
                                item.GecerlilikSuresi = res.get(i).getGecerlilikSuresi();
                                item.Aktif =res.get(i).getAktif();
                                item.KazancFiyat = res.get(i).getKazancFiyat();
                                item.KuponFiyat = res.get(i).getKuponFiyat();
                                item.KuponAd = res.get(i).getKuponAd();
                                item.KuponTipi = res.get(i).getKuponTipi();
                                item.MacAdedi = res.get(i).getMacAdedi();
                                item.ToplamOran = res.get(i).getToplamOran();
                                item.KuponFiyat = res.get(i).getKuponFiyat();
                                item.KayitTarihi = res.get(i).getKayitTarihi();
                                couponList.add(item);

                            }

                            binData(couponList);
                        }
                        hideLoadingPopup();
                    } else {
                        hideLoadingPopup();
                        toastMessage(WonCouponListActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {
                    Log.e("exlogin", ex.toString());
                    hideLoadingPopup();
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(WonCouponListActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });


        //  binData(list);
    }

    private void binData(final ArrayList<Coupon> items)
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.couponslist_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WonCouponListActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if (items.size() == 0) {
            mAdapyerEmpty = new EmptyListAdapter(this);
            mRecyclerView.setAdapter(mAdapyerEmpty);
        }
        else
        {
            mAdapter = new WonCouponListAdapter(WonCouponListActivity.this, items);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        TextView txt_matchestoday = (TextView) findViewById(R.id.txt_matchestoday);
        TextView txt_guarantee = (TextView) findViewById(R.id.txt_guarantee);
        TextView txt_fold = (TextView) findViewById(R.id.txt_fold);
        TextView txt_header = (TextView) findViewById(R.id.txt_header);
        TextView txt_date = (TextView)findViewById(R.id.txt_date);

        txt_matchestoday.setTypeface(type);
        txt_guarantee.setTypeface(type);
        txt_fold.setTypeface(type);
        txt_header.setTypeface(type);
        txt_date.setTypeface(type);

        txt_header.setText(getResources().getString(R.string.woncoupons));

    }
    private void initButtons()
    {

        findTextViewById(R.id.txt_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(WonCouponListActivity.this, new WonCouponListActivity.mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();

            }
        });

        findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {


                if(IsCouponGuarantee)
                {
                    setActiveGuaranteeColor();
                    setPassiveFoldColor();
                    GetList("1",findTextViewById(R.id.txt_date).getText().toString());
                    findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));

                }
                else
                {
                    setActiveFoldColor();
                    setPassiveGuaranteeColor();
                    GetList("2",findTextViewById(R.id.txt_date).getText().toString());
                    findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.fold));
                }

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(false);
            }
        });



        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        findTextViewById(R.id.txt_guarantee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setActiveGuaranteeColor();
                setPassiveFoldColor();
                IsCouponGuarantee = true;

                findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(true);

                GetList("1",findTextViewById(R.id.txt_date).getText().toString());

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(false);

            }
        });

        findTextViewById(R.id.txt_fold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setActiveFoldColor();
                setPassiveGuaranteeColor();
                IsCouponGuarantee = false;
                findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.fold));

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(true);

                GetList("2",findTextViewById(R.id.txt_date).getText().toString());

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(false);

            }
        });
    }

    private void setActiveGuaranteeColor()
    {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            findTextViewById(R.id.txt_guarantee).setBackgroundDrawable(ContextCompat.getDrawable(WonCouponListActivity.this, R.drawable.roun_rect_white) );
            findTextViewById(R.id.txt_guarantee).setTextColor(ContextCompat.getColor(WonCouponListActivity.this, R.color.colorGray));
        }
        else
        {
            findTextViewById(R.id.txt_guarantee).setBackground(ContextCompat.getDrawable(WonCouponListActivity.this, R.drawable.roun_rect_white));
            findTextViewById(R.id.txt_guarantee).setTextColor(getResources().getColor(R.color.colorGray));
        }
    }
    private void setPassiveGuaranteeColor()
    {
        //roun_rect_gray
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            findTextViewById(R.id.txt_guarantee).setBackgroundDrawable(ContextCompat.getDrawable(WonCouponListActivity.this, R.drawable.roun_rect_gray) );
            findTextViewById(R.id.txt_guarantee).setTextColor(ContextCompat.getColor(WonCouponListActivity.this, R.color.colorBackroundWhite));
        }
        else
        {
            findTextViewById(R.id.txt_guarantee).setBackground(ContextCompat.getDrawable(WonCouponListActivity.this, R.drawable.roun_rect_gray));
            findTextViewById(R.id.txt_guarantee).setTextColor(getResources().getColor(R.color.colorBackroundWhite));
        }

    }

    private void setActiveFoldColor()
    {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            findTextViewById(R.id.txt_fold).setBackgroundDrawable(ContextCompat.getDrawable(WonCouponListActivity.this, R.drawable.roun_rect_white) );
            findTextViewById(R.id.txt_fold).setTextColor(ContextCompat.getColor(WonCouponListActivity.this, R.color.colorGray));
        }
        else
        {
            findTextViewById(R.id.txt_fold).setBackground(ContextCompat.getDrawable(WonCouponListActivity.this, R.drawable.roun_rect_white));
            findTextViewById(R.id.txt_fold).setTextColor(getResources().getColor(R.color.colorGray));
        }
    }
    private void setPassiveFoldColor()
    {
        //roun_rect_gray
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            findTextViewById(R.id.txt_fold).setBackgroundDrawable(ContextCompat.getDrawable(WonCouponListActivity.this, R.drawable.roun_rect_gray) );
            findTextViewById(R.id.txt_fold).setTextColor(ContextCompat.getColor(WonCouponListActivity.this, R.color.colorBackroundWhite));
        }
        else
        {
            findTextViewById(R.id.txt_fold).setBackground(ContextCompat.getDrawable(WonCouponListActivity.this, R.drawable.roun_rect_gray));
            findTextViewById(R.id.txt_fold).setTextColor(getResources().getColor(R.color.colorBackroundWhite));
        }

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

            if(IsCouponGuarantee)
            {
                setActiveGuaranteeColor();
                setPassiveFoldColor();
                GetList("1",formattedDate);
                findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));

            }
            else
            {
                setActiveFoldColor();
                setPassiveGuaranteeColor();
                GetList("2",formattedDate);
                findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.fold));
            }
        }
    }


    public void callCouponDetailActivity(String id,String name)
    {
        newActivity(new WonCouponDetailListActivity(),id,name);
    }

}
