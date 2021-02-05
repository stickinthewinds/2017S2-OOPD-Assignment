/*********************
 * Name: Axel Bremner
 * File Name: Plesiosaur.java
 * Purpose: class for the creation and use of Plesiosaur objects
 * ******************/
// The class is inheriting from both an abstract class and an interface
public class Plesiosaur extends Dinosaurs implements IGenome
{
    // CONSTANTS

    // CLASS FIELDS
    private String name;
    private double mass;
    private String sequence;
    private boolean hasTailFin;

    // CONSTRUCTORS

    /*********************************************************
     * DEFAULT CONSTRUCTOR
     * IMPORT: none
     * EXPORT: address of new Plesiosaur object
     * ASSERTION: Creates the default Plesiosaur object with no imports
     * **************1*****************************************/
    public Plesiosaur()
    {
        super();
        name = "Plankton";
        mass = 54.0;
        sequence = "TC";
        hasTailFin = true;
        super.setEnclosureSize(calcEnclosureSize());
    } // end default constructor

    /*********************************************************
     * ALTERNATE CONSTRUCTOR
     * IMPORT: inName (string), inMass (real), inSequence (string), inHasTailFin (boolean)
     * EXPORT: address of new Plesiosaur object
     * ASSERTION: Creates the Plesiosaur object when given imports, checking first to see if they are valid
     * *******************************************************/
    public Plesiosaur(String inName, double inMass, String inSequence, boolean inHasTailFin)
    {
        super();
        if (validateName(inName))
        {
            if (validateMass(inMass))
            {
                if (validateSequence(inSequence))
                {
                    if (validateHasTailFin(inHasTailFin))
                    {
                        name = inName;
                        mass = inMass;
                        sequence = inSequence;
                        hasTailFin = inHasTailFin;
                        super.setEnclosureSize(calcEnclosureSize());
                    }
                    else
                    {
                        throw new IllegalArgumentException("Invalid has tail fin. Must be true or false.");
                    }
                }
                else
                {
                    throw new IllegalArgumentException("Invalid sequence. Must only contain 'A', 'G', 'T' or 'C'.");
                }
            }
            else
            {
                throw new IllegalArgumentException("Invalid mass. Must not be negative.");
            }
        }
        else
        {
            throw new IllegalArgumentException("Invalid name. Must not be empty.");
        }
    } // end alternate constructor

    /**********************************************************
     * COPY CONSTRUCTOR
     * IMPORT: inPlesiosaur (Plesiosaur)
     * EXPORT: address of new Plesiosaur object
     * ASSERTION: creates a copy of an imported plesiosaur object by calling the get methods of the Plesiosaur class
     * ********************************************************/
    public Plesiosaur(Plesiosaur inPlesiosaur)
    {
        super(inPlesiosaur);
        name = inPlesiosaur.getName();
        mass = inPlesiosaur.getMass();
        sequence = inPlesiosaur.getSequence();
        hasTailFin = inPlesiosaur.getHasTailFin();
    } // end copy constructor

    // MUTATORS

    /***********************************************************
     * SUBMODULE: setName
     * IMPORT: inName (string)
     * EXPORT: none
     * ASSERTION: sets the name of the plseiosaur to the imported value if it is valid
     * *********************************************************/
    public void setName(String inName)
    {
        if (validateName(inName))
        {
            name = inName;
        }
        else
        {
            throw new IllegalArgumentException("Invalid name. Must not be empty.");
        }
    } // end setName

    /***********************************************************
     * SUBMODULE: setMass
     * IMPORT: inMass (real)
     * EXPORT: none
     * ASSERTION: sets the mass of the plesiosaur to the imported value if it is valid
     * *********************************************************/
    public void setMass(double inMass)
    {
        if (validateMass(inMass))
        {
            mass = inMass;
        }
        else
        {
            throw new IllegalArgumentException("Invalid mass. Must not be negative.");
        }
    } // end setMass

    /***********************************************************
     * SUBMODULE: setSequence
     * IMPORT: inSequence (string)
     * EXPORT: none
     * ASSERTION: sets the sequence of the plesiosaur to the imported value if it is valid
     * *********************************************************/
    public void setSequence(String inSequence)
    {
        if (validateSequence(inSequence))
        {
            sequence = inSequence;
        }
        else
        {
            throw new IllegalArgumentException("Invalid sequence. Must only contain 'A', 'G', 'T' or 'C'.");
        }
    } // end setSequence

