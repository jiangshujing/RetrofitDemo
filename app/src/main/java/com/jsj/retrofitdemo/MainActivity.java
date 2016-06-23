package com.jsj.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://www.baidu.com";
    private static final String LIST = "http://10.0.1.232:8889/cabzoo/";
    private static final String LIST1 = "http://apis.baidu.com/showapi_open_bus/showapi_joke/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("MainActivity ===", "onCreate");
        new Thread() {
            @Override
            public void run() {

                //创建Retrofit 实例
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LIST1)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                GetBaidu getBaidu = retrofit.create(GetBaidu.class);//使用声明的接口创建
//                Call<ResponseBody> call = getBaidu.getList("1412345678");//获取call,执行请求
//                retrofit.Call<News> call1 = getBaidu.getListBean("1412345678");//获取call,执行请求

                retrofit.Call<Res> repos = getBaidu.getJoke("ffac023cd51e5d0430d6ceaecf623c2e", "1");

                Log.e("MainActivity ===", "repos");
                repos.enqueue(new Callback<Res>(){

                    @Override
                    public void onResponse(Response<Res> response, Retrofit retrofit) {
                        Res body = response.body();
                        Log.e("MainActivity ===", body.showapi_res_body.contentlist.toString());
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("MainActivity ===","onFailure");
                    }
                });

//
                //同步请求
//        try {
//            Response<ResponseBody> bodyResponse = call.execute();
//            String body = bodyResponse.body().string();//获取返回体的字符串
//            Log.e("", "同步请求 body===" + body);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//                //异步请求
//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
//                        String body = null;
//                        try {
//                            body = response.body().string().toString();//获取返回体的字符串
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Log.e("mainActiviy", "异步请求 body===" + body);
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        Log.e("mainActiviy", "异步请求 onFailure");
//                    }
//                });


////                异步请求--返回被解析的bean 这个返回的json 解析时一直报解析错误
//                call1.enqueue(new retrofit.Callback<News>() {
//                    @Override
//                    public void onResponse(Response<News> response, Retrofit retrofit) {
//                        News news = response.body();
//                        Log.e("mainActiviy", "异步请求 bean===" + news.toString());
//                    }
//
//                    @Override
//                    public void onFailure(Throwable t) {
//                        Log.e("mainActiviy", "异步请求 onFailure"+t.toString());
//                    }
//                });


            }
        }.start();

    }
}
