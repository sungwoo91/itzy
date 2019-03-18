package com.example.leesungwoo.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;


public class NetworkTask extends AsyncTask<String, Void, JSONObject> {

    @Override
    protected JSONObject doInBackground(String... strings) {
        Objects.requireNonNull(strings[0]);

        try {
            return BasicHttpURLConnection.request(strings[0]);
        } catch (IOException e) {
            throw new RuntimeException("error occurred when read", e);
        } catch (JSONException e) {
            throw new RuntimeException("error occurred when covert to json", e);
        }
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
    }
}