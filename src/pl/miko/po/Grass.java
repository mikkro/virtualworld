package pl.miko.po;
class Grass extends Plant
{
	public Grass(World world, Coord coord)
	{
		super(world, coord);
		spawnProbability = 20;
		strength = 0;
		spiece = Spiece.GRASS;
	}
}
