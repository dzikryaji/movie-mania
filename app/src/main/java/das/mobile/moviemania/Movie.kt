package das.mobile.moviemania

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val description: String,
    val rating: String,
    val year: String,
    val poster: String
): Parcelable
