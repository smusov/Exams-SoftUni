import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelensAbduction {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int parisEnergy = Integer.parseInt(read.readLine());
        int rows = Integer.parseInt(read.readLine());

        String[][] field = new String[rows][];

        for (int i = 0; i < rows; i++) {
            field[i] = read.readLine().split("");
        }

        int[] parisIndexes = findIndexes(field, "P");
        int[] helenaIndexes = findIndexes(field, "H");

        while (parisEnergy > 0) {
            String[] data = read.readLine().split("\\s+");

            spartanSpawn(data[1], data[2], field);

            switch (data[0]) {
                case "up":
                    parisEnergy = move(field, parisIndexes, parisEnergy, helenaIndexes, parisIndexes[0] - 1, parisIndexes[1]);
                    break;
                case "down":
                    parisEnergy = move(field, parisIndexes, parisEnergy, helenaIndexes, parisIndexes[0] + 1, parisIndexes[1]);
                    break;
                case "left":
                    parisEnergy = move(field, parisIndexes, parisEnergy, helenaIndexes, parisIndexes[0], parisIndexes[1] - 1);
                    break;
                case "right":
                    parisEnergy = move(field, parisIndexes, parisEnergy, helenaIndexes, parisIndexes[0], parisIndexes[1] + 1);
                    break;
            }
            if (helenaIndexes[0] == -1 & helenaIndexes[1] == -1) {
                System.out.println(String.format("Paris has successfully abducted Helen! Energy left: %d", parisEnergy));
                printMatrix(field);
                return;
            }
        }
        System.out.println(String.format("Paris died at %d;%d.",parisIndexes[0],parisIndexes[1]));
        printMatrix(field);
    }

    private static void printMatrix(String[][] field) {
        for (String[] element : field) {
            for (String character : element) {
                System.out.print(character);
            }
            System.out.println();
        }
    }

    private static int move(String[][] field, int[] parisIndexes, int parisEnergy, int[] helenaIndexes, int parisIndexRow, int parisIndexCol) {

        parisEnergy--;

        if (parisEnergy<=0){
            parisDead(field,parisIndexes,parisIndexRow,parisIndexCol);
            return parisEnergy;
        }

        if (parisIndexRow >= 0 && parisIndexRow < field.length && parisIndexCol >= 0 && parisIndexCol < field[0].length) {
            if (checkForSpartanOrHelena(field, parisIndexRow, parisIndexCol, "S")) {
                parisEnergy -= 2;
                if (parisEnergy > 0) {
                    setParis(field, parisIndexes, parisIndexes[0], parisIndexes[1], parisIndexRow, parisIndexCol);
                } else {
                    parisDead(field,parisIndexes,parisIndexRow,parisIndexCol);
                    return parisEnergy;
                }
            } else {

                if (checkForSpartanOrHelena(field, parisIndexRow, parisIndexCol, "H")) {
                    field[parisIndexes[0]][parisIndexes[1]] = "-";
                    field[helenaIndexes[0]][helenaIndexes[1]] = "-";
                    helenaIndexes[0] = -1;
                    helenaIndexes[1] = -1;

                } else {
                    setParis(field, parisIndexes, parisIndexes[0], parisIndexes[1], parisIndexRow, parisIndexCol);

                }
            }
        }
        return parisEnergy;
    }

    private static void parisDead(String[][] field, int[] parisIndexes, int parisIndexRow, int parisIndexCol) {
        field[parisIndexes[0]][parisIndexes[1]] = "-";
        field[parisIndexRow][parisIndexCol] = "X";
        parisIndexes[0] = parisIndexRow;
        parisIndexes[1] = parisIndexCol;
    }

    private static void setParis(String[][] field, int[] parisIndexes, int oldRow, int oldCol, int row, int col) {
        field[oldRow][oldCol] = "-";
        parisIndexes[0] = row;
        parisIndexes[1] = col;
        field[row][col] = "P";
    }

    private static boolean checkForSpartanOrHelena(String[][] field, int row, int col, String type) {
        return field[row][col].equals(type);
    }

    private static int[] findIndexes(String[][] field, String type) {

        int[] indexes = new int[2];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j].equals(type)) {
                    indexes[0] = i;
                    indexes[1] = j;
                    return indexes;
                }
            }
        }
        return indexes;
    }

    private static void spartanSpawn(String index1, String index2, String[][] field) {
        int row = Integer.parseInt(index1);
        int col = Integer.parseInt(index2);

        field[row][col] = "S";
    }
}