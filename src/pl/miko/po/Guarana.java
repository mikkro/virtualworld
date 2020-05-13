package pl.miko.po;
class Guarana extends Plant
{
	public Guarana(World world, Coord coord)
	{
		super(world, coord);
		spawnProbability = 5;
		strength = 0;
		spiece = Spiece.GUARANA;
	}
	@Override
	public void whenDefeated(Organism organism)
	{
		world.printLog(organism + " zjada guarane +3 do sily.");
		organism.eatGuarana();
	}
}
