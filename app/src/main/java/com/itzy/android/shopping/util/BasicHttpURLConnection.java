package com.itzy.android.shopping.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BasicHttpURLConnection {
    private static final String SEARCH_SHOP_URL = "https://openapi.naver.com/v1/search/shop.json?query=";
    private static final String CLIENT_ID_KEY = "X-Naver-Client-Id";
    private static final String CLIENT_SECRET_KEY = "X-Naver-Client-Secret";
    private static final String CLIENT_ID_VALUE = "ZKUJcznhzdDszU9GhhIB";
    private static final String CLIENT_SECRET_VALUE = "dF3KgFfE_6";
    private static final int HTTP_CODE_OK = 200;

    public static JSONObject request(String word) throws IOException, JSONException {
        URL url = new URL(SEARCH_SHOP_URL + URLEncoder.encode(word, "UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty(CLIENT_ID_KEY, CLIENT_ID_VALUE);
        connection.setRequestProperty(CLIENT_SECRET_KEY, CLIENT_SECRET_VALUE);

        return read(connection);
    }

    private static JSONObject read(HttpURLConnection connection) throws IOException, JSONException {
        BufferedReader br;
        if (connection.getResponseCode() == HTTP_CODE_OK) {
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuffer response = new StringBuffer();
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        Log.d("JSON", response.toString());

        return new JSONObject(response.toString());
    }
}
