import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Day3_2019 {
    private static void print(String arg){
        System.out.println(arg);
    }
    public static Hashtable<int[],Integer> calc_points(String[] path) {
        int currx = 0;
        int curry = 0;
        int step = 0;
        Hashtable<int[],Integer> points_hash = new Hashtable<int[], Integer>();
        int[] R_guide = {1,0};
        int[] L_guide = {-1,0};
        int[] U_guide = {0,1};
        int[] D_guide = {0,-1};
        Hashtable<String, int[]> directions_guide = new Hashtable<String, int[]>();
        directions_guide.put("R",R_guide);
        directions_guide.put("L",L_guide);
        directions_guide.put("U",U_guide);
        directions_guide.put("D",D_guide);

        for (String seg : path) {
            System.out.println(seg);
            String direction = seg.substring(0,1);
            int distance = Integer.parseInt(seg.substring(1));
            int[] guide = directions_guide.get(direction);
            for (int i = 0; i<distance;i++){
                currx += guide[0];
                curry += guide[1];
                step += 1;
                int[] tmp_point = {currx,curry};
                if (!points_hash.contains(tmp_point)) {
                    points_hash.put(tmp_point,step);
                }
            }
        }
        return points_hash;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader;
        //Open the input.tx file to get the mass of the modules
        reader = new BufferedReader(new FileReader(
            "test.txt"));
        //get the directions for the first wire it is written in a single line
        String line1 = reader.readLine();
        //get the directions for the second wire it is written in a single line
        String line2 = reader.readLine();
        //close the buffer reader
        reader.close();

        String[] wire1_path = line1.split(",");
        Hashtable<int[],Integer> wire1_Hash = new Hashtable<int[], Integer>();
        wire1_Hash = calc_points(wire1_path);
        List<int[]> wire1_points = new ArrayList<int[]>(wire1_Hash.keySet());
        String[] wire2_path = line2.split(",");
        Hashtable<int[],Integer> wire2_Hash = new Hashtable<int[], Integer>();
        wire2_Hash = calc_points(wire2_path);
        List<int[]> wire2_points = new ArrayList<int[]>(wire2_Hash.keySet());
        List<int[]> intersection_points = new ArrayList<int[]>();
        List<Integer> Manhattan_distance = new ArrayList<Integer>();
        for (int[]point_wire1: wire1_points){
            for (int[]point_wire2: wire2_points){
                if (Arrays.equals(point_wire1, point_wire2)){
                    intersection_points.add(point_wire1);
                    Manhattan_distance.add(  Math.abs(point_wire1[0]) + Math.abs(point_wire1[1]));
                }
            }
        }


        int Min_Manhattan_distance_index  = Manhattan_distance.indexOf (Collections.min(Manhattan_distance));
        print("minimum distance is: "+String.valueOf(Manhattan_distance.get(Min_Manhattan_distance_index)));
        List<Integer> Step = new ArrayList<Integer>();
        for (int[] point : intersection_points ){
            int wire1_step = wire1_Hash.get(point);
            int wire2_step = wire2_Hash.get(point);
            Step.add(wire1_step+wire2_step);
        }
        int Min_step_distance_index  = Manhattan_distance.indexOf (Collections.min(Step));
        print("minimum steps are: "+String.valueOf(Step.get(Min_step_distance_index)));
        
    }
    
}