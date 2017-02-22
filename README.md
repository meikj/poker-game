This project aims to implement a poker card game in Java. So far there are implementations for a card and deck, with
stub holders for a hand, player and table.

The idea is that a table is observable by a player, so for example, when the dealer pops a card from the deck the player
can be notified and their hand updated.

Shuffling has been implemented using the Fisher-Yates shuffle (also known as the Knuth shuffle) algorithm in conjunction
with Java SecureRandom for cryptographically strong random number generation.