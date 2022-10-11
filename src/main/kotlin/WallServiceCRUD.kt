import java.lang.RuntimeException
import kotlin.random.Random


object WallServiceCRUD : CrudService<ObjectOnTheWall> {
    private var reports = mutableListOf<Report>()
    private var objectsOnTheWall = mutableListOf<ObjectOnTheWall>()
    private var deletedNotes = mutableListOf<Note>()

    val testReport get() = reports.last()



    fun clear() {
        objectsOnTheWall = mutableListOf<ObjectOnTheWall>()
        deletedNotes = mutableListOf<Note>()
    }

    override fun add(entity: ObjectOnTheWall, relateObjectId: Int): ObjectOnTheWall {
        if (entity is Comment) {
            if (!objectsOnTheWall.any { it.id == relateObjectId }) {
                throw ObjectNotFoundException("Объект с id $relateObjectId не найден")
            }
        }
        objectsOnTheWall.add(entity)
        entity.id = objectsOnTheWall.indexOf(entity) + 1
        return objectsOnTheWall.last()
    }

    override fun edit(entity: ObjectOnTheWall): Boolean {
        if (!objectsOnTheWall.any {it.id == entity.id}) {
            return false
        }
        entity.text += "!"
        return true
    }


fun addReport(id: Int, reason: Int): Report {
    if (!objectsOnTheWall.any { it.id == id }) {
        throw ObjectNotFoundException("Объект с id $id не найден")
    }
    when (reason) {
        in 0..8 -> {
            val newReport = Report(
                Random.nextInt(Int.MAX_VALUE),
                Random.nextInt(Int.MAX_VALUE),
                reason
            )
            reports += newReport
            return reports.last()
        }
        else -> throw ReasonNotFoundException("Ошибка: $reason отсутствует в базе")
    }

}

override fun getAll(): List<ObjectOnTheWall> {
    return objectsOnTheWall
}

override fun getById(id: Int): ObjectOnTheWall {
    for (objects in objectsOnTheWall) {
        if (objects.id == id) {
            return objects
        }
    }
    throw ObjectNotFoundException("Объект с id $id не найден")
}

override fun restore(id: Int): Boolean {
    for (note in deletedNotes) {
        if (note.id == id) {
            objectsOnTheWall.add(deletedNotes[id - 1])
            deletedNotes.removeAt(id - 1)
            return true
        }
    }
    throw NoteNotFoundException("Заметка с id $id не найдена")
}

override fun delete(id: Int): Boolean {
    for (currentObject in objectsOnTheWall) {
        if (currentObject is Note && currentObject.id == id) {
            deletedNotes.add(objectsOnTheWall[id - 1] as Note)
            objectsOnTheWall.removeAt(id - 1)
            return true
        }
    }
    throw NoteNotFoundException("Заметка с id $id не найдена")
}
}

class ObjectNotFoundException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)
class ReasonNotFoundException(message: String) : RuntimeException(message)
class NoteNotFoundException(message: String) : RuntimeException(message)

sealed class ObjectOnTheWall {
    var id: Int = -1
    var text: String = ""
}