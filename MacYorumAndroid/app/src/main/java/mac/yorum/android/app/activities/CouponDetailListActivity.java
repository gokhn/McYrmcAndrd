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

import mac.yorum.android.app.adapters.CouponDetailListAdapter;
import mac.yorum.android.app.adapters.EmptyListAdapter;
import mac.yorum.android.app.models.mainmodels.CouponMatch;
import yorum.mac.com.macyorumandroid.R;

public class CouponDetailListActivity extends BaseAppCompatActivitiy {

    RecyclerView mRecyclerView;
    EmptyListAdapter mAdapyerEmpty;
    CouponDetailListAdapter mAdapter;
    String name;
    String id;

    @Override
    protected void onResume()
    {
        findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setRefreshing(true);
        GetList();
        findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setRefreshing(false);
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_detail_list_activity);

        id  = getIntent().getStringExtra("Id");
        name  = getIntent().getStringExtra("JSONString");

        findTextViewById(R.id.txt_coupon_name).setText(name);

        SetFont();

        initButtons();

        findSwipeRefreshLayoutById(R.id.coupondetail_swipe_refresh).setRefreshing(true);
         GetList();
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
                GetList();
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
    private void GetList()
    {
        CouponMatch couponMatch = new CouponMatch();
        couponMatch.Home = "Beşiktaş";
        couponMatch.Away = "Fenerbahçe";
        couponMatch.LeagueName = "Spor Toto 1.lig";
        couponMatch.MatchDate = "19:00";
        couponMatch.BeddingRate = "3,40";
        couponMatch.Id = "123";
        couponMatch.BeddingCode = "5048";
        couponMatch.Prediction = "1/1";

        ArrayList<CouponMatch> matchReviewsListArrayList = new ArrayList<>();


        matchReviewsListArrayList.add(couponMatch);
        matchReviewsListArrayList.add(couponMatch);
        matchReviewsListArrayList.add(couponMatch);
        matchReviewsListArrayList.add(couponMatch);


        binData(matchReviewsListArrayList);
    }

    public void callDetailActivity(String Id, String Name)
    {
        newActivity(new CouponDetailActivity(),Id,Name);
    }

}
