/*********************
 * Name: Axel Bremner
 * File Name: Tyrannosaurus.java
 * Purpose: class for the creation and use of Tyrannosaurus objects
 * ******************/
// The class is inheriting from both an abstract class and an interface
public class Tyrannosaurus extends Dinosaurs implements IGenome
{
    // CONSTANTS
    public static final double MINBITEFORCE = 500.5;

    // CLASS FIELDS
    private String name;
    private double mass;
    private String sequence;
    private double biteForce;

    // CONSTRUCTORS

    /*********************************************************
     * DEFAULT CONSTRUCTOR
     * IMPORT: none
     * EXPORT: address of new Tyrannosaurus object
     * ASSERTION: Creates the default Tyrannosaurus object with no imports
     * *******************************************************/
    public Tyrannosaurus()
    {
        super();
        name = "Tyrone";
        mass = 53.0;
        sequence = "GTC";
        biteForce = 501.0;
        super.setEnclosureSize(calcEnclosureSize());
    } // end default constructor

    /*********************************************************
     * ALTERNATE CONSTRUCTOR
     * IMPORT: inName (string), inMass (real), inSequence (string), inBiteForce (real)
     * EXPORT: address of new Tyrannosaurus object
     * ASSERTION: Creates the Tyrannosaurus object when given imports, checking first to see if they are valid
     * *******************************************************/
    public Tyrannosaurus(String inName, double inMass, String inSequence, double inBiteForce)
    {
        super();
        if (validateName(inName))
        {
            if (validateMass(inMass))
            {
                if (validateSequence(inSequence))
                {
                    if (validateBiteForce(inBiteForce))
                    {
                        name = inName;
                        mass = inMass;
                        sequence = inSequence;
                        biteForce = inBiteForce;
                        super.setEnclosureSize(calcEnclosureSize());
                    }
                    else
                    {
                        throw new IllegalArgumentException("Invalid bite force. Must be greater than 500.5");
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
     * IMPORT: inTyrannosaurus (Tyrannosaurus)
     * EXPORT: address of new Tyrannosaurus object
     * ASSERTION: creates a copy of an imported tyrannosaurus object by calling the get methods of the Tyrannosaurus class
     * ********************************************************/
    public Tyrannosaurus(Tyrannosaurus inTyrannosaurus)
    {
        super(inTyrannosaurus);
        name = inTyrannosaurus.getName();
        mass = inTyrannosaurus.getMass();
        sequence = inTyrannosaurus.getSequence();
        biteForce = inTyrannosaurus.getBiteForce();
    } // end copy constructor

    // MUTATORS

    /***********************************************************
     * SUBMODULE: setName
     * IMPORT: inName (string)
     * EXPORT: none
     * ASSERTION: sets the name of the tyrannosaurus to the imported value if it is valid
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
     * ASSERTION: sets the mass of the tyrannosaurus to the imported value if it is valid
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
     * ASSERTION: sets the sequence of the tyrannosaurus to the imported value if it is valid
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
     * SUBMODULE: setBiteForce
     * IMPORT: inBiteForce (real)
     * EXPORT: none
     * ASSERTION: sets the biteForce of the tyrannosaurus to the imported value if it is valid
     * *********************************************************/
    public void setBiteForce(double inBiteForce)
    {
        if (validateBiteForce(inBiteForce))
        {
            biteForce = inBiteForce;
        }
        else
        {
            throw new IllegalArgumentException("Invalid bite force. Must be greater than 500.5");
        }
    } // end setBiteForce

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

    public double getBiteForce()
    {
        return biteForce;
    }

    /***********************************************************
     * SUBMODULE: equals
     * IMPORT: inObj (Object)
     * EXPORT: same (boolean)
     * ASSERTION: will take in imported object, check if it can be converted to a tyrannosaurus, convert it if possible and then compare the original object to the converted object to see if the values are the same
     * *********************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        Tyrannosaurus inTyrannosaurus = null;
        if (inObj instanceof Tyrannosaurus)
        {
            inTyrannosaurus = (Tyrannosaurus) inObj;
            same = ((super.equals(inTyrannosaurus)) &&
                    (name.equals(inTyrannosaurus.getName())) &&
                    (mass == inTyrannosaurus.getMass()) &&
                    (sequence.equals(inTyrannosaurus.getSequence())) &&
                    (biteForce == inTyrannosaurus.getBiteForce()));
        }
        return same;
    } // end equals

    // Creates a clone of the tyrannosaurus and returns it. No imports. Exports a Tyrannosaurus
    public Tyrannosaurus clone()
    {
        return new Tyrannosaurus(this);
    }

    // creates a string for the values of the tyrannosaurus object.
    // No imports. Exports the string created.
    public String toString()
    {
        return (super.toString()
                + name
                + "," + mass
                + "," + sequence
                + "," + biteForce);
    } // end toString

    /***********************************************************
     * SUBMODULE: calcEnclosureSize
     * IMPORT: none
     * EXPORT: enclosureSize (integer)
     * ASSERTION: calculates the enclosure size for the tyrannosaurus object by doing log2(mass) * biteForce * 10 and returns the result as an integer
     * ********************************************************/
    public int calcEnclosureSize()
    {
        return (int) ((Math.log(mass) / Math.log(2)) * biteForce * 10);
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
     * SUBMODULE: validateBiteForce
     * IMPORT: inBiteForce (real)
     * EXPORT: valid (boolean)
     * ASSERTION: checks that the biteForce is greater than 599.5(MINBITEFORCE)
     * *********************************************************/
    public boolean validateBiteForce(double inBiteForce)
    {
        return (inBiteForce > MINBITEFORCE);
    } // end validateBiteForce
} // end class
