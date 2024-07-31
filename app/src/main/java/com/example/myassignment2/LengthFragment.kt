package com.example.myassignment2

// LengthFragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment

class LengthFragment : Fragment() {

    private lateinit var inputField: EditText
    private lateinit var unitSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.length_fragment, container, false)

        inputField = view.findViewById(R.id.input_field)
        unitSpinner = view.findViewById(R.id.unit_spinner)
        convertButton = view.findViewById(R.id.convert_button)
        resultTextView = view.findViewById(R.id.result_text_view)

        val units = arrayOf("Inches", "Feet", "Yards", "Miles")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, units)
        unitSpinner.adapter = adapter

        unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedUnit = units[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        convertButton.setOnClickListener {
            val input = inputField.text.toString().trim()
            if (input.isNotEmpty()) {
                val value = input.toDouble()
                val selectedUnit = units[unitSpinner.selectedItemPosition]
                val result = convertLength(value, selectedUnit)
                resultTextView.text = result
            } else {
                resultTextView.text = "Please enter a value"
            }
        }

        return view
    }

    private fun convertLength(value: Double, unit: String): String {
        when (unit) {
            "Inches" -> {
                val inches = value
                val feet = inches / 12
                val yards = feet / 3
                val miles = yards / 1760
                return "Inches: $inches, Feet: $feet, Yards: $yards, Miles: $miles"
            }
            "Feet" -> {
                val feet = value
                val inches = feet * 12
                val yards = feet / 3
                val miles = yards / 1760
                return "Inches: $inches, Feet: $feet, Yards: $yards, Miles: $miles"
            }
            "Yards" -> {
                val yards = value
                val feet = yards * 3
                val inches = feet * 12
                val miles = yards / 1760
                return "Inches: $inches, Feet: $feet, Yards: $yards, Miles: $miles"
            }
            "Miles" -> {
                val miles = value
                val yards = miles * 1760
                val feet = yards * 3
                val inches = feet * 12
                return "Inches: $inches, Feet: $feet, Yards: $yards, Miles: $miles"
            }
            else -> return "Invalid unit"
        }
    }
}