package mostactivecookie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class most_active_cookie {

    /**
     * find which cookie has the most appearences from validInputs array.
     * @param validInputs the array of cookies and their timestamp
     * @return the cookie seen the most times. ArrayList returned to handle multiple
     *         cookies occuring equal amount of times.
     */
    public static ArrayList<String> getMostActive(ArrayList<String> validInputs){
        // create a HashMap to store cookies and their number of appearances.
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String input : validInputs){
            String currCookie = input.substring(0,16);
            if(map.containsKey(currCookie)){
                Integer value = map.get(currCookie);
                if (value == null)
                    value = 0;
                value++;
                map.put(currCookie, value);
            }else{
                map.put(currCookie, 1);
            }
        }
        // create an array to store the keys with the highest values from the hashmap
        ArrayList<String> mostActive = new ArrayList<String>();
        int maxOccur = 0;
        for (Map.Entry mapElement : map.entrySet()) {
            int value = (int)mapElement.getValue();
            String key = (String)mapElement.getKey();
            if(value == maxOccur){
                mostActive.add(key);
            }else if(value > maxOccur){
                maxOccur = value;
                mostActive.clear();
                mostActive.add(key);
            }
        }
        return mostActive;
    }

    /**
     * create array of cookies that have the same date as param date
     * @param filename the mame of the file of cookies
     * @param date the date that cookies must match
     * @return an arraylist of cookies and their timestamps that match the date
     * @throws FileNotFoundException
     */
    public static ArrayList<String> parseFileForValidDates(String filename, String date) throws FileNotFoundException{
        ArrayList<String> inputsForDate = new ArrayList<String>();
        try{
            Scanner in = new Scanner(new File(filename));
            while(in.hasNextLine()){
                String line = in.nextLine();
                if(line.contains(date)){
                    inputsForDate.add(line);
                }
            }
        }catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not find file: " + filename + ". Please place file in root level.");
        }
        return inputsForDate;
    }

    /**
     * print the most active cookie
     * @param args filename, -d for date format (always -d for now), date
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: most_active_cookie cookie_log.csv -d 2018-12-09");
        }

        String filename = args[0];
        String date = args[2];
        try{
            ArrayList<String> inputsForDate = parseFileForValidDates(filename, date);
            ArrayList<String> mostActive = getMostActive(inputsForDate);
            for(String cookie : mostActive){
                System.out.println(cookie);
            }
        }catch (FileNotFoundException e) {
            System.err.println("Exception caught: "+e);
        }
    }
}
