package mac.yorum.android.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import yorum.mac.com.macyorumandroid.R;

public class LauncherActivity extends BaseAppCompatActivitiy {

    private int SPLASH_DURATION = 2000;
    private SharedPreferences prefs;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeKeyboard();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);

        SetFont();
        initButtons();

        showLoadingPopup();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {

                if(checkIsAlreadyLogin())
                {
                    newActivity(new MainActivity(), Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    LauncherActivity.this.finish();
                }
                hideLoadingPopup();
            }
        }, SPLASH_DURATION);

    }

    public boolean checkIsAlreadyLogin()
    {
        String kullaniciAdi = prefs.getString("KullaniciAdi", "");
        String parola = prefs.getString("Parola", "");
        String token = prefs.getString("Token", "");
        String referansKodu = prefs.getString("ReferansKodu", "");
        if(!kullaniciAdi.equals("") && !parola.equals("") && !token.equals("") && !referansKodu.equals(""))
        {

            return true;
        }
        else
        {
            return false;
        }
    }

    private void SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        TextView txt_header = (TextView) findViewById(R.id.txt_header);
        TextView txt_description1 = (TextView) findViewById(R.id.txt_description1);
        TextView txt_description2 = (TextView) findViewById(R.id.txt_description2);
        TextView txt_signin = (TextView) findViewById(R.id.txt_signin);
        TextView txt_signup = (TextView) findViewById(R.id.txt_signup);

        txt_header.setTypeface(type);
        txt_description1.setTypeface(type);
        txt_description2.setTypeface(type);
        txt_signin.setTypeface(type);
        txt_signup.setTypeface(type);

    }
    private  void  initButtons()
    {
        findTextViewById(R.id.txt_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new LoginActivity());
            }
        });

        findTextViewById(R.id.txt_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivity(new SignUpActivity());
            }
        });
    }
}
