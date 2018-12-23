package mac.yorum.android.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.helpers.ValidationHelper;
import mac.yorum.android.app.models.LoginRequest;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.retrofit.RefrofitClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yorum.mac.com.macyorumandroid.R;

public class LoginActivity extends BaseAppCompatActivitiy {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private  void SetFont()
    {

        /*
        TextView btn_login = findTextViewById(R.id.btn_login);
        TextView btn_signup = findTextViewById(R.id.btn_signup);
        EditText edt_username = findEditTextById(R.id.edt_username);
        EditText edt_password = findEditTextById(R.id.edt_password);

        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");

        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");

        btn_signup.setTypeface(custom_font);
        btn_login.setTypeface(custom_font1);

        edt_username.setTypeface(custom_font);
        edt_password.setTypeface(custom_font);
        */

    }
    private void initButtons()
    {
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Login();
            }
        });

        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new SignUpActivity(),Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        });
    }

    private void Login()
    {
        EditText txt_username = findEditTextById(R.id.edt_username);
        EditText txt_password = findEditTextById(R.id.edt_password);


        if(validateLogin(txt_username, txt_password))
            doLoginUseToRetrofit(txt_username.getText().toString(),txt_password.getText().toString());
    }

    private boolean validateLogin(EditText userName, EditText password)
    {
        if(ValidationHelper.isStringOrEmpty(userName.getText().toString()))
        {
            toastValidationMessage(R.string.username);
            return false;
        }
        else if(ValidationHelper.isStringOrEmpty(password.getText().toString()))
        {
            toastValidationMessage(R.string.password);
            return false;
        }
        else
            return true;
    }

    private void doLoginUseToRetrofit(final String userName, final String password)
    {
        showLoadingPopup();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.kullaniciadi= userName;
        loginRequest.parola = password;
        loginRequest.deviceId = "";
        loginRequest.platform = "MOBIL";
        loginRequest.platformToken = "";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlConfig.API_RETROFIT).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build();
        RefrofitClass apiservice = retrofit.create(RefrofitClass.class);
        Call<LoginResponse> servicecall = apiservice.Login(Constants.API_KEY,"text/json;charset=UTF-8",loginRequest);
        servicecall.enqueue(new Callback<LoginResponse>() {



            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response)
            {
                try
                {
                    if(response.isSuccessful() && response.body() != null)
                    {

                        LoginResponse responseBody =  response.body();
                        closeKeyboard();

                        if(!responseBody.Status.equals("200"))
                        {
                            toastMessage(LoginActivity.this, responseBody.Result.toString());
                        }
                        else
                        {
                            /*
                            Gson gson = new Gson();
                            String res = gson.toJson(response.body());
                            AppEngine.USERNAME = userName;
                            AppEngine.PASSWORD = password;

                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("CurrentUser", res);
                            edit.putBoolean("IsFirstLogin", false);
                            edit.putString("USERNAME",userName);
                            edit.putString("PASSWORD",password);

                            edit.commit();
                            finish();
                            closeKeyboard();
                            newActivity(new PositionListNewVersion(),Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            */
                        }
                        hideLoadingPopup();
                    }
                    else
                    {
                        hideLoadingPopup();
                        toastMessage(LoginActivity.this,getResources().getString(R.string.error_found));
                    }

                }
                catch (Exception ex) {
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
