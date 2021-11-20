package com.example.cocktailrecipe.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import com.example.cocktailrecipe.R
import kotlinx.android.synthetic.main.activity_b_result.*

class BResultActivity : BaseActivity() {
    companion object {
        const val KEY_INFO = "textFromA"
        private lateinit var mData: String
        private lateinit var edt_from_b: EditText
        fun newIntent(context: Context, data: String): Intent {
            val intent = Intent(context, BResultActivity::class.java)
            intent.putExtra(KEY_INFO, data)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_result)

        if (intent.hasExtra(KEY_INFO)) {

            mData = intent.getStringExtra(KEY_INFO)
            edt_from_b.setText(mData)

            Toast.makeText(this, mData, Toast.LENGTH_LONG).show()

            btn_sent_form_b.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra("text", edt_from_b.text.toString())
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}