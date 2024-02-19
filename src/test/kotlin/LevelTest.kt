import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import java.awt.Color

class LevelTest {

    private fun getTestLevel0(): Level {
        return Level(0, "X.......X", 3, 3)
    }

    private fun getTestLevel12(): Level {
        return Level(12, ".............X....X...XX.......X.......XX...X....", 7, 7)
    }

    @Test
    fun allNodesVisited() {
        val level = getTestLevel0()
        assertFalse(level.allNodesVisited())

        level.nodes.forEach { it.visited = true }
        assertTrue(level.allNodesVisited())
    }

    @Test
    fun getCoords() {
        val level = getTestLevel0()
        assertEquals(Pair(1, 0), level.getCoords(1))
        assertEquals(Pair(0, 1), level.getCoords(3))
        assertEquals(Pair(2, 1), level.getCoords(5))
        assertEquals(Pair(2, 2), level.getCoords(8))
    }

    @Test
    fun getNodeByCoords() {
        val level = getTestLevel0()
        assertNull(level.getNodeAtCoords(0, 0))
        assertEquals(0, level.getNodeAtCoords(1, 0)?.id)
        assertEquals(2, level.getNodeAtCoords(0, 1)?.id)
    }
}
