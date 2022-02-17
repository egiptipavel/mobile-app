package org.hyperskill.app.android

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import org.hyperskill.app.android.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val list = intent.getSerializableExtra(CALCULATION_LIST) as ArrayList<*>
        val adapter = ArrayAdapter(this, R.layout.row, list)
        viewBinding.calculationList.adapter = adapter
    }
}