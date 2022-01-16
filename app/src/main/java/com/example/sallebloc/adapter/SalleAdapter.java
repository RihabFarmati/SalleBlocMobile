package com.example.sallebloc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sallebloc.R;
import com.example.sallebloc.beans.Salle;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SalleAdapter extends RecyclerView.Adapter<SalleAdapter.ViewHolder> {
    private List<Salle> produitList;
    private List<Salle> produits;
    private Context context;
    private RecyclerView recyclerView;

    public SalleAdapter(Context context, List<Salle> stars,RecyclerView recyclerView) {
        this.produits = stars;
        this.context = context;
        produitList = new ArrayList<>();
        produitList.addAll(stars);
        this.recyclerView=recyclerView;
    }

    public SalleAdapter(List<Salle> stars,RecyclerView recyclerView) {
        this.produits = stars;
        produitList = new ArrayList<>();
        produitList.addAll(stars);
        this.recyclerView=recyclerView;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);
                Toast.makeText(v.getContext(), ""+itemPosition, Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int position) {

        viewHolder.getSalleName().setText(produitList.get(position).getName());
        viewHolder.getBloc().setText(produitList.get(position).getBloc());
    }

    @Override
    public int getItemCount() {
        return produitList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView salleName;
        private final TextView bloc;

        public ViewHolder(View view) {
                super(view);
                bloc = (TextView) view.findViewById(R.id.bloc);
                salleName = (TextView) view.findViewById(R.id.salleName);
            }
            public TextView getSalleName () {
                return salleName;
            }
            public TextView getBloc () {
                return bloc;
            }
        }

}
