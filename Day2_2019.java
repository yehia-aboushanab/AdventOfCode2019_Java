import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day2_2019 {
    private static void print(String arg){
        System.out.println(arg);
    }
    private static int incode_output(int[] array_int){
        //function to calculatet the return value of the incode a
        for (int i = 0 ; i<array_int.length;i=i+4){
            int op_code = array_int[i];
            int value_1_location=array_int[i+1];
            int value_2_location=array_int[i+2];
            int return_value_location=array_int[i+3];
            int value_1 = array_int[value_1_location];
            int value_2 = array_int[value_2_location];
            int return_value = 0;
            if (op_code == 1){
                return_value = value_1+value_2;
            }else if(op_code == 2){
                return_value = value_1*value_2;
            } 
            else if(op_code == 99){
                break;
            } 
            array_int[return_value_location] = return_value;
        }
        return array_int[0];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader;
        try {
            //Open the input.tx file to get the mass of the modules
            reader = new BufferedReader(new FileReader(
                    "input_Day2.txt"));
            //all data is in a single line therefore there is no need for a loop to read data
            String line = reader.readLine();
            //convert the data from the file to an integer array for easier processing
            int[] input_array = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray(); 
            int noun = 0;
            int verb = 0;
            //nested loops to check for the correct combination to return the required value
            outerloop:
            for (int i = 0 ; i<input_array.length;i++){
                for (int j = 0; j<input_array.length; j++){
                    input_array = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                    noun=j;
                    verb=i;
                    input_array[1]=noun;
                    input_array[2]=verb;
                    int intcode_value = incode_output(input_array);
                    if (intcode_value == 19690720){
                        //break out of both loops after achieveing the required value
                        break outerloop;
                    }
                } 
            }
            
            print("Noun: "+String.valueOf(noun)+" Verb: "+String.valueOf(verb));
            //calculation of the solution according to the formula provided by the challenge
            int solution = 100 * noun + verb;
            print("Solution: "+String.valueOf(solution));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}