package pl.miko.po;

import java.util.*;

abstract class Animal extends Organism {

    protected int defaultStrength;
    protected int defaultInitiative;
    protected Random random;

    public Animal(World world, Coord coord) {
        super(world, coord);
        defaultStrength = strength;
        defaultInitiative = initiative;
        random = new Random();
    }

    public void collision(Organism otherOrg) throws FullException {
        if (this.spiece == otherOrg.getSpiece()) {
            spawnNewChild(otherOrg);
        } else //Różne gatunki
            fight(otherOrg);
    }

    private void spawnNewChild(Organism otherOrg) throws FullException {
        Coord childCoord = world.findFreeCoord(this.coord);
        if (childCoord == null)
            childCoord = world.findFreeCoord(otherOrg.getCoord());
        if (childCoord == null)
            throw new FullException();
        else {
            world.printLog("Organizmy " + otherOrg + " oraz " + this + " rozmnozyly sie.");
            world.addOrganism(this.spiece, childCoord);
        }
    }

    protected void fight(Organism otherOrg) {
        if (otherOrg instanceof Snail) {
            if (this.strength < 2 || (this.strength > 4 && random.nextDouble() < 0.6)) {
                action();
                return;
            }
        } else if (otherOrg instanceof Lion) {
            if(this.strength < 5)
                return;
        }
        Organism loser = otherOrg, winner = this;
        if (loser.getStrength() > winner.getStrength()) {
            Organism temp = loser;
            loser = winner;
            winner = temp;
        } else
            winner.setCoord(loser.getCoord());
        loser.whenDefeated(this);
        world.printLog(loser + " przegrywa walke z organizmem " + winner);
        world.deleteOrganism(loser);
    }

    public void action() {
        if (sick) {
            //koniumiakt: zwierze wyzdrowiało
            if (sicknessTour <= 0)
                sickness(false);
            else
                sicknessTour--;
        } else {
            Random rand = world.getRandomGenerator();
            Coord tmpCoord;
            do {
                World.Dir direction = World.Dir.values()[rand.nextInt(8)];
                tmpCoord = world.move(direction, this.coord);
            } while (!world.correctCoord(tmpCoord));
            Organism collident = world.checkCollision(tmpCoord);
            if (collident != null) {
                try {
                    collision(collident);
                } catch (Exception e) {
                    System.out.println("Wyjatek, zwierze nie moglo sie rozmnozyc");
                }
            } else {
                coord = tmpCoord;
                //System.out.println("Zwierze rusza sie");
            }
        }
    }

    public void sickness(boolean isSick) {
        if (isSick) {
            Random random = world.getRandomGenerator();
            defaultInitiative = initiative;
            defaultStrength = strength;
            sick = true;
            boolean what = random.nextBoolean();
            
            int howManyPoints = random.nextInt(4);
            if (what)
                strength -= howManyPoints;
            else
                initiative -= howManyPoints;
        } else {
            initiative = defaultInitiative;
            strength = defaultStrength;
            sick = false;
        }
    }

    public void eatGuarana() {
        this.strength += 3;
    }


}
