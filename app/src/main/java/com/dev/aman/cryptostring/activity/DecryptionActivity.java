package com.dev.aman.cryptostring.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.aman.cryptostring.R;
import com.dev.aman.cryptostring.helper.AESHelper;

public class DecryptionActivity extends AppCompatActivity {

    private TextView mDecrytedString;
    private Button mDecryptBtn;
    private EditText mEnterString;
    private ImageView mBack;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);

        init();
        onClick();
        textWatcher();

        mDecryptBtn.setClickable(false);
        mDecryptBtn.getBackground().setAlpha(65);
    }

    private void init() {
        mBack = findViewById(R.id.back);
        mDecrytedString = findViewById(R.id.decryptedString);
        mDecryptBtn = findViewById(R.id.decryptString);
        mEnterString = findViewById(R.id.enteredDecryptString);
        linearLayout = findViewById(R.id.linearlayout);
    }

    private void onClick() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        mDecryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String decryptedString = decryption(mEnterString.getText().toString());
                if(!decryptedString.equals("")){
                    mDecrytedString.setText(decryptedString);
                    Snackbar.make(linearLayout,"Decryption Successfull  .  .  .  .",Snackbar.LENGTH_LONG).show();
                }else {
                    Snackbar.make(linearLayout,"Please Enter Valid Encrypted Code  .  .  .  .",Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void textWatcher() {
        mEnterString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().equals("")){
                    mDecryptBtn.setClickable(true);
                    mDecryptBtn.getBackground().setAlpha(255);
                }

                if(s.toString().equals("")){
                    mDecryptBtn.setClickable(false);
                    mDecryptBtn.getBackground().setAlpha(65);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                mDecrytedString.setText("");
            }
        });
    }

    public String decryption(String strEncryptedText){
        String seedValue = "YourSecKey";
        String strDecryptedText="";
        try {
            strDecryptedText = AESHelper.decrypt(seedValue, strEncryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDecryptedText;
    }

}
