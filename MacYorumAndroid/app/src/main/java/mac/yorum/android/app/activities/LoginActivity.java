package mac.yorum.android.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.helpers.ValidationHelper;
import mac.yorum.android.app.models.LoginRequest;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.models.Result;
import mac.yorum.android.app.retrofit.RefrofitClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yorum.mac.com.macyorumandroid.R;

public class LoginActivity extends BaseAppCompatActivitiy {

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        SetFont();
        initButtons();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeKeyboard();
        return super.onTouchEvent(event);
    }

    private void SetFont() {


        TextView btn_login = findTextViewById(R.id.btn_login);
        TextView btn_signup = findTextViewById(R.id.btn_signup);
        EditText edt_username = findEditTextById(R.id.edt_username);
        EditText edt_password = findEditTextById(R.id.edt_password);

        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        btn_signup.setTypeface(type);
        btn_login.setTypeface(type);

        edt_username.setTypeface(type);
        edt_password.setTypeface(type);


    }

    private void initButtons() {
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Login();
            }
        });

        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new SignUpActivity(), Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        });
    }

    private void Login() {
        EditText txt_username = findEditTextById(R.id.edt_username);
        EditText txt_password = findEditTextById(R.id.edt_password);


        if (validateLogin(txt_username, txt_password))
            doLoginUseToRetrofit(txt_username.getText().toString(), txt_password.getText().toString());
    }

    private boolean validateLogin(EditText userName, EditText password) {
        if (ValidationHelper.isStringOrEmpty(userName.getText().toString())) {
            toastValidationMessage(R.string.username);
            return false;
        } else if (ValidationHelper.isStringOrEmpty(password.getText().toString())) {
            toastValidationMessage(R.string.password);
            return false;
        } else
            return true;
    }

    private void doLoginUseToRetrofit(final String userName, final String password)
    {
        showLoadingPopup();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.KullaniciAdi = userName;
        loginRequest.Parola = password;
        loginRequest._Platform = "MOBIL";
        loginRequest.PlatformToken = "456";

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
        Call<LoginResponse> servicecall = apiservice.Login(Constants.API_KEY,"", "text/json;charset=UTF-8", loginRequest);
        servicecall.enqueue(new Callback<LoginResponse>() {


            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                try
                {
                    if (response.isSuccessful() && response.body() != null) {

                        LoginResponse responseBody = response.body();
                        closeKeyboard();

                        if (!responseBody.Status.equals("200"))
                        {
                            toastMessage(LoginActivity.this, responseBody.Message);
                        }
                        else
                            {
                               ArrayList<Result> res = response.body().Result;

                            SharedPreferences.Editor edit = prefs.edit();

                            edit.putString("Token",res.get(0).getToken());
                            edit.putString("ReferansKodu",res.get(0).getReferansKodu());

                            edit.commit();
                            //finish();
                            closeKeyboard();
                            newActivity(new MainActivity(),Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        }
                        hideLoadingPopup();
                    } else {
                        hideLoadingPopup();
                        toastMessage(LoginActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {
                    Log.e("exlogin", ex.toString());
                    hideLoadingPopup();
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(LoginActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });
    }


}
