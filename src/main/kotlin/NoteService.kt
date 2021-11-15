object NoteService {

    var notes: MutableList<Note> = mutableListOf()
    var comments: MutableList<Comment> = mutableListOf()
    var genericList: MutableList<GenericNote> = mutableListOf()
    var nextNoteId = 0
    var nextCommentId = 0

    fun add(note: Note): Note {

        if (notes.isEmpty()) {
            notes.add(note.copy(noteId = 0))
        } else {
            notes.add(note.copy(noteId = nextNoteId))
        }

        nextNoteId++
        return notes.last()
    }

    fun createComment(comment: Comment) {
        var commentIsCreate = false
        for ((id, note) in notes.withIndex()) {
            if (note.noteId == comment.noteId && note.deleteId == false) {
                if (comments.isEmpty()) {
                    comments.add(comment.copy(commentId = 0))
                } else {
                    comments.add(comment.copy(commentId = nextCommentId))
                }
                nextCommentId++
                commentIsCreate = true
            }
        }
        if (commentIsCreate == false) {
            throw NoteNotFoundException("Заметка не найдена")
        }
    }

    fun delete(noteId: Int) {
        var noteIsDelete = false
        for ((id, note) in notes.withIndex()) {
            if (noteId == note.noteId && note.deleteId == false) {
                notes[note.noteId] = note.copy(deleteId = true)
                noteIsDelete = true
            }
        }
        if (noteIsDelete == false) {
            throw NoteNotFoundException("Заметка не найдена")
        }
    }

    fun deleteComment(commentId: Int) {
        var commentIsDelete = false
        for ((id, coment) in comments.withIndex()) {
            if (coment.noteId == commentId && coment.deleteId == false) {
                comments[coment.commentId] = coment.copy(deleteId = true)
                commentIsDelete = true
            }
        }
        if (commentIsDelete == false) {
            throw CommentNotFoundException("Комментарий не найден")
        }
    }

    fun edit(noteId: Int, newNote: Note): Note {
        var noteIsEdit = false
        for ((id, note) in notes.withIndex()) {
            if (noteId == note.noteId && note.deleteId == false) {
                notes[noteId] = newNote
                noteIsEdit = true
            }
        }
        if (noteIsEdit == false) {
            throw NoteNotFoundException("Заметка не найдена")
        }
        return newNote
    }

    fun editComment(commentId: Int, newComment: Comment): Comment {
        var commentIsEdit = false
        for ((id, comment) in comments.withIndex()) {
            if (comment.commentId == commentId && comment.deleteId == false) {
                comments[commentId] = newComment
                commentIsEdit = true
            }
        }
        if (commentIsEdit == false) {
            throw CommentNotFoundException("Комментарий не найден")
        }
        return newComment
    }

    fun get(user: User): MutableList<Note> {
        var userIsGet = false
        var userNote: MutableList<Note> = mutableListOf()
        for ((owner, note) in notes.withIndex()) {
            if (user.userId == note.ownerId && note.deleteId == false) {
                userNote.add(note)
                userIsGet = true
            }
        }
        if (userIsGet == false) {
            throw NoteNotFoundException("У пользователя нет заметок")
        }
        return userNote
    }

    //TODO не возвращать удаленные Note
    fun getById(noteId: Int): MutableList<Note> {
        var noteIsGet = false
        var getNote: MutableList<Note> = mutableListOf()
        for ((id, note) in notes.withIndex()) {
            if (noteId == note.noteId && note.deleteId == false) {
                getNote.add(note)
                noteIsGet = true
            }
        }
        if (noteIsGet == false) {
            throw NoteNotFoundException("Заметка с ID $noteId не найдена")
        }
        return getNote
    }

    fun getComment(noteId: Int) {
        var commentIsGet = false
        var getComment: MutableList<Comment> = mutableListOf()
        for ((id, note) in comments.withIndex()) {
            if (note.noteId == noteId && note.deleteId == false) {
                getComment.add(note)
                commentIsGet = true
            }
        }
        if (commentIsGet == false) {
            throw CommentNotFoundException("Комментарий не найден")
        }
    }

    fun restoreComment(commentId: Int) {
        var commentIsRestore = false
        for ((id, comment) in comments.withIndex()) {
            if (comment.commentId == commentId && comment.deleteId == true) {
                comments[commentId] = comment.copy(deleteId = false)
                commentIsRestore = true
            }
        }
        if (commentIsRestore == false) {
            throw CommentNotFoundException("Комментарий не найден")
        }
    }
}