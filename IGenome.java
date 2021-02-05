/*********************
 * Name: Axel Bremner
 * File Name: IGenome.java
 * Purpose: the interface for Genome which is implemented by the dinosaurs
 * ******************/
public interface IGenome
{
    // CONSTANTS:

    // MUTATORS:
    public void setSequence(String inSequence);

    // ACCESSORS;
    public String getSequence();

    public String toString();

    public boolean validateSequence(String inSequence);
} // end interface
