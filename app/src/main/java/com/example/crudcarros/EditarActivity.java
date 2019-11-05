package com.example.crudcarros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.crudcarros.model.Carro;
import com.example.crudcarros.utils.Message;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class EditarActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db;
    private Button btnSalvar;
    private EditText txtNome, txtModelo, txtPlaca;
    Carro carro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        db = openOrCreateDatabase("db_aluno", Context.MODE_PRIVATE, null);
        Intent itCarro = getIntent();
        carro = (Carro) itCarro.getExtras().getSerializable("objCarro");
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(this);
        txtNome = (EditText) findViewById(R.id.txtEditNome);
        txtModelo = (EditText) findViewById(R.id.txtEditModelo);
        txtPlaca = (EditText) findViewById(R.id.txtEditPlaca);
        clearFields();
        txtNome.setText(carro.getNome());
        txtModelo.setText(carro.getModelo());
        txtPlaca.setText(carro.getPlaca());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSalvar:
                carro.setNome(txtNome.getText().toString());
                carro.setModelo(txtModelo.getText().toString());
                carro.setPlaca(txtPlaca.getText().toString());
                ContentValues cv = new ContentValues();
                cv.put("nome", carro.getNome());
                cv.put("modelo", carro.getModelo());
                cv.put("placa", carro.getPlaca());
                db.update("carro", cv, "id=?", new String[]{carro.getId()+""} );
                Message message = new Message(EditarActivity.this);

                message.show(
                        "Dados alterados com sucesso!",
                        carro.getDados(),
                        R.drawable.ic_add);
                Intent itDetalhes = new Intent(getApplicationContext(), DetalheActivity.class);
                itDetalhes.putExtra("objCarro", carro);
                startActivity(itDetalhes);
                break;
        }
    }
    private void clearFields(){
        txtNome.setText("");
        txtModelo.setText("");
        txtPlaca.setText("");
    }
}
