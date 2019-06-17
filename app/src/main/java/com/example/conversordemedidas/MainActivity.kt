package com.example.conversordemedidas

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mp: MediaPlayer? = null

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

        btnLaranjo.setOnClickListener {
            if (imgLaranjo.visibility == View.VISIBLE) {
                // faz imagem desaparecer
                imgLaranjo.visibility = View.INVISIBLE
                stop()
            } else {
                // faz imagem aparecer
                imgLaranjo.visibility = View.VISIBLE
                play()

            }
        }
    }

    private fun play() {
        if (mp == null){
            mp = MediaPlayer.create(this, R.raw.harrypotter)
        }
        mp!!.start()
    }

    private fun stop(){
        if (mp != null){
            mp!!.release()
            mp = null
        }
    }

}


