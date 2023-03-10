package no.ntnu.idatx2001.oblig4;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for a deck of cards.
 */
public class DeckOfCardsTest 
{
    @Before
    public void setUp() {
        // Test before
    }


    /**
     * Rigorous Test :-)
     */
    @Test
    public void checkForDuplicates()
    {
        DeckOfCards deck = new DeckOfCards();
        Collection<PlayingCard> hand = deck.dealHand(10);
        Set<PlayingCard> set = new HashSet<>(hand);
        System.out.println(hand.size());

        if (hand.size() != set.size()) {
            assertTrue("Duplicate cards found", false);
        } else {
            assertTrue("No duplicates", true);
        }
    }

    @Test
    public void checkNumberOfCardsInHand() {
        DeckOfCards deck = new DeckOfCards();   
        Collection<PlayingCard> hand = deck.dealHand(52);
        assertEquals(52, hand.size());
    }

    
}
