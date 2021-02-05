/*********************
 * Name: Axel Bremner
 * File Name: DinosaurBox.java
 * Purpose: class for the creation and use of DinosaurBox objects which stores an array of Dinosaurs objects
 * ******************/

import java.io.*;

public class DinosaurBox
{
    // CONSTANTS

    // CLASS FIELDS
    private Dinosaurs[] dinos;
    private int numDinosaurs;
    private int parkSize;

    // CONSTRUCTORS

    /**************************************************
     * DEFAULT CONSTRUCTOR
     * IMPORT: none
     * EXPORT: address of new DinosaurBox object
     * ASSERTION: Creates the default DinosaurBox object with no imports
     * ***********************************************/
    public DinosaurBox()
    {
        dinos = new Dinosaurs[0];
        numDinosaurs = dinos.length;
        parkSize = 0;
    } // end default constructor

    /**************************************************
     * ALTERNATE CONSTRUCTOR
     * IMPORT: fileName (string)
     * EXPORT: address of new DinosaurBox object
     * ASSERTION: Creates the DinosaurBox object when given import of a fileName by calling the getLines and readFile methods to create the array
     * ***********************************************/
    public DinosaurBox(String fileName)
    {
        int lines = getLines(fileName);
        int dinoCount = getDinosaursCount(lines, fileName);
        if (dinoCount > 1000)
        {
            throw new IllegalArgumentException("Can't read from file. Exceeds maximum dinosaurs.");
        }
        dinos = new Dinosaurs[dinoCount];
        readFile(lines, dinos, fileName);
        numDinosaurs = dinoCount;
        parkSize = calcParkSize(dinos);
    } // end alternate constructor

    /**************************************************
     * ALTERNATE CONSTRUCTOR2
     * IMPORT: inDinos (Dinosaurs array), inNumDinosaurs (integer)
     * EXPORT: address of new DinosaurBox object
     * ASSERTION: Creates the DinosaurBox object when given imports by setting the values to the imports
     * ***********************************************/
    public DinosaurBox(Dinosaurs[] inDinosaurs, int inNumDinosaurs)
    {
        dinos = inDinosaurs;
        numDinosaurs = inNumDinosaurs;
        parkSize = calcParkSize(dinos);
    } // end alternate constructor2

    /**************************************************
     * COPY CONSTRUCTOR
     * IMPORT: inDinosaurBox (DinosaurBox)
     * EXPORT: address of new DinosaurBox object
     * ASSERTION: creates a copy of an imported dinosaurBox object by calling the get methods of the DinosaurBox class
     * ***********************************************/
    public DinosaurBox(DinosaurBox inDinosaurBox)
    {
        dinos = inDinosaurBox.getDinos();
        numDinosaurs = inDinosaurBox.getNumDinosaurs();
        parkSize = inDinosaurBox.getParkSize();
    } // end copy constructor

    // MUTATORS

    /**************************************************
     * SUBMODULE: setDinosaurs
     * IMPORT: dinos (Dinosaurs array)
     * EXPORT: none
     * ASSERTION: sets the dinos array values of the DinosaurBox to the imported array values by going through a loop, also checks if it is null or can't be copied
     * ************************************************/
    public void setDinosaurs(Dinosaurs[] dinos)
    {
        if (dinos == null)
        {
            throw new IllegalArgumentException("Invalid array of dinosaurs. Must not be empty");
        }

        Dinosaurs[] dinosCopy = new Dinosaurs[dinos.length];
        // loop checks each object in the array to see if it is an instance of one of the dinosaurs then copies the values from the imported array into the new array if true
        for (int ii = 0; ii < dinos.length; ii++)
        {
            if (dinosCopy[ii] instanceof Triceratops)
            {
                dinosCopy[ii] = new Triceratops((Triceratops) dinos[ii]);
            }
            else if (dinosCopy[ii] instanceof Tyrannosaurus)
            {
                dinosCopy[ii] = new Tyrannosaurus((Tyrannosaurus) dinos[ii]);
            }
            else if (dinosCopy[ii] instanceof Plesiosaur)
            {
                dinosCopy[ii] = new Plesiosaur((Plesiosaur) dinos[ii]);
            }
            else
            {
                throw new IllegalArgumentException("Could not copy array. Invalid objects");
            }
        }
    } // end setDinosaurs

