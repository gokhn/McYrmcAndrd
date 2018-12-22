package mac.yorum.android.app.helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class ValidationHelper {
	
	public static boolean isStringOrEmpty(String value)
	{
		if(value == null) return true;
		else if(value.equals("")) return true;
		else if(value.equals("null")) return true;
		else return false;
	}
	
	public static boolean isStringHasValue(String value)
	{
		return !isStringOrEmpty(value);
	}

	public static String getAdressInfo(Context mContext, double latitude, double longitude)
	{
		Geocoder gCoder = new Geocoder(mContext);
		StringBuilder stringBuilder = new StringBuilder();
		List<Address> addresses;
		try {
			addresses = gCoder.getFromLocation(latitude, longitude, 1);

			if (addresses != null && addresses.size() > 0) {
				stringBuilder = new StringBuilder();

				if (addresses.get(0).getThoroughfare() != null) {
					stringBuilder.append(addresses.get(0).getThoroughfare());
					stringBuilder.append(", ");
				}
				if (addresses.get(0).getSubThoroughfare() != null) {
					stringBuilder.append("No:"+ addresses.get(0).getSubThoroughfare());
					stringBuilder.append(", ");
				}
				if (addresses.get(0).getSubLocality() != null) {
					stringBuilder.append(addresses.get(0).getSubLocality());
					stringBuilder.append(", ");
				}
				if(addresses.get(0).getSubAdminArea() != null)
				{
					stringBuilder.append(addresses.get(0).getSubAdminArea());
					stringBuilder.append(", ");
				}
				if (addresses.get(0).getAdminArea() != null) {
					stringBuilder.append(addresses.get(0).getAdminArea());
					stringBuilder.append(", ");
				}
				if(addresses.get(0).getCountryName() != null){
					stringBuilder.append(addresses.get(0).getCountryName());
				}
			}
			else
			{
				stringBuilder.append("");
			}
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}

		return  stringBuilder.toString();
	}
	
	public static void setListViewHeight(ListView listView)
	{
		   ListAdapter listAdapter = listView.getAdapter();
           if (listAdapter == null) return; 
           int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
           for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                if (listItem instanceof ViewGroup) {
                   listItem.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                }
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
           }

           LayoutParams params = listView.getLayoutParams();
           params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
                     listView.setLayoutParams(params);
	}



}
