package com.example.programmercalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.programmercalculator.Selection.*
import com.example.programmercalculator.databinding.ActivityMainBinding
import java.math.BigInteger

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var decListener: TextWatcher
    private lateinit var hexListener: TextWatcher
    private lateinit var octListener: TextWatcher
    private lateinit var binListener: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWatchers()
        addListeners()
        binding.clearBtn.setOnClickListener {
            clearViews()
        }
    }

    private fun updateFields(str: String, selection: Selection) {
        removeListeners()
        val res: Long
        val bigDec = when (selection) {
            DEC -> BigInteger(str)
            HEX -> BigInteger(str.toBigInteger(HEX.base).toString())
            OCT -> BigInteger(str.toBigInteger(OCT.base).toString())
            BIN -> BigInteger(str.toBigInteger(BIN.base).toString())
        }

        val value: String
        var isBigger = false
        if (bigDec > BigInteger.valueOf(Long.MAX_VALUE)) {
            value = Long.MAX_VALUE.toString(selection.base)
            isBigger = true
        } else {
            value = str
        }

        when (selection) {
            DEC -> {
                res = value.toLong(DEC.base)
                if (isBigger) {
                    val result = res.toString(DEC.base)
                    binding.decEt.setText(result)
                    binding.decEt.setSelection(result.length)
                }
                binding.hexEt.setText(res.toString(HEX.base).uppercase())
                binding.octEt.setText(res.toString(OCT.base))
                binding.binEt.setText(res.toString(BIN.base))
            }
            HEX -> {
                res = value.toLong(HEX.base)
                if (isBigger) {
                    val result = res.toString(HEX.base).uppercase()
                    binding.hexEt.setText(result)
                    binding.hexEt.setSelection(result.length)
                }
                binding.decEt.setText(res.toString(DEC.base))
                binding.octEt.setText(res.toString(OCT.base))
                binding.binEt.setText(res.toString(BIN.base))

            }
            OCT -> {
                res = value.toLong(OCT.base)
                if (isBigger) {
                    val result = res.toString(OCT.base)
                    binding.octEt.setText(result)
                    binding.octEt.setSelection(result.length)
                }
                binding.decEt.setText(res.toString(DEC.base))
                binding.hexEt.setText(res.toString(HEX.base).uppercase())
                binding.binEt.setText(res.toString(BIN.base))
            }
            BIN -> {
                res = value.toLong(BIN.base)
                if (isBigger) {
                    val result = res.toString(BIN.base)
                    binding.binEt.setText(result)
                    binding.binEt.setSelection(result.length)
                }
                binding.decEt.setText(res.toString(DEC.base))
                binding.hexEt.setText(res.toString(HEX.base).uppercase())
                binding.octEt.setText(res.toString(OCT.base))
            }
        }
        addListeners()
    }

    private fun clearViews() {
        removeListeners()
        binding.decEt.setText("")
        binding.hexEt.setText("")
        binding.octEt.setText("")
        binding.binEt.setText("")
        addListeners()
    }

    private fun initWatchers() {
        decListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank() || s.isEmpty()) {
                    clearViews()
                } else {
                    updateFields(s.toString(), DEC)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        hexListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank() || s.isEmpty()) {
                    clearViews()
                } else {
                    updateFields(s.toString(), HEX)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        octListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank() || s.isEmpty()) {
                    clearViews()
                } else {
                    updateFields(s.toString(), OCT)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
        binListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank() || s.isEmpty()) {
                    clearViews()
                } else {
                    updateFields(s.toString(), BIN)

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
    }

    private fun addListeners() {
        binding.decEt.addTextChangedListener(decListener)
        binding.hexEt.addTextChangedListener(hexListener)
        binding.octEt.addTextChangedListener(octListener)
        binding.binEt.addTextChangedListener(binListener)
    }

    private fun removeListeners() {
        binding.decEt.removeTextChangedListener(decListener)
        binding.hexEt.removeTextChangedListener(hexListener)
        binding.octEt.removeTextChangedListener(octListener)
        binding.binEt.removeTextChangedListener(binListener)
    }
}