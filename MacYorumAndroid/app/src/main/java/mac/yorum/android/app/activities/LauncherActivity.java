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
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.retrofit.RefrofitClass;
import mac.yorum.android.app.widgets.ForgotPasswordPopUp;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yorum.mac.com.macyorumandroid.R;

public class LauncherActivity extends BaseAppCompatActivitiy {

    private int SPLASH_DURATION = 2000;
    private SharedPreferences prefs;

    private long mLastPress = 0;
    int TOAST_DURATION = 5000;
    Toast onBackPressedToast;

    @Override
    public void onBackPressed() {
        closeKeyboard();

        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastPress > TOAST_DURATION) {
            onBackPressedToast = Toast.makeText(this, R.string.pressexitbutton, Toast.LENGTH_SHORT);
            onBackPressedToast.show();
            mLastPress = currentTime;
        } else {
            if (onBackPressedToast != null) {
                onBackPressedToast.cancel();  //Difference with previous answer. Prevent continuing showing toast after application exit.
                onBackPressedToast = null;
            }
            super.onBackPressed();
        }
    }
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
        TextView txt_forgot_password = (TextView)findViewById(R.id.txt_forgot_password);

        txt_header.setTypeface(type);
        txt_description1.setTypeface(type);
        txt_description2.setTypeface(type);
        txt_signin.setTypeface(type);
        txt_signup.setTypeface(type);
        txt_forgot_password.setTypeface(type);

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

        findTextViewById(R.id.txt_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ForgotPasswordPopUp(LauncherActivity.this) {
                    @Override
                    protected void OnUpdate(String password) {

                        SentForgotPassword(password);
                    }
                };

            }
        });
    }

    private void SentForgotPassword(String email)
    {
        showLoadingPopup();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(UrlConfig.API_RETROFIT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient).build();
        RefrofitClass apiservice = retrofit.create(RefrofitClass.class);
        Call<LoginResponse> servicecall = apiservice.ParolaSifirla(Constants.API_KEY,"text/json;charset=UTF-8",email);
        servicecall.enqueue(new Callback<LoginResponse>() {


            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                try
                {
                    if (response.isSuccessful() && response.body() != null)
                    {
                        LoginResponse responseBody = response.body();
                        closeKeyboard();

                        if (responseBody.Status.equals("200"))
                        {
                            newActivity(new LoginActivity());
                            finish();
                            toastMessage(LauncherActivity.this, getResources().getString(R.string.sentpasswordsuccesfully));
                        }
                        else
                        {
                           toastMessage(LauncherActivity.this, responseBody.Message);
                        }
                        hideLoadingPopup();
                    }
                    else
                    {
                        hideLoadingPopup();
                        toastMessage(LauncherActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {

                    hideLoadingPopup();
                    toastMessage(LauncherActivity.this, ex.getMessage());
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(LauncherActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });

    }
}
