package com.example.sallebloc.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sallebloc.MainActivity;
import com.example.sallebloc.R;
import com.example.sallebloc.beans.Occupation;
import com.example.sallebloc.beans.Results;
import com.example.sallebloc.beans.Salle;
import com.example.sallebloc.databinding.FragmentGalleryBinding;
import com.example.sallebloc.instance.Api;
import com.example.sallebloc.instance.RetrofitClient;
import com.example.sallebloc.ui.home.HomeFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GalleryFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private DatePicker picker;
    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private Api api;
    Spinner spin2;
    private String[] users;
    private String[] oneHeroes;
    private Button btn;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
////////////////////////////////////////////////////////::
        users = new String[]{"08:30 To 10:20", "10:30 To 12:20", "13:30 To 15:20", "15:30 To 17:20"};
        Spinner spin = (Spinner) binding.crenaux;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
//////////////////////////////////////////////////////////:
        api= RetrofitClient.getInstance().getMyApi();
        Call<List<Results>> call2=api.getsuperHeroes();
        ////////////////////////////////////////////////////////
        call2.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List<Results> myheroList = response.body();
                oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getName();
                }
                spin2 = (Spinner) binding.salle;
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, oneHeroes);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin2.setAdapter(adapter2);
            }
            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                // Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
        btn=binding.button2;
        picker=(DatePicker) binding.date;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(),picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear()+"///"+spin.getSelectedItemPosition()+"///"+spin2.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
               int m=(picker.getMonth() + 1);
               String mm;
               if(m<10){
                   mm="0"+m;
               }else{
                   mm= String.valueOf(m);
               }
                Occupation occupation=new Occupation(picker.getDayOfMonth()+"/"+ mm+"/"+picker.getYear(),users[spin.getSelectedItemPosition()],oneHeroes[spin2.getSelectedItemPosition()]);
                Call<List<Results>> call3=api.createOccupation(occupation);
                call3.enqueue(new Callback<List<Results>>() {
                    @Override
                    public void onResponse(Call<List<Results>> call3, Response<List<Results>> response) {
                        List<Results> myheroList = response.body();
                        System.out.println("call the delete method");
                    }
                    @Override
                    public void onFailure(Call<List<Results>> call3, Throwable t) {
                        // Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                    }

                });
                Toast.makeText(getContext(), "you have successfully book the room : "+oneHeroes[spin2.getSelectedItemPosition()], Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getContext(), "Selected User: "+users[i] ,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}