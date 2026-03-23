package com.example.monappliimmo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private AppartementAdapter adapter;

    // Cette méthode remplace le "setContentView" de l'activité
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // On relie le Java au fichier fragment_home.xml
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // On initialise le RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // On lance le chargement des données
        chargerAppartements();

        return view;
    }

    private void chargerAppartements() {
        // On appelle ton API (assure-toi que RetrofitClient est bien configuré)
        RetrofitClient.getInstance().getApi().getAppartements().enqueue(new Callback<List<Appartement>>() {
            @Override
            public void onResponse(Call<List<Appartement>> call, Response<List<Appartement>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new AppartementAdapter(response.body(), null);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Appartement>> call, Throwable t) {
                // Optionnel : ajouter un log d'erreur ici
            }
        });
    }
}