    /**************************************************
     * SUBMODULE: setNumDinosaurs
     * IMPORT: inNumDinosaurs (integer)
     * EXPORT: none
     * ASSERTION: sets the numDinosaurs of the DinosaurBox to the imported value
     * ************************************************/
    public void setNumDinosaurs(int inNumDinosaurs)
    {
        numDinosaurs = inNumDinosaurs;
    } // end setNumDinosaurs

    /**************************************************
     * SUBMODULE: setParkSize
     * IMPORT: inParkSize (integer0
     * EXPORT: none
     * ASSERTION: sets the parkSize of the DinosaurBox to the imported value
     * ************************************************/
    public void setParkSize(int inParkSize)
    {
        parkSize = inParkSize;
    } // end setParkSize

    // ACCESSORS
    // creates temporary array of Dinosaurs ten loops through the stored array and copies the values to the temporary array. exports the new array
    public Dinosaurs[] getDinos()
    {
        Dinosaurs[] temp;
        temp = new Dinosaurs[dinos.length];
        for (int i = 0; i < dinos.length; i++)
        {
            temp[i] = dinos[i].clone();
        }
        return temp;
    }

    // returns a copy of numDinosaurs
    public int getNumDinosaurs()
    {
        return numDinosaurs;
    }

    //returns a copy of parkSize
    public int getParkSize()
    {
        return parkSize;
    }

