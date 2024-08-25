package other

class LRUCache(val capacity: Int) {
    private val map = mutableMapOf<Int, Node>()
    private var head = Node()
    private var tail = Node()

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        val node = map[key] ?: return -1
        remove(node)
        insert(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        map[key]?.let {
            it.value = value
            remove(it)
            insert(it)
        } ?: run {
            Node(key, value).also {
                map[key] = it
                insert(it)
            }
        }

        if (map.size > capacity) {
            val lastNode = tail.prev!!
            remove(lastNode)
            map.remove(lastNode.key)
        }
    }

    private fun insert(node: Node) {
        val next = head.next
        node.next = next
        node.prev = head
        head.next = node
        next?.prev = node
    }

    private fun remove(node: Node) {
        val next = node.next
        val prev = node.prev
        prev?.next = next
        next?.prev = prev
        node.prev = null
        node.next = null
    }

    private class Node(
        val key: Int = 0,
        var value: Int = 0,
        var prev: Node? = null,
        var next: Node? = null
    )
}
