import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Hospital {

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        String input;

        Map<String,String[][]>departmentsAndRooms = new LinkedHashMap<>();
        Map<String, List<String>> doctorAndPatients = new LinkedHashMap<>();

        while(!"Output".equals(input=read.readLine())){

            String[] data = input.split("\\s+");

            departmentsAndRooms.putIfAbsent(data[0],new String[20][3]);

            addPatientInRoom(departmentsAndRooms.get(data[0]),data[3]);

            String doctor = data[1]+" "+ data[2];

            doctorAndPatients.putIfAbsent(doctor,new ArrayList<>());
            doctorAndPatients.get(doctor).add(data[3]);
        }

        while(!"End".equals(input=read.readLine())){

            String[] data = input.split("\\s+");

            if (data.length==1){
                printPatients(departmentsAndRooms.get(data[0]),0,false);
            }else if (data.length==2){
                if (Character.isDigit(data[1].charAt(0))){
                    printPatients(departmentsAndRooms.get(data[0]),Integer.parseInt(data[1])-1,true);
                }else {
                    String doctorName = data[0]+" "+data[1];
                    doctorAndPatients.get(doctorName).stream().sorted(String::compareTo).forEach(System.out::println);
                }
            }

        }

    }

    private static void printPatients(String[][] rooms, int roomNumber, boolean isRoom) {


        if (!isRoom){
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[i].length; j++) {
                    if (rooms[i][j]!=null){
                        System.out.println(rooms[i][j]);
                    }else {
                        return;
                    }
                }
            }
        }else {
            Arrays.stream(rooms[roomNumber]).sorted(String::compareTo).forEach(System.out::println);

        }
    }

    private static void addPatientInRoom(String[][] rooms, String patient) {
        if (rooms[19][2]==null){
            for (int i = 0; i < rooms.length; i++) {
                for (int j = 0; j < rooms[i].length; j++) {
                    if (rooms[i][j]==null){
                        rooms[i][j] = patient;
                        return;
                    }
                }
            }
        }
    }
}
