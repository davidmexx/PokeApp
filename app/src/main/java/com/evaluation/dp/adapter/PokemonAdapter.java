package com.evaluation.dp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.evaluation.dp.R;
import com.evaluation.dp.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> pokemonList;
    private OnItemClickListener onItemClickListener;
    private Context context;
    public PokemonAdapter(ArrayList<Pokemon> pokemonList, Context context) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    public interface OnItemClickListener {
        void onItemClick(Pokemon pokemon);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);

        holder.tvPokemonName.setText(pokemon.getName());
        Glide.with(holder.itemView.getContext())
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getNumber() +".png")
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(holder.ivPokemonImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(pokemon);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPokemonName;
        ImageView ivPokemonImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPokemonName = itemView.findViewById(R.id.tvPokemonName);
            ivPokemonImage = itemView.findViewById(R.id.ivPokemonImage);
        }
    }

    public void setPokemonList(ArrayList<Pokemon> pokemonList) {

        this.pokemonList.addAll(pokemonList);
        notifyDataSetChanged();
    }
}
