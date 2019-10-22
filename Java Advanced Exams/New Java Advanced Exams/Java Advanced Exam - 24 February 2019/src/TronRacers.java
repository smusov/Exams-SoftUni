import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class TronRacers {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(read.readLine());

        String[][] matrix = new String[size][];

        int firstPlayerRow = -1;
        int firstPlayerCol = -1;

        int secondPlayerRow = -1;
        int secondPlayerCol = -1;

        for (int i = 0; i < size; i++) {
            String[] input = read.readLine().split("");
            matrix[i] = new String[input.length];
            for (int j = 0; j < input.length; j++) {
                matrix[i][j] = input[j];
                if (input[j].equals("f")) {
                    firstPlayerRow = i;
                    firstPlayerCol = j;
                } else if (input[j].equals("s")) {
                    secondPlayerRow = i;
                    secondPlayerCol = j;
                }
            }

        }

        Map<String, int[]> playersIndexes = new LinkedHashMap<>();

        playersIndexes.put("f", new int[]{firstPlayerRow, firstPlayerCol});
        playersIndexes.put("s", new int[]{secondPlayerRow, secondPlayerCol});


        while (true) {

            String[] input = read.readLine().split("\\s+");

            for (int i = 0; i < 2; i++) {

                String player;

                if (i == 0) {
                    player = "f";
                } else {
                    player = "s";
                }

                int[] rowsCols = playersIndexes.get(player);

                switch (input[i]) {
                    case "up":
                        rowsCols[0] = rowsCols[0] - 1;
                        break;
                    case "down":
                        rowsCols[0] = rowsCols[0] + 1;
                        break;
                    case "left":
                        rowsCols[1] = rowsCols[1] - 1;
                        break;
                    case "right":
                        rowsCols[1] = rowsCols[1] + 1;
                        break;
                }

                validateIndexes(rowsCols, matrix);

                String element = matrix[rowsCols[0]][rowsCols[1]];

                if (player.equals("f")&&element.equals("s")||player.equals("s")&&element.equals("f")){
                    matrix[rowsCols[0]][rowsCols[1]] = "x";
                    printMatrix(matrix);
                    return;
                }

                matrix[rowsCols[0]][rowsCols[1]] = player;
                playersIndexes.put(player,rowsCols);

            }

        }

    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static void validateIndexes(int[] rowsCols, String[][] matrix) {
        if (rowsCols[0] < 0) {
            rowsCols[0] = matrix.length - 1;
        }
        if (rowsCols[0] > matrix.length - 1) {
            rowsCols[0] = 0;
        }
        if (rowsCols[1] < 0) {
            rowsCols[1] = matrix[rowsCols[0]].length - 1;
        }
        if (rowsCols[1] > matrix[rowsCols[0]].length - 1) {
            rowsCols[1] = 0;
        }
    }
}
