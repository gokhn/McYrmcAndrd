package mac.yorum.android.app.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import mac.yorum.android.app.configs.Constants;
import mac.yorum.android.app.configs.UrlConfig;
import mac.yorum.android.app.helpers.ValidationHelper;
import mac.yorum.android.app.models.LoginResponse;
import mac.yorum.android.app.models.Result;
import mac.yorum.android.app.models.SignUpRequest;
import mac.yorum.android.app.retrofit.RefrofitClass;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yorum.mac.com.macyorumandroid.R;

public class SignUpActivity extends BaseAppCompatActivitiy {

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        SetFont();
        initButtons();
    }

    private void signUp()
    {
        EditText edt_refcode = (EditText) findEditTextById(R.id.edt_refcode);
        EditText edt_nameandsurname = (EditText) findEditTextById(R.id.edt_nameandsurname);
        EditText edt_mail = (EditText) findEditTextById(R.id.edt_mail);
        EditText edt_username = (EditText) findEditTextById(R.id.edt_username);
        EditText edt_password = (EditText) findEditTextById(R.id.edt_password);
        EditText edt_phonenumber = (EditText) findEditTextById(R.id.edt_phonenumber);

        if (validateLogin(edt_refcode, edt_nameandsurname,edt_mail,edt_username,edt_password,edt_phonenumber))
            doSignUpUseToRetrofit(edt_refcode.getText().toString(), edt_nameandsurname.getText().toString(),edt_mail.getText().toString(),
                    edt_username.getText().toString(),edt_password.getText().toString(),edt_phonenumber.getText().toString());
    }

    private void doSignUpUseToRetrofit(final String RefCode,String NameSurname,String Email, final String userName, final String password,final String PhoneNumber)
    {
        showLoadingPopup();

        SignUpRequest signUpRequest = new SignUpRequest();

        signUpRequest.setReferansKodu(RefCode);
        signUpRequest.setAdSoyad(NameSurname);
        signUpRequest.setEposta(Email);
        signUpRequest.setKullaniciAdi(userName);
        signUpRequest.setParola(password);
        signUpRequest.setTelefon(PhoneNumber);

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
        Call<LoginResponse> servicecall = apiservice.SignUp(Constants.API_KEY,"", "text/json;charset=UTF-8", signUpRequest);
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
                            toastMessage(SignUpActivity.this, responseBody.Message);
                        }
                        else
                        {

                            if(ValidationHelper.isStringHasValue(responseBody.Message))
                            {
                                toastMessage(SignUpActivity.this,responseBody.Message);
                            }
                            else
                            {
                                ArrayList<Result> res = response.body().Result;

                                SharedPreferences.Editor edit = prefs.edit();

                                edit.putString("Token",res.get(0).getToken());
                                edit.putString("ReferansKodu",res.get(0).getReferansKodu());
                                edit.putString("KullaniciAdi",res.get(0).getKullaniciAdi());
                                edit.putString("Parola",res.get(0).getParola());
                                edit.putString("AdSoyad",res.get(0).getAdSoyad());
                                edit.putString("Email",res.get(0).getEposta());
                                edit.putString("Telefon",res.get(0).getTelefon());

                                edit.commit();
                                finish();
                                closeKeyboard();
                                newActivity(new MainActivity(), Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            }
                        }
                        hideLoadingPopup();
                    } else {
                        hideLoadingPopup();
                        toastMessage(SignUpActivity.this, getResources().getString(R.string.error_found));
                    }

                } catch (Exception ex) {
                    Log.e("exlogin", ex.toString());
                    hideLoadingPopup();
                }
                hideLoadingPopup();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                toastMessage(SignUpActivity.this, t.getMessage());
                hideLoadingPopup();
            }
        });
    }


    private boolean validateLogin(EditText edt_refcode, EditText edt_nameandsurname,EditText edt_mail,EditText edt_username,EditText edt_password,EditText edt_phonenumber) {
        if (ValidationHelper.isStringOrEmpty(edt_refcode.getText().toString())) {
            toastValidationMessage(R.string.refcode);
            return false;
        }
        else if (ValidationHelper.isStringOrEmpty(edt_nameandsurname.getText().toString()))
        {
            toastValidationMessage(R.string.nameandsurname);
            return false;
        }
        else if (ValidationHelper.isStringOrEmpty(edt_mail.getText().toString()))
        {
            toastValidationMessage(R.string.emailaddress);
            return false;
        }
        else if (ValidationHelper.isStringOrEmpty(edt_username.getText().toString()))
        {
            toastValidationMessage(R.string.username);
            return false;
        }
        else if (ValidationHelper.isStringOrEmpty(edt_password.getText().toString()))
        {
            toastValidationMessage(R.string.password);
            return false;
        }
        else if (ValidationHelper.isStringOrEmpty(edt_phonenumber.getText().toString()))
        {
            toastValidationMessage(R.string.phonenumber);
            return false;
        }

        else
            return true;
    }

    private void SetFont()
    {
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");

        EditText edt_refcode = (EditText) findEditTextById(R.id.edt_refcode);
        EditText edt_nameandsurname = (EditText) findEditTextById(R.id.edt_nameandsurname);
        EditText edt_mail = (EditText) findEditTextById(R.id.edt_mail);
        EditText edt_username = (EditText) findEditTextById(R.id.edt_username);
        EditText edt_password = (EditText) findEditTextById(R.id.edt_password);
        EditText edt_phonenumber = (EditText) findEditTextById(R.id.edt_phonenumber);
        TextView btn_signup = (TextView) findTextViewById(R.id.btn_signup);
        TextView btn_already_have_an_account = (TextView) findTextViewById(R.id.btn_already_have_an_account);

        edt_refcode.setTypeface(type);
        edt_nameandsurname.setTypeface(type);
        edt_mail.setTypeface(type);
        edt_username.setTypeface(type);
        edt_password.setTypeface(type);
        edt_phonenumber.setTypeface(type);
        btn_signup.setTypeface(type);
        btn_already_have_an_account.setTypeface(type);


    }
    private void initButtons()
    {
        findTextViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signUp();
            }
        });

        findTextViewById(R.id.btn_already_have_an_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                newActivity(new LoginActivity());
            }
        });

    }
}
