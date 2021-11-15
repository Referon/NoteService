class CommentNotFoundException(message: String) : Exception(message)
class NoteNotFoundException(message: String) : Exception(message)

fun main() {
    NoteService.genericList.add(GenericNote(NoteService.notes, NoteService.comments))
}
