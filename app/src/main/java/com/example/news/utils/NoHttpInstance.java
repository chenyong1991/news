package com.example.news.utils;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

import java.util.Objects;

/**
 * Created by c8469 on 2016/12/13.
 */

public class NoHttpInstance {

    private static RequestQueue instance;
    private static Object o = new Object();

    private NoHttpInstance(){

    }

    public static RequestQueue getInstance(){

        if(instance == null){

            synchronized (o){

                if(instance == null){

                    instance = NoHttp.newRequestQueue();

                }

            }

        }

        return instance;

    }

}
