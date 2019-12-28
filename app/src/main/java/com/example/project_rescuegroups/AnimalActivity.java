package com.example.project_rescuegroups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.project_rescuegroups.adapter.AnimalAdapter;
import com.example.project_rescuegroups.asynctask.AnimalsAsyncTask;
import com.example.project_rescuegroups.database.AnimalDB;
import com.example.project_rescuegroups.database.AnimalFavoritesDB;
import com.example.project_rescuegroups.database.AnimalTabel;
import com.example.project_rescuegroups.model.Animal;
import com.example.project_rescuegroups.util.ChoiceParamData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class AnimalActivity extends AppCompatActivity {

    private List<Animal> animalList;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        context = this;
        Intent intent = getIntent();
        ChoiceParamData choice = intent.getParcelableExtra("choice");

        final ListView listView = findViewById(R.id.listViewAnimals);

        AnimalDB sh = new AnimalDB(this);
        Cursor cursor = sh.getAnimalsWithParams(choice);
        animalList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                String id = cursor.getString(1);
                String naam = cursor.getString(2);
                String species = cursor.getString(3);
                String breed = cursor.getString(4);
                String sex = cursor.getString(5);
                String birthdate = cursor.getString(6);
                String imageurl = cursor.getString(7);
                String desc = cursor.getString(8);
                Animal animal = new Animal(id,naam,species,breed,sex,birthdate,imageurl,desc);
                animalList.add(animal);
                cursor.moveToNext();

            }
        }

        AnimalAdapter animalAdapter = new AnimalAdapter(listView.getContext(),animalList);
        listView.setAdapter(animalAdapter);
        listView.setFocusable(false);

    }
}
