package org.example.game;

import java.util.Random;

public class GameBoard {
    private Cell[][] field;
    private int width;
    private int height;

    public void init(int width, int height, int mines) {
        this.height = height;
        this.width = width;

        field = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                field[y][x] = new Cell();
            }
        }

        placeMines(mines);
        calculateNumbers();
    }

    private void placeMines(int mines) {
        Random random = new Random();
        int placed = 0;

        while (placed < mines) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            if (!field[y][x].isMine()) {
                field[y][x].setMine(true);
                placed++;
            }
        }
    }

    private void calculateNumbers() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if (field[y][x].isMine()) continue;

                int count = 0;

                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {

                        int ny = y + dy;
                        int nx = x + dx;

                        if (isValid(nx, ny) && field[ny][nx].isMine()) {
                            count++;
                        }
                    }
                }

                field[y][x].setMinesAround(count);
            }
        }
    }

    public Cell openCell(int x, int y) {

        if (!isValid(x, y)) return null;

        Cell cell = field[y][x];

        if (cell.isOpen()) return cell;

        cell.setOpen(true);

        if (cell.isMine()) {
            return cell;
        }

        if (cell.getMinesAround() == 0) {
            openNeighbors(x, y);
        }

        return cell;
    }

    private void openNeighbors(int x, int y) {

        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {

                int nx = x + dx;
                int ny = y + dy;

                if (!isValid(nx, ny)) continue;

                Cell neighbor = field[ny][nx];

                if (!neighbor.isOpen() && !neighbor.isMine()) {
                    openCell(nx, ny);
                }
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public Cell getCell(int x, int y) {
        return field[y][x];
    }
}