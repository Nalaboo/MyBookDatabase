package com.example.pr_idi.mydatabaseexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;
import java.util.Random;

public class AltaLlibre extends AppCompatActivity {
    private BookData bookData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_llibre);

        bookData = new BookData(this);
        bookData.open();

        List<Book> values = bookData.getAllBooks();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Book> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);
        //setListAdapter(adapter);
    }

    public void onClick(View view) {
      //  @SuppressWarnings("unchecked")

        switch (view.getId()) {
            case R.id.addButton:
                Book book = new Book();
                //leer los campos y guardarlo en newBook
                EditText titol = (EditText) findViewById(R.id.editTextTitol);
                EditText autor = (EditText) findViewById(R.id.editTextAutor);
                EditText anyP = (EditText) findViewById(R.id.editTextAnyP);
                EditText editorial = (EditText) findViewById(R.id.editTextEditorial);
                EditText categoria = (EditText) findViewById(R.id.editTextCategoria);
                EditText valoracioP = (EditText) findViewById(R.id.editTextValoracioP);

                String resTitol = titol.getText().toString();
                String resAutor = autor.getText().toString();
                Integer resAnyP = Integer.valueOf(anyP.getText().toString());
                String resEditorial = editorial.getText().toString();
                String resCategoria = categoria.getText().toString();
                String resValoracioP = valoracioP.getText().toString();

                book.setTitle(resTitol);
                book.setAuthor(resAutor);
                book.setYear(resAnyP);
                book.setPublisher(resEditorial);
                book.setCategory(resCategoria);
                book.setPersonal_evaluation(resValoracioP);

                // save the new book to the database
                bookData.altaLlibre(book);
                break;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
