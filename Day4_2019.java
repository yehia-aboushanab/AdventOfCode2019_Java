public class Day4_2019 {
    private static void print(String arg){
        System.out.println(arg);
    }
    private static boolean has_repeated_digits(int num){
        // function to check for repeated numbers in an int
        // the part two is already added that ignores the number that only have repeated digits more than twice
        int i = num;
        
        while (i > 0) {
            int digit1 = i%10;
            i = i/10;
            int digit2 = i%10;
            //check if two adjecent digits are the same
            if (digit1 == digit2){
                int tmp_i = i/10;
                int digit3 = tmp_i%10;
                //check if there is a third adjecent digit is the same
                if (digit2 !=digit3){
                    return true;
                }
                else{
                    i = tmp_i;
                    //loop until either detecting the end of the repeating digits or the digits is finished
                    while (i >0 ){
                        i = i/10;
                        digit3 = i%10;
                        if (digit2!=digit3){
                            break;
                        }
                    }
                    
                }
                
            }
        }
        return false;
    }
    private static boolean digits_check(int num){
        int i = num;
        //function to check that the digits are in decreasing order (right to left)
        while (i > 0){
            int digit1 = i%10;
            i = i/10;
            int digit2 = i%10;
            if (digit2>digit1){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {   
        //counter for the possible passwords
        int possible_password_counter = 0;
        // begining of the range of the password (given in the challenge)
        final int range_begining = 372037;
        // end of the range of the password (given in the challenge)
        final int range_end = 905157;

        //loop through all possible passwords
        for (int i = range_begining ; i < range_end; i++){
            //check if all criteria is in the number
            if (has_repeated_digits(i) && digits_check(i) ){
                //increasing the password counter if possible password is encountered
                possible_password_counter+=1;
            }
        }
        //printing the result
        print ("Number of possible passwords is: "+String.valueOf(possible_password_counter));
        
    }
}