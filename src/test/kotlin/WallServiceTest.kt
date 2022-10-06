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
        val post4 = Post( authorId = 1, date = "now", text = "text1", canEdit = true)
        post4.id = 11
        WallService.update(post4)
        assertFalse(WallService.update(post4))
    }


}