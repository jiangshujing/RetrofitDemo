package com.jsj.retrofitdemo;

import com.squareup.okhttp.ResponseBody;

import java.util.Map;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by jsj on 16/6/21.
 */
public interface GetBaidu {
    @GET("www.baidu.com")
    Call<ResponseBody> get();

    @FormUrlEncoded
    @POST("cab/cell/getall_list")
    Call<ResponseBody> getList(@Field("cabinet_code")String cabinet_code);

    @FormUrlEncoded
    @POST("cab/cell/getall_list")
    Call<News> getListBean(@Field("cabinet_code")String cabinet_code);

    @FormUrlEncoded
    @POST("joke_text")
    Call<Res> getJoke(@Header("apikey") String key, @Field("page") String page);

    @FormUrlEncoded
    @POST("joke_text")
    Observable<Res> getJokeRxJava(@Header("apikey") String key, @Field("page") String page);

    @GET("group/{id}/users")
    Call<ResponseBody> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
}
