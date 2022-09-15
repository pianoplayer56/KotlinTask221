import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPostTest() {
        val post1 = WallService.add(Post(authorId = 1, date = "now", text = "text1"))
        val post2 = WallService.add(Post(authorId = 2, date = "now", text = "text2"))
        val result = 2
        assertEquals(result, post2.id)
    }

    @Test
    fun True_UpdatePostTest() {
        val post3 = WallService.add(Post(authorId = 1, date = "now", text = "text1"))
        WallService.update(post3)
        val result = true
        assertEquals(result, WallService.update(post3))
    }

    @Test
    fun False_UpdatePostTest() {
        val post4 = Post(id = 1, authorId = 1, date = "now", text = "text1")
        WallService.update(post4)
        val result = false
        assertEquals(result, WallService.update(post4))
    }


}