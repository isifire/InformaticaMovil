package com.uomovil.tanteo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uomovil.tanteo.databinding.ActivityMainBinding

// Data class para almacenar los puntos de los equipos
data class Team(var puntosLocal: Int = 0, var puntosVisitante: Int = 0, var d3: Int = 0)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // ViewBinding
    private var equipo = Team() // Instancia para manejar los puntos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Se usa binding en lugar de setContentView(R.layout.activity_main)

        // Inicializar los TextView con el valor inicial de los puntos
        binding.textLocalPuntos.text = equipo.puntosLocal.toString()
        binding.textVisitantePuntos.text = equipo.puntosVisitante.toString()

        // Botón para sumar 1 punto al equipo local
        binding.buttonPlus1Local.setOnClickListener {
            equipo.puntosLocal++
            binding.textLocalPuntos.text = equipo.puntosLocal.toString()
        }

        // Botón para sumar 2 puntos al equipo local
        binding.buttonPlus2Local.setOnClickListener {
            equipo.puntosLocal += 2
            binding.textLocalPuntos.text = equipo.puntosLocal.toString()
        }

        // Botón para sumar 1 punto al equipo visitante
        binding.buttonPlus1Visitante.setOnClickListener {
            equipo.puntosVisitante++
            binding.textVisitantePuntos.text = equipo.puntosVisitante.toString()
        }

        // Botón para sumar 2 puntos al equipo visitante
        binding.buttonPlus2Visitante.setOnClickListener {
            equipo.puntosVisitante += 2
            binding.textVisitantePuntos.text = equipo.puntosVisitante.toString()
        }
    }
}
