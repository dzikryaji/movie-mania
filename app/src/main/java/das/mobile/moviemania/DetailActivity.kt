package das.mobile.moviemania

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import das.mobile.moviemania.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var movie: Movie

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Movie>(EXTRA_MOVIE, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        }

        movie?.also {
            this.movie = movie
            binding.tvTitle.text = it.title
            binding.tvYear.text = "(${it.year})"
            binding.tvRating.text = it.rating
            binding.tvDescription.text = it.description
            Glide.with(this)
                .load(it.poster)
                .into(binding.ivPoster)
            binding.actionShare.setOnClickListener(this)
        }


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.action_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, movie.toString())

                startActivity(Intent.createChooser(intent, null))
            }
        }
    }
}