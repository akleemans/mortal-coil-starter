import java.awt.BorderLayout
import java.awt.Color
import java.awt.EventQueue
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants
import kotlin.math.max
import kotlin.math.min

class LevelUI(level: Level) {
    private val jFrame = JFrame("Mortal Coil UI")

    init {
        println("Initializing UI")
        val dim = max(level.width, level.height)
        val scale = min(max(600 / dim, 10), 50)
        val uiPanel = UIPanel(level, scale)
        jFrame.add(uiPanel, BorderLayout.CENTER)
        jFrame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        jFrame.setSize(level.width * scale, level.height * scale + 38)
        jFrame.setLocationRelativeTo(null)
        jFrame.isResizable = false
        jFrame.isVisible = true
    }

    private fun repaint() {
        jFrame.repaint()
    }

    fun draw() {
        EventQueue.invokeLater(::repaint)
    }
}

class UIPanel(private val level: Level, private val scale: Int) : JPanel() {
    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)
        background = Color.black

        // Draw nodes
        for (i in 0 until level.width * level.height) {
            val (x, y) = level.getCoords(i)
            val node = level.getNodeAtCoords(x, y)

            if (node != null) {
                graphics.color = node.color ?: Color.WHITE
                if (node.visited && node.color != Color.RED) {
                    graphics.color = Color.GREEN
                }

                graphics.fillRect(x * scale + 1, y * scale + 1, scale - 2, scale - 2)

                graphics.color = Color.BLACK
                graphics.font = font.deriveFont(1, 10.0f)
                val x1 = (x + 0.5) * scale - 8
                val y1 = (y + 0.5) * scale + 5

                graphics.drawString(node.toString().drop(1), x1.toInt(), y1.toInt())
            }
        }
    }
}
