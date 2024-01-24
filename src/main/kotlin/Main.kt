import java.nio.file.Files
import java.nio.file.Path

fun main() {
    val level: Level = getLevel(2)
    println("width: ${level.width}")
    val solution = level.solve()
    println("Finished with solution: $solution")
}

fun getLevel(levelNr: Int = 0): Level {
    val levelStr = Files.readAllLines(Path.of("levels.txt"))[levelNr]
    val (boardStr, width, height) = parseLevelStr(levelStr)
    return Level(levelNr, boardStr, width, height, true)
}

fun parseLevelStr(levelStr: String): Triple<String, Int, Int> {
    val width = levelStr.split("width = ")[1].split(";")[0].toInt()
    val height = levelStr.split("height = ")[1].split(";")[0].toInt()
    val boardStr = levelStr.split("boardStr = \"")[1].split("\"")[0]
    return Triple(boardStr, width, height)
}
