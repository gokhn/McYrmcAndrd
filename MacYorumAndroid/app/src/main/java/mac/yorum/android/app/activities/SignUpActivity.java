package mac.yorum.android.app.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import yorum.mac.com.macyorumandroid.R;

public class SignUpActivity extends BaseAppCompatActivitiy {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        SetFont();
        initButtons();
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

            }
        });

        findTextViewById(R.id.btn_already_have_an_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newActivity(new LoginActivity());
            }
        });

    }
}
