import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Day6_2019 {
    private static class Orbits{
        private List<String> Object_Name;
        private List<Integer> Number_of_orbits;
        private List<String> Connected_objects;

        public Orbits(){
            this.Object_Name= new ArrayList<String>();
            this.Number_of_orbits = new ArrayList<Integer>();
            this.Connected_objects = new ArrayList<String>();
        }

        public void Add_connected_object(String Main_name , String Connected_Name){
            int object_index = this.Object_Name.indexOf(Main_name);
            this.Connected_objects.set(object_index, Connected_Name);
        }

        public String get_connected_object(String Main_object){
            int object_index = this.Object_Name.indexOf(Main_object);
            return this.Connected_objects.get(object_index);
        }

        public boolean Got_connected_object(String Name){
            int object_index = this.Object_Name.indexOf(Name);
            return (this.Connected_objects.get(object_index) != "");
        }

        public String get_object_Name(int index){
            return this.Object_Name.get(index);
        }

        public int get_lenght_of_Object_List(){
            return this.Object_Name.size();
        }

        public int get_object_index(String Name){
            return this.Object_Name.indexOf(Name);
        }
        public Integer Get_Number_of_orbits(String Name){
            int object_index = this.Object_Name.indexOf(Name);
            return this.Number_of_orbits.get(object_index);
        }
        public Integer Get_Total_number_of_orbits(){
            int Total_numer_of_orbits=0;
            for (int i =0; i<this.Number_of_orbits.size();i++){
                Total_numer_of_orbits = Total_numer_of_orbits+this.Number_of_orbits.get(i);
            }
            return Total_numer_of_orbits;
        }
        public boolean object_exist(String Name){
            return this.Object_Name.contains(Name);
        }

        public void Add_object(String Name){
            this.Object_Name.add(Name);
            this.Number_of_orbits.add(0);
            this.Connected_objects.add("");
        }

        public void Change_orbit_num(String Name,int number){
            int object_index = this.Object_Name.indexOf(Name);
            this.Number_of_orbits.set(object_index, number);
        }
    }

    private static void print(String arg){
        System.out.println(arg);
    }
    public static void main(String[] args) {
        BufferedReader reader;
        Orbits Orbits_collection = new Orbits();
		try {
            //Open the input.tx file to get the mass of the modules
			reader = new BufferedReader(new FileReader(
					"input_Day6.txt"));
            String line = reader.readLine();
			while (line != null) {
                String[] Object_Array = line.split(Pattern.quote(")"));
                for (int i=0 ; i<Object_Array.length;i++){
                    if (!Orbits_collection.object_exist(Object_Array[i])){
                        Orbits_collection.Add_object(Object_Array[i]);
                    }
                }
                Orbits_collection.Add_connected_object(Object_Array[1], Object_Array[0]);
                line = reader.readLine();
            }
            //Solution for Part 1
            // for (int i = 0 ; i <Orbits_collection.get_lenght_of_Object_List();i++){
            //     String Object_Name = Orbits_collection.get_object_Name(i);
            //     int number_of_orbits=0;
            //     while (Orbits_collection.Got_connected_object(Object_Name)){
            //         number_of_orbits = number_of_orbits+1;
            //         int connected_index = Orbits_collection.get_object_index(Orbits_collection.get_connected_object(Object_Name));
            //         Object_Name = Orbits_collection.get_object_Name(connected_index);
            //     }
            //     Object_Name = Orbits_collection.get_object_Name(i);
            //     Orbits_collection.Change_orbit_num(Object_Name, number_of_orbits);

            // }
            // int Total_number_of_orbits = Orbits_collection.Get_Total_number_of_orbits();
            // print("Total number of orbits: "+String.valueOf(Total_number_of_orbits));

            //Solution for Part 2

            String You_connected_object = Orbits_collection.get_connected_object("YOU");
            int orbits_For_object_YOU = 0;
            int orbits_For_object_SAN = 0;
            outerloop:
            while (You_connected_object !=""){
                orbits_For_object_YOU=orbits_For_object_YOU+1;
                String San_Connected_object = Orbits_collection.get_connected_object("SAN");
                orbits_For_object_SAN=0;
                while (San_Connected_object!=""){
                    orbits_For_object_SAN=orbits_For_object_SAN+1;
                    if (You_connected_object.equals(San_Connected_object)){
                        break outerloop;
                    }
                    San_Connected_object = Orbits_collection.get_connected_object(San_Connected_object);
                }
                You_connected_object = Orbits_collection.get_connected_object(You_connected_object);
            }
            int least_number_of_orbits = orbits_For_object_SAN+orbits_For_object_YOU -2;
            print("Least number of orbits that need to be moved: "+String.valueOf(least_number_of_orbits));
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}