package mac.yorum.android.app.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import mac.yorum.android.app.adapters.CouponListAdapter;
import mac.yorum.android.app.adapters.EmptyListAdapter;
import mac.yorum.android.app.models.mainmodels.Coupon;
import yorum.mac.com.macyorumandroid.R;

public class CouponListActivity extends BaseAppCompatActivitiy {

    RecyclerView mRecyclerView;
    EmptyListAdapter mAdapyerEmpty;
    CouponListAdapter mAdapter;

    boolean IsCouponGuarantee = true;

    @Override
    protected void onResume()
    {
        findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(true);
        if(IsCouponGuarantee)
        {
            GetList(getResources().getString(R.string.guarantee));
            findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));
        }
        else
        {
            GetList(getResources().getString(R.string.fold));
            findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.fold));
        }

        findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(false);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.couponlist_activity);

        SetFont();
        initButtons();

        findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(true);
        if(IsCouponGuarantee)
        {
            GetList(getResources().getString(R.string.guarantee));
            findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));
        }
        else
        {
            GetList(getResources().getString(R.string.fold));
            findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.fold));
        }
    }

    public void GetList(final String couponType)
    {
        Coupon coupon = new Coupon();
        coupon.Id = "123";
        coupon.CouponDescription = "Kazandıran sahane kupon";
        coupon.CouponExpiredDate = "13/01/2019 23:00";
        coupon.CouponName = "Müthiş Kupon";
        coupon.CouponType = couponType;
        coupon.TotalMatch = "5";
        coupon.TotalEarn = "120";
        coupon.TotalPrice = "950,15";
        coupon.TotalRate = "236,48";

        ArrayList<Coupon> list = new ArrayList<>();
        list.add(coupon);

        coupon = new Coupon();
        coupon.Id = "456";
        coupon.CouponDescription = "Efsane sahane kupon";
        coupon.CouponExpiredDate = "13/01/2019 23:00";
        coupon.CouponName = "Yok Böyle Kupon";
        coupon.CouponType = couponType;
        coupon.TotalMatch = "2";
        coupon.TotalEarn = "90";
        coupon.TotalPrice = "10,15";
        coupon.TotalRate = "47,52";
        list.add(coupon);

        coupon = new Coupon();
        coupon.Id = "789";
        coupon.CouponDescription = "Efsane sahane kupon";
        coupon.CouponExpiredDate = "14/01/2019 14:00";
        coupon.CouponName = "Pişman Olmazsın";
        coupon.CouponType = couponType;
        coupon.TotalMatch = "14";
        coupon.TotalEarn = "30.000";
        coupon.TotalPrice = "3";
        coupon.TotalRate = "785,42";
        list.add(coupon);

        binData(list);
    }

    private void binData(final ArrayList<Coupon> items)
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.couponslist_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CouponListActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if (items.size() == 0) {
            mAdapyerEmpty = new EmptyListAdapter(this);
            mRecyclerView.setAdapter(mAdapyerEmpty);
        }
        else
        {
            mAdapter = new CouponListAdapter(CouponListActivity.this, items);
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

        txt_matchestoday.setTypeface(type);
        txt_guarantee.setTypeface(type);
        txt_fold.setTypeface(type);
        txt_header.setTypeface(type);

    }
    private void initButtons()
    {
        findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                if(IsCouponGuarantee)
                {
                    GetList(getResources().getString(R.string.guarantee));
                    findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));

                }
                else
                {
                    GetList(getResources().getString(R.string.fold));
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
            public void onClick(View view) {

                IsCouponGuarantee = true;

                findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.guarantee));

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(true);

                GetList(getResources().getString(R.string.guarantee));

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(false);

            }
        });

        findTextViewById(R.id.txt_fold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IsCouponGuarantee = false;
                findTextViewById(R.id.txt_matchestoday).setText(getResources().getString(R.string.fold));

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(true);

                GetList(getResources().getString(R.string.fold));

                findSwipeRefreshLayoutById(R.id.couponslist_swipe_refresh).setRefreshing(false);

            }
        });
    }

    public void callCouponDetailActivity(String id,String name)
    {
        newActivity(new CouponDetailListActivity(),id,name);
    }
}
