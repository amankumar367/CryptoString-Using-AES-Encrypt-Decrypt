package com.dev.aman.cryptostring.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dev.aman.cryptostring.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mEncrypt, mDecrypt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onClick();
    }

    private void init() {
        mEncrypt = findViewById(R.id.encrypt);
        mDecrypt = findViewById(R.id.decrypt);
    }

    private void onClick() {

        mEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EncryptionActivity.class));
            }
        });

        mDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DecryptionActivity.class));
            }
        });
    }

}
