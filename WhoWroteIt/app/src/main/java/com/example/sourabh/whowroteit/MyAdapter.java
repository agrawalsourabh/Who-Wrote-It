package com.example.sourabh.whowroteit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private LinkedList<Book> bookData;
    private LayoutInflater inflater;
    Context context;

    public MyAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.bookData  = null;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = inflater.inflate(R.layout.book_list, viewGroup, false);
        return new MyViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Book currentBook = bookData.get(position);
        myViewHolder.bindTo(currentBook);

    }

    @Override
    public int getItemCount() {
        return bookData.size();
    }

    public void setBookData(LinkedList<Book> bookData) {
        this.bookData  = bookData;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView bookView;
        public TextView authorView;
        public ImageView bookImg;
        public MyAdapter myAdapter;

        public MyViewHolder(@NonNull View itemView, MyAdapter myAdapter) {
            super(itemView);
            this.bookView = itemView.findViewById(R.id.book_name);
            this.authorView = itemView.findViewById(R.id.author_name);
            this.bookImg = itemView.findViewById(R.id.book_img);
            this.myAdapter = myAdapter;

        }

        public void bindTo(Book currentBook) {
            bookView.setText(currentBook.getBookTitle());
            authorView.setText(currentBook.getBookAuthor());
            Glide.with(context).load(currentBook.getBookImgRes()).into(bookImg);
        }
    }
}
