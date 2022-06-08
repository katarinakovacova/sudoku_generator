import java.util.*;

public class Sudoku {
    int[][] puzzle = new int[9][9];

    private boolean isElementPresent(int[][] puzzle, int element, int i, int j) {
        // Check if given element is already present in a given row and column.

        for (int m = 0; m < 9; m++) {
            if (puzzle[i][m] == element) {
                return true; 
            }
        }

        for (int m = 0; m < 9; m++) {
            if (puzzle[m][j] == element) {
                return true; 
            }
        }

        return false;
    }

    public void makePuzzle() throws Exception {
        // Try to create a solved sudoku.

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = 0;
            }
        }

        List<Integer> indices = new ArrayList<>(Arrays.asList(0, 1, 2));

        for (int x = 1; x < 10; x++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int si = 3 * i;
                    int sj = 3 * j;

                    Collections.shuffle(indices);
                    List<Integer> rowiList = new ArrayList<>(indices);
                    Collections.shuffle(indices);
                    List<Integer> coliList = new ArrayList<>(indices);

                    int[] rowi = rowiList.stream().mapToInt(y->y).toArray();
                    int[] coli = coliList.stream().mapToInt(y->y).toArray();

                    boolean isElementPlaced = false;

                    outerloop:
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            int mi = si + rowi[k];
                            int mj = sj + coli[l];

                            if (puzzle[mi][mj] != 0) {
                                continue;
                            }

                            if (!isElementPresent(puzzle, x, mi, mj)) {
                                puzzle[mi][mj] = x;
                                isElementPlaced = true;
                                break outerloop;
                            }
                        }
                    }

                    if (!isElementPlaced) {
                        throw new Exception("Incorrect sudoku");
                    }
                }
            }
        }
    }

    public void showPuzzle() {
        // Show sudoku as a simple 9x9 matrix.
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();

        int count = 0;

        while (true) {
            try {
                sudoku.makePuzzle();
                break;
            } catch (Exception e) {
                count++;
            }
        }
        
        System.out.println("Number of failed attempts: " + count);
        sudoku.showPuzzle();
    }
}