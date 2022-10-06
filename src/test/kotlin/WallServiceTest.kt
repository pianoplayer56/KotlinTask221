import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.random.Random

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPostTest() {
        val post1 = WallService.add(Post(authorId = 1, date = "now", text = "text1", canEdit = true))
        val post2 = WallService.add(Post(authorId = 2, date = "now", text = "text2", canEdit = true))
        val result = 2
        assertEquals(result, post2.id)
    }

    @Test
    fun true_UpdatePostTest() {
        val post3 = WallService.add(Post(authorId = 1, date = "now", text = "text1", canEdit = true))
        WallService.update(post3)
        assertTrue(WallService.update(post3))
    }


    @Test
    fun false_UpdatePostTest() {
        val post4 = Post(authorId = 1, date = "now", text = "text1", canEdit = true)
        post4.id = 11
        WallService.update(post4)
        assertFalse(WallService.update(post4))
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val post = Post(authorId = 1, date = "now", text = "text1", canEdit = true)
        WallService.createComment(post.id, Comment(1, "today", "hello"))
    }

    @Test
    fun createPostSuccessfully() {
        val post3 = WallService.add(Post(authorId = 1, date = "now", text = "text1", canEdit = true))
        val comment = Comment(1, "today", "hello")
        val result = WallService.createComment(post3.id, comment)
        assertEquals(comment, result)
    }

    @Test
    fun addReport_Correctly() {
        val post3 = WallService.add(Post(authorId = 1, date = "now", text = "text1", canEdit = true))
        val comment = Comment(1, "today", "hello")
        val result = WallService.createComment(post3.id, comment)
        val id = comment.id ?: -1
        val report = WallService.addReport(id, Random.nextInt(0, 8))

        assertEquals(report, WallService.testReport)
    }

    @Test(expected = CommentNotFoundException::class)
    fun shouldThrow_CommentNotFoundException() {
        val post3 = WallService.add(Post(authorId = 1, date = "now", text = "text1", canEdit = true))
        val comment = Comment(1, "today", "hello")
        val id = comment.id ?: -1
        val report = WallService.addReport(id, Random.nextInt(0, 8))

    }

    @Test(expected = ReasonNotFoundException::class)
    fun shouldThrow_ReasonNotFoundExceptionNotFoundException() {
        val post3 = WallService.add(Post(authorId = 1, date = "now", text = "text1", canEdit = true))
        val comment = Comment(1, "today", "hello")
        val result = WallService.createComment(post3.id, comment)
        val id = comment.id ?: -1
        val report = WallService.addReport(id, Random.nextInt(9, Int.MAX_VALUE))
    }
}