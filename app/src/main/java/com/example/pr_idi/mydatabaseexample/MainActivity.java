package com.example.pr_idi.mydatabaseexample;


import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static java.lang.System.in;

public class MainActivity extends ListActivity {
    private BookData bookData;
    Book selectedFromList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    //    ListView lv = (ListView) findViewById(android.R.id.list);

        bookData = new BookData(this);
        bookData.open();

        List<Book> values = bookData.getAllBooks();
        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        final ArrayAdapter<Book> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        /*lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                if(v.getDrawingCacheBackgroundColor() == Color.BLUE){
                    v.setBackgroundColor(Color.BLACK);
                }
                v.setBackgroundColor(Color.BLUE);
                v.get
                adapter.isEnabled(position);
                selectedFromList =(Book) (a.getItemAtPosition(position));
            }
        });*/

    }
    // Basic method to add pseudo-random list of books so that
    // you have an example of insertion and deletion

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Book> adapter = (ArrayAdapter<Book>) getListAdapter();
        ListView lv = (ListView) findViewById(android.R.id.list);
        Book book;
        switch (view.getId()) {
            case R.id.add:
                String[] newBook = new String[] { "Miguel Strogoff", "Jules Verne", "Ulysses", "James Joyce", "Don Quijote", "Miguel de Cervantes", "Metamorphosis", "Kafka" };
                int nextInt = new Random().nextInt(4);
                // save the new book to the database
                book = bookData.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1]);

                // After I get the book data, I add it to the adapter
                adapter.add(book);

                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    book = (Book) getListAdapter().getItem(0);
                    bookData.deleteBook(book);
                    adapter.remove(book);
                }
                break;
        /*    case R.id.deleteselected:
                if (getListAdapter().getCount() > 0) {
                    int c = lv.getSelectedItemPosition();
                    boolean b = adapter.isEnabled(lv.getSelectedItemPosition());
                    if(b) {
                        int a = lv.getSelectedItemPosition();
                        book = (Book) getListAdapter().getItem((int) adapter.getItemId(lv.getSelectedItemPosition()));
                        /*book = (Book) getListAdapter().getItem(getSelectedItemPosition());
                        ((ArrayAdapter<Book>) getListAdapter()).getPosition(book);*/
                       /* adapter.remove(selectedFromList);
                    }
                }
                break;*/
        }
        adapter.notifyDataSetChanged();
    }

    //Mi clase para ir a la otra Activity
    public void donaAltaLlibre(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AltaLlibre.class);
        startActivity(intent);

    }

    // Life cycle methods. Check whether it is necessary to reimplement them

    @Override
    protected void onResume() {
        bookData.open();
        super.onResume();
    }

    // Life cycle methods. Check whether it is necessary to reimplement them

    @Override
    protected void onPause() {
        bookData.close();
        super.onPause();
    }



}