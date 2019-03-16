package com.nytimes.networklayer;


/**
 * Created by anshulsachdeva on 15/03/19.
 *
 */
public interface ResponseListener {

    /**
     *
     * @param object
     * @param tag
     */
    void onResponse(Object object, String tag);

    /**
     *
     * @param message
     * @param tag
     */
    void onFailure(String message, String tag);
}
