import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import java.awt.Color

class NodeTest {

    @Test
    fun testToString() {
        val node = Node(0, 0, 0)
        assertEquals("N0", node.toString())
    }

    @Test
    fun isColored() {
        val node = Node(0, 0, 0)

        assertFalse(node.isColored())
        node.color = Color.GREEN

        assertTrue(node.isColored())
    }

    @Test
    fun getDegree() {
        val node = Node(0, 0, 0)
        val n1 = Node(1, 1, 0)
        assertEquals(0, node.getEdgeCount())

        node.setNeighbor(Direction.DOWN, n1)
        assertEquals(1, node.getEdgeCount())

        node.setNeighbor(Direction.UP, n1)
        node.setNeighbor(Direction.LEFT, n1)
        node.setNeighbor(Direction.RIGHT, n1)
        assertEquals(4, node.getEdgeCount())
    }

    @Test
    fun getNeighbor_setNeighbor() {
        val node = Node(0, 0, 0)
        val n1 = Node(1, 1, 0)
        assertNull(node.getNeighbor(Direction.UP))

        node.setNeighbor(Direction.UP, n1)
        assertEquals(n1, node.getNeighbor(Direction.UP))
    }
}
