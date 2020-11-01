package com.cevdet.coviddemo.ui.activity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cevdet.coviddemo.R
import com.cevdet.coviddemo.data.response.TotalData
import com.cevdet.coviddemo.ui.BaseActivity
import com.cevdet.coviddemo.viewmodel.TotalVM
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.LargeValueFormatter

class HomePageActivity : BaseActivity() {
    private lateinit var chart: PieChart
    private lateinit var viewModel: TotalVM
    private var totalData: TotalData? = null

    override fun initViews() {
        chart = findViewById(R.id.pieChart)
        viewModel = ViewModelProvider(this).get(TotalVM::class.java)
        observeVM()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home_page
    }


    private fun observeVM() {
        viewModel.fetchData()
        viewModel.getTotalData().observe(this, Observer<TotalData> {
            if (it != null) {
                totalData = it
                setChart()
            }
        })
        viewModel.getTotalLoading().observe(this, Observer<Boolean> {
            if (it) showLoading()
            else hideLoading()
        })

        viewModel.getTotalErrorMessage().observe(this, Observer<String> {
            if (it != null) showErrorDialog(viewModel.getTotalErrorMessage().value!!)
        })
    }


    private fun setChart() {
        val listPie = ArrayList<PieEntry>()
        val listColors = ArrayList<Int>()
        listPie.add(
            PieEntry(
                totalData?.totalDeaths!!.replace(",", "").toInt().toFloat(),
                "TotalDeaths"
            )
        )
        listPie.add(
            PieEntry(
                totalData?.totalCases!!.replace(",", "").toInt().toFloat(),
                "TotalCases"
            )
        )
        listPie.add(
            PieEntry(
                totalData?.totalRecovered!!.replace(",", "").toInt().toFloat(),
                "TotalRecovered"
            )
        )
        listColors.add(getColor(R.color.pie_red))
        listColors.add(getColor(R.color.pie_yellow))
        listColors.add(getColor(R.color.pie_green))

        val pieDataSet = PieDataSet(listPie, "")
        pieDataSet.colors = listColors
        pieDataSet.valueFormatter = LargeValueFormatter()
        chart.data = PieData(pieDataSet)
        chart.invalidate()
        chart.isDrawHoleEnabled = true
        chart.description.isEnabled = false
        chart.setEntryLabelColor(R.color.white)
        chart.animate()
    }
}