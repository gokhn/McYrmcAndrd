package mac.yorum.android.app.widgets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import yorum.mac.com.macyorumandroid.R;


public class YesNoPopup {
	private Context context;
	private String header;
	
	public YesNoPopup(Context context, String header) {
		this.context = context;
		this.header = header;
		show();
	}




	
	@SuppressLint("InlinedApi")
	private void show()
	{
		final Dialog dialog = new Dialog(context, android.R.style.Theme_Holo_Dialog);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.yes_no_popup);
		dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
		dialog.getWindow().getAttributes().windowAnimations = R.style.PopupAnimation;
		((TextView)dialog.findViewById(R.id.txt_content)).setText(header);
		dialog.findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				OnCancelConfirmation();
				
			}
		});
		dialog.findViewById(R.id.btn_ok).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				OnApplyConfirmation();
			}
		});
     	dialog.show();
	}
	
	protected void OnApplyConfirmation() { }
	protected void OnCancelConfirmation() { }

}