    /***************************************************
     * SUBMODULE: equals
     * IMPORT: inObj (object)
     * EXPORT: same (boolean)
     * ASSERTION: will take in imported object, check if it can be converted to a DinosaurBox, convert it if possible and then compare the original object to the converted object to see if the values are the same
     * *************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        DinosaurBox inDinosaurBox = null;
        if (inObj instanceof DinosaurBox)
        {
            inDinosaurBox = (DinosaurBox) inObj;
            same = ((dinos.equals(inDinosaurBox.getDinos())) &&
                    (numDinosaurs == inDinosaurBox.getNumDinosaurs()) &&
                    (parkSize == inDinosaurBox.getParkSize()));
        }
        return same;
    } // end equals

    // creates a string for the values of the dinosaurs object.
    // No imports. Exports the string created.
    public String toString()
    {
        String str = "";
        for (int i = 0; i < dinos.length; i++)
        {
            str = str + "\n" + dinos[i];
        }
        str = str + "\nNumber of Dinosaurs: " + numDinosaurs +
                "\nPark size: " + parkSize;
        return str;
    }

    // Creates a clone of the DinosaurBox and returns it. No imports. Exports a DinosaurBox
    public DinosaurBox clone()
    {
        return new DinosaurBox(this);
    }

    ;

    /*******************************************
     * SUBMODULE: addDinosaur
     * IMPORT: dinoBox (DinosaurBox)
     * EXPORT: newDinos (Dinosaurs array)
     * ASSERTION: takes the imported DinosaurBox, gets values for a Dinosaurs object from the user and then creates a new Dinosaurs object with the values, and adds it to a new array of Dinosaurs which is a copy of the old array with 1 larger size
     * *****************************************/
    public Dinosaurs[] addDinosaur(DinosaurBox dinoBox)
    {
        Dinosaurs newDinosaur = null;
        String name, type, sequence;
        double mass;
        boolean loop = true;

        type = User.stringInput("Enter a type of dinosaur. 'TRI', 'TREX' or 'PLESIO'");

        // will loop until user enters one of the types
        do
        {
            if (type.equals("TRI"))
            {
                name = User.stringInput("Enter a name for the triceratops.");
                mass = User.realInput("Enter a mass for the triceratops.");
                sequence = User.stringInput("Enter a uniqe Genome.");
                int numHorns = User.integerInput("Enter a number of horns.");
                try
                {
                    newDinosaur = new Triceratops(name, mass, sequence, numHorns);
                    loop = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else if (type.equals("TREX"))
            {
                name = User.stringInput("Enter a name for the tyrannosaurus.");
                mass = User.realInput("Enter a mass for the tyrannosaurus.");
                sequence = User.stringInput("Enter a uniqe Genome.");
                double biteForce = User.realInput("Enter a number for bite force.");
                try
                {
                    newDinosaur = new Tyrannosaurus(name, mass, sequence, biteForce);
                    loop = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            else if (type.equals("PLESIO"))
            {
                name = User.stringInput("Enter a name for the plesiosaur.");
                mass = User.realInput("Enter a mass for the plesiosaur.");
                sequence = User.stringInput("Enter a uniqe Genome.");
                boolean hasTailFin = User.booleanInput("Does the plesiosaur have a tail fin? True or False.");
                try
                {
                    newDinosaur = new Plesiosaur(name, mass, sequence, hasTailFin);
                    loop = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println(e.getMessage());
                }

            }
            else
            {
                System.out.println("Invalid dinosaur.");
                type = User.stringInput("Enter a type of dinosaur. 'TRI', 'TREX' or 'PLESIO'");
            }
        } while (loop); // end loop

        // check what type of dinosaur the newDinosaur is and create it
        if (newDinosaur instanceof Triceratops)
        {
            Dinosaurs temp = null;
            temp = (Triceratops) newDinosaur;
        }
        else if (newDinosaur instanceof Tyrannosaurus)
        {
            Dinosaurs temp = null;
            temp = (Tyrannosaurus) newDinosaur;
        }
        else if (newDinosaur instanceof Plesiosaur)
        {
            Dinosaurs temp = null;
            temp = (Plesiosaur) newDinosaur;
        }

        // create new Dinosaurs array with length of old array + 1
        Dinosaurs[] newDinos = new Dinosaurs[dinos.length + 1];

        // copies the old arrays values to newDinos
        for (int i = 0; i < dinos.length; i++)
        {
            newDinos[i] = dinos[i];
        }

        // sets final value to the newest dinosaur being added
        newDinos[dinos.length] = (Dinosaurs) newDinosaur;

        numDinosaurs++;
        return newDinos;
    } // end addDinosaur

    /*******************************************************
     * SUBMODULE: getLines
     * IMPORT: fileName (string)
     * EXPORT: numLines (integer)
     * ASSERTION: takes the fileName, reads the file and counts how many lines it has, returning the total count
     * ****************************************************/
    public int getLines(String fileName)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader br;
        int numLines = 0;
        String line;

        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            br = new BufferedReader(rdr);

            line = br.readLine();
            while (line != null)
            {
                numLines = numLines + 1;
                line = br.readLine();
            }
            fileStrm.close();
        } // end try
        catch (FileNotFoundException e)
        {
            System.out.println("Couldn't find your file " + e.getMessage() + " try again!");
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Empty file can't be opened. " + e.getMessage());
        }
        return numLines;
    } // end getLines

    /*******************************************************
     * SUBMODULE: getDinosaursCount
     * IMPORT: lines (int), fileName (string)
     * EXPORT: dinoCount (integer)
     * ASSERTION: takes the fileName, reads the file and counts how many dinosaurs it has, returning the total count
     * ****************************************************/
    public int getDinosaursCount(int lines, String fileName)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader br;
        int dinoCount = 0;
        String line;

        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            br = new BufferedReader(rdr);

            // loops for the amount of lines in the file
            for (int i = 0; i < lines; i++)
            {
                line = br.readLine();
                String[] lineDinosaurs = line.split("/>");

                // loops the lineDinosaurs array for each line adding to the count for each value
                for (int j = 0; j < lineDinosaurs.length; j++)
                {
                    dinoCount++;
                }
            }
            fileStrm.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("");
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Empty file can't be opened. " + e.getMessage());
        }
        return dinoCount;
    } // end getDinosaursCount

