package no.ntnu.idatx2001.oblig4;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a deck of cards. A deck of cards consists of 52 cards, 13 cards of each suit.
 * 
 * Suit: 'S'=spade ♠, 'H'=heart ♥, 'D'=diamonds ♦, 'C'=clubs ♣
 * 
 * @author Stian Lyng Stræte
 * @version 2023-03-10
 */
public class DeckOfCards {
    
    private final char[] suit = { 'S', 'H', 'D', 'C' };
    private final int[] face = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
    private PlayingCard[] deck = new PlayingCard[52];

    /**
     * Creates an instance of a DeckOfCards.
     */
    public DeckOfCards() {
        generateDeck();
    }

    /**
     * Generates a deck of cards.
     */
    private void generateDeck() {
        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < face.length; j++) {
                PlayingCard card = new PlayingCard(suit[i], face[j]);
                deck[i * 13 + j] = card;
            }
        }
    }

    public Collection<PlayingCard> dealHand(int numberOfCards) {

        Collection<PlayingCard> randomNumbers = new ArrayList<>();

        while (randomNumbers.size() < numberOfCards) {
            int random = (int) (Math.random() * 52);
            if (!randomNumbers.contains(deck[random])) {
                randomNumbers.add(deck[random]);
            }
        }  

        return randomNumbers;
    }

    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();
        Collection<PlayingCard> hand = deck.dealHand(52);
        for (PlayingCard playingCard : hand) {
            System.out.println(playingCard.getAsString());
        }    
    }
}
