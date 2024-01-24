enum class Direction(private val dir: String) {
    UP("U"), DOWN("D"), LEFT("L"), RIGHT("R");

    companion object {
        fun fromStr(dir: Char): Direction {
            return when (dir) {
                'U' -> UP
                'D' -> DOWN
                'L' -> LEFT
                else -> RIGHT
            }
        }
    }

    fun reverse(): Direction {
        return when (this) {
            UP -> DOWN
            DOWN -> UP
            LEFT -> RIGHT
            RIGHT -> LEFT
        }
    }

    override fun toString(): String {
        return this.dir
    }
}
