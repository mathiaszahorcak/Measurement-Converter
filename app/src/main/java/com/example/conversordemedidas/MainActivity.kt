package com.example.conversordemedidas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Abre a Activity de Conversor de Moeda (Money Converter)
        btnMoneyConverter.setOnClickListener{

            // cria a intent para preparar os dados que vao abrir a atividade
            val intent = Intent(this, MoneyConverterActivity::class.java)
            startActivity(intent)
        }

        //Abre a Activity de Conversor Métrico (Metric Converter)
        btnMetricConverter.setOnClickListener {

            // cria a intent para preparar os dados que vao abrir a atividade
            val intent = Intent(this, MetricConverterActivity::class.java)
            startActivity(intent)
        }
    }
}


