package com.capstoneproject.csd190.view.casecovid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.capstoneproject.csd190.BuildConfig
import com.capstoneproject.csd190.network.ApiRequest
import com.capstoneproject.csd190.databinding.FragmentCaseCovidBinding
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
        caseCovidBinding.llCard.visibility = View.GONE
        caseCovidBinding.pbProgress.visibility = View.VISIBLE

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
                        caseCovidBinding.pbProgress.visibility = View.GONE
                        caseCovidBinding.llCard.visibility = View.VISIBLE
                        caseCovidBinding.tvPositif.text =
                            data.update.total.jumlah_positif.toString()
                        caseCovidBinding.tvSembuh.text = data.update.total.jumlah_sembuh.toString()
                        caseCovidBinding.tvMeninggal.text =
                            data.update.total.jumlah_meninggal.toString()
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