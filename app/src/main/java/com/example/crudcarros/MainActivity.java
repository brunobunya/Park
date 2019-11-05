package com.example.crudcarros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnInserir, btnListar, btnSair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInserir = (Button) findViewById(R.id.btnInserir);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnSair = (Button) findViewById(R.id.btnSair);
        btnInserir.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        btnSair.setOnClickListener(this);
    }
//tela principal
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInserir: //botao inserir que redireciona para a tela de cadastro
                Intent insert = new Intent(getApplicationContext(), InserirActivity.class);
                startActivity(insert);
                break;
            case R.id.btnListar: //botao listar que redireciona para a tela de lista de cadastrados
                Intent list = new Intent(getApplicationContext(), ListarActivity.class);
                startActivity(list);
                break;
            case R.id.btnSair: //botao fecha a aplicação
                finishAffinity();
                break;
        }
    }
}
