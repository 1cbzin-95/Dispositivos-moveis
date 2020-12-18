package com.example.applace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity implements Runnable{ //1 implementar a classe runnable,
    private static long TEMP_SPLASH = 3000;//tempo em milissegundos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler h = new Handler();                                                                  //criar o handle e fazer o delay
        h.postDelayed(this,TEMP_SPLASH);                                                         //contexto this mesmo ,pq a classe ja e runnable
    }
    @Override
    public void run() {//2 como implementa entao tem que fazer metodo run e criar a intenção
        Intent destinoAutenticacao = new Intent(Splash.this,Autenticacao.class);
        startActivity(destinoAutenticacao);
        finish();
    }
}