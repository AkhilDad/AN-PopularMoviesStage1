package com.udacity.popularmoviesstage1.network;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.udacity.popularmoviesstage1.model.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

/**
 * Created by akhil on 27/02/16.
 */
public class NetworkRequest extends AsyncTask<Void, Void, ResponseBean> {

    private static final int TIMEOUT_MILLIS = 30 * 1000; // 30 seconds
    private NetworkResponseListener mNetworkResponseListener;
    private RequestBean mRequestBean;
    public NetworkRequest(RequestBean requestBean,NetworkResponseListener networkResponseListener) {
        mRequestBean = requestBean;
        mNetworkResponseListener = networkResponseListener;
    }

    @Override
    protected ResponseBean doInBackground(Void... params) {
        String url = mRequestBean.getUrl();
        ResponseBean responseBean = new ResponseBean();
        HttpURLConnection respObj = sendGetRequest(url);
        InputStream inputStream;
        if (respObj != null) {
            try {
                int statusCode = respObj.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = respObj.getInputStream();
                    if (inputStream != null) {
                        responseBean = processResponse(NetworkUtils.readAsString(inputStream),responseBean.getClass());
                        responseBean.setResponseCode(HttpURLConnection.HTTP_OK);
                        inputStream.close();
                    }

                } else {
                    responseBean.setResponseCode(statusCode);
                    inputStream = respObj.getErrorStream();
                    if (inputStream != null) {
                        responseBean.setErrorString(NetworkUtils.readAsString(inputStream));
                        inputStream.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return responseBean;
    }

    @Override
    protected void onPostExecute(ResponseBean responseBean) {
        super.onPostExecute(responseBean);
        mNetworkResponseListener.onResponse(responseBean, mRequestBean.getRequestType());
    }

    private <T> T processResponse(String response, Class<T> typeOfObject) {

        Gson gson = new GsonBuilder().
                registerTypeAdapter(Date.class, new DateDeserializer())
                .create();
        return gson.fromJson(response, typeOfObject);
    }

    private String getSubString(String string, int limit) {
        return string.length() > limit ? string.substring(0, limit-1) : string;
    }

    public static HttpURLConnection sendGetRequest(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
            connection.setConnectTimeout(TIMEOUT_MILLIS);
            connection.setReadTimeout(TIMEOUT_MILLIS);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");
            return connection;

        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class DateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement jsonElement, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException {
                try {
                    return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(jsonElement.getAsString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            return null;
        }
    }
}
