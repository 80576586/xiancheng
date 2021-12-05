package com.example.xiancheng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    Object HttpURLConnection1;
     private RecyclerView recyclerView1;
    private TextView commentNum;
    List<Map<String,Object>> list1 =new ArrayList();
    String list2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerView1=findViewById(R.id.rv);

        commentNum=findViewById(R.id.commentNum);
        Button button = findViewById(R.id.back3);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        String id=bundle.getString("id");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
                finish();

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection1 = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://news-at.zhihu.com/api/3/story-extra/"+id);

                    java.net.HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = url.openStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);

                    }
                    showResponse3(response.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            commentNum.setText(list2+"条评论");
                            int a=Integer.parseInt(list2);
                            if (a>0){
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity3.this, LinearLayoutManager.VERTICAL, false);
                                recyclerView1.setLayoutManager(linearLayoutManager);
                                LinearAdapter LinearAdapter = new LinearAdapter(MainActivity3.this, list1);
                                recyclerView1.setAdapter(LinearAdapter);
                            }
                        }
                    });
                } catch (Exception e) {
                    //断网提示
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection1 = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://news-at.zhihu.com/api/4/story/"+id+"/long-comments");

                    java.net.HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = url.openStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);

                    }
                    showResponse2(response.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity3.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView1.setLayoutManager(linearLayoutManager);
                            LinearAdapter LinearAdapter = new LinearAdapter(MainActivity3.this, list1);
                            recyclerView1.setAdapter(LinearAdapter);


                        }
                    });
                } catch (Exception e) {
                    //断网提示
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();




        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection1 = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://news-at.zhihu.com/api/4/story/"+id+"/short-comments");

                    java.net.HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = url.openStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);

                    }
                    showResponse2(response.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity3.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView1.setLayoutManager(linearLayoutManager);
                            LinearAdapter LinearAdapter = new LinearAdapter(MainActivity3.this, list1);
                            recyclerView1.setAdapter(LinearAdapter);


                        }
                    });
                } catch (Exception e) {
                    //断网提示
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();




    }

    private void showResponse3(String json) {
        try {
            JSONObject jsonObject=new JSONObject(json);
            String all=jsonObject.getString("comments");
            String longComment=jsonObject.getString("long_comments");
            String shortComment=jsonObject.getString("short_comments");
            Map<String,Object> map2=new HashMap<>();
            map2.put("comments",all);
            map2.put("long_comments",longComment);
            map2.put("short_comments",shortComment);
            list2=all;
            list1.add(map2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void showResponse2(String JsonData) {
        try {
            JSONObject jsonObject3=new JSONObject(JsonData);
            JSONArray jsonArray=jsonObject3.getJSONArray("comments");

            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String content=jsonObject1.getString("content");
                String author=jsonObject1.getString("author");
                String avatar=jsonObject1.getString("avatar");


                Map<String,Object> map1=new HashMap<>();
                map1.put("content",content);
                map1.put("author",author);
                map1.put("avatar",avatar);
                list1.add(map1);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}