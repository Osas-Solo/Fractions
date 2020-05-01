package fractions;

public class DenominatorException extends Exception {

    /**
     * Constructs a {@code DenominatorException} with no detailed message
     */
    DenominatorException() {

        super("A fraction cannot have 0 as a denominator");

    }

}  //  end of class
