package das.mobile.moviemania

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import das.mobile.moviemania.databinding.ItemMovieBinding

class MovieAdapter(private val movieList: ArrayList<Movie>) :
    Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private val TAG = "MovieAdapter"
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val (title, description, rating, year, poster) = movieList[position]
        holder.binding.tvTitle.text = title
        holder.binding.tvYear.text = "($year)"
        holder.binding.tvRating.text = rating
        Glide.with(holder.itemView.context)
            .load(poster)
            .into(holder.binding.ivPoster)
        Log.d(TAG, movieList[position].toString())
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(movieList[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
}