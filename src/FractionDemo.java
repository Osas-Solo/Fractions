import fractions.DenominatorException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class FractionDemo extends Application {

    private Stage window;
    private Scene scene;
    private BorderPane windowContent;

    private HBox northContent;
    static TextField firstFractionTextField;
    static TextField secondFractionTextField;

    private GridPane centreContent;
    static Button addButton;
    static Button subtractButton;
    static Button multiplyButton;
    static Button divideButton;
    static Button raiseToPowerButton;
    static Button negateButton;
    static Button reciprocalButton;
    static Button simplifyButton;
    static Button convertToDecimalButton;
    static Button compareButton;

    static TextArea resultDisplay;

    private Alert errorAlert;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //  initialise window
        window = primaryStage;
        windowContent = new BorderPane();

        //  north content
        northContent = new HBox(20);
        northContent.setAlignment(Pos.CENTER);
        firstFractionTextField = new TextField();
        firstFractionTextField.setPromptText("first fraction");
        secondFractionTextField = new TextField();
        secondFractionTextField.setPromptText("second fraction or power");
        northContent.getChildren().addAll(firstFractionTextField, secondFractionTextField);
        windowContent.setTop(northContent);

        //  centre content
        centreContent = new GridPane();
        centreContent.setAlignment(Pos.CENTER);
        centreContent.setHgap(30);
        centreContent.setVgap(30);
        addButton = new Button("Add Fractions");
        subtractButton = new Button("Subtract Fractions");
        multiplyButton = new Button("Multiply Fractions");
        divideButton = new Button("Divide Fractions");
        raiseToPowerButton = new Button("Raise To Power");
        negateButton = new Button("Negate Fraction");
        reciprocalButton = new Button("To Reciprocal");
        convertToDecimalButton = new Button("Convert To Decimal");
        compareButton = new Button("Compare Fractions");
        simplifyButton = new Button("Simplify Fraction");
        arrangeButtons();
        centreContent.getChildren().addAll( addButton, subtractButton, multiplyButton,
                                            divideButton, raiseToPowerButton, negateButton,
                                            reciprocalButton, convertToDecimalButton, compareButton,
                                            simplifyButton);
        windowContent.setCenter(centreContent);



        //  bottom content
        resultDisplay = new TextArea();
        windowContent.setBottom(resultDisplay);

        //  set scene
        scene = new Scene(windowContent, 1000, 500);
        scene.getStylesheets().add("Style.css");
        window.setScene(scene);
        window.setMaximized(true);
        window.setTitle("Fraction Demo");
        window.show();

        //  set error alert
        errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Input Error");
        errorAlert.setHeaderText("");


        //  set actions
        addButton.setOnAction(e -> {

            try {
                FractionDemoController.addFractions();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                                            "You can also enter a whole number.\n" +
                                            "Ensure that only 1 space is typed between a whole number\n" +
                                            "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of addButton action

        subtractButton.setOnAction(e -> {

            try {
                FractionDemoController.subtractFractions();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of subtractButton action

        multiplyButton.setOnAction(e -> {

            try {
                FractionDemoController.multiplyFractions();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of multiplyButton action

        divideButton.setOnAction(e -> {

            try {
                FractionDemoController.divideFractions();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.\n" +
                                            "A fraction with 0 as its numerator has no reciprocal");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of divideButton action

        raiseToPowerButton.setOnAction(e -> {

            try {
                FractionDemoController.raiseToPower();
            } catch (NumberFormatException e1) {
                errorAlert.setContentText("Only an integer value can be entered as power.");
                errorAlert.showAndWait();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.\n" +
                                            "Or a negative power cannot be applied to\n" +
                                            "a fraction with 0 as it numerator.");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of raiseToPowerButton action

        negateButton.setOnAction(e -> {

            try {
                FractionDemoController.negateFraction();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of negateButton action

        reciprocalButton.setOnAction(e -> {

            try {
                FractionDemoController.toReciprocal();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.\n" +
                                            "A fraction with 0 as its numerator has no reciprocal");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of reciprocalButton action

        simplifyButton.setOnAction(e -> {

            try {
                FractionDemoController.simplify();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of simplifyButton action

        convertToDecimalButton.setOnAction(e -> {

            try {
                FractionDemoController.convertToDecimal();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of convertToDecimalButton action

        compareButton.setOnAction(e -> {

            try {
                FractionDemoController.compareFractions();
            } catch (DenominatorException e1) {
                errorAlert.setContentText("A fraction cannot have 0 as a denominator.");
                errorAlert.showAndWait();
            } catch (Exception e1) {
                errorAlert.setContentText("Please enter a fraction in the form \"a\", \"b/c\" or \"a b/c\".\n" +
                        "You can also enter a whole number.\n" +
                        "Ensure that only 1 space is typed between a whole number\n" +
                        "and a fraction when entering a mixed fraction.");
                errorAlert.showAndWait();
            }

        });  //  end of compareButton action

    }  //  end of start

    private void arrangeButtons() {

        GridPane.setConstraints(addButton, 0, 0);
        GridPane.setConstraints(subtractButton, 1, 0);
        GridPane.setConstraints(multiplyButton, 2, 0);

        GridPane.setConstraints(divideButton, 0, 1);
        GridPane.setConstraints(raiseToPowerButton, 1, 1);
        GridPane.setConstraints(negateButton, 2, 1);

        GridPane.setConstraints(reciprocalButton, 0, 2);
        GridPane.setConstraints(convertToDecimalButton, 1, 2);
        GridPane.setConstraints(compareButton, 2, 2);

        GridPane.setConstraints(simplifyButton, 1, 3);

    }  //  end of arrangeButtons

}  //  end of class
