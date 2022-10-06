data class Post(

    val authorId: Int,
    val date: String,
    var text: String?,
    val likes: Like = Like(10, userLiked = true, canRepost = true),
    val comments: Comment = Comment(1, 1, "10.09.2022 18:36", "Hello!"),
    val canPin: Boolean = true,
    val canEdit: Boolean?,
    val attachments: Array<Attachment> = emptyArray()
) {

    var id: Int? = null
}


data class Like(
    val count: Int,
    val userLiked: Boolean,
    val canRepost: Boolean
)


data class Comment(
    val id: Int,
    val authorId: Int,
    val date: String,
    val text: String
)


fun main() {

    val post1 = Post(
        1,
        "10.09.2022 18:35",
        "Hi everybody",
        canEdit = true,
        attachments = arrayOf(
            AudioAttachment(
                Audio(
                    1,
                    2,
                    3,
                    4,
                    "Dua Lipa"
                )
            ),
            VideoAttachment(
                Video(
                    1,
                    1,
                    1,
                    1
                )
            )
        )
    )


    val post2 = Post(
        1,
        "10.09.2022 18:35",
        "Hi everybody",
        canEdit = true,
        attachments = arrayOf(
            FileAttachment(
                File(
                    1,
                    102,
                    "pdf",
                    10,
                    5
                )
            ),
            PhotoAttachment(
                Photo(
                    256,
                    256,
                    6,
                    "png",
                    4,
                    3
                )
            )
        )
    )

    val post3 = Post(
        1,
        "10.09.2022 18:35",
        "Hi everybody",
        canEdit = true,
        attachments = arrayOf(
            CoordinatesAttachment(
                Coordinates(
                    "City", Coordinates.Place(
                        1,
                        "Moscow",
                        55,
                        45,
                        "Russia",
                        "Moscow"
                    )
                )
            )
        )
    )


    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)
    WallService.update(post3)
    WallService.update(post1)
    WallService.update(post2)


    for (post in WallService.posts) {
        println(post.id)
    }


    for (attachment in post1.attachments) {
        println(attachment)
    }

}