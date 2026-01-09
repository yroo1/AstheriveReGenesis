package arg.content.deterra.blocks;

import arg.content.deterra.DeterraItems;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.power.*;
import mindustry.world.draw.*;
import arg.world.blocks.power.*;
import mindustry.world.meta.*;
import mindustry.content.*;
import mindustry.gen.*;

import static mindustry.type.ItemStack.with;

public class DeterraPower{
    public static Block
    relay, windTurbine, ventTurbine;

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
            requirements(Category.power, with(DeterraItems.quartz, 25, DeterraItems.magnetite, 20));
            size = 2;
            squareSprite = false;
            powerProduction = 1f/6f + 1f/4f;
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator", 5, true));
            researchCost = with(DeterraItems.quartz,10,DeterraItems.magnetite,10);
        }};
        ventTurbine = new ThermalGenerator("vent-turbine"){{
            requirements(Category.power, with(DeterraItems.quartz, 60, DeterraItems.magnetite,80));
            size = 3;
            attribute = Attribute.steam;
            displayEfficiencyScale = 1f / 9f;
            minEfficiency = 9f - 0.0001f;
            powerProduction = 160f/60f / 9f;
            displayEfficiency = false;
            generateEffect = Fx.turbinegenerate;
            effectChance = 0.04f;
            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.06f;

            drawer = new DrawMulti(new DrawRegion("-bottom"),new DrawRegion("-rotator", 6f,true),new DrawDefault());
            researchCost = with(DeterraItems.quartz,300,DeterraItems.magnetite,400);
        }};
    }
}
