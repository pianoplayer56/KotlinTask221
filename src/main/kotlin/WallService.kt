object WallService {
    var posts = emptyArray<Post>()
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