package com.example.login

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_toma.*
import java.time.LocalDate
import java.util.*

class toma : AppCompatActivity() {
    // Access a Cloud Firestore instance from your Activity
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toma)
        setup()
    }
    private fun setup(){
        pad.setOnClickListener{
            guardarRegistro()
        }
    }
    private fun alerta(titulo: String, menssa: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
        builder.setMessage(menssa)
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun guardarRegistro(){
        val data = hashMapOf<String, Any>()
        data["fecha"] = idfechas.text.toString()
        data["documento"] = ididentificacion.text.toString()
        data["edad"] = idedades.text.toString()
        data["celular"] = celular.text.toString()
        data["sexo"] = idSexo.checkedRadioButtonId.toString()
        data["fiebre"] =if(idfiebre.isChecked) "Si" else "No"
        data["covid"] =if(idcovid.isChecked) "Si" else "No"
        data["dolores"] =if(iddolores.isChecked) "Si" else "No"
        data["garganta"] =if(idgarganta.isChecked) "Si" else "No"
        data["enfermedad"] =if(idenfermedad.isChecked) "Si" else "No"
        data["dolor"] =if(iddolor.isChecked) "Si" else "No"


// Add a new document with a generated ID
        db.collection("registros").document(ididentificacion.text.toString()).collection("ingresos")
            .add(data)
            .addOnSuccessListener {
                alerta("Registro Guardado", "Ok")
                finish()
            }
            .addOnFailureListener {
                alerta("Error", "No se ingreso al sistema")
            }
        
    }
}