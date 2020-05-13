package pl.miko.po;
class Bacteria extends Animal
{
	public Bacteria(World world, Coord coord)
	{
		super(world, coord);
		spiece = Spiece.BACTERIA;
		initiative = 1;
		strength = 1;
	}
	public void fight(Organism organism)
	{
		organism.sickness(true);
		world.printLog(this + " zaraza " + organism );
		world.deleteOrganism(this);
	}
	@Override
	public void whenDefeated(Organism organism)
	{
		fight(organism);
	}
}
