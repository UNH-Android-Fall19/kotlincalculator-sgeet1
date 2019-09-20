package edu.newhaven.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.util.Log
import net.objecthunter.exp4j.ExpressionBuilder
import kotlinx.android.synthetic.main.activity_main.*

// Reference : https://codingjuction.com/2018/10/12/how-to-create-calculator-in-android-studio-using-kotlin/
// The article uses TextViews, we have used Buttons instead.
// The article uses Kotlin extensions, we have used findViewById() instead.
// Have added additional functionality - power(^).

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val one =  findViewById(R.id.tvOne) as Button
        val two =  findViewById(R.id.tvTwo) as Button
        val three =  findViewById(R.id.tvThree) as Button
        val four =  findViewById(R.id.tvFour) as Button
        val five =  findViewById(R.id.tvFive) as Button
        val six =  findViewById(R.id.tvSix) as Button
        val seven =  findViewById(R.id.tvSeven) as Button
        val eight =  findViewById(R.id.tvEight) as Button
        val nine =  findViewById(R.id.tvNine) as Button
        val zero =  findViewById(R.id.tvZero) as Button
        val clear =  findViewById(R.id.tvClear) as Button
        val plus =  findViewById(R.id.tvPlus) as Button
        val minus =  findViewById(R.id.tvMinus) as Button
        val divide =  findViewById(R.id.tvDivide) as Button
        val mul =  findViewById(R.id.tvMul) as Button
        val square =  findViewById(R.id.tvSquare) as Button

        one.setOnClickListener{
            appendOnExpresstion("1", true)
        }
        two.setOnClickListener{
            appendOnExpresstion("2", true)
        }
        three.setOnClickListener{
            appendOnExpresstion("3", true)
        }
        four.setOnClickListener{
            appendOnExpresstion("4", true)
        }
        five.setOnClickListener{
            appendOnExpresstion("5", true)
        }
        six.setOnClickListener{
            appendOnExpresstion("6", true)
        }

        seven.setOnClickListener{
            appendOnExpresstion("7", true)
        }

        eight.setOnClickListener{
            appendOnExpresstion("8", true)
        }

        nine.setOnClickListener{
            appendOnExpresstion("9", true)
        }

        zero.setOnClickListener{
            appendOnExpresstion("0", true)
        }

        clear.setOnClickListener{
            tvExpression.text = " "
            tvResult.text = " "
        }
        plus.setOnClickListener { appendOnExpresstion("+", false) }
        minus.setOnClickListener { appendOnExpresstion("-", false) }
        mul.setOnClickListener { appendOnExpresstion("*", false) }
        divide.setOnClickListener { appendOnExpresstion("/", false) }
        tvOpen.setOnClickListener { appendOnExpresstion("(", false) }
        tvClose.setOnClickListener { appendOnExpresstion(")", false) }
        tvDot.setOnClickListener { appendOnExpresstion(".", true) }
        square.setOnClickListener { appendOnExpresstion("^", false) }


        tvEquals.setOnClickListener {
            try {

                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }


    }


    fun appendOnExpresstion(string: String, canClear: Boolean) {

        if(tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }

        if (canClear) {
            Log.i("MainActivity", "canClear-1:" +canClear)
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            Log.i("MainActivity", "canClear-2:" +canClear)
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}