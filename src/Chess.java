import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chess extends JFrame {
    /*
     * Size const for board length and width
     */
    private static final int BOARD_SIZE = 8;

    /**
     * Enum class for piece color
     */
    private enum pieceColor {
        WHITE,
        BLACK
    }

    /**
     * Class to manage coordinates
     */
    class Coordinates {
        int x;
        int y;
        
        Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
     * Current color
     */
    private pieceColor currentColor = pieceColor.WHITE;

    /*
     * JButton array for the board
     */
    private JButton[][] board = new JButton[BOARD_SIZE][BOARD_SIZE];

    // Selected button x and y
    private Coordinates selected = null;

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Chess chessGame = new Chess();
        });
    }

    /**
     * Constructor with gui components/board setup
     */
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

    /**
     * Listener for play control/piece movement
     */
    private class ClickListener implements ActionListener {
        private final int row;
        private final int col;

        /**
         * Constructor to initialize row and column for this listener
         * @param row
         * @param col
         */
        public ClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * actionPerformed
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Clicked button
            JButton clickedButton = board[row][col];

            // Get text (piece)
            String piece = clickedButton.getText();

            // Color of piece
            pieceColor pieceColor = getColor(piece);

            if (selected == null) {
                if (pieceColor == currentColor) {
                    selected = new Coordinates(row, col);

                    // Highlight square
                    clickedButton.setBackground(Color.RED);
                }
            } else {
                JButton selectedButton = board[selected.x][selected.y];
                String selectedPiece = selectedButton.getText();


                // Assign elements to reflect move
                clickedButton.setText(selectedPiece);
                selectedButton.setText("");

                // Check if king is captured
                if (piece == "\u2654" || piece == "\u265A") {
                    // Display popup with message
                    JOptionPane.showMessageDialog(null, "Game Over!");

                    // Exit program
                    System.exit(0);
                }

                // Reset square color
                selectedButton.setBackground((selected.x + selected.y) % 2 == 0 ? Color.WHITE : Color.BLACK);
                clickedButton.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);

                // Change turns
                currentColor = (currentColor == pieceColor.WHITE) ? pieceColor.BLACK : pieceColor.WHITE;

                // Reset
                selected = null;
            }
        }

        /**
         * Returns piece color
         * @param piece
         * @return
         */
        private pieceColor getColor(String piece) {
            if (piece.equals("")) {
                return null;
            }

            // Get unicode char
            char pieceChar = piece.charAt(0);

            // Utilize Unicode char value to determine piece color
            if (pieceChar <= '\u2659') {
                return pieceColor.WHITE;
            } else if (pieceChar >= '\u265A') {
                return pieceColor.BLACK;
            }
            return null;
        }
    }

    /*
     * Default board with starting positions utilizing chess piece Unicode characters
     */
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
