package net.squidinc.mtg.model;

/**
 * @author AYee
 *         Keeps track of the details of a game instance (game type / default life totat, number of players)
 *         Date: 2017-11-12
 */

public class Game {
  private int numPlayers = 2;
  private int defaultLifeTotal = 20;

  public int getNumPlayers() {
    return numPlayers;
  }

  public void setNumPlayers( int numPlayers ) {
    this.numPlayers = numPlayers;
  }

  public int getDefaultLifeTotal() {
    return defaultLifeTotal;
  }

  public void setDefaultLifeTotal( int defaultLifeTotal ) {
    this.defaultLifeTotal = defaultLifeTotal;
  }
}
