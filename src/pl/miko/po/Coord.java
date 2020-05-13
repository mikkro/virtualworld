package pl.miko.po;
class Coord
{

	private int y, x;

	public Coord(int argx, int argy)
	{
		x = argx;
		y = argy;
	}
	public Coord() {};
	public Coord(Coord oldCoord)
	{
		this.x = oldCoord.getx();
		this.y = oldCoord.gety();
	} 
	public int getx() { return x; }
	public int gety() { return y; }
	public void setx(int x) { this.x = x; }
	public void sety(int y) { this.y = y; }
	public String toString()
	{
		return "(" + x + "," + y + ") "; 
	}		

};
