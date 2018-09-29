package com.example.sourabh.whowroteit;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static RecyclerView recyclerView;
    MyAdapter myAdapter;
    public static LinkedList<Book> bookData;
    public final static String LOG_MSG = "msg";
    EditText bookNameDialogText;
    TextView okDialogBtn, cancelDialogBtn;
    String bookName;
    TypedArray imgArray;
    AlertDialog alertDialog;

    private final static String LOG_TAG = "fragment_msg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bookData = new LinkedList<>();
        Log.d(LOG_TAG, "on Create");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_MSG, "fab clicked");
                AlertDialog.Builder myDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                View myDialogView = getLayoutInflater().inflate(R.layout.addbook_dialog, null);
                initDialogViews(myDialogView);

                myDialogBuilder.setView(myDialogView);
                 alertDialog = myDialogBuilder.create();
                alertDialog.show();
            }
        });

        initRecyclerView();


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(LOG_TAG, "onPostResume");
    }

    private void initDialogViews(View myDialogView) {
        bookNameDialogText = myDialogView.findViewById(R.id.bookname_txt);
        cancelDialogBtn = myDialogView.findViewById(R.id.cancel_btn);
        okDialogBtn = myDialogView.findViewById(R.id.ok_btn);

        cancelDialogBtn.setOnClickListener(MainActivity.this);
        okDialogBtn.setOnClickListener(MainActivity.this);
    }




    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        myAdapter = new MyAdapter(this);
        myAdapter.setBookData(bookData);
        recyclerView.setAdapter(myAdapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == cancelDialogBtn){
            Toast.makeText(MainActivity.this, "cancel click", Toast.LENGTH_LONG).show();
            Log.d(LOG_TAG, "dialog dismiss");
            alertDialog.dismiss();

        }
        else if(v == okDialogBtn){
            bookName = bookNameDialogText.getText().toString();
            Toast.makeText(MainActivity.this, "ok click", Toast.LENGTH_LONG).show();
            new FetchBook(MainActivity.this).execute(bookName);
            Log.d(LOG_TAG, "dialog dismiss");
            alertDialog.dismiss();
        }
    }

    public static void initializeList(Book book) {
        int size = bookData.size();
        bookData.add(book);
        int index = bookData.indexOf(book);
        Log.d(LOG_TAG, "initial size: " + size);
        Log.d(LOG_TAG, "index: " + index);
        Log.d(LOG_TAG, bookData.get(index).getBookTitle());
        Log.d(LOG_TAG, bookData.get(index).getBookAuthor());
        Log.d(LOG_TAG, "after adding new book: " + bookData.size());
//        Log.d(LOG_TAG, book.getBookAuthor());
//        Log.d(LOG_TAG, book.getBookTitle());

//        if(recyclerView.getAdapter() == null){
//            Toast.makeText(this, "null hai", Toast.LENGTH_LONG).show();
//        }
        recyclerView.getAdapter().notifyItemInserted(size);
        recyclerView.smoothScrollToPosition(size);
//
//        Toast.makeText(this, "size: " + bookData.size(), Toast.LENGTH_LONG).show();
    }

    private void notifyRecyclerView() {
        if(bookData != null) {
            myAdapter.notifyDataSetChanged();
        }
//        myAdapter.notifyDataSetChanged();
    }
}
