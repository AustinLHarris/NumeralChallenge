import java.util.*;

public class NumeralParser {
    static Set<Character> numerals;
    private static Map<Character, Integer> numeralValue;

    public NumeralParser() {
        numeralValue = new HashMap<>();
        numeralValue.put('i', 1);
        numeralValue.put('v', 5);
        numeralValue.put('x', 10);
        numeralValue.put('l', 50);
        numeralValue.put('c', 100);
        numeralValue.put('d', 500);
        numeralValue.put('m', 1000);

        numerals = new HashSet<>();
        numerals.add('i');
        numerals.add('v');
        numerals.add('x');
        numerals.add('l');
        numerals.add('c');
        numerals.add('d');
        numerals.add('m');
    }



    public static void main(String[] args) {
        NumeralParser parser = new NumeralParser();
        //args[0] is 'java'
        String userSelection = args[1];

        String userInput = "first";
        while(!userInput.equals("exit")){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter \"exit\" at any time to end program");
            System.out.println("Enter \"V\" to convert to decimal or enter \"5\" to convert to numeral: ");

            userInput= myObj.nextLine();  // Read user input
            if(userInput.equals("V")){
                convertNumeraltoDecimal("first");
            }
            if(userInput.equals("5")) {
                convertDecimalToNumeral("first");
            }
            else{
                System.out.println("Invalid, please enter V or 5");
            }
        }







    }



    private static boolean isValidNumeral(String argString) {
        String adjustedString = argString.toLowerCase();
        StringBuilder builder = new StringBuilder();
        builder.insert(0, adjustedString);
        for (int i = 0; i < builder.length(); i++) {
            char thisChar = builder.charAt(i);
            if (!numerals.contains(thisChar)) {
                return false;
            }
        }
        for (int i = 0; i < builder.length() - 1; i++) {
            char thisChar = builder.charAt(i);
            char nextChar = builder.charAt(i + 1);
            if (numeralValue.get(thisChar) < numeralValue.get(nextChar)) {
                if (numeralValue.get(nextChar) > (10 * numeralValue.get(thisChar))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int parseNumeral(String argString) {
        int total = 0;
        String adjustedString = argString.toLowerCase();
        StringBuilder builder = new StringBuilder();
        builder.insert(0, adjustedString);
        if(builder.length() == 1){
            return numeralValue.get(builder.charAt(0));
        }
        for (int i = 0; i < builder.length() - 1; ++i) {
            boolean secondUsed = false;
            char thisChar = builder.charAt(i);
            char nextChar = builder.charAt(i + 1);
            if(numeralValue.get(thisChar) >= numeralValue.get(nextChar)){
                total += numeralValue.get(thisChar);
            } else{
                secondUsed = true;
                int addValue = numeralValue.get(nextChar) - numeralValue.get(thisChar);
                total += addValue;

            }
            if((i + 1) == builder.length() - 1 && !secondUsed){
                total += numeralValue.get(nextChar);
            }
            if(secondUsed){
                i++;
            }
        }
        return total;
    }

    private static void convertNumeraltoDecimal(String userInput){
        while(!userInput.equals("exit")) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter \"exit\" at any time to end program");
            System.out.println("Enter numeral to parse: ");

            userInput = myObj.nextLine();  // Read user input

            if (isValidNumeral(userInput)) {
                int decimalAnswer = parseNumeral(userInput);
                System.out.println(decimalAnswer);
            } else {
                System.out.println("Error: Please enter a roman numeral");
            }
        }
    }

    private static void convertDecimalToNumeral(String userInput){
        while(!userInput.equals("exit")) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter \"exit\" at any time to end program");
            System.out.println("Enter decimal number to convert: ");

            userInput = myObj.nextLine();  // Read user input
            int userInt = Integer.parseInt(userInput);

            String numeralAnswer = parseDecimal(userInt);
            System.out.println(numeralAnswer);

        }
    }

    private static String parseDecimal(int userInput) {
        StringBuilder builder = new StringBuilder();
        if(userInput >= 1000){
            int mMultiplier = (userInput - (userInput % 1000))/1000;
            builder.append(String.valueOf("M").repeat(Math.max(0, mMultiplier)));
            userInput = userInput % 1000;;
        }
        if(userInput >= 500){
            int dMultiplier = (userInput - (userInput % 500))/500;
            builder.append(String.valueOf("D").repeat(Math.max(0, dMultiplier)));
            userInput = userInput % 500;;
        }
        if(userInput >= 100){
            int cMultiplier = (userInput - (userInput % 100))/100;
            builder.append(String.valueOf("C").repeat(Math.max(0, cMultiplier)));
            userInput = userInput % 100;
        }
        if(userInput >= 50){
            int lMultiplier = (userInput - (userInput % 50))/50;
            builder.append(String.valueOf("L").repeat(Math.max(0, lMultiplier)));
            userInput = userInput % 50;
        }
        if(userInput >= 10){
            int xMultiplier = (userInput - (userInput % 10))/10;
            builder.append(String.valueOf("X").repeat(Math.max(0, xMultiplier)));
            userInput = userInput % 10;
        }
        if(userInput >= 5){
            int vMultiplier = (userInput - (userInput % 5))/5;
            builder.append(String.valueOf("V").repeat(Math.max(0, vMultiplier)));
            userInput = userInput % 5;
        }
        if(userInput >= 1){
            int iMultiplier = userInput;
            builder.append(String.valueOf("I").repeat(Math.max(0, iMultiplier)));
        }
        return String.valueOf(builder);
    }

    private static StringBuilder addNumeralXTimes(char charToAdd, int times, StringBuilder builder){

        return builder;
    }
}

//This code took me 2 hours to write. With final projects due this coming week I felt this was an adequate completion
//of the challenge but given more time I could certainly polish it up more.
