package com.example.applace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.applace.db.DBHelp;
import com.example.applace.ClasseNegocio.Imovel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton bt_floatAdiconar;
    private DBHelp db;
    AdapterImovel adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Bundle user = new Bundle();
        user = getIntent().getExtras();
        String x;
        x = user.getString("User");*/                                                                                     // id do Usuario logado "importante para modicar dados de acordo com usuario"

        db = new DBHelp(this);
        bt_floatAdiconar = findViewById(R.id.bt_floatAdicionar);
        adapter = new AdapterImovel(this, gerarDadosDeImoveis());
        ListView lv = (ListView)findViewById(R.id.listv_Imoveis);                                   //Layout da listView de Imoveis---*
        lv.setAdapter(adapter);                                                                     //adpapter vai dar um jeito de adaptar esse cara
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                Intent i = new Intent(getApplicationContext(),DetalheImovel.class);
                i.putExtra("imovelName","x");
                startActivity(i);
            }
        });
        ouvirBotoes();
    }
    private void ouvirBotoes() {
        bt_floatAdiconar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ZformImovel.class);
                startActivity(i);
            }
        });
    }
    private ArrayList<Imovel> gerarDadosDeImoveis(){
        return db.getImoveisDB();
    }
    @Override
    public void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }
}