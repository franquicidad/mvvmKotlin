package com.felipefranco.mvvm.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.felipefranco.mvvm.databinding.ArtistItemListBinding
import com.felipefranco.mvvm.domain.ArtistDomain

class ArtistsListAdapter(private val clickListener: ArtistListListener) :
    ListAdapter<ArtistDomain, ArtistsListAdapter.ViewHolder>(ArtistDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),clickListener)
    }


    class ViewHolder private constructor(private val binding: ArtistItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ArtistDomain,
            clickListener: ArtistListListener
        ) {
            binding.artist = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ArtistItemListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ArtistDiffCallback : DiffUtil.ItemCallback<ArtistDomain>() {
    override fun areItemsTheSame(oldItem: ArtistDomain, newItem: ArtistDomain): Boolean {
        return oldItem.mbid == newItem.mbid
    }

    override fun areContentsTheSame(oldItem: ArtistDomain, newItem: ArtistDomain): Boolean {
        return oldItem == newItem
    }
}

class ArtistListListener(val clickListener: (mbid: String) -> Unit) {
    fun onClick(artist: ArtistDomain) = clickListener(artist.mbid)
}
