package mac.yorum.android.app.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mac.yorum.android.app.activities.CouponDetailListActivity;
import mac.yorum.android.app.models.mainmodels.CouponMatch;
import yorum.mac.com.macyorumandroid.R;

public class CouponDetailListAdapter extends RecyclerView.Adapter<CouponDetailListAdapter.SimpleViewHolder> {
    private Context mContext;
    private ArrayList<CouponMatch> couponMatchLists;
    public Typeface type;


    public CouponDetailListAdapter(Context context, ArrayList<CouponMatch> couponMatches) {

        this.mContext = context;
        this.couponMatchLists = couponMatches;
        type = Typeface.createFromAsset(mContext.getAssets(),"fonts/Roboto-Medium.ttf");
    }


    @Override
    public CouponDetailListAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_detail_adapter, parent, false);
        return new CouponDetailListAdapter.SimpleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final CouponDetailListAdapter.SimpleViewHolder vi, final int position) {
        final CouponMatch item = couponMatchLists.get(position);

        vi.txt_home.setTypeface(type);
        vi.txt_away.setTypeface(type);
        vi.txt_league.setTypeface(type);
        vi.txt_match_date.setTypeface(type);
        vi.txt_prediction.setTypeface(type);
        vi.txt_bedding_code.setTypeface(type);
        vi.txt_rate.setTypeface(type);

        vi.txt_home.setText(item.getHome());
        vi.txt_away.setText(item.getAway());
        vi.txt_league.setText(item.getLeagueName());
       // vi.txt_match_date.setText(Converter.stringToShortDate(item.getMatchDate()));
        vi.txt_rate.setText(item.getBeddingRate());

        vi.txt_prediction.setText(mContext.getString(R.string.prediction) +item.getPrediction());
        vi.txt_bedding_code.setText(item.getBeddingCode());

        vi.lnr_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((CouponDetailListActivity)mContext).callDetailActivity(item.getId(),item.getCouponName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return couponMatchLists.size();
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        TextView txt_home;
        TextView txt_away;
        TextView txt_match_date;
        TextView txt_league;
        TextView txt_rate;

        TextView txt_prediction;
        TextView txt_bedding_code;
        LinearLayout lnr_selected;

        public SimpleViewHolder(View vi) {
            super(vi);

            txt_home = vi.findViewById(R.id.txt_home);
            txt_away =  vi.findViewById(R.id.txt_away);
            txt_match_date = vi.findViewById(R.id.txt_match_date);
            txt_league = vi.findViewById(R.id.txt_league);
            txt_rate = vi.findViewById(R.id.txt_rate);

            txt_prediction = vi.findViewById(R.id.txt_prediction);
            txt_bedding_code = vi.findViewById(R.id.txt_bedding_code);
            lnr_selected = vi.findViewById(R.id.lnr_selected);
        }
    }
}
