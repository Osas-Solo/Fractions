import fractions.Fraction;

public class FractionDemoController {

    private static Fraction firstFraction;
    private static Fraction secondFraction;

    public static void addFractions() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        Fraction resultFraction = Fraction.addFractions(firstFraction, secondFraction);

        String solution = firstFraction + " + " + secondFraction + " = " + resultFraction + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of addFractions()

    public static void subtractFractions() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        Fraction resultFraction = Fraction.subtractFractions(firstFraction, secondFraction);

        String solution = firstFraction + " - " + secondFraction + " = " + resultFraction + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of subtractFractions()

    public static void multiplyFractions() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        Fraction resultFraction = Fraction.multiplyFractions(firstFraction, secondFraction);

        String solution = firstFraction + " * " + secondFraction + " = " + resultFraction + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of multiplyFractions()

    public static void divideFractions() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        Fraction resultFraction = Fraction.divideFractions(firstFraction, secondFraction);

        String solution = firstFraction + " / " + secondFraction + " = " + resultFraction + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of divideFractions()

    public static void raiseToPower() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        int power = Integer.parseInt(FractionDemo.secondFractionTextField.getText());

        Fraction resultFraction = Fraction.raiseToPower(firstFraction, power);

        String solution = firstFraction + " ^ " + power + " = " + resultFraction + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of raiseToPower()

    public static void negateFraction() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        Fraction resultFraction = Fraction.negateFraction(firstFraction);

        String solution = resultFraction + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of negateFraction()

    public static void toReciprocal() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        Fraction resultFraction = Fraction.toReciprocal(firstFraction);

        String solution = resultFraction + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of toReciprocal()

    public static void simplify() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        Fraction resultFraction;

        if (firstFraction.isSimplified()) {
            resultFraction = firstFraction;
        } else {
            resultFraction = firstFraction;
            resultFraction.simplify();
        }

        String solution = resultFraction + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of simplify()

    public static void convertToDecimal() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        String solution = firstFraction.convertToDecimal() + "\n";
        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of convertToDecimal()

    public static void compareFractions() throws Exception {

        FractionDemo.resultDisplay.clear();
        getTextFieldValues();

        int comparisonResult = Fraction.compareFractions(firstFraction, secondFraction);
        String solution;

        if (comparisonResult == 0) {
            solution = firstFraction + " = " + secondFraction;
        } else if (comparisonResult < 0) {
            solution = firstFraction + " < " + secondFraction;
        } else {
            solution = firstFraction + " > " + secondFraction;
        }

        FractionDemo.resultDisplay.appendText(solution);

    }  //  end of compareFractions()


    private static void getTextFieldValues() throws Exception {

        if (FractionDemo.firstFractionTextField.getText().equals("")) {
            firstFraction = new Fraction();
        } else {
            firstFraction = Fraction.convertToFraction(FractionDemo.firstFractionTextField.getText());
        }  //  end of ifs to get first fraction

        if (FractionDemo.secondFractionTextField.getText().equals("")) {
            secondFraction = new Fraction();
        } else {
            secondFraction = Fraction.convertToFraction(FractionDemo.secondFractionTextField.getText());
        }  //  end of ifs to get second fraction

    }  //  end of getTextFieldValues()

}  //  end of class
