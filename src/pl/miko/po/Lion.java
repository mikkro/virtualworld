package pl.miko.po;
class Lion extends Animal
{
	public Lion(World world, Coord coord)
	{
		super(world, coord);
		spiece = Spiece.LION;
		initiative = 7;
		strength = 11;
	}
}
