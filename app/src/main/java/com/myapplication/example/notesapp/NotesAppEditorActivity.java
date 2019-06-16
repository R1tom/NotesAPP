package com.myapplication.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NotesAppEditorActivity extends AppCompatActivity {

    int noteID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_app_editor);

        EditText editText = findViewById(R.id.editText);        //editText

        Intent intent= getIntent();                             //intent
        noteID= intent.getIntExtra("noteID",-1); //getting the id of the arraylist & ListView

        if(noteID != -1){
            editText.setText(MainActivity.notes.get(noteID));          //getting the notes form the mainActivity using the noteID  set it to editText
        }else{
            MainActivity.notes.add(" ");
            noteID = MainActivity.notes.size() -1;  // id of the newly added notes // total size -1 //
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteID,String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.myapplication.example.notesapp", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
