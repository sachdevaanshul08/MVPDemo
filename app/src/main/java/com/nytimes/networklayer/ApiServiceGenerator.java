package com.nytimes.networklayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nytimes.AppConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * This will act as a wrapper of Retrofit http client library.
 * <p/>
 * This Class allows us to make the synchronus and asynchronus network calls using Retrofit.
 * along with the caching mechanism.
 *
 * Created by anshulsachdeva on 15/03/19.
 */

public final class ApiServiceGenerator {

    private static final String TAG = ApiServiceGenerator.class.getCanonicalName();

    private Retrofit retrofit;
    private OkHttpClient.Builder okHttpClientBuilder;
    Retrofit.Builder retrofitBuilder;
    final Gson gson;
    OkHttpClient okHttpClient;
    private static ApiServiceGenerator mTaskServiceGenerator;

    private ApiServiceGenerator() {

        gson = new GsonBuilder().serializeNulls().setLenient().create();

        //RetroBuilder & OkHttpClientBuilder
        retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(getApiBaseUrl())
                        .addConverterFactory(GsonConverterFactory.create(gson));
        okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClient = okHttpClientBuilder.build();
        retrofit = retrofitBuilder.client(okHttpClient).build();
    }

    /**
     *
     * @return
     */
    public static ApiServiceGenerator getInstance() {
        if (mTaskServiceGenerator == null) {
            mTaskServiceGenerator = new ApiServiceGenerator();
        }
        return mTaskServiceGenerator;
    }

    /**
     * this will generate & return the service for each of the network calls
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public <T> T getService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }


    /**
     * @return
     */
    public String getApiBaseUrl() {

        return AppConfig.BASE_URL;
    }


}
