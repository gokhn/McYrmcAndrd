package mac.yorum.android.app.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import yorum.mac.com.macyorumandroid.R;

public class LauncherActivity extends BaseAppCompatActivitiy {

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeKeyboard();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);

        SetFont();
        initButtons();

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
