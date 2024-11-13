import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess extends JFrame {
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

    // Constructor, containing gui components
    public Chess() {
        setTitle("Chess");
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create board
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                // Instantiate JButton
                board[row][col] = new JButton(defaultBoard[row][col]);

                // Make font as bold and scale
                board[row][col].setFont(new Font("", Font.BOLD, 48));

                // Create black and white board pattern with opacity to distinguish color difference
                board[row][col].setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                board[row][col].setOpaque(true);

                // Add listener for click
                board[row][col].addActionListener(new ClickListener(row, col));

                add(board[row][col]);
            }
        }

        // Display window
        this.setVisible(true);
    }

    // Listener for play control/piece movement
    private class ClickListener implements ActionListener {
        private final int row;
        private final int col;

        // Constructor to initialize row and column for this listener
        public ClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }










    // Default board with starting positions utilizing chess piece unicode characters
    private final String[][] defaultBoard = {
            {"\u2656", "\u2658", "\u2657", "\u2654", "\u2655", "\u2657", "\u2658", "\u2656"},
            {"\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659"},
            {"",       "",       "",       "",       "",       "",       "",       ""},
            {"",       "",       "",       "",       "",       "",       "",       ""},
            {"",       "",       "",       "",       "",       "",       "",       ""},
            {"",       "",       "",       "",       "",       "",       "",       ""},
            {"\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F"},
            {"\u265C", "\u265E", "\u265D", "\u265A", "\u265B", "\u265D", "\u265E", "\u265C"}
    };
}
