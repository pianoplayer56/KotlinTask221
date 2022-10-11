import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallServiceCRUD.clear()
    }

    @Test
    fun addPostTest() {
        val post1 = WallServiceCRUD.add(Post(authorId = 1, date = "now", canEdit = true), 0)
        val post2 = WallServiceCRUD.add(Post(authorId = 2, date = "now", canEdit = true), 0)
        val result = 2
        assertEquals(result, post2.id)
    }

    @Test
    fun true_UpdatePostTest() {
        val post3 = WallServiceCRUD.add(Post(authorId = 1, date = "now", canEdit = true), 0)
        WallServiceCRUD.edit(post3)
        assertTrue(WallServiceCRUD.edit(post3))
    }


    @Test
    fun false_UpdatePostTest() {
        val post4 = Post(authorId = 1, date = "now", canEdit = true)
        post4.id = 11
        WallServiceCRUD.edit(post4)
        assertFalse(WallServiceCRUD.edit(post4))
    }

    @Test(expected = ObjectNotFoundException::class)
    fun shouldThrowObject() {
        val comment1 = Comment(20, 1, "yesterday")
        WallServiceCRUD.add(comment1, comment1.relateObjectId)
    }

    @Test
    fun createCommentSuccessfully() {
        val post3 = WallServiceCRUD.add(Post(authorId = 1, date = "now", canEdit = true), 0)
        val comment2 = Comment(post3.id, 1, "today")
        val result = WallServiceCRUD.add(comment2, comment2.relateObjectId)
        assertEquals(comment2, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowNoteNotFound_InDelete() {
        val note1 = Note("Hometask", 1, 1)
        WallServiceCRUD.delete(1)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowNoteNotFound_InRestore() {
        val note1 = Note("Homework", 1, 1)
        val note2 = Note("Hometask", 1, 1)
        WallServiceCRUD.add(note2, 1)
        WallServiceCRUD.delete(note2.id - 1)
        WallServiceCRUD.restore(note2.id)
    }

    @Test
    fun RestoreNoteSuccessfully() {
        val note2 = Note("Hometask", 1, 1)
        WallServiceCRUD.add(note2, 1)
        WallServiceCRUD.delete(note2.id)
        assertTrue(WallServiceCRUD.restore(note2.id))

    }

    @Test
    fun DeleteNoteSuccessfully() {
        val note2 = Note("Hometask", 1, 1)
        WallServiceCRUD.add(note2, 1)
        assertTrue(WallServiceCRUD.delete(note2.id))

    }
}