    /***********************************************************
     * SUBMODULE: setHasTailFin
     * IMPORT: inHasTailFin (boolean)
     * EXPORT: none
     * ASSERTION: sets the hasTailFin of the plesiosaur to the imported value if it is valid
     * *********************************************************/
    public void setHasTailFin(boolean inHasTailFin)
    {
        if (validateHasTailFin(inHasTailFin))
        {
            hasTailFin = inHasTailFin;
        }
        else
        {
            throw new IllegalArgumentException("Invalid has tail fin. Must be true or false.");
        }
    } // end setHasTailFin

    // ACCESSORS
    // Each get method returns a copy of the value
    public String getName()
    {
        return name;
    }

    public double getMass()
    {
        return mass;
    }

    public String getSequence()
    {
        return sequence;
    }

    public boolean getHasTailFin()
    {
        return hasTailFin;
    }

    /***********************************************************
     * SUBMODULE: equals
     * IMPORT: inObj (Object)
     * EXPORT: same (boolean)
     * ASSERTION: will take in imported object, check if it can be converted to a plesiosaur, convert it if possible and then compare the original object to the converted object to see if the values are the same
     * *********************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        Plesiosaur inPlesiosaur = null;
        if (inObj instanceof Plesiosaur)
        {
            inPlesiosaur = (Plesiosaur) inObj;
            same = ((super.equals(inPlesiosaur)) &&
                    (name.equals(inPlesiosaur.getName())) &&
                    (mass == inPlesiosaur.getMass()) &&
                    (sequence.equals(inPlesiosaur.getSequence())) &&
                    (hasTailFin == inPlesiosaur.getHasTailFin()));
        }
        return same;
    } // end equals

    // Creates a clone of the plesiosaur and returns it. No imports. Exports a Plesiosaur
    public Plesiosaur clone()
    {
        return new Plesiosaur(this);
    }

    // creates a string for the values of the plesiosaur object.
    // No imports. Exports the string created.
    public String toString()
    {
        return (super.toString()
                + name
                + "," + mass
                + "," + sequence
                + "," + hasTailFin);
    } // end toString

    /***********************************************************
     * SUBMODULE: calcEnclosureSize
     * IMPORT: none
     * EXPORT: enclosureSize (integer)
     * ASSERTION: calculates the enclosure size for the plesiosaur object by doing mass times PI times 10^3 and returns the result as an integer
     * ********************************************************/
    public int calcEnclosureSize()
    {
        return (int) (mass * Math.PI * Math.pow(10, 3));
    } // end calcEnclosureSize

    // PRIVATE METHODS

    /***********************************************************
     * SUBMODULE: validateName
     * IMPORT: inName (string)
     * EXPORT: valid (boolean)
     * ASSERTION: checks to make sure the name is not empty or null
     * *********************************************************/
    public boolean validateName(String inName)
    {
        return (inName.length() > 0);
    } // end validateName

    /***********************************************************
     * SUBMODULE: validateMass
     * IMPORT: inMass (real)
     * EXPORT: valid (boolean)
     * ASSERTION: checks that the mass is not negative
     * *********************************************************/
    public boolean validateMass(double inMass)
    {
        return (inMass >= 0.0);
    } // end validateMass

    /***********************************************************
     * SUBMODULE: validateSequence
     * IMPORT: inSequence (string)
     * EXPORT: valid (boolean)
     * ASSERTION: checks the sequence only contains the specified characters
     * *********************************************************/
    public boolean validateSequence(String inSequence)
    {
        boolean valid = true;
        for (int i = 0; i < inSequence.length(); i++)
        {
            char tempChar = inSequence.charAt(i);
            if (!((tempChar == 'A') ||
                    (tempChar == 'G') ||
                    (tempChar == 'T') ||
                    (tempChar == 'C')))
            {
                valid = false;
            }
        }
        return valid;
    } // end validateSequence

    /***********************************************************
     * SUBMODULE: validateHasTailFin
     * IMPORT: inHasTailFin (boolean)
     * EXPORT: valid (boolean)
     * ASSERTION: checks the hasTailFin is either true or false
     * *********************************************************/
    public boolean validateHasTailFin(boolean inHasTailFin)
    {
        return ((inHasTailFin = true) || (inHasTailFin = false));
    } // end validateHasTailFin
} // end class
