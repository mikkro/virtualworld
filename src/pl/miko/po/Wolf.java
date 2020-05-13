package pl.miko.po;
class Wolf extends Animal
{
	public Wolf(World world, Coord coord)
	{
		super(world, coord);
		spiece = Spiece.WOLF;
		initiative = 5;
		strength = 9;
	}
}
