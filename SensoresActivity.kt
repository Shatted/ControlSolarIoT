// ======================================================================
// SensoresActivity.kt — Pantalla 4 de 6: INFORMACIÓN DE SENSORES
// ======================================================================
// Muestra las lecturas simuladas de los 4 sensores en una grilla 2x2
// (temperatura, humedad, distancia e iluminación). La tarjeta de
// iluminación es interactiva: al tocarla alterna CLARA/OSCURA.
// Desde aquí se abre el historial (DetalleActivity) y el panel de
// control (ControlActivity).
// Técnica de conexión de vistas: findViewById (SIN ViewBinding).
// ======================================================================
package com.example.controlsolariot   // <-- AJUSTA al paquete real de tu proyecto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView

class SensoresActivity : AppCompatActivity() {

    // Estado de la iluminación simulada (true = está oscuro)
    private var iluminacionOscura = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Conecta esta clase con su layout XML (grilla de sensores)
        setContentView(R.layout.activity_sensores)

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
        // TextViews de las 4 lecturas
        val tvTemperatura = findViewById<TextView>(R.id.tvTemperatura) // "36°C"
        val tvHumedad = findViewById<TextView>(R.id.tvHumedad)         // "60%"
        val tvDistancia = findViewById<TextView>(R.id.tvDistancia)     // "36 cm"
        val tvIluminacion = findViewById<TextView>(R.id.tvIluminacion) // "CLARA"

        // Tarjeta interactiva y botones de navegación
        val cardIluminacion = findViewById<MaterialCardView>(R.id.cardIluminacion)
        val btnHistorial = findViewById<Button>(R.id.btnHistorial)
        val btnControl = findViewById<Button>(R.id.btnControl)

        // ------------------------------------------------------------------
        // SECCIÓN 3: LECTURAS SIMULADAS DE LOS SENSORES
        // ------------------------------------------------------------------
        // En las Unidades 3-4 estos valores llegarán desde Arduino por
        // Bluetooth o WiFi. Aquí se asignan valores fijos de prueba.
        tvTemperatura.text = "36°C"
        tvHumedad.text = "60%"
        tvDistancia.text = "36 cm"
        tvIluminacion.text = "CLARA"

        // ------------------------------------------------------------------
        // SECCIÓN 4: TARJETA DE ILUMINACIÓN INTERACTIVA (demo)
        // ------------------------------------------------------------------
        // Al tocar la tarjeta se alterna el estado, simulando el cambio
        // que reportaría el sensor LDR conectado a Arduino.
        cardIluminacion.setOnClickListener {
            iluminacionOscura = !iluminacionOscura
            tvIluminacion.text = if (iluminacionOscura) "OSCURA" else "CLARA"
            Toast.makeText(this, "Iluminación: ${tvIluminacion.text}", Toast.LENGTH_SHORT).show()
        }

        // ------------------------------------------------------------------
        // SECCIÓN 5: BOTÓN HISTORIAL -> ABRE DETALLEACTIVITY
        // ------------------------------------------------------------------
        btnHistorial.setOnClickListener {
            val intent = Intent(this, DetalleActivity::class.java)
            startActivity(intent)
        }

        // ------------------------------------------------------------------
        // SECCIÓN 6: BOTÓN CONTROL -> ABRE CONTROLACTIVITY
        // ------------------------------------------------------------------
        btnControl.setOnClickListener {
            val intent = Intent(this, ControlActivity::class.java)
            startActivity(intent)
        }
    }
}
