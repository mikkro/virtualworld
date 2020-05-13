package pl.miko.po;
import java.io.*;

abstract class Organism
{
	protected Coord coord;
	protected World world;
	protected int strength;
	protected int initiative;
	protected int age;
	protected boolean sick;
	protected int sicknessTour;
	protected Spiece spiece;

	public Organism(World world, Coord coord)
	{
		this.world = world;
		this.coord = coord;
		sick = false;
		sicknessTour = 5;
		age = 0;
	}
	public Coord getCoord()
	{
		return coord;
	}
	public void setCoord(Coord newCoord)
	{
		this.coord = newCoord;
	}
	public int getStrength()
	{
		return strength;
	}
	public int getInitiative()
	{
		return initiative;
	}
	public int getAge()
	{
		return age;
	}
	public abstract void action();
	public void collision(Organism otherOrg) throws FullException {}
	public void eatGuarana(){};
	public void sickness(boolean isSick){}
	public void whenDefeated(Organism organism) {}
	public void increaseAge()
	{
		age++;
	}
	public Spiece getSpiece()
	{
		return spiece;
	}
	public enum Spiece
	{	
		WOLF, SHEEP, SNAIL, LION, BACTERIA, GRASS, GUARANA, DEADLY_NIGHTSHADE;
		public String toString()
		{
			switch(this)
			{
				case WOLF : return "Wilk";
				case SHEEP : return "Owca";
				case SNAIL: return "Ślimak";
				case LION: return "Lew";
				case BACTERIA : return "Bakteria";
				case GRASS : return "Trawa";
				case GUARANA : return "Guarana";
				case DEADLY_NIGHTSHADE: return "Wilcze jagody";
			}
			return "BRAK";
		}
	}
	public void save(BufferedOutputStream outputStream)
	{
		try
		{
			outputStream.write(spiece.ordinal());
			outputStream.write(age);
			outputStream.write(coord.getx());
			outputStream.write(coord.gety());
		}
		catch(Exception e)
		{
			world.printLog("Błąd przy zapisie do pliku!");
		}
	}
	public String toString()
	{
		return spiece.toString() + "  " + coord.toString();
	}

}
