package pl.miko.po;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
class ImagePanel extends JPanel
{
	private World world;
	private int worldSize;
	private Organism.Spiece[][] spiecesOnMap;
	private final BufferedImage[] images;

	public ImagePanel(String[] path, World world, int size)
	{
		super();
		this.world = world;
		images = new BufferedImage[path.length];
		for(int i = 0 ; i < path.length ; i ++)
		{
			File imageFile = new File(path[i]);
			try
			{
				images[i] = ImageIO.read(imageFile);
			}	
			catch ( IOException e )
			{
				System.err.println("Blad odczytu obrazka: " + path[i]);
			}
		}
		worldSize = size;
		System.out.println("Rozmiar: " + size);
		spiecesOnMap = new Organism.Spiece[worldSize][worldSize];
		setForeground(Color.green);
		setBackground(Color.green);
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		System.out.println("Joły");
		Graphics2D g2d = (Graphics2D)g;
		setForeground(Color.red);
		for(int i = 0 ; i <= 20 ; i++)
		{
			g2d.drawLine(0, i*40 , 20 * 40, i*40);
			g2d.drawLine(i*40 , 20 * 40, i*40, 0);
		}
		Coord tmpCoord;
		for( int i = 0 ; i < worldSize ; i ++ )
			for( int j = 0 ; j < worldSize ; j++ )
			{
				if( spiecesOnMap[i][j] != null )
				{
					g2d.drawImage(images[spiecesOnMap[i][j].ordinal()], 40 * i, 40 * j, this); 
				}
			}
	}
	public void deleteSprites()
	{
		for(int i = 0 ; i < worldSize ; i++)
			for(int j = 0 ; j < worldSize ; j++)
			{
				spiecesOnMap[i][j] = null;
			}
	}
	public void loadSprites(Organism organism)
	{
		if(organism != null)
			spiecesOnMap[organism.getCoord().getx()][organism.getCoord().gety()] = organism.getSpiece();
		repaint();
	}

}
