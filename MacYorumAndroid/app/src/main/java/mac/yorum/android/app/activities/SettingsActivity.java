package mac.yorum.android.app.activities;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.retrofit.RefrofitClass;
import mac.yorum.android.app.widgets.UpdatePasswordPopUp;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yorum.mac.com.macyorumandroid.R;

public class SettingsActivity extends BaseAppCompatActivitiy {

    private SharedPreferences prefs;
    String Token;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        Token =  prefs.getString("Token","");
        SetFont();
        initButtons();
    }

    private void UpdatePassword(String password)
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
        Call<LoginResponse> servicecall = apiservice.ParolaGuncelle(Constants.API_KEY,Token, "text/json;charset=UTF-8",password);
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
                            if(responseBody.Status.equals("999"))
                            {
                                clearUser();
                                newActivity(new LoginActivity());
                                finish();
                                toastMessage(SettingsActivity.this, getResources().getString(R.string.pleaserelogin));
                            }
                            else
                            {
                                toastMessage(SettingsActivity.this, responseBody.Message.toString());
                            }
                        }
                        else
                        {
                            clearUser();
                            newActivity(new LoginActivity());
                            finish();
                            toastMessage(SettingsActivity.this, getResources().getString(R.string.updatepasswordchangesuccesfully));
                        }
                        hideLoadingPopup();
                    } else {
                        hideLoadingPopup();
                        toastMessage(SettingsActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {
                    Log.e("exlogin", ex.toString());
                    hideLoadingPopup();
                    toastMessage(SettingsActivity.this, ex.getMessage());
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(SettingsActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });

    }

    private void DeleteMyAccount()
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
        Call<LoginResponse> servicecall = apiservice.HesapSilme(Constants.API_KEY,Token, "text/json;charset=UTF-8");
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
                            if(responseBody.Status.equals("999"))
                            {
                                clearUser();
                                newActivity(new LoginActivity());
                                finish();
                                toastMessage(SettingsActivity.this, getResources().getString(R.string.pleaserelogin));
                            }
                            else
                            {
                                toastMessage(SettingsActivity.this, responseBody.Message.toString());
                            }
                        }
                        else
                        {
                            clearUser();
                            newActivity(new LoginActivity());
                            finish();
                            toastMessage(SettingsActivity.this, getResources().getString(R.string.deletedaccountsuccesfully));
                        }
                        hideLoadingPopup();
                    } else {
                        hideLoadingPopup();
                        toastMessage(SettingsActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {
                    Log.e("exlogin", ex.toString());
                    hideLoadingPopup();
                    toastMessage(SettingsActivity.this, ex.getMessage());
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(SettingsActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });

        hideLoadingPopup();
    }

    public void clearUser()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("Token","");
        edit.putString("ReferansKodu", "");
        edit.putString("KullaniciAdi","");
        edit.putString("Parola","");
        edit.putString("AdSoyad","");
        edit.putString("Email","");
        edit.putString("Telefon","");
        edit.commit();
    }

    private void SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        TextView edt_refcode = (TextView) findTextViewById(R.id.edt_refcode);
        TextView edt_nameandsurname = (TextView) findTextViewById(R.id.edt_nameandsurname);
        TextView edt_mail = (TextView) findTextViewById(R.id.edt_mail);
        TextView edt_username = (TextView) findTextViewById(R.id.edt_username);
        TextView edt_password = (TextView) findTextViewById(R.id.edt_password);
        TextView edt_phonenumber = (TextView) findTextViewById(R.id.edt_phonenumber);
        TextView btn_update_password = (TextView) findTextViewById(R.id.btn_update_password);
        TextView btn_delete_account = (TextView) findTextViewById(R.id.btn_delete_account);

        edt_refcode.setTypeface(type);
        edt_nameandsurname.setTypeface(type);
        edt_mail.setTypeface(type);
        edt_username.setTypeface(type);
        edt_password.setTypeface(type);
        edt_phonenumber.setTypeface(type);
        btn_update_password.setTypeface(type);
        btn_delete_account.setTypeface(type);

        edt_refcode.setText(prefs.getString("ReferansKodu", ""));
        edt_nameandsurname.setText(prefs.getString("AdSoyad",""));
        edt_mail.setText(prefs.getString("Email",""));
        edt_username.setText(prefs.getString("KullaniciAdi",""));
        edt_password.setText(prefs.getString("Parola",""));
        edt_phonenumber.setText(prefs.getString("Telefon",""));
        }
    private void initButtons()
    {
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        findTextViewById(R.id.btn_update_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new UpdatePasswordPopUp(SettingsActivity.this) {
                    @Override
                    protected void OnUpdate(String password) {

                        UpdatePassword(password);
                    }
                };
            }
        });

        findTextViewById(R.id.btn_delete_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DeleteMyAccount();
            }
        });

    }
}
