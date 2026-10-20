// ======================================================================
// ControlActivity.kt — Pantalla 5 de 6: PANEL DE CONTROL REMOTO
// ======================================================================
// Contiene las interacciones de control a distancia sobre el arco
// solar (requisito de la rúbrica): encendido/apagado (ON/OFF),
// movimiento (SUBIR/BAJAR con Toast) y alarma (AlertDialog con
// confirmación Aceptar/Cancelar). En U3-U4 estos comandos se enviarán
// a Arduino por Bluetooth o WiFi.
// Técnica de conexión de vistas: findViewById (SIN ViewBinding).
// ======================================================================
package com.example.controlsolariot   // <-- AJUSTA al paquete real de tu proyecto

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ControlActivity : AppCompatActivity() {

    // ------------------------------------------------------------------
    // ESTADO DEL SISTEMA
    // Guarda si el arco solar está encendido (true) o apagado (false).
    // ------------------------------------------------------------------
    private var sistemaEncendido = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Conecta esta clase con su layout XML (panel de control)
        setContentView(R.layout.activity_control)

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
        val btnToggle = findViewById<Button>(R.id.btnToggle) // ON / OFF
        val btnSubir = findViewById<Button>(R.id.btnSubir)   // mover arriba
        val btnBajar = findViewById<Button>(R.id.btnBajar)   // mover abajo
        val btnAlarma = findViewById<Button>(R.id.btnAlarma) // alarma

        // ------------------------------------------------------------------
        // SECCIÓN 3: BOTÓN TOGGLE ON/OFF (encender / apagar el sistema)
        // ------------------------------------------------------------------
        btnToggle.setOnClickListener {
            // Invierte el estado actual (false -> true o true -> false)
            sistemaEncendido = !sistemaEncendido

            if (sistemaEncendido) {
                // Estado ENCENDIDO: texto "ON" y fondo verde
                btnToggle.text = "ON"
                // backgroundTintList cambia el color SIN perder la forma
                // redondeada del botón (setBackgroundColor la destruiría)
                btnToggle.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#2E7D32"))
                Toast.makeText(this, "Sistema ENCENDIDO", Toast.LENGTH_SHORT).show()
            } else {
                // Estado APAGADO: texto "OFF" y fondo gris
                btnToggle.text = "OFF"
                btnToggle.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#757575"))
                Toast.makeText(this, "Sistema APAGADO", Toast.LENGTH_SHORT).show()
            }
        }

        // ------------------------------------------------------------------
        // SECCIÓN 4: BOTONES SUBIR / BAJAR (movimiento del panel solar)
        // ------------------------------------------------------------------
        btnSubir.setOnClickListener {
            // Toast corto que indica el movimiento realizado
            Toast.makeText(this, "Moviendo el arco solar hacia ARRIBA", Toast.LENGTH_SHORT).show()
        }

        btnBajar.setOnClickListener {
            Toast.makeText(this, "Moviendo el arco solar hacia ABAJO", Toast.LENGTH_SHORT).show()
        }

        // ------------------------------------------------------------------
        // SECCIÓN 5: BOTÓN ACTIVAR ALARMA (AlertDialog de confirmación)
        // ------------------------------------------------------------------
        btnAlarma.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Alarma")                          // título del diálogo
                .setMessage("¿Confirmas activar la alarma?") // mensaje del diálogo
                .setPositiveButton("Aceptar") { dialog, _ ->
                    // Aquí iría la lógica real (enviar el comando al buzzer
                    // de Arduino por Bluetooth/WiFi). Por ahora confirmamos:
                    Toast.makeText(this, "Alarma ACTIVADA", Toast.LENGTH_LONG).show()
                    dialog.dismiss() // cierra el diálogo
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss() // cierra el diálogo sin activar nada
                }
                .setCancelable(true) // permite cerrarlo tocando fuera
                .show()              // ¡importante! muestra el diálogo
        }
    }
}
