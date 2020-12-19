package com.example.applace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class DetalheImovel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_imovel2);
        Toast.makeText(getApplicationContext(), "Passando descrição do item!", Toast.LENGTH_SHORT).show();
    }
}