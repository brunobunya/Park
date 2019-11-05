package com.example.crudcarros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.crudcarros.model.Carro;

import org.w3c.dom.Text;

public class DetalheActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView lblId, lblNome, lblModelo, lblPlaca;
    private Button btnEditar, btnExcluir;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        db = openOrCreateDatabase("db_aluno", Context.MODE_PRIVATE, null);
        lblId = (TextView) findViewById(R.id.lblDetailIDCarro);
        lblNome = (TextView) findViewById(R.id.lblDetailNomeCarro);
        lblModelo = (TextView) findViewById(R.id.lblDetailModeloCarro);
        lblPlaca = (TextView) findViewById(R.id.lblDetailPlacaCarro);
        Intent itCarro = getIntent();
        final Carro carro = (Carro) itCarro.getExtras().getSerializable("objCarro");
        lblId.setText(lblId.getText()+(carro.getId()+""));
        lblNome.setText(lblNome.getText()+carro.getNome());
        lblModelo.setText(lblModelo.getText()+carro.getModelo());
        lblPlaca.setText(lblPlaca.getText()+carro.getPlaca());

        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);

        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(this);
    }
//mostra os dados do carro clicado na listagem geral, e disponibilizar as opções de editar e excluir (mas nao pode ser alterado o id)
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnEditar:
                Carro carro = new Carro();
                carro.setId(Long.parseLong(lblId.getText().toString().substring(13)));
                carro.setNome(lblNome.getText().toString().substring(15));
                carro.setModelo(lblModelo.getText().toString().substring(17));
                carro.setPlaca(lblPlaca.getText().toString().substring(16));
                Intent itCarro = new Intent(getApplicationContext(), EditarActivity.class);
                itCarro.putExtra("objCarro", carro);
                startActivity(itCarro);
                break;
            case R.id.btnExcluir:
                Carro car = new Carro();
                car.setId(Long.parseLong(lblId.getText().toString().substring(13)));
                car.setNome(lblNome.getText().toString().substring(15));
                car.setModelo(lblModelo.getText().toString().substring(17));
                car.setPlaca(lblPlaca.getText().toString().substring(16));
                Intent itLista = new Intent(getApplicationContext(), ListarActivity.class);
                startActivity(itLista);
                db.delete("carro", "id=?", new String[]{car.getId()+""});
                break;
        }
    }
}
