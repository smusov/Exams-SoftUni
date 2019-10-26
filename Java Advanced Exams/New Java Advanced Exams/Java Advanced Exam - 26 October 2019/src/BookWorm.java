import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookWorm {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String string = read.readLine();
        int n = Integer.parseInt(read.readLine());

        String[][] matrix = new String[n][n];

        int playerRow = -1;
        int playerCol = -1;

        for (int i = 0; i < n; i++) {

            String[] data = read.readLine().split("");

            for (int j = 0; j < data.length; j++) {

                matrix[i][j] = data[j];

                if (data[j].equals("P")) {

                    playerRow = i;
                    playerCol = j;

                }
            }
        }

        String input;

        matrix[playerRow][playerCol] = "-";

        while (!"end".equals(input = read.readLine())) {

            switch (input) {
                case "up":
                    playerRow--;
                    break;
                case "down":
                    playerRow++;
                    break;
                case "left":
                    playerCol--;
                    break;
                case "right":
                    playerCol++;
                    break;
            }

            if (validateIndex(playerRow, playerCol, n)) {

                String element = matrix[playerRow][playerCol];

                if (Character.isAlphabetic(element.charAt(0))){

                    matrix[playerRow][playerCol] = "-";
                    string+=element;

                }
            } else {

                playerRow = setValidIndexes(playerRow, n);
                playerCol = setValidIndexes(playerCol, n);

                if (!string.equals("")){

                    string = string.substring(0, string.length() - 1);

                }
            }

        }

        matrix[playerRow][playerCol] = "P";

        System.out.println(string);

        printMatrix(matrix);

    }

    private static void printMatrix(String[][] matrix) {

            for (int i = 0; i < matrix.length; i++) {

                for (int j = 0; j < matrix[i].length; j++) {

                    System.out.print(matrix[i][j]);

                }

                System.out.println();
            }
        }

    private static int setValidIndexes(int index, int n) {

        if (index<0){
            return 0;
        }

        if (index >= n) {
            return n - 1;
        }

        return index;
    }

    public static boolean validateIndex(int playerRow, int playerCol, int size) {
        return playerRow >= 0 && playerRow < size && playerCol >= 0 && playerCol < size;
    }
}
