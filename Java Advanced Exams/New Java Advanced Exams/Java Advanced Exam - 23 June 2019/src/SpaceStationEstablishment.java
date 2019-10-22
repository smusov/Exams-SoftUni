import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SpaceStationEstablishment {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        int rowCol = Integer.parseInt(read.readLine());

        String[][] matrix = new String[rowCol][rowCol];

        for (int i = 0; i < rowCol; i++) {
            matrix[i] = read.readLine().split("");
        }

        int [] indexSpaceShip = findSpaceship(matrix);
        int [] blackHoleIndexes = findBlackHoles (matrix);

        int points = 0;

        while (points<50){

            String text = read.readLine();
            int index;

            switch (text){
                case "up":
                     index = indexSpaceShip[0]-1;
                    if (validIndexRow(index,matrix)){
                        if (index!=blackHoleIndexes[0]){
                            indexSpaceShip[0] = index;
                            points = calculatePointsAndMoveSpaceShip(matrix,indexSpaceShip,points);
                            matrix[indexSpaceShip[0]+1][indexSpaceShip[1]] = "-";
                        }else {
                            moveToBlackHole(indexSpaceShip,blackHoleIndexes,matrix);
                        }
                    }else {
                        matrix[indexSpaceShip[0]][indexSpaceShip[1]] = "-";
                        System.out.println("Bad news, the spaceship went to the void.");
                        System.out.println("Star power collected: "+points);
                        printMatrix(matrix);
                        return;
                    }
                    break;
                case "down":
                    index = indexSpaceShip[0]+1;
                    if (validIndexRow(index,matrix)){
                        if (index!=blackHoleIndexes[0]){
                            indexSpaceShip[0] = index;
                            points = calculatePointsAndMoveSpaceShip(matrix,indexSpaceShip,points);
                            matrix[indexSpaceShip[0]-1][indexSpaceShip[1]] = "-";
                        }else {
                            moveToBlackHole(indexSpaceShip,blackHoleIndexes,matrix);
                        }
                    }else {
                        matrix[indexSpaceShip[0]][indexSpaceShip[1]] = "-";
                        System.out.println("Bad news, the spaceship went to the void.");
                        System.out.println("Star power collected: "+points);
                        printMatrix(matrix);
                        return;
                    }
                    break;
                case "left":
                    index = indexSpaceShip[1] - 1;
                    if (validIndexCol(index,matrix)){
                        if (index!=blackHoleIndexes[1]){
                            indexSpaceShip[1] = index;
                            points = calculatePointsAndMoveSpaceShip(matrix,indexSpaceShip,points);
                            matrix[indexSpaceShip[0]][indexSpaceShip[1]+1] = "-";
                        }else {
                            moveToBlackHole(indexSpaceShip,blackHoleIndexes,matrix);
                        }
                    }else {
                        matrix[indexSpaceShip[0]][indexSpaceShip[1]] = "-";
                        System.out.println("Bad news, the spaceship went to the void.");
                        System.out.println("Star power collected: "+points);
                        printMatrix(matrix);
                        return;
                    }
                    break;
                case "right":
                    index = indexSpaceShip[1] +1;
                    if (validIndexCol(index,matrix)){
                        if (index!=blackHoleIndexes[1]){
                            indexSpaceShip[1] = index;
                            points = calculatePointsAndMoveSpaceShip(matrix,indexSpaceShip,points);
                            matrix[indexSpaceShip[0]][indexSpaceShip[1]-1] = "-";
                        }else {
                            moveToBlackHole(indexSpaceShip,blackHoleIndexes,matrix);
                        }
                    }else {
                        matrix[indexSpaceShip[0]][indexSpaceShip[1]] = "-";
                        System.out.println("Bad news, the spaceship went to the void.");
                        System.out.println("Star power collected: "+points);
                        printMatrix(matrix);
                        return;
                    }
                    break;
            }
        }

        System.out.println("Good news! Stephen succeeded in collecting enough star power!");
        System.out.println("Star power collected: "+points);
        printMatrix(matrix);

    }


    private static void printMatrix(String[][] matrix) {
        for (String[] element : matrix) {
            System.out.println(Arrays.toString(element).replaceAll("[\\[\\], ]", ""));
        }
    }

    private static boolean validIndexRow (int index,String [][] matrix){
        return index>=0&&index<matrix.length;
    }

    private static boolean validIndexCol (int index,String [][] matrix){
        return index>=0&&index<matrix[0].length;
    }

    private static int calculatePointsAndMoveSpaceShip(String[][] matrix, int[] indexSpaceship, int points) {

        if (Character.isDigit(matrix[indexSpaceship[0]][indexSpaceship[1]].charAt(0))){
            points+=Integer.parseInt(matrix[indexSpaceship[0]][indexSpaceship[1]]);
        }
        matrix[indexSpaceship[0]][indexSpaceship[1]] = "S";

        return points;
    }

    private static void moveToBlackHole(int[] indexSpaceShip, int[] blackHoleIndexes, String[][] matrix) {
        matrix[indexSpaceShip[0]][indexSpaceShip[1]] = "-";
        matrix[blackHoleIndexes[0]][blackHoleIndexes[1]] = "-";
        matrix[blackHoleIndexes[2]][blackHoleIndexes[3]] = "S";
        indexSpaceShip[0] = blackHoleIndexes[2];
        indexSpaceShip[1] = blackHoleIndexes[3];
        blackHoleIndexes[0] = -1;
        blackHoleIndexes[1] = -1;
        blackHoleIndexes[2] = -1;
        blackHoleIndexes[3] = -1;
    }

    private static int[] findBlackHoles(String[][] matrix) {
        int[] blackHoles = new int[4];
        blackHoles[0]=-1;
        blackHoles[1]=-1;
        blackHoles[2]=-1;
        blackHoles[3]=-1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("O")&&blackHoles[0]==-1){
                    blackHoles[0]=i;
                    blackHoles[1]=j;
                }else if (matrix[i][j].equals("O")&&blackHoles[2]==-1) {
                    blackHoles[2] = i;
                    blackHoles[3] = j;
                }
            }
        }
        return blackHoles;
    }

    private static int[] findSpaceship(String[][] matrix) {
        int [] indexes = new int[2];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col].equals("S")){
                    indexes[0] = row;
                    indexes[1] = col;
                    return indexes;
                }
            }
        }
        indexes[0]=-1;
        indexes[1]=-1;
        return indexes;
    }
}
