package mac.yorum.android.app.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import yorum.mac.com.macyorumandroid.R;

public abstract class UpdatePasswordPopUp extends Dialog {

    private Activity activity;
    private  String amounth;

    public UpdatePasswordPopUp(Context context)
    {
        super(context);
        this.activity = (Activity)context;
        this.amounth = amounth;

        showPopup();
    }

    private void showPopup()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_password_popup);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.PopupAnimation;

        Typeface type = Typeface.createFromAsset(activity.getAssets(),"fonts/Roboto-Medium.ttf");

        EditText txt_newpassword = (EditText)findViewById(R.id.txt_newpassword);
        final EditText txt_newpassword_again = (EditText)findViewById(R.id.txt_newpassword_again);
        TextView btn_signup = (TextView)findViewById(R.id.btn_signup);
        TextView txtclose = (TextView)findViewById(R.id.txtclose);

        txt_newpassword.setTypeface(type);
        txt_newpassword_again.setTypeface(type);
        btn_signup.setTypeface(type);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ValidationText())
                {
                    OnUpdate(txt_newpassword_again.getText().toString());
                    dismiss();
                }
            }
        });

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        show();
        setCancelable(false);
    }

    private boolean ValidationText()
    {
        EditText txt_newpassword = (EditText)findViewById(R.id.txt_newpassword);
        EditText txt_newpassword_again = (EditText)findViewById(R.id.txt_newpassword_again);

        closeKeyboard();
        if(txt_newpassword.getText().length() ==0)
        {
            Toast.makeText(activity, activity.getResources().getString(R.string.newpassword), Toast.LENGTH_LONG).show();
            return false;
        }
        else if(txt_newpassword_again.getText().length() ==0)
        {
            Toast.makeText(activity, activity.getResources().getString(R.string.newpasswordagain), Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!txt_newpassword.getText().toString().equals(txt_newpassword_again.getText().toString()))
        {
            Toast.makeText(activity, activity.getResources().getString(R.string.cannotmathingpassword), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
    public void closeKeyboard()
    {
        if(getCurrentFocus() != null)
        {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected abstract void OnUpdate(String password);

}
