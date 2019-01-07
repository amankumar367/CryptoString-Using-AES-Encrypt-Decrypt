package com.dev.aman.cryptostring.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

public class EncryptionActivity extends AppCompatActivity {

    private TextView mEncrytedString, mCopy;
    private Button mEncryptBtn;
    private EditText mEnterString;
    private ImageView mBack;
    private String encryptedString;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        init();
        onClick();
        textWatcher();

        mEncryptBtn.setClickable(false);
        mEncryptBtn.getBackground().setAlpha(65);
    }

    private void init() {
        mBack = findViewById(R.id.back);
        mEncrytedString = findViewById(R.id.encryptedString);
        mEncryptBtn = findViewById(R.id.encryptString);
        mEnterString = findViewById(R.id.enteredEncryptString);
        mCopy = findViewById(R.id.copy);
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

        mEncryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encryptedString = encryption(mEnterString.getText().toString());
                mEncrytedString.setText(encryptedString);
                mCopy.setVisibility(View.VISIBLE);
                Snackbar.make(linearLayout,"Encryption Successfull  .  .  .  .",Snackbar.LENGTH_LONG).show();
            }
        });

        mCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Encrypted Code", encryptedString);
                clipboard.setPrimaryClip(clip);
                Snackbar.make(linearLayout,"Encrypted Code Copy to Clipboard .  .  .  .",Snackbar.LENGTH_LONG).show();
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
                    mEncryptBtn.setClickable(true);
                    mEncryptBtn.getBackground().setAlpha(255);
                }

                if(s.toString().equals("")){
                    mEncryptBtn.setClickable(false);
                    mEncryptBtn.getBackground().setAlpha(65);
                    mCopy.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                mEncrytedString.setText("");
            }
        });
    }

    public String encryption(String strNormalText){
        String seedValue = "YourSecKey";
        String normalTextEnc="";
        try {
            normalTextEnc = AESHelper.encrypt(seedValue, strNormalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return normalTextEnc;
    }

}
