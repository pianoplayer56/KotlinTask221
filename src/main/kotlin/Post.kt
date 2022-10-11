data class Post(

    val authorId: Int,
    val date: String,
    val likes: Like = Like(10, userLiked = true, canRepost = true),
    var comments: Comment? = null,
    val canPin: Boolean = true,
    val canEdit: Boolean?,
    val attachments: Array<Attachment> = emptyArray()
) : ObjectOnTheWall()

data class Note(
    val title: String,
    val privacy: Int,
    val commentPrivacy: Int
) : ObjectOnTheWall()

data class Like(
    val count: Int,
    val userLiked: Boolean,
    val canRepost: Boolean
)


data class Comment(
    val relateObjectId: Int,
    val authorId: Int,
    val date: String
) : ObjectOnTheWall()

data class Report(
    val ownerId: Int,
    val commentId: Int,
    val reason: Int
)


fun main() {}