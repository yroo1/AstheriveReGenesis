package arg.content.deterra.blocks;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.Sounds;
import mindustry.content.*;
import mindustry.world.draw.*;
import mindustry.type.*;
import arg.world.blocks.production.*;
import arg.content.deterra.DeterraItems;
import arg.content.*;
import arg.graphics.*;

import static mindustry.type.ItemStack.with;

public class DeterraProduction {
    public static Block
    jaggedDrill, cliffBore, clusterDrill,

    polteritePress, nylonSynthesizer;
    public static void load() {
        jaggedDrill = new Drill("jagged-drill"){{
            requirements(Category.production, with(DeterraItems.quartz, 10, DeterraItems.magnetite, 5));
            consumePower(10/60f);
            consumeLiquid(Liquids.hydrogen, 0.05f).boost();

            drillTime = 500;
            tier = 2;
            size = 2;
            fogRadius = 0;
            researchCost = with(DeterraItems.quartz, 10);
        }};
        cliffBore = new CliffDrill("cliff-bore"){{
            requirements(Category.production, with(DeterraItems.quartz, 10, DeterraItems.magnetite, 20));
            consumePower(1/3f);
            consumeLiquid(Liquids.hydrogen, 0.05f).boost();

            drillTime = 280;
            tier = 3;
            size = 2;
            researchCost = with(DeterraItems.quartz, 50, DeterraItems.magnetite, 50);
        }};
        clusterDrill = new ClusterDrill("cluster-drill"){{
            requirements(Category.production, with(DeterraItems.quartz, 20, DeterraItems.magnetite, 15));
            consumePower(0.5f);
            consumeLiquid(Liquids.hydrogen, 0.05f).boost();

            squareSprite = false;
            drillTime = 550;
            tier = 3;
            size = 3;
            fogRadius = 3;
            researchCost = with(DeterraItems.quartz, 10);
            buildVisibility = BuildVisibility.editorOnly;
        }};
        polteritePress = new GenericCrafter("polterite-press"){{
            requirements(Category.crafting, with(DeterraItems.quartz, 20, DeterraItems.magnetite, 15));
            
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawPistons(){{
                sinMag = 2.75f;
                sinScl = 5f;
                sides = 4;
                sideOffset = (float) Math.PI / 2f;
                angleOffset = 45f;
            }}, new DrawDefault());

            size = 2;

            ambientSound = Sounds.loopSmelter;
            ambientSoundVolume = 0.06f;

            researchCostMultiplier = 0.1f;
            consumePower(1/6f);
            consumeItems(with(DeterraItems.magnetite, 1, DeterraItems.carbon, 2));
            craftTime = 150f;
            outputItem = new ItemStack(DeterraItems.polterite, 2);
        }};
        nylonSynthesizer = new GenericCrafter("nylon-synthesizer"){{
            requirements(Category.crafting, with(DeterraItems.quartz, 75, DeterraItems.magnetite, 100, DeterraItems.polterite, 50));
            
            drawer = new DrawMulti(new DrawRegion("-bottom"),  new DrawLiquidTile(GenesisLiquids.tritagen, 0), new DrawPistons(){{
                sinMag = 2f;
                sinScl = 12f;
                sides = 4;
            }}, new DrawDefault(), new DrawGlowRegion(){{
                alpha = 0.5f;
                glowScale = 12f;
                color = GenesisPal.lightNylon;
            }});

            size = 3;

            ambientSound = Sounds.loopSmelter;
            ambientSoundVolume = 0.08f;

            consumeLiquids(LiquidStack.with(GenesisLiquids.tritagen, 16f / 60f));
            liquidCapacity = 30f;
            itemCapacity = 16;

            researchCostMultiplier = 0.1f;
            consumePower(2f);
            consumeItems(with(DeterraItems.quartz, 8, DeterraItems.polterite, 4));
            craftTime = 300f;
            outputItem = new ItemStack(DeterraItems.nylon, 2);
        }};
    }
}
