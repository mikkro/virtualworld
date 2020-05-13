package pl.miko.po;
import java.util.*;
import java.io.*;
import java.nio.ByteBuffer;
class World
{
	private int organismCount;
	private int size;
	private Organism[] organismsTab;
	private Random random;
	MyFrame frame;

	public World(int size)
	{
		organismCount = 0;
		this.size = size;
		organismsTab = returnNewContainer(size);
		random = new Random();
		frame = new MyFrame(this, size);
	}
	public int getSize()
	{
		return size;
	}
	boolean correctCoord(Coord coord)
	{
		if( coord.getx() >= 0 && coord.gety() >=0 && coord.getx() < size && coord.gety() < size)
			return true;
		else
			return false;
	}
	Organism checkCollision(Coord coord)
	{
		Coord orgCoord;
		for( Organism org : organismsTab )
		{
			if( org == null ) //bedzie posortowana, wiec przejzdzie.
				break;
			else
			{
				orgCoord = org.getCoord();
				if(coord.getx() == orgCoord.getx() && coord.gety() == orgCoord.gety())
					return org;
			}
		}
		return null;
	}
	public void fillWorld()
	{
		final int SPIECES_COUNT = 8;
		boolean allowPlants = false;
		for(int i = 0 ; i < SPIECES_COUNT ; i++)
		{
			for(int j = 0 ; j <= size/2 ; j++ )
			{
				Organism.Spiece spiece =  Organism.Spiece.values()[random.nextInt(SPIECES_COUNT)];
				Coord organismCoord = new Coord();
				do
				{
					organismCoord.setx(random.nextInt(size));
					organismCoord.sety(random.nextInt(size));
				}while( !( correctCoord(organismCoord) && checkCollision(organismCoord) == null ) );
				addOrganism(spiece, organismCoord);
				sortOrganisms();
			}
			if(i > SPIECES_COUNT / 2)
				allowPlants = true;
		}
		paintOrganisms();
	}
	public void paintOrganisms()
	{
		ImagePanel images = frame.getAnimalPanel();
		images.deleteSprites();
		for( Organism org : organismsTab )
			if( org != null )
				images.loadSprites(org);
	}
	public void addOrganism(Organism.Spiece spiece, Coord coord) //Switch z poszczególnymi organizmami
	{
		if(organismCount < size*size)
		{
			Organism organism = null;
			switch(spiece)
			{
				case WOLF : organism = new Wolf(this, coord); break;
				case SHEEP : organism = new Sheep(this, coord); break;
				case SNAIL: organism = new Snail(this, coord); break;
				case LION: organism = new Lion(this, coord); break;
				case GRASS : organism = new Grass(this, coord);break;
				case BACTERIA : organism = new Bacteria(this, coord);break;
				case GUARANA : organism = new Guarana(this, coord); break;
				case DEADLY_NIGHTSHADE: organism = new DeadlyNightshade(this, coord);break;
			}
			if(organism != null)
			{
				organismsTab[organismCount++] = organism;
				sortOrganisms();
			}
		}
	}
	public void addOrganism(Organism newOrganism)
	{
		try
		{
			if(newOrganism != null && organismCount < size*size)
			{
				if(checkCollision(newOrganism.getCoord()) == null && correctCoord(newOrganism.getCoord()))
				{
					organismsTab[organismCount++] = newOrganism;
					sortOrganisms();
				}
			}
		}catch (Exception e) {System.err.print("Nie mozna dodac takiego organizmu!");}
	}
	public Organism extractOrganism(Organism organism)
	{
		int i;
		for( i = 0 ; i < organismCount ; i ++)
			if( organismsTab[i] == organism )
				break;
		for(; i < organismCount-1; i ++)
			organismsTab[i] = organismsTab[i+1];
		organismCount--;
		organismsTab[organismCount] = null;
		return organism;
	}
	public void deleteOrganism(Organism organism)
	{
		extractOrganism(organism);
	}
	public void nextTour()
	{
		for(int i = 0 ; i < organismCount ; i++)
			organismsTab[i].action();
		for(int i = 0 ; i < organismCount ; i++)
			organismsTab[i].increaseAge();
		paintOrganisms();
	}


