// ======================================================================
// LoginActivity.kt — Pantalla 2 de 6: CONTROL DE ACCESO LOCAL
// ======================================================================
// Valida usuario y contraseña contra credenciales locales (simulación
// para la Unidad 2). En las Unidades 3-4 se reemplazará por un sistema
// real (SQLite local o Firebase Authentication en la nube).
// Técnica de conexión de vistas: findViewById (SIN ViewBinding).
// ======================================================================
package com.example.controlsolariot   // <-- AJUSTA al paquete real de tu proyecto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    // ------------------------------------------------------------------
    // CREDENCIALES VÁLIDAS (control de acceso local simulado).
    // Se guardan en un companion object: constantes compartidas de la
    // clase. En producción irían cifradas en una base de datos.
    // ------------------------------------------------------------------
    companion object {
        const val USUARIO_VALIDO = "admin"
        const val CLAVE_VALIDA = "1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Conecta esta clase con su layout XML (formulario de acceso)
        setContentView(R.layout.activity_login)

        // ------------------------------------------------------------------
        // SECCIÓN 1: MANEJO DE INSETS (estándar del template actual)
        // ------------------------------------------------------------------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            WindowInsetsCompat.CONSUMED
        }

        // ------------------------------------------------------------------
        // SECCIÓN 2: CONEXIÓN DE VISTAS CON findViewById
        // ------------------------------------------------------------------
        val etUsuario = findViewById<EditText>(R.id.etUsuario)       // usuario (legible)
        val etPassword = findViewById<EditText>(R.id.etPassword)     // contraseña (enmascarada)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)     // botón INGRESAR
        val tvErrorLogin = findViewById<TextView>(R.id.tvErrorLogin) // mensaje de error

        // ------------------------------------------------------------------
        // SECCIÓN 3: VALIDACIÓN DEL ACCESO AL PRESIONAR "INGRESAR"
        // ------------------------------------------------------------------
        btnIngresar.setOnClickListener {
            // Lee lo escrito por el usuario (trim quita espacios sobrantes)
            val usuario = etUsuario.text.toString().trim()
            val clave = etPassword.text.toString()

            // 1) Se rechazan los campos vacíos
            if (usuario.isEmpty() || clave.isEmpty()) {
                tvErrorLogin.text = "Complete usuario y contraseña"
                tvErrorLogin.visibility = View.VISIBLE
                return@setOnClickListener // corta el listener aquí
            }

            // 2) Se comparan las credenciales con las válidas
            if (usuario == USUARIO_VALIDO && clave == CLAVE_VALIDA) {
                // Acceso correcto: se oculta el error y se avanza
                tvErrorLogin.visibility = View.GONE
                Toast.makeText(this, "Bienvenido, $usuario", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, DescripcionActivity::class.java)
                startActivity(intent)
                finish() // cierra el login: con "atrás" no se puede volver a él
            } else {
                // Acceso incorrecto: se muestra el error y se limpia la clave
                tvErrorLogin.text = "Usuario o contraseña incorrectos"
                tvErrorLogin.visibility = View.VISIBLE
                etPassword.text.clear()
            }
        }
    }
}
