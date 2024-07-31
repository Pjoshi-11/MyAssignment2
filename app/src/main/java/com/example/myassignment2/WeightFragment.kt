package com.example.myassignment2

// WeightFragment.kt
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

class WeightFragment : Fragment() {

    private lateinit var inputField: EditText
    private lateinit var unitSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.weight_fragment, container, false)

        inputField = view.findViewById(R.id.input_field)
        unitSpinner = view.findViewById(R.id.unit_spinner)
        convertButton = view.findViewById(R.id.convert_button)
        resultTextView = view.findViewById(R.id.result_text_view)

        val units = arrayOf("Pounds", "Kilograms", "Grams", "Ounces")
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
                val result = convertWeight(value, selectedUnit)
                resultTextView.text = result
            } else {
                resultTextView.text = "Please enter a value"
            }
        }

        return view
    }

    private fun convertWeight(value: Double, unit: String): String {
        when (unit) {
            "Pounds" -> {
                val pounds = value
                val kilograms = pounds / 2.20462
                val grams = kilograms * 1000
                val ounces = pounds * 16
                return "Pounds: $pounds, Kilograms: $kilograms, Grams: $grams, Ounces: $ounces"
            }
            "Kilograms" -> {
                val kilograms = value
                val pounds = kilograms * 2.20462
                val grams = kilograms * 1000
                val ounces = pounds * 16
                return "Pounds: $pounds, Kilograms: $kilograms, Grams: $grams, Ounces: $ounces"
            }
            "Grams" -> {
                val grams = value
                val kilograms = grams / 1000
                val pounds = kilograms * 2.20462
                val ounces = pounds * 16
                return "Pounds: $pounds, Kilograms: $kilograms, Grams: $grams, Ounces: $ounces"
            }
            "Ounces" -> {
                val ounces = value
                val pounds = ounces / 16
                val kilograms = pounds / 2.20462
                val grams = kilograms * 1000
                return "Pounds: $pounds, Kilograms: $kilograms, Grams: $grams, Ounces: $ounces"
            }
            else -> return "Invalid unit"
        }
    }
}