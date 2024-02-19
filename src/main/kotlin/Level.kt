import java.awt.Color
import java.util.*

class Level(
    id: Int,
    boardStr: String,
    val width: Int,
    val height: Int,
    private var debug: Boolean = false
) {
    val nodes: MutableList<Node> = ArrayList()
    private val nodeMap: MutableMap<Pair<Int, Int>, Node> = HashMap()
    private var ui: LevelUI? = null
    private var solution: Triple<String, Int, Int>? = null

    init {
        println("Initializing level: $id ($width x $height)")
        initializeNodes(boardStr)

        if (debug) {
            ui = LevelUI(this)
        }
    }

    private fun initializeNodes(boardStr: String) {
        // Create nodes
        var id = 0
        for (i in 0 until width * height) {
            if (boardStr[i] == 'X') {
                continue
            }
            val (x, y) = getCoords(i)
            val node = Node(id, x, y)
            nodes.add(node)
            nodeMap[Pair(x, y)] = node
            id += 1
        }

        // Set neighbors
        for (node in nodes) {
            node.setNeighbor(Direction.UP, nodes.find { it.x == node.x && it.y == node.y - 1 })
            node.setNeighbor(Direction.DOWN, nodes.find { it.x == node.x && it.y == node.y + 1 })
            node.setNeighbor(Direction.LEFT, nodes.find { it.x == node.x - 1 && it.y == node.y })
            node.setNeighbor(Direction.RIGHT, nodes.find { it.x == node.x + 1 && it.y == node.y })
        }
        println("Initialized ${nodes.size} Nodes")
    }

    fun getNodeAtCoords(x: Int, y: Int): Node? {
        return this.nodeMap[Pair(x, y)]
    }

    fun getCoords(i: Int): Pair<Int, Int> {
        val x = i % width
        val y = (i - x) / width
        return Pair(x, y)
    }

    private fun drawUI() {
        ui?.draw()
        println("(showing board)")
        readln()
    }

    fun allNodesVisited(): Boolean {
        return nodes.all { it.visited }
    }

    /**
     * Move through the level and visit nodes along the way
     */
    private fun move(node: Node, direction: Direction): Node {
        var nextNode: Node? = node
        lateinit var currentNode: Node
        while (nextNode != null) {
            currentNode = nextNode
            currentNode.visited = true
            nextNode = currentNode.getNeighbor(direction)
            if (nextNode != null && nextNode.visited) {
                break
            }
        }
        return currentNode
    }

    /**
     * Resets nodes along a path back to unvisited
     */
    private fun moveBack(wrongNode: Node, lastNode: Node, lastMove: Direction) {
        var node: Node = wrongNode
        while (node != lastNode) {
            node.visited = false
            val backDirection = lastMove.reverse()
            node = node.edges[backDirection]!!
        }
        node.visited = false
    }

    private fun isSolved(): Boolean {
        return solution != null
    }

    private fun debugLog(s: String, draw: Boolean = false) {
        if (debug) {
            println(s)
            if (draw) {
                drawUI()
            }
        }
    }

    private fun bruteForce() {
        for (node in nodes) {
            node.color = Color.RED
            debugLog("bruteForce(): Starting with $node", true)
            var path = ""
            val pathNodes = mutableListOf(node)
            var lastMove: Direction? = null

            while (true) {
                val currentNode = pathNodes.last()
                val candidates = currentNode.edges

                var nextDirection: Direction? = null
                for ((direction, candidate) in candidates) {
                    if (path.isNotEmpty() && direction == Direction.fromStr(path.last()).reverse()) {
                        continue
                    } else if (candidate != null && !candidate.visited && lastMove == null) {
                        nextDirection = direction
                        break
                    } else if (lastMove == direction) {
                        lastMove = null
                    }
                }

                if (nextDirection == null) {
                    if (allNodesVisited()) {
                        solution = Triple(path, node.x, node.y)
                        break
                    } else {
                        if (pathNodes.size == 1) {
                            break
                        }
                        // Rollback
                        lastMove = Direction.fromStr(path.last())
                        path = path.dropLast(1)
                        val wrongNode = pathNodes.removeLast()
                        moveBack(wrongNode, pathNodes.last(), lastMove)
                    }
                } else {
                    path += nextDirection.toString()
                    val nextNode = move(currentNode, nextDirection)
                    pathNodes.add(nextNode)
                    debugLog("Moved on grid", true)
                }
            }
            if (isSolved()) {
                break
            }
        }
    }

    fun solve(): Triple<String, Int, Int> {
        bruteForce()

        if (solution != null) {
            return solution!!
        } else {
            throw IllegalStateException("No solution found!")
        }
    }
}
