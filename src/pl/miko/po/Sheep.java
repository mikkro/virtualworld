package pl.miko.po;
class Sheep extends Animal
{
	public Sheep(World world, Coord coord)
	{
		super(world, coord);
		spiece = Spiece.SHEEP;
		initiative = 4;
		strength = 4;
	}
}
