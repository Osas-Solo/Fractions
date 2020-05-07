package fractions;

import java.util.ArrayList;

/**
 * The {@code Fraction} class represents parts of a whole number
 */
public class Fraction {

    private double numerator;
    private double denominator;

    private double getNumerator() {
        return numerator;
    }

    private void setNumerator(double numerator) {
        this.numerator = numerator;
    }

    private double getDenominator() {
        return denominator;
    }

    private void setDenominator(double denominator) {
        if (denominator < 0) {  //  place sign in proper position
            this.numerator *= -1;
            this.denominator = denominator * -1;
        } else {
            this.denominator = denominator;
        }
    }

    /**
     *  Initialises a {@code Fraction} object to {@code 0}
     */
    public Fraction() {

        setNumerator(0);
        setDenominator(1);

    }

    /**
     * Initialises a {@code Fraction} object to represent a whole number
     *
     * @param numerator     a whole
     */
    public Fraction(double numerator) {

        setNumerator(numerator);
        setDenominator(1);

        removeDecimalPoints();
        simplify();

    }

    /**
     * Initialises a {@code Fraction} object to represent a proper or improper fraction
     *
     * @param numerator     part(s) of a whole
     * @param denominator   a whole
     *
     * @throws DenominatorException     if the denominator parsed is {@code 0}
     */
    public Fraction(double numerator, double denominator)
                    throws DenominatorException {

        if (denominator == 0) {
            throw new DenominatorException();
        } else if (numerator == 0) {
            setNumerator(numerator);
            setDenominator(1);
        } else {
            setNumerator(numerator);
            setDenominator(denominator);
        }

        removeDecimalPoints();
        simplify();

    }

    /**
     * Initialises a {@code Fraction} object to represent a mixed fraction
     *
     * @param wholeNumber   the wholes of the fraction
     * @param numerator     the remainder of the wholes of the fraction
     * @param denominator   a whole
     *
     * @throws DenominatorException     if the denominator parsed is {@code 0}
     */
    public Fraction(double wholeNumber, double numerator, double denominator)
                    throws DenominatorException {

        Fraction firstFraction = new Fraction(wholeNumber);
        Fraction secondFraction = new Fraction(numerator, denominator);
        Fraction resultFraction;

        if (secondFraction.numerator < 0) {

            firstFraction.setNumerator(firstFraction.numerator * -1);
            secondFraction.setNumerator(secondFraction.numerator * -1);

        }

        if (firstFraction.numerator >= 0) {

            resultFraction = addFractions(firstFraction, secondFraction);

        } else {

            resultFraction = subtractFractions(firstFraction, secondFraction);

        }

        setNumerator(resultFraction.numerator);
        setDenominator(resultFraction.denominator);

    }

    /**
     * Initialises a {@code Fraction} object to copy another fraction
     *
     * @param fraction      the {@code Fraction} to be copied
     */
    public Fraction(Fraction fraction) {

        setNumerator(fraction.getNumerator());
        setDenominator(fraction.getDenominator());

    }

    /**
     * Breaks a {@code Fraction} numerator and denominator to their smallest forms without a common factor
     */
    public void simplify() {

        while (!isSimplified()) {

            double simplifier = Math.min(Math.abs(numerator), Math.abs(denominator));

            for (int i = (int) simplifier; i > 1; i--) {

                if (numerator % i == 0 && denominator % i == 0) {

                    numerator /= i;
                    denominator /= i;
                    break;

                }  //  end of if

            }  //  end of for

        }  //  end of while

    }  //  end of simplify()

    /**
     * Returns true or false depending on if the numerator and denominator of the {@code Fraction}
     * has a common factor
     *
     * @return true if the the numerator and denominator of the {@code Fraction} have a common factor,
     * false otherwise
     */
    public boolean isSimplified() {

        double simplifier = Math.min(Math.abs(numerator), Math.abs(denominator));

        for (int i = (int)simplifier; i > 1; i--) {

            if (numerator % i == 0 && denominator % i == 0) {

                return false;

            }  //  end of if

        }  //  end of for

        return true;

    }  //  end of isSimplified()

    /**
     * Returns a string representation of the fraction
     *
     * @return a string in the form "<b>numerator / denominator</b>". Special case:
     * <ul>
     *     <li>If the denominator is equal to 1, only the numerator is returned</li>
     * </ul>
     */
    @Override
    public String toString() {

        if (denominator == 1) {
            return String.format("%.0f", numerator);
        }

        return String.format("%.0f/%.0f", numerator, denominator);

    }  //  end of toString()

