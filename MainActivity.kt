// ======================================================================
// MainActivity.kt — Pantalla 1 de 6: INICIO (actividad LAUNCHER)
// ======================================================================
// Muestra el título del proyecto, los integrantes del equipo inicial
// (requisito de la rúbrica) y el botón INICIAR que abre el login.
// Técnica de conexión de vistas: findViewById (SIN ViewBinding).
// Clase base: AppCompatActivity.
// ======================================================================
package com.example.controlsolariot   // <-- AJUSTA al paquete real de tu proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Conecta esta clase con su layout XML (la pantalla de inicio)
        setContentView(R.layout.activity_main)

        // ------------------------------------------------------------------
        // SECCIÓN 1: MANEJO DE INSETS (código estándar del template actual)
        // ------------------------------------------------------------------
        // Aplica como padding del contenedor raíz (R.id.main) el espacio
        // de las barras del sistema (estado y navegación) para que el
        // contenido no quede tapado en pantallas edge-to-edge.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            WindowInsetsCompat.CONSUMED
        }

        // ------------------------------------------------------------------
        // SECCIÓN 2: BOTÓN INICIAR -> ABRE EL CONTROL DE ACCESO (LOGIN)
        // ------------------------------------------------------------------
        val btnIniciar = findViewById<Button>(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            // Intent explícito: "orden" para abrir LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
