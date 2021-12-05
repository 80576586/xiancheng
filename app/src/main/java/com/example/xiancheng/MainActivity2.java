package com.example.xiancheng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    Button backButton;
    private Button commentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        WebView webView=findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String id=bundle.getString("id");

        webView.loadUrl(" https://zhihu.com/story/"+id);
        backButton=findViewById(R.id.back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity2.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        commentButton=findViewById(R.id.btnComment);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity2.this,MainActivity3.class);
                Bundle bundle2=new Bundle();
                bundle2.putString("id",id);
                intent1.putExtras(bundle2);
                startActivity(intent1);

            }
        });
    }


}
