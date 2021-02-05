/***********************
 * Name: Axel Bremner
 *File Name: Triceratops.java
 *Purpose: class for the creation and use of Triceratops objects
 ***********************/
// The class is inheriting from both an abstract class and an interface
public class Triceratops extends Dinosaurs implements IGenome
{
    // CONSTANTS
    public static final int MINNUMHORNS = 3;

    // CLASS FIELDS
    private String name;
    private double mass;
    private String sequence;
    private int numHorns;

    // CONSTRUCTORS

    /*********************************************************
     * DEFAULT CONSTRUCTOR
     * IMPORT: none
     * EXPORT: address of new Triceratops object
     * ASSERTION: Creates the default Triceratops object with no imports
     * *******************************************************/
    public Triceratops()
    {
        super();
        name = "Trikey";
        mass = 52.0;
        sequence = "AGTC";
        numHorns = 3;
        super.setEnclosureSize(calcEnclosureSize());
    } // end default constructor

    /*********************************************************
     * ALTERNATE CONSTRUCTOR
     * IMPORT: inName (string), inMass (real), inSequence (string), inNumHorns (integer)
     * EXPORT: address of new Triceratops object
     * ASSERTION: creates the Triceratops object when given imports, checking first to see if they are valid
     * *******************************************************/
    public Triceratops(String inName, double inMass, String inSequence, int inNumHorns)
    {
        super();
        if (validateName(inName))
        {
            if (validateMass(inMass))
            {
                if (validateSequence(inSequence))
                {
                    if (validateNumHorns(inNumHorns))
                    {
                        name = inName;
                        mass = inMass;
                        sequence = inSequence;
                        numHorns = inNumHorns;
                        super.setEnclosureSize(calcEnclosureSize());
                    }
                    else
                    {
                        throw new IllegalArgumentException("Invalid number of horns. Must be at least 3 and a multiple of 3.");
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
     * IMPORT: inTriceratops (Triceratops)
     * EXPORT: address of new Triceratops object
     * ASSERTION: creates a copy of an imported triceratops object by calling the get methods of the Triceratops class
     * ********************************************************/
    public Triceratops(Triceratops inTriceratops)
    {
        super(inTriceratops);
        name = inTriceratops.getName();
        mass = inTriceratops.getMass();
        sequence = inTriceratops.getSequence();
        numHorns = inTriceratops.getNumHorns();
    } // end copy constructor

    // MUTATORS

    /***********************************************************
     * SUBMODULE: setName
     * IMPORT: inName (string)
     * EXPORT: none
     * ASSERTION: sets the name of the triceratops to the imported value if it is valid
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
     * ASSERTION: sets the mass of the triceratops to the imported value if valid
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
     * ASSERTION: sets the sequence of the triceratops to the imported value if valid
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
     * SUBMODULE: setNumHorns
     * IMPORT: inNumHorns (integer)
     * EXPORT: none
     * ASSERTION: sets the number of horns for the triceratops to the imported value if valid
     * *********************************************************/
    public void setNumHorns(int inNumHorns)
    {
        if (validateNumHorns(inNumHorns))
        {
            numHorns = inNumHorns;
        }
        else
        {
            throw new IllegalArgumentException("Invalid number of horns. Must be at least 3 and a multiple of 3.");
        }
    } // end setNumHorns

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

    public int getNumHorns()
    {
        return numHorns;
    }

    /***********************************************************
     * SUBMODULE: equals
     * IMPORT: inObj (Object)
     * EXPORT: same (boolean)
     * ASSERTION: will take in imported object, check if it can be converted to a triceratops, convert it if possible and then compare the original object to the converted object to see if the values are the same
     * *********************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        Triceratops inTriceratops = null;
        if (inObj instanceof Triceratops)
        {
            inTriceratops = (Triceratops) inObj;
            same = ((super.equals(inTriceratops)) &&
                    (name.equals(inTriceratops.getName())) &&
                    (mass == inTriceratops.getMass()) &&
                    (sequence.equals(inTriceratops.getSequence())) &&
                    (numHorns == inTriceratops.getNumHorns()));
        }
        return same;
    } // end equals

    // Creates a clone of the triceratops and returns it. No imports. Exports a Triceratops
    public Triceratops clone()
    {
        return new Triceratops(this);
    }

    // creates a string for the values of the triceratops object.
    // No imports. Exports the string created.
    public String toString()
    {
        return (super.toString()
                + name
                + "," + mass
                + "," + sequence
                + "," + numHorns);
    } // end toString

    /***********************************************************
     * SUBMODULE: calcEnclosureSize
     * IMPORT: none
     * EXPORT: enclosureSize (integer)
     * ASSERTION: calculates the enclosure size for the triceratops object by doing mass times 5.5 times 4 to the power of numHorns and returns the result as an integer
     * ********************************************************/
    public int calcEnclosureSize()
    {
        return (int) (mass * 5.5 * Math.pow(4, numHorns));
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
        return ((inName.length() > 0) && (inName != null));
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
     * SUBMODULE: validateNumHorns
     * IMPORT: inNumHorns (integer)
     * EXPORT: valid (boolean)
     * ASSERTION: checks that numHorns is both greater than 3(MINNUMHORNS) and a multiple of 3
     * *********************************************************/
    public boolean validateNumHorns(int inNumHorns)
    {
        return ((inNumHorns >= MINNUMHORNS) && (inNumHorns % 3 == 0));
    } // end validateNumHorns
} // end class
