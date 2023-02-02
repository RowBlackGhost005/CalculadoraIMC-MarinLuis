package marin.luis.asignacion4_calculadora_imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var txtImc: TextView = findViewById(R.id.tvResultadoimc)
        var txtEstado: TextView = findViewById(R.id.tvResultadoRango)

        val etPeso: EditText = findViewById(R.id.etPeso)
        val etEstatura: EditText = findViewById(R.id.etEstatura)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)

        btnCalcular.setOnClickListener{

            if(!etPeso.text.isBlank() && !etEstatura.text.isBlank()){

                var estatura = etEstatura.text.toString().toFloat()
                var peso = etPeso.text.toString().toFloat()

                var imc = calcularIMC(estatura, peso)

                var categoria = defCategoria(imc)

                when(categoria){

                    "Bajo Peso" -> txtEstado.setBackgroundResource(R.color.colorGreenish)
                    "Saludable" -> txtEstado.setBackgroundResource(R.color.colorGreen)
                    "Sobrepeso" -> txtEstado.setBackgroundResource(R.color.colorYellow)
                    "Obesidad de grado 1" -> txtEstado.setBackgroundResource(R.color.colorOrange)
                    "Obesidad de grado 2" -> txtEstado.setBackgroundResource(R.color.colorRed)
                    "Obesidad de grado 3" -> txtEstado.setBackgroundResource(R.color.colorBrown)

                }

                txtEstado.text = categoria
                txtImc.text = "IMC: " + imc.toString()
            }

        }
    }

    fun calcularIMC(estatura: Float , peso: Float): Float{

        var imc: Float = peso / (estatura * estatura)

        return imc
    }

    fun defCategoria(imc: Float):String{

        when{
            imc < 18.5 -> return "Bajo Peso"
            imc >= 18.5 && imc <= 24.9 -> return "Saludable"
            imc > 24.9 && imc <= 29.9 -> return "Sobrepeso"
            imc > 29.9 && imc <= 34.9  -> return "Obseidad de grado 1"
            imc > 34.9 && imc <= 39.9 -> return "Obseidad de grado 2"
            imc >= 40 -> return "Obesidad de grado 3"

        }

        return "Error"
    }
}