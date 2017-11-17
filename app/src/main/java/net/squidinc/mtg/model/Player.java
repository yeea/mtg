package net.squidinc.mtg.model;

/**
 * @author AYee
 *         Player obect contains associated stats (e.g. life, energy, loyalty, poison)
 *         Date: 2017-11-08
 */

public class Player {
  private static final String TAG = "Player";

  private String name = "";
  private int lifeTotal = 20;
  private int energyTotal = 0;
  private int poisonTotal = 0;
  private int loyaltyTotal = 0;

  public Player() {
  }

  public Player( String name, int lifeTotal, int energyTotal, int poisonTotal, int loyaltyTotal ) {
    this.name = name;
    this.lifeTotal = lifeTotal;
    this.energyTotal = energyTotal;
    this.poisonTotal = poisonTotal;
    this.loyaltyTotal = loyaltyTotal;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public int getLifeTotal() {
    return lifeTotal;
  }

  public void setLifeTotal( Integer lifeTotal ) {
    this.lifeTotal = lifeTotal;
  }

  public int getEnergyTotal() {
    return energyTotal;
  }

  public void setEnergyTotal( Integer energyTotal ) {
    this.energyTotal = energyTotal;
  }

  public int getPoisonTotal() {
    return poisonTotal;
  }

  public void setPoisonTotal( Integer poisonTotal ) {
    this.poisonTotal = poisonTotal;
  }

  public int getLoyaltyTotal() {
    return loyaltyTotal;
  }

  public void setLoyaltyTotal( Integer loyaltyTotal ) {
    this.loyaltyTotal = loyaltyTotal;
  }

}
