package com.uomovil.tanteo

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.uomovil.tanteo.databinding.ActivityMainBinding

// Clase parcelable para almacenar las anotaciones de cada equipo
data class Team(
    var anotaciones1: Int = 0, // Cantidad de veces que anotaron 1 punto
    var anotaciones2: Int = 0, // Cantidad de veces que anotaron 2 puntos
    var anotaciones3: Int = 0  // Cantidad de veces que anotaron 3 puntos
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(anotaciones1)
        parcel.writeInt(anotaciones2)
        parcel.writeInt(anotaciones3)
    }

    override fun describeContents(): Int = 0

    // Calcular el total sumando (cantidad * valor de cada anotación)
    fun calcularTotal(): Int = (anotaciones1 * 1) + (anotaciones2 * 2) + (anotaciones3 * 3)

    companion object CREATOR : Parcelable.Creator<Team> {
        override fun createFromParcel(parcel: Parcel): Team = Team(parcel)
        override fun newArray(size: Int): Array<Team?> = arrayOfNulls(size)
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var equipoLocal = Team()
    private var equipoVisitante = Team()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Restaurar estado si existe
        equipoLocal = savedInstanceState?.getParcelable("equipoLocal") ?: Team()
        equipoVisitante = savedInstanceState?.getParcelable("equipoVisitante") ?: Team()

        // Actualizar la UI con los valores actuales
        actualizarMarcador()

        // Botones para sumar puntos al equipo local
        binding.buttonPlus1Local.setOnClickListener {
            equipoLocal.anotaciones1++
            actualizarMarcador()
        }
        binding.buttonPlus2Local.setOnClickListener {
            equipoLocal.anotaciones2++
            actualizarMarcador()
        }
        binding.buttonPlus3Local.setOnClickListener {
            equipoLocal.anotaciones3++
            actualizarMarcador()
        }

        // Botones para sumar puntos al equipo visitante
        binding.buttonPlus1Visitante.setOnClickListener {
            equipoVisitante.anotaciones1++
            actualizarMarcador()
        }
        binding.buttonPlus2Visitante.setOnClickListener {
            equipoVisitante.anotaciones2++
            actualizarMarcador()
        }
        binding.buttonPlus3Visitante.setOnClickListener {
            equipoVisitante.anotaciones3++
            actualizarMarcador()
        }
    }

    private fun actualizarMarcador() {
        binding.textLocalPuntos.text = equipoLocal.calcularTotal().toString()
        binding.textVisitantePuntos.text = equipoVisitante.calcularTotal().toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("equipoLocal", equipoLocal)
        outState.putParcelable("equipoVisitante", equipoVisitante)
    }
}
