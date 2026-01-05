package arg.content.deterra.blocks;

import arg.content.deterra.DeterraItems;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.power.*;
import mindustry.world.draw.*;
import arg.world.blocks.power.*;

import static mindustry.type.ItemStack.with;

public class DeterraPower{
    public static Block
    relay, windTurbine;

    public static void load(){
        relay = new PowerNode("relay"){{
            requirements(Category.power, with(DeterraItems.quartz, 5));
            health = 20;
            size = 1;
            squareSprite = false;
            laserRange = 10f;
            maxNodes = 8;
            researchCost = with(DeterraItems.quartz,5);
        }};
        windTurbine = new WindTurbine("wind-turbine"){{
            requirements(Category.power, with(DeterraItems.quartz, 20, DeterraItems.magnetite, 20));
            size = 2;
            squareSprite = false;
            powerProduction = (float) 1.0/3;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 5, true));
            researchCost = with(DeterraItems.quartz,20,DeterraItems.magnetite,10);
        }};
    }
}
