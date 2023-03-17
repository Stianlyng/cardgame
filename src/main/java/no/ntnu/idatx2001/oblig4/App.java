package no.ntnu.idatx2001.oblig4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collection;

/**
 * A JavaFX application that displays a deck of cards.
 * The user can click a button to display a new set of cards.
 * The number of cards displayed is set by the numElementsToDisplay variable.
 *
 * @author Stian Lyng Stræte
 * @version 2023-03-11
 */
public class App extends Application {

    private int numElementsToDisplay = 5;
    DeckOfCards deck = new DeckOfCards();
    Collection<PlayingCard> hand;

    private HBox elementsHBox;
    private VBox loremVBox;

    @Override
    public void start(Stage primaryStage) {
        
        // Create a GridPane layout manager
        GridPane root = new GridPane();
        root.setHgap(10); // Set horizontal spacing between columns
        root.setVgap(10); // Set vertical spacing between rows
        root.setPadding(new Insets(10)); // Set padding around content

        // Create an HBox to hold the elements
        elementsHBox = new HBox();
        elementsHBox.setAlignment(Pos.CENTER_LEFT); // Set alignment to center-left

        // Display the first set of elements
        displayElements(numElementsToDisplay);

        // Create an HBox to hold the buttons
        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER); // Set alignment to center
        buttonHBox.setSpacing(10); // Set spacing between buttons

        // Create a button to render new elements
        Button renderButton = new Button("Render " + numElementsToDisplay + " cards");

        // Add an event handler to the button using a lambda expression
        renderButton.setOnAction(event -> {
            displayElements(numElementsToDisplay);
        });

        // Create a button to render some Lorem text
        Button checkButton = new Button("Check hand");

        // Add an event handler to the button using a lambda expression
        checkButton.setOnAction(event -> {

            // get the sum of the faces using streams
            int sum = this.hand.stream()
                    .mapToInt(card -> card.getFace())
                    .sum();
            
            // check for hearts in hand using streams
            long hearts = this.hand.stream()
                    .filter(card -> card.getSuit() == 'H')
                    .count();
    
            // get all hearts in hand using streams
            String heartsString = this.hand.stream()
                .filter(card -> card.getSuit() == 'H')
                .map(card -> card.getAsString())
                .reduce("", (a, b) -> a + " " + b);

            if (heartsString.length() > 0) {
                heartsString = " -" + heartsString;
            }

            // check if the hand is a flush
            boolean isFlush = this.hand.stream()
                    .map(card -> card.getSuit())
                    .distinct()
                    .count() == 1;
            
            // check if the hand is a straight
            boolean isStraight = this.hand.stream()
                    .map(card -> card.getFace())
                    .distinct()
                    .count() == 5;

            // check if the hand contains a queen of spades
            boolean hasQueenOfSpades = this.hand.stream()
                    .anyMatch(card -> card.getFace() == 12 && card.getSuit() == 'S');
            

            Label sumLabel = new Label("Sum of the faces:" + sum + 
                                        "\nHearts: " + hearts + "" + heartsString +
                                        "\nFlush: " + isFlush +
                                        "\nStraight: " + isStraight +
                                        "\nHas Queen of Spades: " + hasQueenOfSpades);
            if (loremVBox != null) {
                loremVBox.getChildren().clear();
            } else {
                loremVBox = new VBox();
                loremVBox.setSpacing(10);
                root.add(loremVBox, 0, 2);
            }
            loremVBox.getChildren().add(sumLabel);
        });

        // Add the buttons to the HBox
        buttonHBox.getChildren().addAll(renderButton, checkButton);

        // Add the HBox and VBox to the GridPane
        root.add(elementsHBox, 0, 0);
        root.add(buttonHBox, 0, 1);

        // Create a scene and add it to the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Set the title and show the stage
        primaryStage.setTitle("List Generator Example");
        primaryStage.show();
    }

    /**
     * Displays a set of elements in the HBox.
     *
     * @param numElements the number of elements to display
     */
    private void displayElements(int numElements) {
        // Clear the HBox
        elementsHBox.getChildren().clear();

        this.hand = this.deck.dealHand(numElements);
        for (PlayingCard card : hand) {
            Label elementLabel = new Label(card.getAsString());
            elementsHBox.getChildren().add(elementLabel);
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}
   
