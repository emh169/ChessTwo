import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess {
    // Size const for board length and width
    private static final int BOARD_SIZE = 8;

    // Enum class for piece color
    private enum pieceColor {
        WHITE,
        BLACK
    }

    // Current color
    private pieceColor currentColor = pieceColor.WHITE;

    // 8x8 JButton array for the board
    private JButton[][] board = new JButton[BOARD_SIZE][BOARD_SIZE];

    // Selected button x and y
    private Point selected = null;

    // main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Chess chessGame = new Chess();
        });
    }



}
