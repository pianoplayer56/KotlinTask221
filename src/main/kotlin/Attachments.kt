sealed class Attachment(val type: String)


data class Audio(
    val album_id: Int?,
    val id: Int?,
    val owner_id: Int?,
    val duration: Int?,
    val artist: String
)

data class AudioAttachment(
    val audio: Audio

) : Attachment("audio")

data class Video(
    val album_id: Int?,
    val id: Int?,
    val owner_id: Int?,
    val user_id: Int?
)

class VideoAttachment(
    val video: Video

) : Attachment("video")

data class File(
    val id: Int?,
    val size: Int,
    val ext: String,
    val owner_id: Int?,
    val user_id: Int?
)

data class FileAttachment(
    val file: File

) : Attachment("file")

data class Photo(
    val height: Int,
    val width: Int,
    val id: Int?,
    val ext: String,
    val owner_id: Int?,
    val user_id: Int?
)

data class PhotoAttachment(
    val photo: Photo

) : Attachment("photo")

data class Coordinates(
    val type: String,
    val place: Place?,

    ) {
    data class Place(
        val id: Int,
        val title: String,
        val latitude: Int,
        val longitude: Int,
        val country: String,
        val city: String
    )
}

data class CoordinatesAttachment(
    val coordinates: Coordinates

) : Attachment("coordinates")


