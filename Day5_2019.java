import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day5_2019 {
    private static void print(String arg){
        System.out.println(arg);
    }
    private static void incode_output(String[] input) throws IOException {
        //function to process the opcode and get the op4utput from a string array
        int i = 0;
        while (i<input.length){
            //get the opcode and parameter modes
            String opcode_parameter = input[i];
            int opcode=0;
            int mode_1st_parameter = 0;
            int mode_2st_parameter = 0;
            int mode_3st_parameter = 0;
            if (opcode_parameter.length() !=1){
                opcode = Integer.parseInt(opcode_parameter.substring(opcode_parameter.length()-2));
                //have to check the length of the string before processing to avoid exceptions
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
            //if opcode is 99 the loop breaks and the processing is finished
            if(opcode == 99){
                break;
            }
            int value1=0;
            int value1_location =0 ;
            int value2=0;
            int value2_location =0 ;
            int return_location =0 ;
            int return_value=0;
            //getting the 1st value and the 2nd value according to the opcode and the modes
            if ((opcode >= 1 && opcode <= 2) || (opcode >= 5 && opcode <=8)){
                if (mode_1st_parameter == 0){
                    value1_location = Integer.parseInt(input[i+1]);
                    value1 = Integer.parseInt(input[value1_location]);
                }
                else{
                    value1_location = i+1;
                    value1 = Integer.parseInt(input[value1_location]);
                }
            }
            
            if ((opcode >=1 && opcode <=2)|| (opcode >= 5 && opcode <=8)){
                if (mode_2st_parameter == 0){
                    value2_location = Integer.parseInt(input[i+2]);
                    value2 = Integer.parseInt(input[value2_location]);
                }
                else{
                    value2_location = i+2;
                    value2 = Integer.parseInt(input[value2_location]);
                }
            }
            // calculating the result value according to the opcode
            if (opcode == 1){
                
                return_value = value1+value2;
                
            }
            else if (opcode == 2){
                return_value = value1*value2;

            }
            else if (opcode == 3){
                //opcode 3 requires the user to input a system ID according to the chcallenge
                print("opcode require input please enter the correct value");
                BufferedReader reader =
                   new BufferedReader(new InputStreamReader(System.in));
                String input_value = reader.readLine();
                // inserting the return value in the correct location according to the return location
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
                //mode 4 outputs the result of a certain test or the result of the full opcode
                if (mode_1st_parameter == 0){
                    return_location = Integer.parseInt(input[i+1]);
                    print("output: "+ input[return_location]);
                }
                else{
                    return_location = i+1;
                    print("output: "+ input[return_location]);
                }
            }
            //opcode 5 and 6 has the possibility to minpulate the loop counter 
            //therefore it can skip the remaining code in the loop if the counter is minpulated
            else if (opcode == 5){
                if (value1 != 0 ){
                    i = value2;
                    continue;
                }
            }
            else if (opcode == 6){
                if (value1 ==0){
                    i=value2;
                    continue;
                }
            }
            else if (opcode == 7){
                if (value1 <value2){
                    return_value=1;
                }
                else{
                    return_value=0;
                }
            }
            else if (opcode == 8){
                if (value1==value2){
                    return_value=1;
                }
                else{
                    return_value=0;
                }
            }
            // inserting the return value according to the return location, opcode and mode of the parameter
            if ((opcode >=1 && opcode <=2)|| (opcode >=7 && opcode <=8)){
                if (mode_3st_parameter == 0){
                    return_location = Integer.parseInt(input[i+3]);
                    input[return_location] = String.valueOf(return_value);
                }
                else{
                    return_location = i+3;
                    input[return_location] = String.valueOf(return_value);
                }
            } 
            //incrementing the loop counter according to the opcode
            if ((opcode >=1 && opcode <=2)|| (opcode >=7 && opcode <=8)){
                i = i+4;

            }
            else if (opcode == 5 || opcode == 6){
                i=i+3;
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
            //convert the data from the file to an array for easier processing
            String[] input_array = line.split(","); 
            //the output of the opcode will be printed to the console
            incode_output(input_array);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
  }  
}