package com.example.crudcarros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.crudcarros.model.Carro;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {
    SQLiteDatabase db;
    private List<Carro> carros;
    private ListView lstCarros;
    ArrayAdapter<Carro> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        db = openOrCreateDatabase("db_aluno", Context.MODE_PRIVATE, null);
        lstCarros = (ListView) findViewById(R.id.lstCarros);
        carros = new ArrayList<Carro>();
        // Carrega os registros em ordem alfabética no ArrayList para anexar ao adaptador
        carros.clear();
        Cursor c = db.rawQuery("SELECT * FROM carro ORDER BY nome ASC", null);
        while (c.moveToNext()) {
            Carro carro = new Carro (c.getString(c.getColumnIndex("nome")),
                    c.getString(c.getColumnIndex("modelo")),
                    c.getString(c.getColumnIndex("placa")));
            carro.setId(c.getLong(c.getColumnIndex("id")));
            carros.add(carro);
        }
        // Configura o adaptador
        adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                carros);


        // Anexa o adaptador à ListView
        lstCarros.setAdapter(adapter);

        lstCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carro carro = (Carro) lstCarros.getItemAtPosition(position);
                Intent itCarro = new Intent(getApplicationContext(), DetalheActivity.class);
                itCarro.putExtra("objCarro", carro);
                startActivity(itCarro);
            }
        });
    }
}
