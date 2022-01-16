package com.example.sallebloc.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sallebloc.R;
import com.example.sallebloc.adapter.SalleAdapter;
import com.example.sallebloc.beans.Results;
import com.example.sallebloc.beans.Salle;
import com.example.sallebloc.databinding.FragmentSlideshowBinding;
import com.example.sallebloc.instance.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private HomeViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private SalleAdapter salleAdapter;
    private List<Salle> produits;
    private TextView txt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.sallerecycle;
        txt=binding.txt;
        txt.setText("Loading");
        produits=new ArrayList<>();
        getSuperHeroes();

        return root;
    }
    private void getSuperHeroes() {
        Call<List<Results>> call = RetrofitClient.getInstance().getMyApi().getsuperHeroes();
        txt.setText("Loading");
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myheroList = response.body();
                if(myheroList.isEmpty()){
                    txt.setText("Loading");
                    ProgressDialog progress = new ProgressDialog(getContext());
                    progress.setTitle("Loading");
                    progress.setMessage("Wait while loading...");
                    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                    progress.show();
                    // To dismiss the dialog
                    progress.dismiss();
                }else{
                    txt.setText("");
                    String[] oneHeroes = new String[myheroList.size()];

                    for (int i = 0; i < myheroList.size(); i++) {
                        oneHeroes[i] = myheroList.get(i).getName();
                        produits.add(new Salle(oneHeroes[i],myheroList.get(i).getBloc()));
                    }
                    salleAdapter=new SalleAdapter(produits,recyclerView);
                    recyclerView.setAdapter(salleAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                } }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
               // Toast.makeText(getContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}