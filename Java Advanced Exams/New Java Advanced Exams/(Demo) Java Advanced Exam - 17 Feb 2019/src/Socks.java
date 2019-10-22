import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Socks {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        Deque<Integer> leftSocks = new ArrayDeque<>();

        Deque<Integer> rightSocks = new ArrayDeque<>();

        Arrays.stream(read.readLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(leftSocks::push);
        Arrays.stream(read.readLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(rightSocks::offer);

        List<Integer> pairs = new ArrayList<>();

        while (!rightSocks.isEmpty()&&!leftSocks.isEmpty()){

            int rightSock = rightSocks.poll();
            int leftSock = leftSocks.pop();

            if (leftSock>rightSock){
                pairs.add(rightSock+leftSock);
            }else if (rightSock>leftSock){
                rightSocks.addFirst(rightSock);
            }else {
                leftSock++;
                leftSocks.push(leftSock);
            }


        }
        System.out.println(pairs.stream().mapToInt(e->e).max().getAsInt());
        System.out.println(pairs.toString().replaceAll("[\\[\\],]",""));
    }
}
