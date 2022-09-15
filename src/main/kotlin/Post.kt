data class Post(

    val authorId: Int,
    val date: String,
    var text: String,
    var likes: Like = Like(10, userLiked = true, canRepost = true),
    val comments: Comment = Comment(1, 1, "10.09.2022 18:36", "Hello!"),
    val canPin: Boolean = true,
    val canEdit: Boolean = true,

    /* добавил переменную для отображения статуса добавлена или нет на стену
    для избежания ситуаций с повтором размещения поста на стене */
    var isAdded: Boolean = false,
    var id: Int = 0

)


data class Like(
    var count: Int,
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

        )
    val post2 = Post(
        1,
        "10.09.2022 18:35",
        "Hi everybody",

        )

    val post3 = Post(
        1,
        "10.09.2022 18:35",
        "Hi everybody",
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


}