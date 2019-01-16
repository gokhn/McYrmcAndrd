package mac.yorum.android.app.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import yorum.mac.com.macyorumandroid.R;

/**
 * Created by gkhngngr on 7.8.2017.
 */

public class BaseAppCompatActivitiy extends AppCompatActivity {

    private Dialog loadingPopup;

    public void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void toastError(Context context, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void toastValidationMessage(int fieldId) {
        Toast.makeText(getBaseContext(), String.format(getResources().getString(R.string.field_required), getResources().getString(fieldId)), Toast.LENGTH_SHORT).show();
    }

    public void toastValidationMessage(String field) {
        Toast.makeText(getBaseContext(), String.format(getResources().getString(R.string.field_required), field), Toast.LENGTH_SHORT).show();
    }

    public SwipeRefreshLayout findSwipeRefreshLayoutById(int resId)
    {
        return  (SwipeRefreshLayout)findViewById(resId);
    }

    public EditText findEditTextById(int resId)
    {
        return (EditText)findViewById(resId);
    }

    public ImageView findImageViewById(int resId)
    {
        return (ImageView)findViewById(resId);
    }

    public TextView findTextViewById(int resId)
    {
        return (TextView)findViewById(resId);
    }

    public ListView findListViewById(int resId)
    {
        return (ListView)findViewById(resId);
    }

    public void newActivity(Activity newActivity)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    public void newActivity2(Activity newActivity, String id)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("Id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    public void newActivityWithOperationType(Activity newActivity, String id, String customerId, String operationTypeId)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("Id", id);
        intent.putExtra("CustomerId", customerId);
        intent.putExtra("OperationTypeId", operationTypeId);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    public void newActivity(Activity newActivity, String id)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("Id", id);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    public void newActivity(Activity newActivity, String id, String departurecityname, String arrivalcityname)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("Id", id);
        intent.putExtra("DepartureCityName", departurecityname);
        intent.putExtra("ArrivalCityName", arrivalcityname);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }



    public void newActivity(Activity newActivity, String id, String JSONString)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("Id", id);
        intent.putExtra("JSONString", JSONString);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    public void newActivity(Activity newActivity, String id, String evsahibi,String konuktakim,String macssati,String yorum,
                            String tahmin,String iddakodu,String mactipi,String kuponad)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("Id", id);
        intent.putExtra("EVSAHIBI", evsahibi);
        intent.putExtra("KONUKTAKIM", konuktakim);
        intent.putExtra("MACSAATI", macssati);
        intent.putExtra("YORUM", yorum);
        intent.putExtra("TAHMIN", tahmin);
        intent.putExtra("IDDIAKODU", iddakodu);
        intent.putExtra("MACTIPI", mactipi);
        intent.putExtra("KUPONADI", kuponad);

        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    //(String id,String evsahibi,String konuk,String iddiaKodu,String mactarihi,String macsonucu,String yorum)
    public void newActivity(Activity newActivity, String id, String evsahibi,String konuktakim,String iddiaKodu,String mactarihi,
                            String macsonucu,String yorum,String oran)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("Id", id);
        intent.putExtra("EVSAHIBI", evsahibi);
        intent.putExtra("KONUKTAKIM", konuktakim);
        intent.putExtra("IDDIAKODU", iddiaKodu);
        intent.putExtra("MACTARIHI", mactarihi);
        intent.putExtra("MACSONUCU", macsonucu);
        intent.putExtra("YORUM", yorum);
        intent.putExtra("ORAN", oran);

        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    public void newActivityCamera(Activity newActivity, String JSONString)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("JSONString", JSONString);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }
    public void newActivityCamera(Activity newActivity)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }



    public void newActivityForResult(Activity newActivity, int reqCode)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        startActivityForResult(intent, reqCode);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    public void newActivity(Activity newActivity, int flags)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.setFlags(flags);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    public void newActivity(Activity newActivity, String id, boolean isEditable)
    {
        Intent intent = new Intent(this, newActivity.getClass());
        intent.putExtra("Id", id);
        intent.putExtra("IsEditable", isEditable);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_in_left_fast, R.anim.animation_in_right);
    }

    @SuppressLint("InlinedApi")
    public void showLoadingPopup()
    {
        if(loadingPopup == null)
        {
            loadingPopup = new Dialog(this, android.R.style.Theme_Holo_Dialog);
            loadingPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loadingPopup.setContentView(R.layout.loading_popup);
            loadingPopup.getWindow().setBackgroundDrawableResource(R.color.transparent);
            loadingPopup.setCancelable(false);
        }
        ImageView img_logo = (ImageView)loadingPopup.findViewById(R.id.img_logo);
        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        animation.setDuration(200);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        img_logo.startAnimation(animation);
        loadingPopup.show();
    }

    public void hideLoadingPopup()
    {
        if(loadingPopup != null)
        {
            ImageView img_logo = (ImageView)loadingPopup.findViewById(R.id.img_logo);
            img_logo.clearAnimation();
            loadingPopup.dismiss();
        }
    }

    public void closeKeyboard(EditText txt)
    {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txt.getWindowToken(), 0);
    }

    public void closeKeyboard()
    {
        if(getCurrentFocus() != null)
        {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animation_out_left_fast, R.anim.animation_out_right_fast);
    }
}
