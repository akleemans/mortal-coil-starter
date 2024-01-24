import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DirectionTest {

    @Test
    fun testToString() {
        assertEquals("U", Direction.UP.toString())
        assertEquals("D", Direction.DOWN.toString())
        assertEquals("L", Direction.LEFT.toString())
        assertEquals("R", Direction.RIGHT.toString())
    }

    @Test
    fun reverse() {
        assertEquals(Direction.DOWN, Direction.UP.reverse())
        assertEquals(Direction.UP, Direction.DOWN.reverse())
        assertEquals(Direction.RIGHT, Direction.LEFT.reverse())
        assertEquals(Direction.LEFT, Direction.RIGHT.reverse())
    }
}