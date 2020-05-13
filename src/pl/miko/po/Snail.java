package pl.miko.po;

import java.util.Random;

class Snail extends Animal {

    protected Random random;

    public Snail(World world, Coord coord) {
        super(world, coord);
        spiece = Spiece.SNAIL;
        initiative = 1;
        strength = 1;
        random = new Random();
    }

    @Override
    public void action() {
        // Rusza się w 10% przypadków.
        if (random.nextDouble() > 0.9) {
            super.action();
        }
    }
}
