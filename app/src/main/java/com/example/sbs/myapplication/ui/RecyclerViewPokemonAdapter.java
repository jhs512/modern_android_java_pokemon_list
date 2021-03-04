package com.example.sbs.myapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sbs.myapplication.R;
import com.example.sbs.myapplication.databinding.ItemPokemonContentBinding;
import com.example.sbs.myapplication.dto.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TYPE_HEADER = 0;
    private final int TYPE_ITEM = 1;
    private final int TYPE_FOOTER = 2;

    private List<Pokemon> data = new ArrayList<>();
    private View.OnClickListener onClickLoadMore;

    public RecyclerViewPokemonAdapter() {
        this.data = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        else if (position == data.size() + 1)
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_pokemon_header, parent, false);
            return new HeaderViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_pokemon_footer, parent, false);
            return new FooterViewHolder(view);
        } else {
            ItemPokemonContentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_pokemon_content, parent, false);

            ContentViewHolder contentViewHolder = new ContentViewHolder(binding.getRoot());
            contentViewHolder.binding = binding;
            return contentViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
        } else {
            ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            PokemonListItemViewModel pokemonListItemViewModel = data.get(position - getHeaderCount()).getItemViewModel(R.layout.item_pokemon_content);
            contentViewHolder.binding.setPokemonListItemViewModel(pokemonListItemViewModel);
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 2;
    }

    private int getHeaderCount() {
        return 1;
    }

    public void addPokemons(List<Pokemon> pokemons) {
        for (Pokemon pokemon : pokemons) {
            data.add(pokemon);
        }

        notifyItemRangeInserted(getHeaderCount() + getDataSize(), getLoadCount());
    }

    public void setOnClickLoadMore(View.OnClickListener onClickLoadMore) {
        this.onClickLoadMore = onClickLoadMore;
    }

    public int getLoadCount() {
        return 5;
    }

    public int getDataSize() {
        return data.size();
    }

    public void setData(List<Pokemon> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {
        public ItemPokemonContentBinding binding;

        public ContentViewHolder(@NonNull View view) {
            super(view);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(@NonNull View view) {
            super(view);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(@NonNull View view) {
            super(view);
        }
    }
}
