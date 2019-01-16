package mac.yorum.android.app.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mac.yorum.android.app.activities.CouponListActivity;
import mac.yorum.android.app.helpers.Converter;
import mac.yorum.android.app.models.mainmodels.Coupon;
import yorum.mac.com.macyorumandroid.R;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.SimpleViewHolder> {

    private Context mContext;
    private ArrayList<Coupon> couponList;
    public Typeface type;


    public CouponListAdapter(Context context, ArrayList<Coupon> couponList) {

        this.mContext = context;
        this.couponList = couponList;
        type = Typeface.createFromAsset(mContext.getAssets(),"fonts/Roboto-Medium.ttf");
    }

    @Override
    public CouponListAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.couponlist_adapter, parent, false);
        return new CouponListAdapter.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CouponListAdapter.SimpleViewHolder vi, final int position) {
        final Coupon item = couponList.get(position);


        vi.txt_coupon_name.setTypeface(type);
        vi.txt_coupon_type.setTypeface(type);
        vi.txt_description.setTypeface(type);
        vi.txt_expired_date.setTypeface(type);
        vi.txt_total_match.setTypeface(type);
        vi.txt_rate.setTypeface(type);
        vi.txt_coupon_price.setTypeface(type);
        vi.txt_max_earn.setTypeface(type);

        vi.txt_coupon_name.setText(item.getKuponAd());
        vi.txt_coupon_type.setText(item.getKuponTipi());
        vi.txt_expired_date.setText(Converter.stringToShortDate(item.getGecerlilikSuresi()));
        vi.txt_register_date.setText( mContext.getResources().getString(R.string.register_date)+ Converter.stringToShortDate(item.getKayitTarihi()));


        if(item.getAciklama().length() >0 && item.getAciklama().length() > 10)
        {
            vi.txt_description.setText(item.getAciklama().substring(0,10));
        }
        else {
            vi.txt_description.setText(item.getAciklama());
        }
        vi.txt_total_match.setText(mContext.getString(R.string.match) +" " +item.getMacAdedi());
        vi.txt_rate.setText(mContext.getString(R.string.rate) +" " + item.getToplamOran());
        vi.txt_coupon_price.setText(mContext.getString(R.string.amount) +" " +  item.getKuponFiyat());
        vi.txt_max_earn.setText(mContext.getString(R.string.earn) +" " +  item.getKazancFiyat());


        vi.lnr_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((CouponListActivity)mContext).callCouponDetailActivity(item.getId(),item.getKuponAd());

            }
        });

    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {

        TextView txt_coupon_name;
        TextView txt_coupon_type;
        TextView txt_expired_date;
        TextView txt_description;
        TextView txt_total_match;
        TextView txt_rate;
        TextView txt_coupon_price;
        TextView txt_max_earn;
        TextView txt_register_date;

        RelativeLayout lnr_selected;

        public SimpleViewHolder(View vi) {
            super(vi);

            txt_coupon_name = vi.findViewById(R.id.txt_coupon_name);
            txt_coupon_type =  vi.findViewById(R.id.txt_coupon_type);
            txt_expired_date = vi.findViewById(R.id.txt_expired_date);
            txt_description = vi.findViewById(R.id.txt_description);
            txt_total_match = vi.findViewById(R.id.txt_total_match);
            txt_rate = vi.findViewById(R.id.txt_rate);
            txt_coupon_price = vi.findViewById(R.id.txt_coupon_price);
            txt_max_earn =  vi.findViewById(R.id.txt_max_earn);
            txt_register_date = vi.findViewById(R.id.txt_register_date);

            lnr_selected = vi.findViewById(R.id.lnr_selected);
        }
    }



}
