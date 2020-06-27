import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Day2_2019 {
    private static void print(String arg){
        System.out.println(arg);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader;
        int total_Fuel=0;
		try {
            //Open the input.tx file to get the mass of the modules
			reader = new BufferedReader(new FileReader(
					"input.txt"));
            String line = reader.readLine();
			while (line != null) {
                int TotalFuelForModule = 0;
                //read each line for every entry each entry is in a seperate line
                print("mass= "+line);
                double mass_D = 0;
                try{
                    mass_D = Double.parseDouble(line);
                }catch (NumberFormatException nfe) {
                    print("invalid number");
                }
                //make the necessary calculation according to the advent of code requirment 
                mass_D = mass_D/3.0;
                int mass_I = 0;
                //round the double division
                mass_I = (int) mass_D;
                mass_I = mass_I -2;
                TotalFuelForModule = mass_I;
                print("required fuel= "+String.valueOf(mass_I));
                //Start calculation for the fuel needed for the added fuel(part 2 of Day 1)
                while (mass_I != 0){
                    mass_D = (double) mass_I / 3.0;
                    mass_I = (int) mass_D;
                    if (mass_I<2){
                        mass_I=0;
                    }
                    if (mass_I !=0){
                        
                        mass_I = mass_I -2;
                        //Adding fuel requirments for each module
                        TotalFuelForModule = TotalFuelForModule+mass_I;
                    }

                }
                //Adding fuel requirements in total including the additional fuel required for part 2
                total_Fuel = total_Fuel+TotalFuelForModule;
                print("Total Fuel= "+String.valueOf(total_Fuel));
				line = reader.readLine();
			}
            reader.close();
            //print the total fuel required 
            print("Total fuel required= "+String.valueOf(total_Fuel));
            
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
}