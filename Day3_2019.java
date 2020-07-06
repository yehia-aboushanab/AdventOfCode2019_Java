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
        //custom class to be able to return both the points and the steps of each point in the path
        //from a single function
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
        // Function to calculate the total points in the path of a single wire
        // uses the custome class to return both the points and steps on the path
        int currx = 0;
        int curry = 0;
        int step = 0;
        List<int[]> points_list = new ArrayList<int[]>();
        List<Integer> steps_list = new ArrayList<Integer>();
        // a Hashtabel definition to translate the given path to points
        int[] R_guide = {1,0};
        int[] L_guide = {-1,0};
        int[] U_guide = {0,1};
        int[] D_guide = {0,-1};
        Hashtable<String, int[]> directions_guide = new Hashtable<String, int[]>();
        directions_guide.put("R",R_guide);
        directions_guide.put("L",L_guide);
        directions_guide.put("U",U_guide);
        directions_guide.put("D",D_guide);

        //loop to go through all the path segments given
        //This is a lenghty operation due to the number of points in each segment
        for (String seg : path) {
            String direction = seg.substring(0,1);
            int distance = Integer.parseInt(seg.substring(1));
            int[] guide = directions_guide.get(direction);
            for (int i = 0; i<distance;i++){
                currx += guide[0];
                curry += guide[1];
                step += 1;
                int[] tmp_point = {currx,curry};
                for (int j =0;j<points_list.size();j++){
                    int[] point = points_list.get(j);
                    if (Arrays.equals(tmp_point,point)){
                        //add the points and the steps of each location on the path of the wire
                        points_list.add(tmp_point);
                        steps_list.add(step);
                        break;
                    }
                }
            }
        }

        return new calc_points_return(points_list, steps_list);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader;
        //Open the input.tx file to get the mass of the modules
        reader = new BufferedReader(new FileReader(
            "input_Day3.txt"));
        //get the directions for the first wire it is written in a single line
        String line1 = reader.readLine();
        //get the directions for the second wire it is written in a single line
        String line2 = reader.readLine();
        //close the buffer reader
        reader.close();
        // getting the points and steps of both wires from the given segments
        calc_points_return wire1_calc = calc_points(line1.split(","));
        calc_points_return wire2_calc = calc_points(line2.split(","));
        // extracting the points and the steps from the custom class for both wires
        List<int[]>wire1_points = wire1_calc.get_points();
        List<Integer>wire1_steps = wire1_calc.get_steps();
        List<int[]>wire2_points = wire2_calc.get_points();
        List<Integer>wire2_steps = wire2_calc.get_steps();

        List<int[][]> intersection_points = new ArrayList<int[][]>();
        List<Integer> Manhattan_distance = new ArrayList<Integer>();
        // getting the intersection points between the paths of both wires
        // this operation is a lengthy operation due to the large number of points in each wire (around 140,000 points each)
        for (int i =0;i<wire1_points.size();i++){
            int[] point_wire1 = wire1_points.get(i);
            for (int j =0;j<wire1_points.size();j++){
                int[] point_wire2 = wire2_points.get(j);
                if (Arrays.equals(point_wire1,point_wire2)){
                    int[][] tmp_intersection_points = {point_wire1,point_wire2};
                    //adding intersection points and Manhattan distance of each intersection points
                    intersection_points.add(tmp_intersection_points);
                    Manhattan_distance.add(  Math.abs(point_wire1[0]) + Math.abs(point_wire1[1]));
                    break;
                }
            }
        }
        //printing the result for part one of Day 3(minimum manhattan distance)
        print("minimum distance is: "+
            String.valueOf(Manhattan_distance.get(Manhattan_distance.indexOf (Collections.min(Manhattan_distance)))));
        List<Integer> Step = new ArrayList<Integer>();
        //getting the nearest steps from intersection point to the origin
        for (int[][] point : intersection_points ){
            int wire1_step_index = wire1_points.indexOf(point[0]);
            int wire2_step_index = wire2_points.indexOf(point[1]);
            int wire1_step = wire1_steps.get(wire1_step_index);
            int wire2_step = wire2_steps.get(wire2_step_index);
            Step.add(wire1_step+wire2_step);
        }
        //printing the result for part 2 of Day 3 (minimum distance to origin)
        print("minimum steps are: "+String.valueOf(Step.get(Step.indexOf (Collections.min(Step)))));
        
    }
    
}