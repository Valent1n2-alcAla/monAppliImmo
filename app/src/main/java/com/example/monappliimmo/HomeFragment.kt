package com.example.monappliimmo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var adapter: AppartementAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewHome)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        chargerAppartements()

        return view
    }

    private fun chargerAppartements() {
        RetrofitClient.getInstance()
            .getApi()
            .getAppartements()
            .enqueue(object : Callback<List<Appartement>> {

                override fun onResponse(
                    call: Call<List<Appartement>>,
                    response: Response<List<Appartement>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { appartements ->
                            adapter = AppartementAdapter(appartements)
                            recyclerView?.adapter = adapter
                        }
                    }
                }

                override fun onFailure(call: Call<List<Appartement>>, t: Throwable) {
                    // Tu peux log ici si besoin
                }
            })
    }
}