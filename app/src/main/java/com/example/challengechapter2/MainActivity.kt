package com.example.challengechapter2

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import com.example.challengechapter2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var tipPercentage = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTipOk.setOnClickListener{
            setTipPercentage(0.15)
        }
        binding.btnTipGood.setOnClickListener{
            setTipPercentage(0.18)
        }
        binding.btnTipAmazing.setOnClickListener{
            setTipPercentage(0.2)
        }

        binding.btnCalculate.setOnClickListener{
            val inputBill = binding.etBillAmount.text.toString().trim()
            var isEmptyFields = false

            if (inputBill.isEmpty()) {
                isEmptyFields = true
                binding.etBillAmount.error = "Input This Field"
            }
            if (tipPercentage == 0.0){
                Toast.makeText(applicationContext,"Select the TIP Percentage",Toast.LENGTH_LONG).show()
            }
            if(!isEmptyFields && tipPercentage != 0.0){
                val billAmount = inputBill.toDoubleOrNull() ?: 0.0
                val tipAmount = billAmount * tipPercentage
                val finalAmount = if(binding.swRoundUp.isChecked){
                    val tipAmountRound = kotlin.math.ceil(tipAmount / 1000) * 1000
                    binding.tvRoundUpLabel.visibility = View.VISIBLE
                    binding.tvRoundUpTipAmount.visibility = View.VISIBLE
                    binding.tvRoundUpTipAmount.text = tipAmountRound.toString()
                    billAmount + tipAmountRound
                } else {
                    binding.tvRoundUpLabel.visibility = View.INVISIBLE
                    binding.tvRoundUpTipAmount.visibility = View.INVISIBLE
                    billAmount + tipAmount
                }

                binding.tvFinalTipAmount.text = tipAmount.toString()
                binding.tvFinalBillAmount.text = finalAmount.toString()
            }
        }
    }

    private fun setTipPercentage(percent: Double) {
        tipPercentage = percent
    }
}
