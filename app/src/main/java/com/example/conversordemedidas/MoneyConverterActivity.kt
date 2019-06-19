package com.example.conversordemedidas

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_metric_converter.*
import kotlinx.android.synthetic.main.activity_money_converter.*
import kotlinx.android.synthetic.main.activity_money_converter.btnConverter
import kotlinx.android.synthetic.main.activity_money_converter.editTxtValor
import kotlinx.android.synthetic.main.activity_money_converter.sbValor
import kotlinx.android.synthetic.main.activity_money_converter.txtViewConversao
import java.math.RoundingMode
import java.text.DecimalFormat

class MoneyConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_converter)

        //tornar o botão de voltar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rdBtnReal.setOnClickListener {
            rdBtnDolar2.isChecked = true
        }
        rdBtnDolar.setOnClickListener {
            rdBtnReal2.isChecked = true
        }
        rdBtnReal2.setOnClickListener {
            rdBtnDolar.isChecked = true
        }
        rdBtnDolar2.setOnClickListener {
            rdBtnReal.isChecked = true
        }

        sbValor.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    editTxtValor.setText(progress.toString())
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            })

        editTxtValor.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    try {
                        if (editTxtValor.text.isEmpty() || editTxtValor.text.toString().toDouble().isNaN()) {
                            /* Do nothing */
                        } else {
                            val etValueInt = editTxtValor.text.toString().toInt()

                            if (etValueInt in 0..100000) {
                                sbValor.progress = etValueInt
                            }
                        }
                    } catch (e: Exception) {
                        print("Valor invalido para seekBar.")
                    }
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                }
            })

        btnConverter.setOnClickListener {
            try{
                var rdBtn: RadioButton = findViewById(rdGroupMoeda1.checkedRadioButtonId)
                val moeda1: String = rdBtn.text as String
                rdBtn = findViewById(rdGroupMoeda2.checkedRadioButtonId)
                val moeda2: String = rdBtn.text as String
                val moeda = "$moeda1-$moeda2"
                moeda.toLowerCase()

                val valor = editTxtValor.text.toString().toDouble()
                txtViewConversao.text = converterMoeda(moeda, valor)
            } catch (e: Exception){
                Toast.makeText(this, "Insira algum valor!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun converterMoeda(moeda: String, valor: Double): String {
        val currencyMap = HashMap<String, Double>()
        currencyMap["Real-Dolar"] = 0.26
        currencyMap["Real-Euro"] = 0.23
        currencyMap["Dolar-Real"] = 3.88
        currencyMap["Dolar-Euro"] = 0.88
        currencyMap["Euro-Real"] = 4.39
        currencyMap["Euro-Dolar"] = 1.13

        val valorMoeda: Double = valor
        var valorConvertido = (0).toDouble()

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        if(valorMoeda.toString().isNotEmpty()){
            for ((k, v) in currencyMap)
            {
                if (k == moeda) {
                    valorConvertido = valorMoeda * v
                    Toast.makeText(this, df.format(valorConvertido), Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "Insira um valor válido!", Toast.LENGTH_SHORT).show()
            return "Insira um valor válido! $valorConvertido"
        }
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
