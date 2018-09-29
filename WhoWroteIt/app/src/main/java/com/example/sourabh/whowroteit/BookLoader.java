package com.example.sourabh.whowroteit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

public class BookLoader extends AsyncTaskLoader<String> {
    String mQueryString;
    Context mContext;
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public BookLoader(@NonNull Context context, String mQueryString) {
        super(context);
        this.mContext = context;
        this.mQueryString = mQueryString;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        try {
            return NetworkUtils.getBookInfo(mQueryString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
