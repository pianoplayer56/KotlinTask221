import kotlin.random.Random

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reports = emptyArray<Report>()

    val testReport get() = reports.last()

    private var lastId: Int = 1

    fun clear() {
        lastId = 1
        posts = emptyArray()
    }

    fun add(newPost: Post): Post {
        if (!posts.any { it.id == newPost.id }) {
            newPost.id = lastId
            lastId++
            posts += newPost
        }
        return posts.last()
    }

    fun createComment(postId: Int?, comment: Comment): Comment {
        if (!posts.any { it.id == postId }) {
            throw PostNotFoundException("Пост с id $postId не найден")
        }
        for (post in posts) {
            if (post.id == postId) {
                post.comments = comment
                comments += comment
                comment.id = comments.indexOf(comment)
            }
        }
        return comments.last()
    }

    fun addReport(commentId: Int, reason: Int): Report {
        if (!comments.any { it.id == commentId }) {
            throw CommentNotFoundException("Комментарий с id $commentId не найден")
        }
        when (reason) {
            in 0..8 -> {
                val newReport = Report(
                    Random.nextInt(Int.MAX_VALUE),
                    Random.nextInt(Int.MAX_VALUE),
                    reason
                )
                reports += newReport
                return reports.last()
            }
            else -> throw ReasonNotFoundException("Ошибка: $reason отсутствует в базе")
        }

    }

    fun update(post: Post): Boolean {
        for (currentPost in posts) {
            if (post.id == currentPost.id) {
                post.text += "!"
                return true
            }
        }
        return false
    }

}

class PostNotFoundException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)
class ReasonNotFoundException(message: String) : RuntimeException(message)