    /********************************************************
     * SUBMODULE: readFile
     * IMPORT: lines (integer), dinos (Dinosaurs Array), fileName (string)
     * EXPORT: none
     * ASSERTION: opens the file and goes through all the lines, filling the dinos Array with values
     * *****************************************************/
    public void readFile(int lines, Dinosaurs[] dinos, String fileName)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader br;
        String line;
        int count = 0;
        int type = 0;
        int numHorns;
        String name = new String();
        String sequence = new String();
        double mass, biteForce;

        try
        {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            br = new BufferedReader(rdr);

            // loops for the amount of lines in the file  reading each line and filling the array
            for (int i = 0; i < lines; i++)
            {
                line = br.readLine();
                String[] lineDinosaurs = line.split("/>");

                // loops to get each dinosaur on the line by going through the lineDinosaurs array
                for (int j = 0; j < lineDinosaurs.length; j++)
                {

                    String[] lineArray = lineDinosaurs[j].split(",");
                    String tempType = lineArray[0];

                    if (tempType.equals("<TRI"))
                    {
                        type = 1;
                    }
                    else if (tempType.equals("<TREX"))
                    {
                        type = 2;
                    }
                    else if (tempType.equals("<PLESIO"))
                    {
                        type = 3;
                    }

                    // creates a Dinosaurs depending on the type at lineArray[0]
                    switch (type)
                    {
                        case 1:
                            name = processName(lineDinosaurs[j]);
                            mass = processMass(lineDinosaurs[j]);
                            sequence = processSequence(lineDinosaurs[j]);
                            numHorns = processNumHorns(lineDinosaurs[j]);
                            dinos[count] = new Triceratops(name, mass, sequence, numHorns);
                            count++;
                            break;
                        case 2:
                            name = processName(lineDinosaurs[j]);
                            mass = processMass(lineDinosaurs[j]);
                            sequence = processSequence(lineDinosaurs[j]);
                            biteForce = processBiteForce(lineDinosaurs[j]);
                            dinos[count] = new Tyrannosaurus(name, mass, sequence, biteForce);
                            count++;
                            break;
                        case 3:
                            name = processName(lineDinosaurs[j]);
                            mass = processMass(lineDinosaurs[j]);
                            sequence = processSequence(lineDinosaurs[j]);
                            boolean hasTailFin = processHasTailFin(lineDinosaurs[j]);
                            dinos[count] = new Plesiosaur(name, mass, sequence, hasTailFin);
                            count++;
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid dinosaur type");
                    }
                }
            }
            fileStrm.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("");
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Empty file can't be opened. " + e.getMessage());
        }
    } // end readFile

    /*****************************************************
     * SUBMODULE: processName
     * IMPORT: line (string)
     * EXPORT: string (string)
     * ASSERTION: imports the line, splits the line into an array and returns the 2nd value as a string
     * ***************************************************/
    public String processName(String line)
    {
        String lineArray[] = line.split(",");
        String string = lineArray[1];
        return string;
    } // end processName

    /*****************************************************
     * SUBMODULE: processMass
     * IMPORT: line (string)
     * EXPORT: real (real)
     * ASSERTION: imports the line, splits the line into an array and returns the 3rd value as a real
     * ***************************************************/
    public double processMass(String line)
    {
        String lineArray[] = line.split(",");
        double real = Double.parseDouble(lineArray[2]);
        return real;
    } // end procesMass

    /*****************************************************
     * SUBMODULE: processSequence
     * IMPORT: line (string(
     * EXPORT: string (string)
     * ASSERTION: imports the line, splits the line into an array and returns the 4th value as a string
     * ***************************************************/
    public String processSequence(String line)
    {
        String lineArray[] = line.split(",");
        String string = lineArray[3];
        return string;
    } // end procesSequence

    /*****************************************************
     * SUBMODULE: processNumHorns
     * IMPORT: line (string)
     * EXPORT: number (integer)
     * ASSERTION: imports the line, splits the line into an array and returns the 5th value as an integer
     * ***************************************************/
    public int processNumHorns(String line)
    {
        String lineArray[] = line.split(",");
        int number = Integer.parseInt(lineArray[4]);
        return number;
    } // end processNumHorns

    /*****************************************************
     * SUBMODULE: processBiteForce
     * IMPORT: line (string)
     * EXPORT: real (real)
     * ASSERTION: imports the line, splits the line into an array and returns the 5th value as a real
     * ***************************************************/
    public double processBiteForce(String line)
    {
        String lineArray[] = line.split(",");
        double real = Double.parseDouble(lineArray[4]);
        return real;
    } // end procesBiteForce

    /*****************************************************
     * SUBMODULE: processHasTailFin
     * IMPORT: line
     * EXPORT: hasTailFin
     * ASSERTION: imports the line, splits the line into an array and returns the 5th value as a boolean if it is true or false otherwise FAILS
     * ***************************************************/
    public boolean processHasTailFin(String line)
    {
        boolean hasTailFin;
        String lineArray[] = line.split(",");
        String str = lineArray[4];
        if (str.equals("true") || str.equals("TRUE") || str.equals("True"))
        {
            hasTailFin = true;
        }
        else if (str.equals("false") || str.equals("FALSE") || str.equals("False"))
        {
            hasTailFin = false;
        }
        else
        {
            throw new IllegalArgumentException("Invalid has tail fin.");
        }
        return hasTailFin;
    } // end procesHasTailFin

    /*****************************************************
     * SUBMODULE: writeFile
     * IMPORT: dinos (Dinosaurs array)
     * EXPORT: none
     * ASSERTION: will get the user to select the type of dinosaur to write to file then try to print all dinosaurs of that type in the array to a file named output.csv
     * **************************************************/
    public void writeFile(Dinosaurs[] dinos)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        String fileName = new String();
        fileName = "output.csv";
        String dinosaurOptionPrompt = "Enter a character corresponding to the following options:\n(a) Triceratops\n(b) Tyrannosaurus\n(c) Plesiosaur";
        boolean loop = true;

        try
        {
            // loop until a valid case is selected
            do
            {
                fileStrm = new FileOutputStream(fileName);
                pw = new PrintWriter(fileStrm);
                char dinosaurOption = User.characterInput(dinosaurOptionPrompt);

                switch (dinosaurOption)
                {
                    case 'a':
                    case 'A':
                        for (int i = 0; i < dinos.length; i++)
                        {
                            if (dinos[i] instanceof Triceratops)
                            {
                                pw.println(dinos[i]);
                            }
                        }
                        loop = false;
                        break;
                    case 'b':
                    case 'B':
                        for (int i = 0; i < dinos.length; i++)
                        {
                            if (dinos[i] instanceof Tyrannosaurus)
                            {
                                pw.println(dinos[i]);
                            }
                        }
                        loop = false;
                        break;
                    case 'c':
                    case 'C':
                        for (int i = 0; i < dinos.length; i++)
                        {
                            if (dinos[i] instanceof Plesiosaur)
                            {
                                pw.println(dinos[i]);
                            }
                        }
                        loop = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        dinosaurOption = User.characterInput(dinosaurOptionPrompt);
                }
            } while (loop); // end loop
            pw.close();
            System.out.println("Created file: " + fileName);
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException e2)
                {
                }
            }
            System.out.println("Error in writing to file: " + e.getMessage());
        }
    } // end writeFile

    /*****************************************************
     * SUBMODULE: calcParkSize
     * IMPORT: dinos (Dinosaurs array)
     * EXPORT: parkSize (integer)
     * ASSERTION: takes the imported array and adds all of the enclosure sizes of the dinosaurs
     * ***************************************************/
    public int calcParkSize(Dinosaurs[] dinos)
    {
        // loops through dinos array to add all of them
        for (int i = 0; i < dinos.length; i++)
        {
            parkSize = parkSize + dinos[i].getEnclosureSize();
        }
        return parkSize;
    } // end calcParkSize
} // end DinosaurBox
