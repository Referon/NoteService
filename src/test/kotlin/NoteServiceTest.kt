import org.junit.Assert.*
import org.junit.Test

class NoteServiceTest {

    @Test
    fun add() {
        val note1 = NoteService.add(Note(0,3,"title","text",false))
        val note2 = NoteService.add(Note(0,3,"title","text",false))
        val result = NoteService.add(
            note = note2
        )
        assertTrue(NoteService.notes.isNotEmpty())
        assertTrue(note2.noteId != 0)
    }

    @Test
    fun createComment() {
        val note1 = NoteService.add(Note(0,3,"title","text",false))
        val comment1 = Comment(1,0,1,1,"message",false)
        val comment2 = Comment(1,0,1,1,"message",false)
        val result = NoteService.createComment(
            comment = comment1
        )
        assertTrue(NoteService.comments.isNotEmpty())
        assertTrue(comment2.commentId != 0)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentIsTrouble() {
        val note2 = NoteService.add(Note(0,3,"title","text",false))
        val comment1 = Comment(1,90,6,1,"message",false)
        val result = NoteService.createComment(
            comment = comment1
        )
    }

    @Test
    fun delete() {
        val note1 = NoteService.add(Note(1,0,"title", "text", false))
        val note2 = NoteService.add(Note(1,4,"TITLE", "TEXT", false))
        val result = NoteService.delete(
            noteId = 1
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteIsTrouble() {
        val note1 = NoteService.add(Note(1,0,"title", "text", false))
        val note2 = NoteService.add(Note(1,4,"TITLE", "TEXT", false))
        val result = NoteService.delete(
            noteId = 10
        )
    }

    @Test
    fun deleteComment() {
        val note1 = NoteService.add(Note(1,3,"title","text",false))
        val comment1 = Comment(9,9,3,1,"message",false)
        val comment = NoteService.createComment(comment1)
        val result = NoteService.deleteComment(
            commentId = 9
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentIsTrouble() {
        val note1 = NoteService.add(Note(0,3,"title","text",false))
        val comment = NoteService.createComment(Comment(1,0,3,1,"message",false))
        val result = NoteService.deleteComment(
            commentId = 15
        )
    }

    @Test
    fun edit() {
        val note1 = NoteService.add(Note(0,3,"1111","1111",false))
        val note2 = Note(20,1,"0000","0000",false)
        val result = NoteService.edit(
            noteId = 0,
            newNote = note2
        )
        assertEquals(note2,result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editIsTrouble() {
        val note1 = NoteService.add(Note(0,3,"1111","1111",false))
        val note2 = Note(25,1,"0000","0000",false)
        val result = NoteService.edit(
            noteId = 20,
            newNote = note2
        )
        assertEquals(note2,result)
    }

    @Test
    fun editComment() {
        val note1 = NoteService.add(Note(0,3,"1111","1111",false))
        val comment = NoteService.createComment(Comment(0,0,0,0,"0",false))
        val comment2 = Comment(6,6,6,6,"6",false)
        val result = NoteService.editComment(
            commentId = 0,
            newComment = comment2
        )
        assertEquals(comment2,result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentIsTrouble() {
        val note1 = NoteService.add(Note(0,3,"1111","1111",false))
        val comment = NoteService.createComment(Comment(0,14,0,0,"0",false))
        val comment2 = Comment(1,14,1,1,"1",false)
        val result = NoteService.editComment(
            commentId = 0,
            newComment = comment2
        )
    }

    @Test
    fun get(){
        val note1 = NoteService.add(Note(0,1,"1111","1111",false))
        val user1 = User(1)
        val result = NoteService.get(
            user = user1
        )
//        assertEquals(note1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getIsTrouble(){
        val note1 = NoteService.add(Note(10,1,"1111","1111",false))
        val user1 = User(15)
        val result = NoteService.get(
            user = user1
        )
//        assertEquals(note1, result)
    }

    @Test
    fun getById() {
        val note1 = NoteService.add(Note(0,1,"1111","1111",false))
        val result = NoteService.getById(
            noteId = 0
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun getByIdIsTrouble() {
        val note1 = NoteService.add(Note(0,1,"1111","1111",false))
        val result = NoteService.getById(
            noteId = 10
        )
    }

    @Test
    fun getComment() {
        val note1 = NoteService.add(Note(0,1,"1111","1111",false))
        val comment = NoteService.createComment(Comment(0,14,0,0,"0",false))
        val result = NoteService.getComment(
            noteId = 0
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun getCommentIsTrouble() {
        val note1 = NoteService.add(Note(0,1,"1111","1111",false))
        val comment = NoteService.createComment(Comment(0,14,0,0,"0",false))
        val result = NoteService.getComment(
            noteId = 11
        )
    }

    @Test
    fun restoreComment() {
        val note1 = NoteService.add(Note(0,1,"1111","1111",false))
        val comment = NoteService.createComment(Comment(4,4,4,4,"4",true))
        val result = NoteService.restoreComment(
            commentId = 4
        )
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentIsTrouble() {
        val note1 = NoteService.add(Note(0,1,"1111","1111",false))
        val comment = NoteService.createComment(Comment(3,3,3,3,"3",true))
        val result = NoteService.restoreComment(
            commentId = 1
        )
    }
}