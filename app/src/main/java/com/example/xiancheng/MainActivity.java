package com.example.xiancheng;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Object HttpURLConnection;
    TextView textView;
    TextView textView1;
    TextView textViewTime;
    TextView textViewMonth;
    TextView textViewFirst;
    private RecyclerView recyclerView;
    private tryAdapter tryAdapter;
    ImageView imageView;
     LinearLayout linearLayout;
    private List<Map<String,Object>> list =new ArrayList();
    String format1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.tv1);
        textView1=findViewById(R.id.tv2);
        textViewFirst=findViewById(R.id.tvFirst);
        imageView=findViewById(R.id.iv);
        linearLayout=findViewById(R.id.ln);
        textViewTime=findViewById(R.id.textViewDay);
        textViewMonth=findViewById(R.id.textViewMonth);
        recyclerView=findViewById(R.id.recy);
        tryAdapter =new tryAdapter(MainActivity.this,list);
        setPullRefresher();



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("MM");
        Date date = new Date(System.currentTimeMillis());

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String year1=String.valueOf(year);
        int montha = calendar.get(Calendar.MONTH);
        int month=montha+1;
        String month1=String.valueOf(month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String day1=String.valueOf(day);
        Time time = new Time();
        time.setToNow();
        int time_hour = time.hour;
        String date1=month1+"月"+day1+"日";
        textViewTime.setText(""+simpleDateFormat.format(date));

        if (month==1)
            textViewMonth.setText("一月");
        if (month==2)
            textViewMonth.setText("二月");
        if (month==3)
            textViewMonth.setText("三月");
        if (month==4)
            textViewMonth.setText("四月");
        if (month==5)
            textViewMonth.setText("五月");
        if (month==6)
            textViewMonth.setText("六月");
        if (month==7)
            textViewMonth.setText("七月");
        if (month==8)
            textViewMonth.setText("八月");
        if (month==9)
            textViewMonth.setText("九月");
        if (month==10)
            textViewMonth.setText("十月");
        if (month==11)
            textViewMonth.setText("十一月");

        if (month==12)
            textViewMonth.setText("十二月");

        if (time_hour>6&&time_hour<12)
            textViewFirst.setText("早上好！");
        else if (time_hour>=12&&time_hour<18)
            textViewFirst.setText("知乎日报");
        else if (time_hour>=18&&time_hour<23)
            textViewFirst.setText("晚上好！");
        else if (time_hour>=23||time_hour<3)
            textViewFirst.setText("早点休息~");
        else textViewFirst.setText("知乎日报");
        refreshAll();
    }

    private void setPullRefresher() {
        SmartRefreshLayout smartRefreshLayout = findViewById(R.id.refreshLayout);
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshAll();
                tryAdapter.notifyDataSetChanged();
                smartRefreshLayout.finishRefresh(2000);

            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                messageDate();
//                sendRequestWithOKHttp();
                smartRefreshLayout.finishLoadMore(2000);
            }
        });
    }
    int x=1;
    int y=1;

    private void refreshAll(){
        list.clear();
        new Thread(new Runnable() {


            @Override
            public void run() {

                HttpURLConnection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://news-at.zhihu.com/api/3/stories/latest");

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

                    DateFormat df = new SimpleDateFormat("yyyyMMdd");// 多态
                    Date date = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.DAY_OF_MONTH, -0);
                    date = (Date) calendar.getTime();
                    String format = df.format(date);
                    HttpURLConnection = null;
                    BufferedReader reader1 = null;
                    URL url1 = new URL("https://news-at.zhihu.com/api/3/news/before/"+format);

                    java.net.HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                    connection1.setRequestMethod("GET");
                    connection1.setConnectTimeout(8000);
                    connection1.setReadTimeout(8000);
                    InputStream in1 = url1.openStream();
                    reader1 = new BufferedReader(new InputStreamReader(in1));
                    StringBuilder response1 = new StringBuilder();
                    String line1;

                    while ((line1 = reader1.readLine()) != null) {
                        response1.append(line1);

                    }
                    showResponse2(response1.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(tryAdapter);

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

    private void messageDate() {

        DateFormat df1=new SimpleDateFormat("MM月dd日");
        Date date1=new Date();
        Calendar calendar1=Calendar.getInstance();
        calendar1.setTime(date1);
        calendar1.add(Calendar.DAY_OF_MONTH,-y);
        date1=calendar1.getTime();
        format1=df1.format(date1);
        String title="";
        String hint="";
        String images="";
        String id="";

        y++;
        Map<String,Object> map=new HashMap<>();
//        map.put("format1",format1);
//        map.put("title",title);
//        map.put("hint",hint);
//        map.put("images",images);
//        map.put("id",id);
//        list.add(map);
        sendRequestWithOKHttp();

    }

    private void sendRequestWithOKHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                DateFormat df = new SimpleDateFormat("yyyyMMdd");// 多态
                Date date = new Date();

                Calendar calendar = Calendar.getInstance();

                calendar.setTime(date);

                calendar.add(Calendar.DAY_OF_MONTH, -x);

                date = (Date) calendar.getTime();

                String format = df.format(date);

                x++;
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://news-at.zhihu.com/api/3/news/before/" +format);
                    //Log.i("date",format);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
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
                            tryAdapter.notifyDataSetChanged();
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


    public void showResponse2(String JsonData) {

        try {
            JSONObject jsonObject=new JSONObject(JsonData);
            JSONArray jsonArray=jsonObject.getJSONArray("stories");

            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                JSONArray jsonArray1=jsonObject1.getJSONArray("images");
                String title=jsonObject1.getString("title");
                String hint=jsonObject1.getString("hint");
                String id=jsonObject1.getString("id");
                String images=null;
                for (int j = 0; j<jsonArray1.length(); j++){

                    images =jsonArray1.getString(j)+images;
                }
                Map<String,Object> map=new HashMap<>();
                map.put("title",title);
                map.put("hint",hint);
                map.put("images",images);
                map.put("id",id);
                list.add(map);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}

