package com.muliamaulana.gogadget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSamsung, btnApple, btnXiaomi, btnLenovo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSamsung = (Button) findViewById(R.id.btnSamsung);
        btnSamsung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SamsungActivity.class));
            }
        });

        btnApple = (Button) findViewById(R.id.btnApple);
        btnApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AppleActivity.class));
            }
        });

        btnXiaomi = (Button) findViewById(R.id.btnSony);
        btnXiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SonyActivity.class));
            }
        });

        btnLenovo = (Button) findViewById(R.id.btnLenovo);
        btnLenovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LenovoActivity.class));
            }
        });
    }
}
