package com.example.cocktailrecipe.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cocktailrecipe.R
import kotlinx.android.synthetic.main.activity_a_result.*

class AResultActivity : BaseActivity() {
    companion object {
        const val REQUEST_CODE = 206
    }

    private lateinit var textA: String
    private lateinit var textAA: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_result)


        btn_sent_form_a.setOnClickListener {
            textA = edt_from_a.text.toString()
            startActivityForResult(
                BResultActivity.newIntent(this, textA), REQUEST_CODE
            )
        }

        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                    edt_from_aa.setText(intent!!.getStringExtra("text"))
                }
            }

        fun openActivityForResult() {
            textAA = edt_from_aa.text.toString()
            startForResult.launch(BResultActivity.newIntent(this, textAA))
        }

        btn_sent_form_aa.setOnClickListener {

            openActivityForResult()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            data.let {

                if (it!!.hasExtra("text")) {
                    edt_from_a.setText(it.getStringExtra("text"))

                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)


    }

}