    private void removeDecimalPoints() {

        while (Math.abs(numerator) != Math.floor(Math.abs(numerator))
                || Math.abs(denominator) != Math.floor(Math.abs(denominator))) {

            numerator *= 10;
            denominator *= 10;

        }  //  end of while

    }  //  end of removeDecimalPoints()

    /**
     * Returns the result of the addition of two fractions
     *
     * @param firstFraction     a fraction to be added
     * @param secondFraction    another fraction to be added
     *
     * @return a {@code Fraction} that is the result of the addition of two fractions
     *
     * @throws DenominatorException     if a denominator parsed is {@code 0}
     */
    public static Fraction addFractions(Fraction firstFraction, Fraction secondFraction)
                                        throws DenominatorException {

        double resultNumerator, resultDenominator;

        resultDenominator = firstFraction.denominator * secondFraction.denominator;
        resultNumerator = (firstFraction.numerator * secondFraction.denominator)
                        + (secondFraction.numerator * firstFraction.denominator);

        return new Fraction(resultNumerator, resultDenominator);

    }  //  end of addFractions()

    /**
     * Returns the result of the subtraction of two fractions
     *
     * @param firstFraction     the minuend
     * @param secondFraction    the subtrahend
     *
     * @return a {@code Fraction} that is the result of the subtraction of two fractions
     *
     * @throws DenominatorException     if a denominator parsed is {@code 0}
     */
    public static Fraction subtractFractions(Fraction firstFraction, Fraction secondFraction)
                                            throws DenominatorException {

        Fraction secondFractionCopy = negateFraction(secondFraction);

        return addFractions(firstFraction, secondFractionCopy);

    }  //  end of subtractFractions()

    /**
     * Returns the result of the multiplication of two fractions
     *
     * @param firstFraction     the multiplicand
     * @param secondFraction    the multiplier
     *
     * @return a {@code Fraction} that is the result of the multiplication of two fractions
     *
     * @throws DenominatorException     if a denominator parsed is {@code 0}
     */
    public static Fraction multiplyFractions(Fraction firstFraction, Fraction secondFraction)
                                            throws DenominatorException {

        double resultNumerator, resultDenominator;

        resultNumerator = firstFraction.numerator * secondFraction.numerator;
        resultDenominator = firstFraction.denominator * secondFraction.denominator;

        return new Fraction(resultNumerator, resultDenominator);

    }  //  end of multiplyFractions()

    /**
     * Returns the result of the division of two fractions
     *
     * @param firstFraction     the dividend
     * @param secondFraction    the divisor
     *
     * @return a {@code Fraction} that is the result of the division of two fractions
     *
     * @throws DenominatorException     if a denominator parsed is {@code 0}
     */
    public static Fraction divideFractions(Fraction firstFraction, Fraction secondFraction) throws DenominatorException {

        Fraction secondFractionCopy = toReciprocal(secondFraction);

        return multiplyFractions(firstFraction, secondFractionCopy);

    }  //  end of divideFractions()

    /**
     * Returns the result of a fraction raised to the power of the specified integer
     *
     * @param fraction  the base
     * @param power     the exponent
     *
     * @return a {@code Fraction} that is the result of the fraction raised to the
     * power of the specified integer
     *
     * @throws DenominatorException     if a denominator parsed is {@code 0}
     */
    public static Fraction raiseToPower(Fraction fraction, int power) throws DenominatorException {

        double resultDenominator, resultNumerator;

        if (power < 0) {
            power = Math.abs(power);
            Fraction resultFraction = toReciprocal(fraction);
            resultNumerator = Math.pow(resultFraction.numerator, power);
            resultDenominator = Math.pow(resultFraction.denominator, power);
        } else {
            resultNumerator = Math.pow(fraction.numerator, power);
            resultDenominator = Math.pow(fraction.denominator, power);
        }

        return new Fraction(resultNumerator, resultDenominator);

    }  //  end of raiseToPower()

    /**
     * Creates a new {@code Fraction} from a given {@code Fraction} and
     * turns it negative if it was positive and vice versa
     *
     * @param fraction      a {@code Fraction} to copy
     *
     * @return a new {@code Fraction} from {@code fraction} and
     * turns it negative if it was positive and vice versa
     */
    public static Fraction negateFraction(Fraction fraction) {

        Fraction resultFraction = new Fraction(fraction);
        resultFraction.setNumerator(resultFraction.numerator * -1);

        return resultFraction;

    }  //  end of negateFraction()

