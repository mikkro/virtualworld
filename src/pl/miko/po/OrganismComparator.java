package pl.miko.po;
import java.util.*;
class OrganismComparator implements Comparator<Organism> 
{
	@Override
	public int compare(Organism org1, Organism org2)
	{
		if(org1 == null)
			return 1;
		else if(org2 == null)
			return -1;
		else if(org1.getInitiative() == org2.getInitiative())
		{
			if( org1.getAge() > org2.getAge() )
				return -1;
			else
				return 1;
		}
		else if( org1.getInitiative() > org2.getInitiative() )
			return -1;
		else return 1;
	}
}
