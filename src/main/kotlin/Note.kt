data class Note(
    val noteId: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val deleteId: Boolean
) {
}