    /**
     * Creates a new {@code Fraction} from a given {@code Fraction} and
     * interchanges the numerator and denominator
     *
     * @param fraction      a {@code Fraction} to copy
     *
     * @return a new {@code Fraction} from {@code fraction} and
     * interchanges the numerator and denominator
     *
     * @throws DenominatorException     if the numerator is 0
     */
    public static Fraction toReciprocal(Fraction fraction) throws DenominatorException {

        Fraction resultFraction = new Fraction(fraction);

        //  interchange numerator and denominator
        double temp = resultFraction.numerator;
        resultFraction.setNumerator(resultFraction.denominator);
        resultFraction.setDenominator(temp);

        if (resultFraction.denominator == 0) {
            throw new DenominatorException();
        }

        //  place sign in the proper position
        if (resultFraction.denominator < 0) {
            resultFraction.setNumerator(resultFraction.numerator * -1);
            resultFraction.setDenominator(resultFraction.denominator * -1);
        }

        return resultFraction;

    } //  end of toReciprocal()


    /**
     * Returns the result of the division of the numerator by the denominator
     *
     * @return  the result of the division of the numerator by the denominator
     */
    public double convertToDecimal() {

        return numerator / denominator;

    }  //  end of convertToDecimal

    /**
     *
     * Returns  a {@code Fraction} object from a string in a {@code Fraction} format
     *
     * @param fractionString    a string in a fraction format: {@code a, b/c or a b/c}
     *
     * @return  a {@code Fraction} object from the {@code fractionString}
     *
     * @throws Exception    if the {@code fractionString} is in an invalid format
     *                      and if {@code 0 is parsed as denominator}
     */
    public static Fraction convertToFraction(String fractionString) throws Exception{

        ArrayList<String> fractionParts = new ArrayList<>();

        try {
            if (fractionString.contains(" ")) {
                fractionParts.add(fractionString.split(" ", 2)[0]);

                if (fractionString.contains("/")) {
                    fractionParts.add(fractionString.split("/")[0].split(" ")[1]);
                    fractionParts.add(fractionString.split("/")[1]);
                }
            } else {
                fractionParts.add(fractionString.split("/")[0]);

                if (fractionString.contains("/")) {
                    fractionParts.add(fractionString.split("/")[1]);
                }
            }  //  end of ifs to split

            return getFractionFromString(fractionParts);

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new FractionException();
        }   //  end of catches

    }  //  end of convertToFraction()

    private static Fraction getFractionFromString(ArrayList<String> fractionParts) throws DenominatorException {

        final int FRACTION_TYPE = fractionParts.size();
        final int WHOLE_NUMBER = 1;
        final int PROPER_OR_IMPROPER_FRACTION = 2;
        final int MIXED_FRACTION = 3;

        Fraction fraction = new Fraction();

        if (FRACTION_TYPE == WHOLE_NUMBER) {

            double wholeNumber = Double.parseDouble(fractionParts.get(0));
            fraction = new Fraction(wholeNumber);

        } else if (FRACTION_TYPE == PROPER_OR_IMPROPER_FRACTION) {

            double numerator = Double.parseDouble(fractionParts.get(0));
            double denominator = Double.parseDouble(fractionParts.get(1));
            fraction = new Fraction(numerator, denominator);

        } else if (FRACTION_TYPE == MIXED_FRACTION) {

            double wholeNumber = Double.parseDouble(fractionParts.get(0));
            double numerator = Double.parseDouble(fractionParts.get(1));
            double denominator = Double.parseDouble(fractionParts.get(2));
            fraction = new Fraction(wholeNumber, numerator, denominator);

        }  //  end of ifs to create fractions

        return fraction;

    }

    /**
     * Compare two fractions based on their equivalent decimal forms
     *
     * @param firstFraction     main fraction
     * @param secondFraction    compared fraction
     *
     * @return  the value {@code 0} if the fractions are equal,
     *          a value lesser than {@code 0} if the {@code firstFraction} is lesser than the {@code secondFraction}
     *          and a value greater than {@code 0} if the {@code firstFraction} is greater than
     *          the {@code secondFraction}
     */
    public static int compareFractions(Fraction firstFraction, Fraction secondFraction) {

        return Double.compare(firstFraction.convertToDecimal(), secondFraction.convertToDecimal());

    }  //  end of compareFractions()

    /**
     * Indicates if the {@code Fraction} is equal to the {@code otherFraction} or not
     *
     * @param otherFraction    compared fraction
     *
     * @return  true if the {@code Fraction} is equal to the {@code otherFraction}, false if otherwise
     *
     */
    public boolean equals(Fraction otherFraction) {

        return Fraction.compareFractions(this, otherFraction) == 0;

    }  //  end of compareFractions()

}  //  end of class
