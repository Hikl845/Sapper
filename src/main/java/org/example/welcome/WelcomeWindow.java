package org.example.welcome;

import org.example.game.GameWindow;

import javax.swing.*;
import java.awt.*;

public class WelcomeWindow extends JFrame {

    public WelcomeWindow() {

        setTitle("Welcome to Minesweeper");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Select difficulty", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        String[] options = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficulty = new JComboBox<>(options);

        JButton button = new JButton("Start");

        button.addActionListener(e -> {

            String selected = (String) difficulty.getSelectedItem();

            int width = 10;
            int height = 10;
            int mines = 10;

            if ("Medium".equals(selected)) {
                width = 16;
                height = 16;
                mines = 40;
            } else if ("Hard".equals(selected)) {
                width = 24;
                height = 24;
                mines = 99;
            }

            new GameWindow(width, height, mines).setVisible(true);
            dispose();
        });

        JPanel centerPanel = new JPanel();
        centerPanel.add(difficulty);

        add(label, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }
}