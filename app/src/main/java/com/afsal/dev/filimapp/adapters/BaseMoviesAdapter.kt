package com.afsal.dev.filimapp.adaptersimport android.view.LayoutInflaterimport android.view.ViewGroupimport androidx.recyclerview.widget.RecyclerViewimport com.afsal.dev.filimapp.databinding.BaseRvItemBindingclass BaseMoviesAdapter : RecyclerView.Adapter<BaseMoviesAdapter.ItemHolder>() {    val list = listOf(        "Movie1",        "Movie2",        "Movie3",        "Movie4",        "Movie5",        "Movie6",        "Movie7",        "Movie8",        "Movie9"    )    inner class ItemHolder(val binding: BaseRvItemBinding) :        RecyclerView.ViewHolder(binding.root) {    }    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {        return ItemHolder(            BaseRvItemBinding.inflate(                LayoutInflater.from(parent.context),                parent,                false            )        )    }    override fun onBindViewHolder(holder: ItemHolder, position: Int) {        val item = list[position]            holder.binding.tittle.text=item    }    override fun getItemCount() = list.size}