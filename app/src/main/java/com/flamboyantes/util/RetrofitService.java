package com.flamboyantes.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitService {

    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;


    /**
     *
     * @param serviceClass
     * @param serverURL
     * @param withHeader
     * @param <S>
     * @return
     */
    //Retrofit service method for all api calling
    public static <S> S createService(Class<S> serviceClass, String serverURL, boolean withHeader
    ) {

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(60, TimeUnit.SECONDS );
        httpClient.readTimeout(60, TimeUnit.SECONDS );




        if (withHeader) {

            httpClient.addInterceptor(

                    new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            final Request request = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json")
                                    .build();

                            return chain.proceed(request);
                        }
                    }

            );

        }



        builder = new Retrofit.Builder()
                .baseUrl(serverURL)
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}
