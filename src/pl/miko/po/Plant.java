package pl.miko.po;
import java.util.*;
abstract class Plant extends Organism
{
	protected int spawnProbability;

	public Plant(World world, Coord coord)
	{
		super(world, coord);
		this.initiative = 0;
	}
	@Override
	public void action()
	{
		Random rand = world.getRandomGenerator();
		if(rand.nextInt(100) < spawnProbability)
		{
			Coord tmpCoord = world.findFreeCoord(this.coord);
			if(tmpCoord != null)
			{
				world.addOrganism(this.spiece, tmpCoord);
				world.printLog("Roslina " + this + " rozmnozyla sie.");
			}
		}
	}
}
