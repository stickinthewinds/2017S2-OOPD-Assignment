/*********************
 * Name: Axel Bremner
 * File Name: User.java
 * Purpose: class for all user interaction (user inputs)
 * ******************/

import java.util.*;

public class User
{
    /*************************************************
     * SUBMODULE: integerInput
     * IMPORT: intPrompt (string)
     * EXPORT: number (integer)
     * ASSERTION: will print intPrompt to the user and take input from the user then convert the input to an integer, check it is above 0 and return the result
     * ***********************************************/
    public static int integerInput(String intPrompt)
    {
        Scanner sc = new Scanner(System.in);
        int number = 0;

        try
        {
            System.out.println(intPrompt);
            number = sc.nextInt();
            while (!(0 <= number))
            {
                System.out.println("Invalid input. Number must not be negative.");
                System.out.println(intPrompt);
                number = sc.nextInt();
            } // assertion: 0 >= number
        } // end try
        catch (InputMismatchException m)
        {
            System.out.println("Invalid input. " + m.getMessage());
        }
        return number;
    } // end integerInput

    /*************************************************
     * SUBMODULE: realInput
     * IMPORT: realPrompt (string)
     * EXPORT: real (real)
     * ASSERTION: will print realPrompt to the user and take input from the user then convert the input to a real, check it is above 0.0 and return the result
     * ***********************************************/
    public static double realInput(String realPrompt)
    {
        Scanner sc = new Scanner(System.in);
        double real = 0.0;

        try
        {
            System.out.println(realPrompt);
            real = sc.nextDouble();
            while (!(0.0 <= real))
            {
                System.out.println("Invalid input. Number must not be negative.");
                System.out.println(realPrompt);
                real = sc.nextDouble();
            } // assertion: 0.0 >= number
        } // end try
        catch (InputMismatchException m)
        {
            System.out.println("Invalid input. " + m.getMessage());
        }
        return real;
    } // end realInput

    /*************************************************
     * SUBMODULE: stringInput
     * IMPORT: prompt (string)
     * EXPORT: str (string)
     * ASSERTION: will print prompt to the user and take input from the user then return the string
     * ***********************************************/
    public static String stringInput(String prompt)
    {
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.println(prompt);
        str = sc.nextLine();

        return str;
    } // end stringInput

    /*************************************************
     * SUBMODULE: booleanInput
     * IMPORT: booleanPrompt (string)
     * EXPORT: answer (boolean)
     * ASSERTION: will print booleanPrompt to the user and take input from the user then convert the input to a boolean by checking if it is true or false and return the result
     * ***********************************************/
    public static boolean booleanInput(String booleanPrompt)
    {
        Scanner sc = new Scanner(System.in);
        String str;
        boolean answer = true;
        boolean loop = true;

        System.out.println(booleanPrompt);
        str = sc.nextLine();

        do
        {
            if ((str.equals("true")) ||
                    (str.equals("TRUE")) ||
                    (str.equals("True")))
            {
                answer = true;
                loop = false;
            }
            else if ((str.equals("false")) ||
                    (str.equals("FALSE")) ||
                    (str.equals("False")))
            {
                answer = false;
                loop = false;
            }
            else
            {
                System.out.println("Invalid has tail fin.");
                System.out.println(booleanPrompt);
                str = sc.nextLine();
            }
        } while (loop);
        return answer;
    } // end booleanInput

    /*************************************************
     * SUBMODULE: characterInput
     * IMPORT: characterPrompt (string)
     * EXPORT: letter (char)
     * ASSERTION: will print characterPrompt to the user and take input from the user then convert the first letter to a character and return the result
     * ***********************************************/
    public static char characterInput(String characterPrompt)
    {
        Scanner sc = new Scanner(System.in);
        String str;

        System.out.println(characterPrompt);
        str = sc.nextLine();
        char letter = str.charAt(0);

        return letter;
    } // end characterInput
} // end class