	public enum Dir
	{
		GORA, GORA_PRAWO, PRAWO, PRAWO_DOL, DOL, DOL_LEWO, LEWO, LEWO_GORA
	}
	Coord move(Dir dir, final Coord originalCoord)
	{
		Coord tmpCoord = new Coord(originalCoord);
		switch(dir)
		{
			case GORA: tmpCoord.sety(tmpCoord.gety() - 1); break;
			case GORA_PRAWO : tmpCoord.sety(tmpCoord.gety() - 1) ; tmpCoord.setx(tmpCoord.getx() + 1); break;
			case PRAWO: tmpCoord.setx(tmpCoord.getx() + 1); break;
			case PRAWO_DOL : tmpCoord.sety(tmpCoord.gety() + 1) ; tmpCoord.setx(tmpCoord.getx() + 1); break;
			case DOL : tmpCoord.sety(tmpCoord.gety() + 1); break;
			case DOL_LEWO : tmpCoord.sety(tmpCoord.gety() + 1); tmpCoord.setx(tmpCoord.getx() - 1); break;
			case LEWO : tmpCoord.setx(tmpCoord.getx() - 1); break;
			case LEWO_GORA: tmpCoord.sety(tmpCoord.gety() - 1); ; tmpCoord.setx(tmpCoord.getx() - 1) ; break;
		}
		return tmpCoord;
	}
	Coord findFreeCoord(final Coord actualCoord)
	{
		Coord newCoord;
		for(int i = 0 ; i < 8 ; i ++)//Sprawdza kolejne kierunki
		{
			newCoord = move(Dir.values()[i], actualCoord);
			if(checkCollision(newCoord) == null && correctCoord(newCoord))
				return newCoord;
		}
		return null;
	}
	public void printOrganisms()
	{
		int i = 0;
		for(Organism org : organismsTab)
		{
			i++;
			System.out.print(i + ": ");
			if(org != null)
				System.out.print( org );
			System.out.println();
		}
	}
	public Random getRandomGenerator()
	{
		return random;
	}
	public Organism[] getOrganismsTab()
	{
		return organismsTab;
	}
	public void printLog(String string)
	{
		frame.printLog(string);
	}
	private Organism[] returnNewContainer(int size)
	{
		return new Organism[size*size];
	}
	public void save(BufferedOutputStream outputStream)
	{
		try
		{
			byte[] bytes = ByteBuffer.allocate(4).putInt(size).array();
			outputStream.write(bytes);
			bytes = ByteBuffer.allocate(4).putInt(organismCount).array();
			outputStream.write(bytes);
			for(Organism organism : organismsTab)
			{
				if(organism != null)
					organism.save(outputStream);
				else
					break;
			}
			outputStream.close();
		}
		catch(Exception e)
		{
			printLog("Błąd przy zapisie do pliku!");
		}
	}

	public void load(BufferedInputStream inputStream)
	{
		try
		{
			byte bytes[] = new byte[4];
			organismCount = 0;
			inputStream.read(bytes);
			ByteBuffer wrapper = ByteBuffer.wrap(bytes);
			size = wrapper.getInt();
			organismsTab = returnNewContainer(size);
			inputStream.read(bytes);
			wrapper = ByteBuffer.wrap(bytes);
			int tmpOrganismCount = wrapper.getInt();
			int tmpAge = 0;
			for(int i = 0 ; i < tmpOrganismCount ; i ++)
			{
				Coord tmpCoord = new Coord();

				Organism.Spiece tmpSpiece = Organism.Spiece.values()[inputStream.read()];
				tmpAge = inputStream.read();
				tmpCoord.setx(inputStream.read());
				tmpCoord.sety(inputStream.read());

				addOrganism(tmpSpiece, tmpCoord);
			}
			paintOrganisms();
		}
		catch (Exception e)
		{
			printLog("Błąd przy wczytywaniu z pliku!");
		}
	}
	private void sortOrganisms()
	{
		OrganismComparator comp = new OrganismComparator();
		Arrays.sort(organismsTab, comp);
	}

}
