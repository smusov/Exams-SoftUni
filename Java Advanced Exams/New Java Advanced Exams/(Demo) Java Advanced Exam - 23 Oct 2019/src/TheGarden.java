import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class TheGarden {

    private static int harmed = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(read.readLine());

        String[][] field = new String[rows][];

        Map<String, Integer> counts = new LinkedHashMap<>();

        counts.put("Carrots", 0);
        counts.put("Potatoes", 0);
        counts.put("Lettuce", 0);

        for (int i = 0; i < rows; i++) {
            field[i] = read.readLine().split("\\s+");
        }

        String input;

        while (!"End of Harvest".equals(input = read.readLine())) {

            String[] data = input.split("\\s+");

            int row = Integer.parseInt(data[1]);
            int col = Integer.parseInt(data[2]);

            if (isValidIndexes(row, col, field)) {

                switch (data[0]) {
                    case "Harvest":

                        String type = field[row][col];
                        String harvest = getHarvest(type);

                        if (!harvest.equals("Empty")) {
                            counts.put(harvest, counts.get(harvest) + 1);
                            field[row][col] = " ";
                        }

                        break;
                    case "Mole":

                        switch (data[3]) {
                            case "up":
                                up(row, col, field);
                                break;
                            case "down":
                                down(row, col, field);
                                break;
                            case "left":
                                left(row, col, field);
                                break;
                            case "right":
                                right(row, col, field);
                                break;
                        }

                        break;
                }

            }
        }

        printField(field);
        counts.forEach((key, value) -> System.out.println(String.format("%s: %d", key, value)));
        System.out.printf("Harmed vegetables: %d",harmed);
    }

    private static void printField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (j==field[i].length-1){
                    System.out.println(field[i][j]);
                }else {
                    System.out.print(field[i][j] + " ");
                }
            }
        }
    }

    private static void right(int row, int col, String[][] field) {
        for (int i = col; i < field[row].length; i += 2) {
            if (isValidCol(row, i, field)) {
                if (!field[row][i].equals(" ")){
                    field[row][i] = " ";
                    harmed++;
                }
            }
        }
    }

    private static void left(int row, int col, String[][] field) {
        for (int i = col; i >= 0; i -= 2) {
            if (isValidCol(row, i, field)) {
                if (!field[row][i].equals(" ")){
                    field[row][i] = " ";
                    harmed++;
                }
            }
        }
    }

    private static void down(int row, int col, String[][] field) {
        for (int i = row; i < field.length; i += 2) {

            if (isValidRow(i,field)) {
                if (!field[i][col].equals(" ")){
                    field[i][col] = " ";
                    harmed++;
                }
            }

        }
    }

    private static void up(int row, int col, String[][] field) {
        for (int i = row; i >= 0; i -= 2) {
            if (isValidRow(i,field)) {
                if (!field[i][col].equals(" ")){
                    field[i][col] = " ";
                    harmed++;
                }
            }
        }
    }

    private static String getHarvest(String type) {
        switch (type) {
            case "L":
                return "Lettuce";
            case "P":
                return "Potatoes";
            case "C":
                return "Carrots";
        }
        return "Empty";
    }

    private static boolean isValidCol(int row, int col, String[][] field) {
        return col >= 0 && col < field[row].length;
    }

    private static boolean isValidRow(int row, String[][] field) {
        return row >= 0 && row < field.length;
    }

    private static boolean isValidIndexes(int row, int col, String[][] field) {
        return (row >= 0 && row < field.length) && col >= 0 && col < field[row].length;
    }
}
