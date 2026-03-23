package com.example.monappliimmo

import android.os.Bundle
import android.util.Log
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

class InterventionFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var adapter: InterventionAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_intervention, container, false)

        recyclerView = view.findViewById(R.id.rvToutesInterventions)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        chargerInterventions()

        return view
    }

    private fun chargerInterventions() {
        RetrofitClient.getInstance()
            .getApi()
            .getInterventions()
            .enqueue(object : Callback<List<Intervention>> {

                override fun onResponse(
                    call: Call<List<Intervention>>,
                    response: Response<List<Intervention>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { interventions ->

                            adapter = InterventionAdapter(interventions)
                            recyclerView?.adapter = adapter

                            Log.d("API_SUCCESS", "Interventions reçues : ${interventions.size}")
                        }
                    } else {
                        Log.e("API_ERROR", "Erreur : ${response.code()}")
                        Toast.makeText(context, "Erreur serveur ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Intervention>>, t: Throwable) {
                    Log.e("API_FAILURE", "Erreur réseau : ${t.message}")
                    Toast.makeText(context, "Connexion échouée au port 9005", Toast.LENGTH_SHORT).show()
                }
            })
    }
}