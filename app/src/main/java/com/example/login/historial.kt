package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_historial.*

class historial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        h1.setText("Historial")
        verhistorial()
    }
    private  val db = FirebaseFirestore.getInstance()

    private fun verhistorial(){
        val bundle = intent.extras
        val correo = bundle?.getString("email")
        db.collection("registros").document(correo.toString()).collection("ingresos").get().addOnSuccessListener{documentos ->
            for (document in documentos) {
                val textLista = TextView(this)
                textLista.textSize = 20f
                textLista.text = document.data.getValue("fecha").toString()
               /// textLista.setOnClickListener { ... }
                // añade el TextView al LinearLayout
                resumen.addView(textLista)
                //Log.d(TAG, "${document.id} => ${document.data}")
            }
        }
    }
}