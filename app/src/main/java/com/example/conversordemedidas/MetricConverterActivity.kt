package com.example.conversordemedidas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_metric_converter.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MetricConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metric_converter)

        //tornar o botão de voltar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var moeda1 = ""
        var moeda2 = ""
        val medidasSelection = arrayOf(
            "Metro",
            "Quilômetro",
            "Milha"
        )

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_dropdown_item_1line,
            medidasSelection)

        // adicionar o modelo spinner com a lista de valores para o spinner
        sMoeda1.adapter = adapter
        sMoeda2.adapter = adapter

        fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            // pegar o valor de texto do item selecionado no spinner
            val selectedItem = parent!!.getItemAtPosition(position).toString()

            // para cada posição, iremos adicionar uma explicação
            when(position){
                0 -> {
                    moeda1 = medidasSelection[0]
                }
                1 -> {
                    moeda1 = medidasSelection[1]
                }
                2 -> {
                    moeda1 = medidasSelection[2]
                }
            }
        }

        btnConverter.setOnClickListener {
            try{

                val medida1: String = rdBtn.text as String
                rdBtn = findViewById(rdGroupMoeda2.checkedRadioButtonId)
                val medida2: String = rdBtn.text as String
                val medida = "$medida1-$medida2"
                medida.toLowerCase()

                val valor = editTxtValor.text.toString().toDouble()
                txtViewConversao.text = converterMedida(medida, valor)
            } catch (e: Exception){
                Toast.makeText(this, "Insira algum valor!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun converterMedida(medida: String, valor: Double): String {
        val currencyMap = HashMap<String, Double>()
        currencyMap["Metro-Metro"] = 1.0
        currencyMap["Metro-Quilômetro"] = 0.001
        currencyMap["Metro-Milha"] = 0.000621371
        currencyMap["Quilômetro-Quilômetro"] = 1.0
        currencyMap["Quilômetro-Metro"] = 1000.0
        currencyMap["Quilômetro-Milha"] = 0.621371
        currencyMap["Milha-Milha"] = 1.0
        currencyMap["Milha-Metro"] = 1609.34
        currencyMap["Milha-Quilômetro"] = 1.60934

        val valorMedida: Double = valor
        var valorConvertido = (0).toDouble()

        if(valorMedida.toString().isNotEmpty()){
            for ((k, v) in currencyMap)
            {
                if (k == medida) {
                    valorConvertido = valorMedida * v
                    Toast.makeText(this, "$valorConvertido", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Insira um valor válido!", Toast.LENGTH_SHORT).show()
            return "Insira um valor válido! $valorConvertido"
        }
        val df = DecimalFormat("#.####")
        df.roundingMode = RoundingMode.CEILING
        return df.format(valorConvertido)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if(item?.itemId == android.R.id.home){
            // fecha a atividade
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }
}
