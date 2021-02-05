/*********************
 * Name: Axel Bremner
 * File Name: JurassicPark.java
 * Purpose: main class of the program, runs the menu
 * ******************/
public class JurassicPark
{
    public static void main(String[] args)
    {
        try
        {
            run();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**********************************************************
     * SUBMODULE: run
     * IMPORT: none
     * EXPORT: none
     * ********************************************************/
    public static void run()
    {
        DinosaurBox dinoBox = new DinosaurBox();
        boolean constructed = false;
        int count = 0;
        String optionPrompt = "Enter a number corresponding to the following options:\n1 - Add dinosaur\n2 - Display dinosaurs\n3 - Calculate park size\n4 - Read in a jurassic park file\n5 - Write out to jurassic park file\n6 - Exit";
        int option = User.integerInput(optionPrompt);
        boolean loop = true;
        int maxParkSize = 100;

        do
        {
            switch (option)
            {
                case 1:
                    if (constructed == false)
                    {
                        dinoBox = new DinosaurBox(dinoBox.addDinosaur(dinoBox), dinoBox.getNumDinosaurs());
                        if (dinoBox.getNumDinosaurs() > 0)
                        {
                            constructed = true;
                        }
                    }
                    else if ((dinoBox.getNumDinosaurs()) < maxParkSize)
                    {
                        dinoBox = new DinosaurBox(dinoBox.addDinosaur(dinoBox), dinoBox.getNumDinosaurs());
                    }
                    else
                    {
                        System.out.println("Park is full.");
                    }
                    option = User.integerInput(optionPrompt);
                    break;
                case 2:
                    if (constructed == true)
                    {
                        displayDinosaurs(dinoBox.getDinos());
                    }
                    else
                    {
                        System.out.println("No dinosaurs in park.");
                    }
                    option = User.integerInput(optionPrompt);
                    break;
                case 3:
                    if (constructed == true)
                    {
                        int parkSize = dinoBox.getParkSize();
                        System.out.println("Park size is: " + parkSize);
                    }
                    else
                    {
                        System.out.println("Park size is: 0");
                    }
                    option = User.integerInput(optionPrompt);
                    break;
                case 4:
                    if (constructed == false)
                    {
                        maxParkSize = 1000;
                        String fileName = User.stringInput("Enter a file name to read.");
                        dinoBox = new DinosaurBox(fileName);
                        if (dinoBox.getNumDinosaurs() > 0)
                        {
                            constructed = true;
                        }
                    }
                    else
                    {
                        System.out.println("Can't read from a file. Park has already been constructed.");
                    }
                    option = User.integerInput(optionPrompt);
                    break;
                case 5:
                    dinoBox.writeFile(dinoBox.getDinos());
                    option = User.integerInput(optionPrompt);
                    break;
                case 6:
                    System.out.println("Goodbye");
                    loop = false;
                    break;
                default:
                    option = User.integerInput(optionPrompt);
            } // end option case
        } while (loop);
    } // end run

    /*************************************************************************
     * SUBMODULE: displayDinosaurs
     * IMPORT: dinos (Dinosaurs array)
     * EXPORT: none
     * ASSERTION: loops 3 times through the array to print all the Triceratops then all the Tyrannosaurus then all the Plesiosaur to the user
     * **********************************************************************/
    public static void displayDinosaurs(Dinosaurs[] dinos)
    {
        // loop for printing triceratops
        System.out.println("Triceratops:");
        for (int i = 0; i < dinos.length; i++)
        {
            if (dinos[i] instanceof Triceratops)
            {
                System.out.println(dinos[i].toString());
            }
        }

        // loop for printing tyrannosaurus
        System.out.println("Tyrannosaurus:");
        for (int j = 0; j < dinos.length; j++)
        {
            if (dinos[j] instanceof Tyrannosaurus)
            {
                System.out.println(dinos[j].toString());
            }
        }

        // loop for printing plesiosaur
        System.out.println("Plesiosaur:");
        for (int k = 0; k < dinos.length; k++)
        {
            if (dinos[k] instanceof Plesiosaur)
            {
                System.out.println(dinos[k].toString());
            }
        }
    } // end displayDinosaurs
} // end JurassicPark
