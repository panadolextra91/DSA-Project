import java.awt.*;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
/**
 * Battleship
 * Author: Kudo
 *
 * StatusPanel class:
 * Defines a simple text panel to show a top and bottom line of text.
 * Some of these are already defined in the class, and it provides
 * additional methods to set the messages to custom values.
 */
public class StatusPanel extends Rectangle{
    private String playerHitCount = "PLAYER HITS: 0";
    private String compHitCount = "COMPUTER HITS: 0";
    /**
     * The font to use for drawing both of the messages.
     */
    private final Font font = new Font("Monospaced", Font.BOLD, 30);
    private final Font font2 = new Font ("Monospaced", Font.ITALIC + Font.BOLD + Font.CENTER_BASELINE, 30);
    private final Font font3 = new Font ("Monospaced", Font.ITALIC, 30);
    private Font audiowide;

    /**
     * Message to show on the top line during ship placement.
     */
    private final String placingShipLine1 = "PLACE YOUR SHIPS!";
    /**
     * Message to show on the bottom line during ship placement.
     */
    private final String placingShipLine2 = "PRESS R TO ROTATE THE SHIPS.";
    /**
     * Message to show on the top line when the game is lost.
     */
    private final String gameOverLossLine = "YOU ARE DEFEATED :(";
    /**
     * Message to show on the top line when the game is won.
     */
    private final String gameOverWinLine = "VICTORY!";
    /**
     * Message to show on the bottom line when the game is won or lost.
     */
    private final String gameOverBottomLine = "PRESS S TO RESTART AGAIN.";

    /**
     * The current message to display on the top line.
     */
    private String topLine;
    /**
     * The current message to display on the bottom line.
     */
    private String bottomLine;

    /**
     * Configures the status panel to be ready for drawing a background,
     * and initial default text.
     *
     * @param position Top left corner of the panel.
     * @param width Width of the area to draw within.
     * @param height Height of the area to draw within.
     */
    public StatusPanel(Position position, int width, int height) {
        super(position, width, height);
        reset();
    }
    public void setPlayerHitCount(int hits) {
        playerHitCount = "PLAYER HITS: " + hits; // Update the hit count
    }

    public void setCompHitCount(int hits) {
        compHitCount = "COMPUTER HITS: " + hits; // Update the hit count
    }

    /**
     * Resets the message back to the default for ship placement.
     */
    public void reset() {
        topLine = placingShipLine1;
        bottomLine = placingShipLine2;
    }

    /**
     * Sets the message to display based on whether the player has won or lost.
     *
     * @param playerWon True if the player has won, or false if the player lost.
     */
    public void showGameOver(boolean playerWon) {
        topLine = (playerWon) ? gameOverWinLine : gameOverLossLine;
        bottomLine = gameOverBottomLine;
    }

    /**
     * Sets the message to display on the top line of output to any specified String.
     *
     * @param message Message to display on the top line.
     */
    public void setTopLine(String message) {
        topLine = message;
    }

    /**
     * Sets the message to display on the bottom line of output to any specified String.
     *
     * @param message Message to display on the bottom line.
     */
    public void setBottomLine(String message) {
        bottomLine = message;
    }

    /**
     * Draws a light gray background with black text centred over two lines using
     * the top line and bottom line messages.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        try {
            audiowide = Font.createFont(Font.TRUETYPE_FONT, new File("Audiowide-Regular.ttf")).deriveFont(25f); // Adjust the font size as needed
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(audiowide);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            audiowide = new Font("Serif", Font.BOLD, 25); // Fallback font in case of error
        }
        g.setColor(Color.BLACK);
        g.fillRect(position.x, position.y, width, height);
        g.setColor(new Color(50, 205, 50));
        g.setFont(audiowide);
        int strWidth = g.getFontMetrics().stringWidth(topLine);
        g.drawString(topLine, position.x+width/2-strWidth/2, position.y+70);
        strWidth = g.getFontMetrics().stringWidth(bottomLine);
        g.drawString(bottomLine, position.x+width/2-strWidth/2, position.y+70+30+10);
        //g.setFont(font2);
        g.setColor(Color.RED);
        g.drawString(playerHitCount, position.x + 750, position.y + height - 20);
        g.drawString(compHitCount, position.x + 115, position.y + height - 20);
    }
}
