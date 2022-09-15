object WallService {
    var posts = emptyArray<Post>()
    private var lastId: Int = 1

    fun clear() {
        lastId = 1
        posts = emptyArray()
    }

    fun add(newPost: Post): Post {
        if (!newPost.isAdded) {
            newPost.id = lastId
            lastId++
            newPost.isAdded = true
        }
        posts += newPost
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for (currentPost in posts) {
            if (post.id == currentPost.id) {
                post.text += "!"
                post.likes = post.likes.copy(count = post.likes.count + 2)
                return true
            }
        }
        return false
    }

}