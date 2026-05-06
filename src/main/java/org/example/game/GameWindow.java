package org.example.game;

import org.example.welcome.WelcomeWindow;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private GameBoard board;
    private JButton[][] buttons;

    private int width;
    private int height;
    private int mines;

    public GameWindow(int width, int height, int mines) {

        this.width = width;
        this.height = height;
        this.mines = mines;

        setTitle("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        initGame();
    }

    private void initGame() {

        board = new GameBoard();
        board.init(width, height, mines);

        getContentPane().removeAll();

        JPanel panel = new JPanel(new GridLayout(height, width));
        buttons = new JButton[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                JButton btn = new JButton();
                buttons[y][x] = btn;

                int fx = x;
                int fy = y;

                btn.addActionListener(e -> handleClick(fx, fy));

                panel.add(btn);
            }
        }

        add(panel);
//        pack();
        revalidate();
        repaint();
    }

    private void handleClick(int x, int y) {

        Cell cell = board.openCell(x, y);

        updateUI();

        if (cell.isMine()) {

            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Game Over! Restart?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                initGame(); // 🔥 перезапуск той же игры
            } else {
                new WelcomeWindow().setVisible(true);
                dispose();
            }
        }
    }

    private void updateUI() {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Cell cell = board.getCell(x, y);
                JButton btn = buttons[y][x];

                if (cell.isOpen()) {
                    btn.setEnabled(false);

                    if (cell.isMine()) {
                        btn.setText("X");
                    } else if (cell.getMinesAround() > 0) {
                        btn.setText(String.valueOf(cell.getMinesAround()));
                    } else {
                        btn.setText("");
                    }
                }
            }
        }
    }
}