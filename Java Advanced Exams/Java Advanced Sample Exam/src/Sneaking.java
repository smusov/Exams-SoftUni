import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sneaking {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int rows = Integer.parseInt(read.readLine());

        String[][] room = new String[rows][];

        for (int i = 0; i < rows; i++) {
            room[i] = read.readLine().split("");
        }

        String[] directions = read.readLine().split("");

        int[] samIndexes = findSamOrNikoladze(room, "S");
        int[] nikoladzeIndexes = findSamOrNikoladze(room, "N");

        for (String direction : directions) {

            moveEnemies(room);

            if (checkForEnemies(room, samIndexes)) {

                switch (direction) {
                    case "U":
                        moveSam(room, samIndexes, samIndexes[0] - 1, samIndexes[1]);
                        break;
                    case "D":
                        moveSam(room, samIndexes, samIndexes[0] + 1, samIndexes[1]);
                        break;
                    case "L":
                        moveSam(room, samIndexes, samIndexes[0], samIndexes[1] - 1);
                        break;
                    case "R":
                        moveSam(room, samIndexes, samIndexes[0], samIndexes[1] + 1);
                        break;
                    case "W":
                        break;
                }

            } else {
                System.out.println(String.format("Sam died at %d, %d", samIndexes[0], samIndexes[1]));
                printRoom(room);
                break;
            }
            if (nikoladzeIndexes[0] == samIndexes[0]) {
                System.out.println("Nikoladze killed!");
                room[nikoladzeIndexes[0]][nikoladzeIndexes[1]] = "X";
                printRoom(room);
                break;
            }
        }
    }

    private static int[] findSamOrNikoladze(String[][] room, String samOrNikoladze) {

        int[] indexes = new int[2];

        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (samOrNikoladze.equals(room[i][j])) {
                    indexes[0] = i;
                    indexes[1] = j;
                    return indexes;
                }
            }
        }
        return indexes;
    }

    private static boolean checkForEnemies(String[][] room, int[] samIndexes) {

        int samRow = samIndexes[0];
        int samCol = samIndexes[1];

        for (int i = 0; i < room[samRow].length; i++) {
            if (room[samRow][i].equals("b") && enemyBeforeSam(samCol, i)) {
                room[samRow][samCol] = "X";
                return false;
            }
            if (room[samRow][i].equals("d") && enemyAfterSam(samCol, i)) {
                room[samRow][samCol] = "X";
                return false;
            }
        }
        return true;
    }

    private static boolean enemyBeforeSam(int samCol, int enemyCol) {
        return enemyCol < samCol;
    }

    private static boolean enemyAfterSam(int samCol, int enemyCol) {
        return enemyCol > samCol;
    }

    private static void moveSam(String[][] room, int[] samIndexes, int row, int col) {
        room[row][col] = "S";
        room[samIndexes[0]][samIndexes[1]] = ".";
        samIndexes[0] = row;
        samIndexes[1] = col;
    }

    private static void moveEnemies(String[][] room) {

        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (room[i][j].equals("b")) {
                    moveRight(room, i, j);
                    break;
                } else if (room[i][j].equals("d")) {
                    moveLeft(room, i, j);
                    break;
                }
            }
        }

    }

    private static void moveRight(String[][] room, int row, int col) {
        if (room[row].length - 1 == col) {
            room[row][col] = "d";
        } else {
            room[row][col + 1] = "b";
            room[row][col] = ".";
        }
    }

    private static void moveLeft(String[][] room, int row, int col) {

        if (col == 0) {
            room[row][col] = "b";
        } else {
            room[row][col - 1] = "d";
            room[row][col] = ".";
        }

    }

    private static void printRoom(String[][] room) {
        for (String[] strings : room) {
            for (String character : strings) {
                System.out.print(character);
            }
            System.out.println();
        }
    }
}
