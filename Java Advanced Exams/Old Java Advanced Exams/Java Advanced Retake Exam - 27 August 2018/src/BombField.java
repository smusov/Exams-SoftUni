import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BombField {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(read.readLine());

        String[] commands = read.readLine().split(",");

        String[][] field = new String[size][size];

        int sapperRow = -1;
        int sapperCol = -1;

        for (int i = 0; i < size; i++) {

            field[i] = read.readLine().split("\\s+");

            for (int j = 0; j < field[i].length; j++) {

                if (field[i][j].equals("s")) {

                    sapperRow = i;
                    sapperCol = j;

                }
            }
        }

        for (String command : commands) {

            switch (command) {
                case "up":
                    sapperRow -= 1;
                    break;
                case "down":
                    sapperRow += 1;
                    break;
                case "left":
                    sapperCol -= 1;
                    break;
                case "right":
                    sapperCol += 1;
                    break;
            }
            sapperRow = validateRow(sapperRow, field.length);
            sapperCol = validateCol(sapperCol, field[0].length);

            if (field[sapperRow][sapperCol].equals("B")) {
                System.out.println("You found a bomb!");
                field[sapperRow][sapperCol] = "+";
            }else if (field[sapperRow][sapperCol].equals("e")){
                System.out.println(String.format("END! %d bombs left on the field",getBombCount(field)));
                return;
            }

            if (getBombCount(field)==0){
                System.out.println("Congratulations! You found all bombs!");
                return;
            }
        }
        System.out.println(String.format("%d bombs left on the field. Sapper position: (%d,%d)",getBombCount(field),sapperRow,sapperCol));
    }

    private static int getBombCount(String[][] field) {

        int count = 0;

        for (String[] element : field) {
            for (int j = 0; j < field.length; j++) {
                if (element[j].equals("B")) {
                    count++;
                }
            }
        }

        return count;
    }

    private static int validateCol(int sapperCol, int fieldCol) {
        if (sapperCol < 0) {
            return 0;
        }
        return Math.min(sapperCol, fieldCol - 1);
    }

    private static int validateRow(int sapperRow, int fieldRow) {

        if (sapperRow < 0) {
            return 0;
        }

        return Math.min(sapperRow, fieldRow - 1);
    }
}
