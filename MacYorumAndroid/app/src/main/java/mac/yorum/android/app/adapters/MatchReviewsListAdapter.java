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

import mac.yorum.android.app.activities.MatchReviewsListActivity;
import mac.yorum.android.app.helpers.Converter;
import mac.yorum.android.app.models.mainmodels.MatchReviewsList;
import yorum.mac.com.macyorumandroid.R;

/**
 * Created by gkhngngr on 13.1.2017.
 */

public class MatchReviewsListAdapter extends RecyclerView.Adapter<MatchReviewsListAdapter.SimpleViewHolder> {
    private Context mContext;
    private ArrayList<MatchReviewsList> matchReviewsLists;
    public   Typeface type;


    public MatchReviewsListAdapter(Context context, ArrayList<MatchReviewsList> matchReviewsLists) {

        this.mContext = context;
        this.matchReviewsLists = matchReviewsLists;
         type = Typeface.createFromAsset(mContext.getAssets(),"fonts/Roboto-Medium.ttf");
    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matchreviews_adapter, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder vi, final int position) {
        final MatchReviewsList item = matchReviewsLists.get(position);

        vi.txt_home.setTypeface(type);
        vi.txt_away.setTypeface(type);
        vi.txt_league.setTypeface(type);
        vi.txt_match_date.setTypeface(type);
        vi.txt_summary.setTypeface(type);
        vi.txt_bedding_code.setTypeface(type);

        vi.txt_home.setText(item.getEvSahibiTakim());
        vi.txt_away.setText(item.getKonukTakim());
        vi.txt_league.setText(mContext.getString(R.string.rate)+" " +item.getOran());
        vi.txt_match_date.setText(Converter.stringToShortDate(item.getMacTarihi()));

        vi.txt_summary.setText(item.getMacSonucu());

        vi.txt_bedding_code.setText(mContext.getString(R.string.code)+" " +item.getIddaKodu());

        vi.txt_read_count.setText(mContext.getString(R.string.readcount)+" " +item.getOkunmaSayisi());

        vi.lnr_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                //new MatchReviewsDetailActivity(),id,evsahibi,konuk,iddiaKodu,mactarihi,macsonucu,yorum
                ((MatchReviewsListActivity)mContext).callMatchReviewsDetailActivity(item.getId(),item.getEvSahibiTakim(),
                        item.getKonukTakim(),item.getIddaKodu(),item.getMacTarihi(),item.getMacSonucu(),item.getMacYorumu(),item.getOran());

            }
        });
    }

    @Override
    public int getItemCount() {
        return matchReviewsLists.size();
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        TextView txt_home;
        TextView txt_away;
        TextView txt_match_date;
        TextView txt_league;
        TextView txt_summary;
        TextView txt_bedding_code;
        LinearLayout lnr_selected;
        TextView txt_read_count;

        public SimpleViewHolder(View vi) {
            super(vi);

            txt_home = vi.findViewById(R.id.txt_home);
            txt_away =  vi.findViewById(R.id.txt_away);
            txt_match_date = vi.findViewById(R.id.txt_match_date);
            txt_league = vi.findViewById(R.id.txt_league);
            txt_summary = vi.findViewById(R.id.txt_summary);
            txt_bedding_code = vi.findViewById(R.id.txt_bedding_code);
            lnr_selected = vi.findViewById(R.id.lnr_selected);
            txt_read_count = vi.findViewById(R.id.txt_read_count);
        }
    }
}


