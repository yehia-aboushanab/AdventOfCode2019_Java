import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day5_2019 {
    private static void print(String arg){
        System.out.println(arg);
    }
    private static void incode_output(String[] input) throws IOException {
        int i = 0;
        while (i<input.length){
            String opcode_parameter = input[i];
            int opcode=0;
            int mode_1st_parameter = 0;
            int mode_2st_parameter = 0;
            int mode_3st_parameter = 0;
            if (opcode_parameter.length() !=1){
                opcode = Integer.parseInt(opcode_parameter.substring(opcode_parameter.length()-2));
                if (opcode_parameter.length() >=3){
                    mode_1st_parameter = Integer.parseInt(opcode_parameter.substring(opcode_parameter.length()-3,opcode_parameter.length()-2));
                }
                if (opcode_parameter.length() >=4){
                    mode_2st_parameter = Integer.parseInt(opcode_parameter.substring(opcode_parameter.length()-4,opcode_parameter.length()-3));
                }
                if (opcode_parameter.length() >=5){
                    mode_3st_parameter = Integer.parseInt(opcode_parameter.substring(opcode_parameter.length()-5,opcode_parameter.length()-4));
                }
            }
            else {
                opcode = Integer.parseInt(opcode_parameter);

            }
            if(opcode == 99){
                break;
            }
            int value1=0;
            int value1_location =0 ;
            int value2=0;
            int value2_location =0 ;
            int return_location =0 ;
            int return_value=0;
            if (opcode != 5 ){
                if (mode_1st_parameter == 0){
                    value1_location = Integer.parseInt(input[i+1]);
                    value1 = Integer.parseInt(input[value1_location]);
                }
                else{
                    value1_location = i+1;
                    value1 = Integer.parseInt(input[value1_location]);
                }
            }
            
            if (opcode <3){
                if (mode_2st_parameter == 0){
                    value2_location = Integer.parseInt(input[i+2]);
                    value2 = Integer.parseInt(input[value2_location]);
                }
                else{
                    value2_location = i+2;
                    value2 = Integer.parseInt(input[value2_location]);
                }
            }
            if (opcode == 1){
                
                return_value = value1+value2;
                
            }
            else if (opcode == 2){
                return_value = value1*value2;

            }
            else if (opcode == 3){
                print("opcode require input please enter the correct value");
                BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));
                String input_value = reader.readLine();
                if (mode_1st_parameter == 0){
                    return_location = Integer.parseInt(input[i+1]);
                    input[return_location] = input_value;
                }
                else{
                    return_location = i+1;
                    input[return_location] = input_value;
                }

            }
            else if (opcode == 4){
                if (mode_1st_parameter == 0){
                    return_location = Integer.parseInt(input[i+1]);
                    print("output: "+ input[return_location]);
                }
                else{
                    return_location = i+1;
                    print("output: "+ input[return_location]);
                }
            }
            
            if (opcode < 3){
                if (mode_3st_parameter == 0){
                    return_location = Integer.parseInt(input[i+3]);
                    input[return_location] = String.valueOf(return_value);
                }
                else{
                    return_location = i+3;
                    input[return_location] = String.valueOf(return_value);
                }
            } 

            if (opcode == 1 || opcode == 2){
                i = i+4;

            }
            else{
                i=i+2;
            }
        }
    }
    public static void main(String[] args) {
    
        BufferedReader reader;
        try {
            //Open the input.tx file to get the mass of the modules
            reader = new BufferedReader(new FileReader(
                    "input_Day5.txt"));
            //all data is in a single line therefore there is no need for a loop to read data
            String line = reader.readLine();
            //close the buffer reader
            reader.close();
            //convert the data from the file to an integer array for easier processing
            String[] input_array = line.split(","); 
            incode_output(input_array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
  }  
}