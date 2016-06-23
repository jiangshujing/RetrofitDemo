package com.jsj.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddRxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LIST1 = "http://apis.baidu.com/showapi_open_bus/showapi_joke/";

    private ProgressBar pb;

    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrxjava);
        Button button = (Button) findViewById(R.id.button);
        pb = (ProgressBar) findViewById(R.id.pb);
        textview = (TextView) findViewById(R.id.textview);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        loadingData();
    }

    /**
     * 加载数据
     */
    private void loadingData() {
        //创建Retrofit 实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LIST1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//这样接口就能返回Observable了
                .build();

        retrofit.create(GetBaidu.class)//使用声明的接口创建
                .getJokeRxJava("ffac023cd51e5d0430d6ceaecf623c2e", "1")
                .subscribeOn(Schedulers.io())//请求网络在io线程执行
                .observeOn(AndroidSchedulers.mainThread())//更新ui在主线程执行
                .subscribe(new Subscriber<Res>() {

                    @Override
                    public void onStart() {
                        pb.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCompleted() {
                        pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        textview.setText("onError");
                        Log.e("AddRxJavaActivity ===", "onError" + e);
                        pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(Res res) {
                        textview.setText(res.toString());
                        Log.e("AddRxJavaActivity ===", res.showapi_res_body.contentlist.toString());
                    }
                });
    }
}
