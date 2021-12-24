package com.capstoneproject.csd190.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.capstoneproject.csd190.BuildConfig
import com.capstoneproject.csd190.api.ApiRequest
import com.capstoneproject.csd190.databinding.FragmentCaseCovidBinding
import kotlinx.android.synthetic.main.fragment_case_covid.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

@DelicateCoroutinesApi
class CaseCovidFragment : Fragment() {
    private lateinit var caseCovidBinding: FragmentCaseCovidBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        caseCovidBinding = FragmentCaseCovidBinding.inflate(layoutInflater, container, false)

        return caseCovidBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCurrentData()
    }

    private fun getCurrentData() {
        ll_card.visibility = View.GONE
        pb_progress.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BuildConfig.URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCovidIndonesia().awaitResponse()

                if (response.isSuccessful) {

                    val data = response.body()!!

                    withContext(Dispatchers.Main) {
                        pb_progress.visibility = View.GONE
                        ll_card.visibility = View.VISIBLE
                        tv_positif.text = data.update.total.jumlah_positif.toString()
                        tv_sembuh.text = data.update.total.jumlah_sembuh.toString()
                        tv_meninggal.text = data.update.total.jumlah_meninggal.toString()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(activity, "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}