import java.awt.Color
import java.util.*

class Node(var id: Int, var x: Int, var y: Int) {
    var edges: MutableMap<Direction, Node?> = EnumMap(Direction::class.java)
    var color: Color? = null
    var visited = false

    fun getEdgeCount(): Int {
        return edges.values.filterNotNull().count()
    }

    fun getNeighbor(direction: Direction): Node? {
        return edges[direction]
    }

    fun setNeighbor(direction: Direction, node: Node?) {
        edges[direction] = node
    }

    fun isColored(): Boolean {
        return color != null
    }

    override fun toString(): String {
        return "N$id"
    }
}

