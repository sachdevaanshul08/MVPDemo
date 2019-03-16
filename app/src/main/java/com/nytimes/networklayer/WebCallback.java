package com.nytimes.networklayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by anshulsachdeva on 15/03/19.
 *
 */
public class WebCallback<T> implements Callback<T> {


    private static final String TAG = WebCallback.class.getCanonicalName();
    private Call<T> call;
    private static final List<WebCallback> NETWORKLIST = new ArrayList<>();
    private String mTag = null;
    private String requestTag = null;
    private ResponseListener networkResponseListener;

    public WebCallback(Call<T> call, String tag, ResponseListener networkResponseListener) {
        mTag = tag;
        this.networkResponseListener = networkResponseListener;
        this.call = call;
        NETWORKLIST.add(this);
    }

    /**
     * Remove the saved network call from the ArrayList
     *
     * @param requestTag A tag which identifies the network call
     */
    public static void removeRequest(String requestTag) {
        if (requestTag != null) {
            Iterator<WebCallback> iterator = NETWORKLIST.iterator();
            WebCallback item;
            while (iterator.hasNext()) {
                item = iterator.next();
                if (item != null && item.mTag != null && requestTag.equals(item.mTag)) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.isSuccessful()) {
            if (networkResponseListener != null)
                networkResponseListener.onResponse(response.body(), requestTag);
            //Remove the tag on completion of the request.
            // on success of response remove the tag.
            removeRequest(requestTag);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable error) {
        requestTag = call.request().header("mTag");
        //Can implement your retry function here
        if (networkResponseListener != null) {
            networkResponseListener.onFailure(error.getMessage(), requestTag);
        }
        removeRequest(requestTag);
    }



}


