package mac.yorum.android.app.activities;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import mac.yorum.android.app.adapters.EmptyListAdapter;
import mac.yorum.android.app.adapters.MatchReviewsListAdapter;
import mac.yorum.android.app.models.mainmodels.MatchReviewsList;
import yorum.mac.com.macyorumandroid.R;

public class MatchReviewsListActivity extends  BaseAppCompatActivitiy
{

    RecyclerView mRecyclerView;
    EmptyListAdapter mAdapyerEmpty;
    MatchReviewsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchreviewslistactivity);

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

        txt_matchestoday.setTypeface(type);
        txt_date.setTypeface(type);
        txt_matchreviews.setTypeface(type);

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
        MatchReviewsList matchReviewsList = new MatchReviewsList();
        matchReviewsList.Home = "Beşiktaş";
        matchReviewsList.Away = "Fenerbahçe";
        matchReviewsList.LeagueName = "Spor Toto 1.lig";
        matchReviewsList.MatchDate = "19:00";
        matchReviewsList.Summary = "İki takım adına zor bir...";
        matchReviewsList.Id = "123";
        matchReviewsList.BeddingCode = "5048";

        ArrayList<MatchReviewsList> matchReviewsListArrayList = new ArrayList<>();


        matchReviewsListArrayList.add(matchReviewsList);
        matchReviewsListArrayList.add(matchReviewsList);
        matchReviewsListArrayList.add(matchReviewsList);
        matchReviewsListArrayList.add(matchReviewsList);


       binData(matchReviewsListArrayList);
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

}
