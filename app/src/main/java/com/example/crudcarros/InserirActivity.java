package com.example.crudcarros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.crudcarros.model.Carro;
import com.example.crudcarros.utils.Message;

public class InserirActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    Button btnCadastrar;
    EditText txtNome, txtModelo, txtPlaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = configDatabase(db);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(this);
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtModelo = (EditText) findViewById(R.id.txtModelo);
        txtPlaca = (EditText) findViewById(R.id.txtPlaca);
    }

    private SQLiteDatabase configDatabase(SQLiteDatabase database){
        // Abertura ou criação do Banco de Dados
        database = openOrCreateDatabase("db_aluno", Context.MODE_PRIVATE, null);
        // Cria a tabela se não existir, senão carrega a tabela para uso
        database.execSQL("CREATE TABLE IF NOT EXISTS carro(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR NOT NULL, " +
                "modelo VARCHAR NOT NULL, " +
                "placa VARCHAR NOT NULL);");
        return database;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCadastrar:
                Carro c = new Carro(txtNome.getText().toString(), txtModelo.getText().toString(),
                        txtPlaca.getText().toString());
                ContentValues cv = new ContentValues();
                cv.put("nome", c.getNome());
                cv.put("modelo", c.getModelo());
                cv.put("placa", c.getPlaca());
                db.insert("carro", null, cv);
                c.setId(buscaIDDoCarroPorPlaca(c.getPlaca()));
                // Cria uma caixa de mensagem e mostra os dados incluídos
                Message message = new Message(InserirActivity.this);
                message.show(
                        "Dados incluídos com sucesso!",
                        c.getDados(),
                        R.drawable.ic_add);
                clearFields();
                break;
        }
    }

    private void clearFields(){
        txtNome.setText("");
        txtModelo.setText("");
        txtPlaca.setText("");
    }


    private Long buscaIDDoCarroPorPlaca(String placa){
        Cursor c = db.rawQuery("SELECT id FROM carro WHERE placa like '"+placa+"'", null);
        Carro carro=null;
        while (c.moveToNext()) {
            carro = new Carro ();
            carro.setId(c.getLong(c.getColumnIndex("id")));
        }
        return carro.getId();
    }
}
