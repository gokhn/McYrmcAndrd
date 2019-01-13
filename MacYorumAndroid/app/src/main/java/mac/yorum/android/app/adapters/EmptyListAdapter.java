package mac.yorum.android.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yorum.mac.com.macyorumandroid.R;

/**
 * Created by gkhngngr on 16.1.2017.
 */

public class EmptyListAdapter extends RecyclerView.Adapter<EmptyListAdapter.SimpleViewHolder>
{

    private Context mContext;

    public EmptyListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public EmptyListAdapter.SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_adapter, parent, false);
        return new EmptyListAdapter.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmptyListAdapter.SimpleViewHolder simpleViewHolder, int position) {

    }


    @Override
    public int getItemCount() {
        return 1;
    }



    public static class SimpleViewHolder extends RecyclerView.ViewHolder
    {

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
