package com.jsj.retrofitdemo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jsj on 16/6/22.
 */
public class Res implements Serializable {

    public int showapi_res_code;

    public String showapi_res_error;

    public BeanBody showapi_res_body;

    @Override
    public String toString() {
        return "Res [showapi_res_code=" + showapi_res_code
                + ", showapi_res_error=" + showapi_res_error
                + ", showapi_res_body=" + showapi_res_body + "]";
    }


    static class BeanBody {
        public int allNum;

        public int allPages;

        public List<Joke> contentlist;

        @Override
        public String toString() {
            return "BeanBody [allNum=" + allNum + ", allPages=" + allPages
                    + ", contentlist=" + contentlist + "]";
        }

        static class Joke {
            public String date;
            public String text;
            public String title;
            public int type;

            @Override
            public String toString() {
                return "Joke [date=" + date + ", text=" + text + ", title=" + title
                        + ", type=" + type + "]";
            }
        }
    }
}
