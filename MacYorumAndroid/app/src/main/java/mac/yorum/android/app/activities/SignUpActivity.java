package mac.yorum.android.app.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import yorum.mac.com.macyorumandroid.R;

public class SignUpActivity extends BaseAppCompatActivitiy {

    EditText mail,mophone,pswd,usrusr;
    TextView lin,sup;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        /*
        sup = (TextView) findViewById(R.id.sup);
        lin = (TextView) findViewById(R.id.lin);
        usrusr = (EditText) findViewById(R.id.usrusr);
        pswd = (EditText) findViewById(R.id.pswrdd);
        mail = (EditText) findViewById(R.id.mail);
        mophone = (EditText) findViewById(R.id.mobphone);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/LatoLight.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/LatoRegular.ttf");
        mophone.setTypeface(custom_font);
        sup.setTypeface(custom_font1);
        pswd.setTypeface(custom_font);
        lin.setTypeface(custom_font);
        usrusr.setTypeface(custom_font);
        mail.setTypeface(custom_font);
        lin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignUpActivity.this,CouponListActivity.class);//yeni sayfa çağırma gitme işlemi yapar bu iki satır
                startActivity(it);
            }
        });
        */
    }
}
