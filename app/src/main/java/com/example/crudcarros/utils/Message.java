package com.example.crudcarros.utils;

import android.app.AlertDialog;
import android.content.Context;

public class Message {
        private Context context;

        // Construtor
        public Message(Context context) {
            this.context = context;
        }

        // Mostra a mensagem
        public void show(String titulo, final String texto, int icone) {
            AlertDialog.Builder msg = new AlertDialog.Builder(context);
            msg.setIcon(icone);
            msg.setTitle(titulo);
            msg.setMessage(texto);
            msg.setPositiveButton("OK", null);

            msg.show();


    }
}
