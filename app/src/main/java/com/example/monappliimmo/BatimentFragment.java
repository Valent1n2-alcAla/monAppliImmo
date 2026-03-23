package com.example.monappliimmo;

import android.os.Bundle;
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

public class BatimentFragment extends Fragment {

    private RecyclerView rvBatiments;
    private BatimentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_batiment, container, false);

        rvBatiments = view.findViewById(R.id.rvBatiments);
        rvBatiments.setLayoutManager(new LinearLayoutManager(getContext()));

        chargerBatiments();

        return view;
    }

    private void chargerBatiments() {
        // Remplace par ton appel Retrofit habituel
        RetrofitClient.getInstance().getApi().getBatiments().enqueue(new Callback<List<Batiment>>() {
            @Override
            public void onResponse(Call<List<Batiment>> call, Response<List<Batiment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // On créera l'adapter juste après
                    adapter = new BatimentAdapter(response.body());
                    rvBatiments.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Batiment>> call, Throwable t) {
                Toast.makeText(getContext(), "Erreur batiments", Toast.LENGTH_SHORT).show();
            }
        });
    }
}