/**
 * File name : InputChecker.java
 * Author: Kevin Christian Decena
 * Date created: 07/25/2022
 *
 * Details: This class is used to check the users' inputs specially the dates
 * so it can comply with the programs' requirements and maintain its function
 *
 */



import java.time.LocalDate;
import java.time.format.*;

public class InputChecker{
    public boolean checkDateFormat(String date) {
        try {
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT));
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isNotPast(String date){
        try {
            return LocalDate.now().isBefore(LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT))) || LocalDate.now().isEqual(LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT)));
        } catch (DateTimeParseException e){
            return false;
        }
        }

    public boolean isNumber(String input){
        try {
            if (Integer.parseInt(input) < 0){
                System.out.println("input a positive integer");
                return false;
            }
            return true;
        }catch(NumberFormatException e) {
            System.out.println("input is not an integer value");
            return false;
        }
    }



}//end InputChecker Class
