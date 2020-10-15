package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.security.Provider
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        setup()
    }
    private fun setup() {
        butAgregar.setOnClickListener {
            if (editEmail.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    editEmail.text.toString(),
                    editPassword.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            goHome(editEmail.text.toString(), ProviderType.BASIC)
                        } else {
                            alerta("error", "error al crear el usuario")
                        }
                    }

            } else {
                alerta("error", "los campos son obligatorios")
            }
        }
        btnIniciar.setOnClickListener {
            if (editEmail.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    editEmail.text.toString(),
                    editPassword.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            goHome(editEmail.text.toString(), ProviderType.BASIC)
                        } else {
                            alerta("error", "error al iniciar")
                        }
                    }

            } else {
                alerta("error", "los campos son obligatorios")
            }
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
    private  fun goHome(email: String, Provider: ProviderType) {
        val i = Intent(this, InicioActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", Provider.name)
        }
        startActivity(i)
    }

}