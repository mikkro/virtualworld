package pl.miko.po;
class FullException extends Exception
{
	public FullException()
	{
		super("Brak wolnego miejsca na rozmnażanie w okolicy");
	}
}
