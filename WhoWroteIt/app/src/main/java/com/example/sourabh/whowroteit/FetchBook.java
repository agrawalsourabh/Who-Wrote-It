package com.example.sourabh.whowroteit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FetchBook extends AsyncTask<String, Void, String> {
    Context context;


    FetchBook(Context context){
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        int i=0;
        String title = null;
        String authors = null;

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemArray = jsonObject.getJSONArray("items");

            while(i < itemArray.length() && title == null && authors == null){
                JSONObject book = itemArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try{
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                }catch (Exception e){
                    e.printStackTrace();
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(title != null && authors != null) {
            Book book = new Book(title, "", 0);
            MainActivity ourActivity = new MainActivity();
            ourActivity.initializeList(book);
        }
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            return NetworkUtils.getBookInfo(strings[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  "";
    }
}
