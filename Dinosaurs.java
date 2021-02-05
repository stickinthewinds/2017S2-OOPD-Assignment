/*********************
 * Name: Axel Bremner
 * File Name: Dinosaurs.java
 * Purpose: class for the creation and use of Dinosaurs objects
 * ******************/
// Abstract class which is inherited by the 3 dinosaurs
public abstract class Dinosaurs
{
    // CONSTANTS

    // CLASS FIELDS
    private int enclosureSize;

    // CONSTRUCTORS

    /**************************************************
     * DEFAULT CONSTRUCTOR
     * IMPORT: none
     * EXPORT: address of new Dinosaurs object
     * ASSERTION: Creates the default Dinosaurs object with no imports
     * ***********************************************/
    public Dinosaurs()
    {
        enclosureSize = 0;
    } // end default constructor

    /**************************************************
     * ALTERNATE CONSTRUCTOR
     * IMPORT: inEnclosureSize (integer)
     * EXPORT: address of new Dinosaurs object
     * ASSERTION: Creates the Dinosaurs object when given imports
     * ***********************************************/
    public Dinosaurs(int inEnclosureSize)
    {
        enclosureSize = calcEnclosureSize();
    } // end alternate constructor

    /**************************************************
     * COPY CONSTRUCTOR
     * IMPORT: inDinosaurs (Dinosaurs)
     * EXPORT: address of new Dinosaurs object
     * ASSERTION: creates a copy of an imported dinosaurs object by calling the get methods of the Dinosaurs class
     * ***********************************************/
    public Dinosaurs(Dinosaurs inDinosaurs)
    {
        enclosureSize = inDinosaurs.getEnclosureSize();
    } // end copy constructor

    // MUTATORS

    /**************************************************
     * SUBMODULE: setEnclosureSize
     * IMPORT: inEnclosureSize (integer)
     * EXPORT: none
     * ASSERTION: sets the enclosureSize of the dinosaur to the imported value
     * ************************************************/
    public void setEnclosureSize(int inEnclosureSize)
    {
        enclosureSize = inEnclosureSize;
    } // end setEnclosureSize

    // ACCESSORS
    // Get method returns a copy of the enclosureSize
    public int getEnclosureSize()
    {
        return enclosureSize;
    }

    /***************************************************
     * SUBMODULE: equals
     * IMPORT: inObj (object)
     * EXPORT: same (boolean)
     * ASSERTION: will take in imported object, check if it can be converted to a dinosaurs, convert it if possible and then compare the original object to the converted object to see if the values are the same
     * *************************************************/
    public boolean equals(Object inObj)
    {
        boolean same = false;
        Dinosaurs inDinosaurs = null;
        if (inObj instanceof Dinosaurs)
        {
            inDinosaurs = (Dinosaurs) inObj;
            same = (enclosureSize == inDinosaurs.getEnclosureSize());
        }
        return same;
    } // end equals

    // creates a string for the values of the dinosaurs object.
    // No imports. Exports the string created.
    public String toString()
    {
        return ("");
    }

    // ABSTRACT ACCESSORS
    // abstract method takes no imports, creates a clone of Dinosaurs object exports the clone
    public abstract Dinosaurs clone();

    // abstract method takes no imports, exports integer enclosureSize
    public abstract int calcEnclosureSize();
} // end Dinosaurs
