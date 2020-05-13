package pl.miko.po;
class DeadlyNightshade extends Plant
{
	public DeadlyNightshade(World world, Coord coord)
	{
		super(world, coord);
		spawnProbability = 20;
		strength = 0;
		spiece = Spiece.DEADLY_NIGHTSHADE;
	}

	@Override
	public void whenDefeated(Organism organism)
	{
		world.printLog(organism + " zjada wilcze jagody i zdycha.");
		world.deleteOrganism(organism);
	}
}
