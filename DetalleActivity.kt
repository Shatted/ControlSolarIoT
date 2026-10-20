// ======================================================================
// DetalleActivity.kt — Segunda pantalla: historial de lecturas simulado
// ======================================================================
// Se abre desde el panel de monitoreo de MainActivity mediante un Intent
// explícito. Muestra lecturas simuladas en un ListView y un botón
// "Volver" que cierra la pantalla con finish().
// Técnica de conexión de vistas: findViewById (SIN ViewBinding).
// ======================================================================
package com.example.controlsolariot   // <-- AJUSTA al paquete real de tu proyecto

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Infla el layout activity_detalle.xml (sin ViewBinding)
        setContentView(R.layout.activity_detalle)

        // ------------------------------------------------------------------
        // SECCIÓN 1: MANEJO DE INSETS (mismo patrón que en MainActivity)
        // ------------------------------------------------------------------
        // Aplica el padding de las barras del sistema al contenedor raíz.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            WindowInsetsCompat.CONSUMED
        }

        // ------------------------------------------------------------------
        // SECCIÓN 2: CONEXIÓN DE VISTAS CON findViewById
        // ------------------------------------------------------------------
        val lvHistorial = findViewById<ListView>(R.id.lvHistorial) // lista de lecturas
        val btnVolver = findViewById<Button>(R.id.btnVolver)       // botón volver

        // ------------------------------------------------------------------
        // SECCIÓN 3: DATOS SIMULADOS (historial de lecturas)
        // ------------------------------------------------------------------
        // En una app real estos datos vendrían de una base de datos (Room)
        // o de la nube. Aquí usamos una lista fija para la práctica.
        val lecturas = listOf(
            "08:00 - Temperatura: 22°C - Humedad: 70%",
            "09:00 - Temperatura: 26°C - Humedad: 66%",
            "10:00 - Temperatura: 30°C - Humedad: 62%",
            "11:00 - Temperatura: 33°C - Humedad: 58%",
            "12:00 - Temperatura: 36°C - Humedad: 60%",
            "13:00 - Temperatura: 35°C - Humedad: 63%",
            "14:00 - Temperatura: 34°C - Humedad: 65%",
            "15:00 - Temperatura: 32°C - Humedad: 68%"
        )

        // ------------------------------------------------------------------
        // SECCIÓN 4: LLENAR EL LISTVIEW CON UN ArrayAdapter
        // ------------------------------------------------------------------
        // simple_list_item_1 = fila sencilla del sistema (un TextView),
        // es la forma más simple de mostrar una lista de Strings.
        val adaptador = ArrayAdapter(
            this,                                   // contexto
            android.R.layout.simple_list_item_1,    // layout de cada fila
            lecturas                                // datos a mostrar
        )
        lvHistorial.adapter = adaptador

        // ------------------------------------------------------------------
        // SECCIÓN 5: BOTÓN VOLVER — cierra esta pantalla
        // ------------------------------------------------------------------
        btnVolver.setOnClickListener {
            finish() // destruye DetalleActivity y regresa a MainActivity
        }
    }
}
