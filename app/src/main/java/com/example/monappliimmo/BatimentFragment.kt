package com.example.monappliimmo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BatimentFragment : Fragment() {

    private var rvBatiments: RecyclerView? = null
    private var adapter: BatimentAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_batiment, container, false)

        rvBatiments = view.findViewById(R.id.rvBatiments)
        rvBatiments?.layoutManager = LinearLayoutManager(context)

        chargerBatiments()

        return view
    }

    private fun chargerBatiments() {
        RetrofitClient.getInstance()
            .getApi()
            .getBatiments()
            .enqueue(object : Callback<List<Batiment>> {

                override fun onResponse(
                    call: Call<List<Batiment>>,
                    response: Response<List<Batiment>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { batiments ->
                            adapter = BatimentAdapter(batiments)
                            rvBatiments?.adapter = adapter
                        }
                    }
                }

                override fun onFailure(call: Call<List<Batiment>>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Erreur lors du chargement des bâtiments",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}