package com.example.monappliimmo

import android.os.Bundle
import android.util.Log
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

    // On utilise lateinit car on sait qu'on va l'initialiser dans onCreateView
    private lateinit var recyclerView: RecyclerView
    private var adapter: AppartementAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Chargement du layout
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialisation de la vue
        recyclerView = view.findViewById(R.id.recyclerViewHome)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        chargerAppartements()

        return view
    }

    private fun chargerAppartements() {
        RetrofitClient.getInstance()
            .api // En Kotlin, on accède souvent aux getters comme des propriétés
            .getAppartements()
            .enqueue(object : Callback<List<Appartement>> {

                override fun onResponse(
                    call: Call<List<Appartement>>,
                    response: Response<List<Appartement>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { listAppart ->
                            // Mise à jour de l'UI
                            adapter = AppartementAdapter(listAppart)
                            recyclerView.adapter = adapter
                        }
                    }
                }

                override fun onFailure(call: Call<List<Appartement>>, t: Throwable) {
                    Log.e("HomeFragment", "Erreur API : ${t.message}")
                }
            })
    }
}