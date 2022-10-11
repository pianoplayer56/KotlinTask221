interface CrudService <E> {
    fun add(entity: E, relateObjectId: Int) : E
    fun delete(id: Int): Boolean
    fun edit(entity: E) : Boolean
    fun getAll(): List<E>
    fun getById(id: Int) : E
    fun restore(id: Int): Boolean
}