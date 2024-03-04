package com.evaluation.dp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evaluation.dp.R;
import com.evaluation.dp.model.Abilities;

import java.util.ArrayList;

public class PokemonAbilityAdapter extends RecyclerView.Adapter<PokemonAbilityAdapter.ViewHolder> {

    private ArrayList<Abilities> abilities;

    public PokemonAbilityAdapter(ArrayList<Abilities> abilities) {
        this.abilities = abilities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAbilityAdapter.ViewHolder holder, int position) {
        Abilities abilities = this.abilities.get(position);
        Log.d("prueba", "onBindViewHolder: " + abilities.getAbility().getName());
        holder.tvabilityName.setText(""+abilities.getAbility().getName());
        holder.tvIsHidden.setText(abilities.getIs_hidden() ? "está oculta" : "está visible");
        holder.tvSlot.setText("Ranura " + abilities.getSlot());
    }

    @Override
    public int getItemCount() {
        return this.abilities.size();
    }

    public void setPokemonAbilities(ArrayList<Abilities> abilities) {
        this.abilities.addAll(abilities);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvabilityName, tvIsHidden, tvSlot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvabilityName = itemView.findViewById(R.id.tvabilityName);
            tvIsHidden = itemView.findViewById(R.id.tvIsHidden);
            tvSlot = itemView.findViewById(R.id.tvSlot);
        }
    }
}
