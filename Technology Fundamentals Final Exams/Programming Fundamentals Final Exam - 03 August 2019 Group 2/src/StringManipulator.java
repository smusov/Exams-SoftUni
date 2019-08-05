import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringManipulator {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String input = read.readLine();

        String data;

        while(!"Done".equals(data=read.readLine())){

            String[] com = data.split("\\s+");

            switch (com[0]){
                case "Change":
                    input = input.replace(com[1],com[2]);
                    System.out.println(input);
                    break;
                case "Includes":
                    if (input.contains(com[1])){
                        System.out.println("True");
                    }else {
                        System.out.println("False");
                    }
                    break;
                case "End":
                    if (input.endsWith(com[1])){
                        System.out.println("True");
                    }else {
                        System.out.println("False");
                    }
                    break;
                case "Uppercase":
                    input = input.toUpperCase();
                    System.out.println(input);
                    break;
                case "FindIndex":
                    System.out.println(input.indexOf(com[1]));
                    break;
                case "Cut":
                    int startIndex = Integer.parseInt(com[1]);
                   int length = Integer.parseInt(com[2]);

                   input = input.substring(startIndex,startIndex+length);
                    System.out.println(input);
                    break;
            }

        }

    }
}
