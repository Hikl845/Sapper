package org.example.game;

public class Cell {
        private boolean isMine;
        private boolean isOpen;
        private int minesAround;

        public boolean isMine() { return isMine; }
        public void setMine(boolean mine) { isMine = mine; }

        public boolean isOpen() { return isOpen; }
        public void setOpen(boolean open) { isOpen = open; }

        public int getMinesAround() { return minesAround; }
        public void setMinesAround(int minesAround) { this.minesAround = minesAround; }
}
