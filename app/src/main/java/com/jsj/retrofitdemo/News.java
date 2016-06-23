package com.jsj.retrofitdemo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by laucherish on 16/3/15.
 */
public class News implements Serializable {


    public String msg;

    public List<New> body;

    public int code;


    static class New {

        public int status;

        public String code;

        public int type;


        @Override
        public String toString() {
            return "New{" +
                    "status=" + status +
                    ", code='" + code + '\'' +
                    ", type=" + type +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "News{" +
                "msg='" + msg + '\'' +
                ", body=" + body +
                ", code=" + code +
                '}';
    }
}


