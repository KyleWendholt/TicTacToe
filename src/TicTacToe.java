import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToeGame extends JFrame implements ActionListener {

    private final JButton[] buttons = new JButton[9];

    private final JTextArea textArea = new JTextArea();

    private static int[][] board = new int[3][3];

    private static int turn = 1;

    public TicTacToeGame() {
        //frame setting
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());
        //Main panel setting
        JPanel mainPanel = new JPanel(new GridLayout(3, 3));

        //Button settings
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setActionCommand(i + "");
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.darkGray);
            mainPanel.add(buttons[i]);
        }

        add(mainPanel, BorderLayout.CENTER);

        JPanel resetPanel = new JPanel(new GridLayout(2, 1));
        JButton reset = new JButton("Reset");
        reset.addActionListener(this);
        reset.setBackground(Color.pink);
        reset.setFont(new Font("comic sans", Font.PLAIN,20));
        resetPanel.add(reset);

        textArea.setEditable(false);
        textArea.setBackground(Color.lightGray);
        textArea.setFont(new Font("comic sans", Font.PLAIN,15));

        resetPanel.add(textArea);

        add(resetPanel, BorderLayout.SOUTH);



        reset();


    }



    private void reset() {
        board = new int[3][3];
        for (JButton jButton : buttons) {
            jButton.setText("");
            jButton.setEnabled(true);
        }
        turn = 1;
        textArea.setText("Player " + turn + "'s turn");
    }

    private void lockBoard() {
        for (JButton jButton : buttons) {
            jButton.setEnabled(false);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        String xo = switch (turn) {
            case 1 -> "X";
            case 2 -> "O";
            default -> "";
        };

        switch (actionCommand) {
            case "Reset" -> reset();
            case "0" -> {
                board[0][0] = turn;
                buttons[0].setEnabled(false);
                buttons[0].setText(xo);
                endTurn();
            }
            case "1" -> {
                board[0][1] = turn;
                buttons[1].setEnabled(false);
                buttons[1].setText(xo);
                endTurn();
            }
            case "2" -> {
                board[0][2] = turn;
                buttons[2].setEnabled(false);
                buttons[2].setText(xo);
                endTurn();
            }
            case "3" -> {
                board[1][0] = turn;
                buttons[3].setEnabled(false);
                buttons[3].setText(xo);
                endTurn();
            }
            case "4" -> {
                board[1][1] = turn;
                buttons[4].setEnabled(false);
                buttons[4].setText(xo);
                endTurn();
            }
            case "5" -> {
                board[1][2] = turn;
                buttons[5].setEnabled(false);
                buttons[5].setText(xo);
                endTurn();
            }
            case "6" -> {
                board[2][0] = turn;
                buttons[6].setEnabled(false);
                buttons[6].setText(xo);
                endTurn();
            }
            case "7" -> {
                board[2][1] = turn;
                buttons[7].setEnabled(false);
                buttons[7].setText(xo);
                endTurn();
            }
            case "8" -> {
                board[2][2] = turn;
                buttons[8].setEnabled(false);
                buttons[8].setText(xo);
                endTurn();
            }
        }
    }

    private boolean checkForWin() {
        for (int j = 1; j <= 2; j++) {
            for (int i = 0; i < 3; i++) {//columns
                if (board[0][i] == j && board[1][i] == j && board[2][i] == j) {
                    return true;
                }
            }
            for (int i = 0; i < 3; i++) {//rows
                if (board[i][0] == j && board[i][1] == j && board[i][2] == j) {
                    return true;
                }
            }
        }
        for (int i = 1; i <= 2; i++) {//diagonals
            if (board[1][1] == i && ((board[0][0] == i && board[2][2] == i) || (board[0][2] == i && board[2][0] == i))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfFull() {
        for (int[] i : board) {
            for (int j : i) {
                if (j == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void endTurn() {
        if (checkForWin()) {
            textArea.setText("Player " + turn + " wins. Click reset to play again.");
            lockBoard();
        } else if (checkIfFull()) {
            textArea.setText("Draw. Click reset to play again.");
        } else {
            if (turn == 1) {
                turn++;
            } else {
                turn = 1;
            }
            textArea.setText("Player " + turn + "'s turn");
        }
    }
}