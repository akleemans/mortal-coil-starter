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
    fun isGraphColored() {
        val level = getTestLevel0()
        assertFalse(level.isGraphColored())

        level.nodes.forEach { it.color = Color.BLUE }
        assertTrue(level.isGraphColored())
    }

    @Test
    fun allNodesVisited() {
        val level = getTestLevel0()
        assertFalse(level.allNodesVisited())

        level.nodes.forEach { it.visited = true }
        assertTrue(level.allNodesVisited())
    }

    @Test
    fun colorCaves() {
        val level = getTestLevel0()
        assertFalse(level.isGraphColored())

        level.colorCaves()
        assertTrue(level.isGraphColored())
    }

    @Test
    fun getOverlappingNodes() {
        val level = getTestLevel0()
        level.colorCaves()
        assertEquals(0, level.getOverlappingNodes().size)
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

    @Test
    fun isBottleneck() {
        val level = getTestLevel12()
        val node = level.getNodeAtCoords(0, 2)!!
        val upNeighbor = level.getNodeAtCoords(0, 1)!!
        val downNeighbor = level.getNodeAtCoords(0, 3)!!
        assertFalse(level.isBoundary(node, upNeighbor, Direction.UP))
        assertTrue(level.isBoundary(node, downNeighbor, Direction.DOWN))
    }

    @Test
    fun getRandomColor() {
        val level = getTestLevel0()
        val color1 = level.getRandomColor()
        val color2 = level.getRandomColor()
        assertNotEquals(color1, color2)
    }
}
