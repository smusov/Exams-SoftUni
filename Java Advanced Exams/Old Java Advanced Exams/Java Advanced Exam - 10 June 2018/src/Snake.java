import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Snake {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));


        int fieldLength = Integer.parseInt(read.readLine());

        String[] commands = read.readLine().split(", ");

        String[][] field = new String[fieldLength][fieldLength];

        int startRow = -1;
        int startCol = -1;

        int food = 0;

        for (int i = 0; i < field.length; i++) {

            String[] data = read.readLine().split("\\s+");

            for (int j = 0; j < data.length; j++) {

                field[i][j] = data[j];

                if (data[j].equals("s")) {

                    startRow = i;
                    startCol = j;

                }else if (data[j].equals("f")){
                    food++;
                }
            }
        }


        int snakeLength = 1;

        for (String command : commands) {

            if (food<=0){
                System.out.printf("You win! Final snake length is %d",snakeLength);
                return;
            }

            switch (command) {
                case "up":
                    startRow -= 1;
                    break;
                case "down":
                    startRow += 1;
                    break;
                case "left":
                    startCol -= 1;
                    break;
                case "right":
                    startCol += 1;
                    break;
            }

            startRow = validateIndexes(startRow, field.length);
            startCol = validateIndexes(startCol,field.length);

            String type = field[startRow][startCol];

            switch (type){
                case "f":
                    snakeLength++;
                    food--;
                    break;
                case "e":
                    System.out.println("You lose! Killed by an enemy!");
                    return;
            }
        }

        if (food>0){
            System.out.printf("You lose! There is still %d food to be eaten.",food);
        }else if (food==0){
            System.out.printf("You win! Final snake length is %d",snakeLength);
        }

    }

    private static int validateIndexes(int startRow, int length) {

        if (startRow < 0) {
            return length - 1;
        }

        if (startRow >= length) {
            return 0;
        }

        return startRow;
    }
}
