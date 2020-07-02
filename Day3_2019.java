import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

final class calc_points_return{
    private List<int[]> points;
    private List<Integer> steps;

    public calc_points_return(List<int[]> point_list, List<Integer> step_list){
        this.points=point_list;
        this.steps=step_list;
    }
    public List<int[]> get_points(){
        return this.points;
    }
    public List<Integer> get_steps(){
        return this.steps;
    }
}
public class Day3_2019 {
    private static void print(String arg){
        System.out.println(arg);
    }
    
    public static calc_points_return calc_points(String[] path) {
        int currx = 0;
        int curry = 0;
        int step = 0;
        List<int[]> points_list = new ArrayList<int[]>();
        List<Integer> steps_list = new ArrayList<Integer>();
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
                if (!points_list.contains(tmp_point)) {
                    points_list.add(tmp_point);
                    steps_list.add(step);
                }
            }
        }
        return new calc_points_return(points_list, steps_list);
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
        calc_points_return wire1_calc = calc_points(wire1_path);
        String[] wire2_path = line2.split(",");
        calc_points_return wire2_calc = calc_points(wire2_path);
        List<int[]>wire1_points = wire1_calc.get_points();
        List<Integer>wire1_steps = wire1_calc.get_steps();
        List<int[]>wire2_points = wire2_calc.get_points();
        List<Integer>wire2_steps = wire2_calc.get_steps();
        List<int[]> intersection_points = new ArrayList<int[]>();
        List<Integer> Manhattan_distance = new ArrayList<Integer>();
        for (int[]point_wire1: wire1_points){
            if (wire2_points.contains(point_wire1)){
                intersection_points.add(point_wire1);
                Manhattan_distance.add(  Math.abs(point_wire1[0]) + Math.abs(point_wire1[1]));
            }
        }


        int Min_Manhattan_distance_index  = Manhattan_distance.indexOf (Collections.min(Manhattan_distance));
        print("minimum distance is: "+String.valueOf(Manhattan_distance.get(Min_Manhattan_distance_index)));
        List<Integer> Step = new ArrayList<Integer>();
        for (int[] point : intersection_points ){
            int wire1_step_index = wire1_points.indexOf(point);
            int wire2_step_index = wire2_points.indexOf(point);
            int wire1_step = wire1_steps.get(wire1_step_index);
            int wire2_step = wire2_steps.get(wire2_step_index);
            Step.add(wire1_step+wire2_step);
        }
        int Min_step_distance_index  = Step.indexOf (Collections.min(Step));
        print("minimum steps are: "+String.valueOf(Step.get(Min_step_distance_index)));
        
    }
    
}