// ======================================================================
// DescripcionActivity.kt — Pantalla 3 de 6: DESCRIPCIÓN DEL PROYECTO
// ======================================================================
// Muestra la problemática, el objetivo y los sensores/actuadores que
// utilizará el sistema IoT (requisito de la rúbrica). El botón
// SIGUIENTE abre la pantalla de información de sensores.
// Técnica de conexión de vistas: findViewById (SIN ViewBinding).
// ======================================================================
package com.example.controlsolariot   // <-- AJUSTA al paquete real de tu proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DescripcionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Conecta esta clase con su layout XML (descripción del proyecto)
        setContentView(R.layout.activity_descripcion)

        // ------------------------------------------------------------------
        // SECCIÓN 1: MANEJO DE INSETS (estándar del template actual)
        // ------------------------------------------------------------------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            WindowInsetsCompat.CONSUMED
        }

        // ------------------------------------------------------------------
        // SECCIÓN 2: BOTÓN SIGUIENTE -> ABRE LA INFO DE SENSORES
        // ------------------------------------------------------------------
        val btnSiguiente = findViewById<Button>(R.id.btnSiguiente)

        btnSiguiente.setOnClickListener {
            val intent = Intent(this, SensoresActivity::class.java)
            startActivity(intent)
        }
    }
}
