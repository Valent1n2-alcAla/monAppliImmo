package com.example.monappliimmo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterventionFragment extends Fragment {

    private RecyclerView recyclerView;
    private InterventionAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Gonfler le layout du fragment
        View view = inflater.inflate(R.layout.fragment_intervention, container, false);

        // 2. Initialiser le RecyclerView
        recyclerView = view.findViewById(R.id.rvToutesInterventions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 3. Charger les données via ton RetrofitClient
        chargerInterventions();

        return view;
    }

    private void chargerInterventions() {
        // Utilisation de TON RetrofitClient avec getInstance() et getApi()
        RetrofitClient.getInstance().getApi().getInterventions().enqueue(new Callback<List<Intervention>>() {
            @Override
            public void onResponse(Call<List<Intervention>> call, Response<List<Intervention>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Intervention> interventions = response.body();

                    // On attache les données à l'adapter
                    adapter = new InterventionAdapter(interventions);
                    recyclerView.setAdapter(adapter);

                    Log.d("API_SUCCESS", "Interventions reçues : " + interventions.size());
                } else {
                    Log.e("API_ERROR", "Erreur : " + response.code());
                    Toast.makeText(getContext(), "Erreur serveur " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Intervention>> call, Throwable t) {
                Log.e("API_FAILURE", "Erreur réseau : " + t.getMessage());
                Toast.makeText(getContext(), "Connexion échouée au port 9005", Toast.LENGTH_SHORT).show();
            }
        });
    }
}