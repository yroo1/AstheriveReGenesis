package arg.content.deterra.blocks;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.Sounds;
import mindustry.content.*;
import arg.world.blocks.distribution.*;
import arg.content.deterra.DeterraItems;

import static mindustry.type.ItemStack.with;

public class DeterraDistribution {
    public static Block
            itemTube, itemOverpass, filter, splitter, itemGate;
    public static void load() {
        {
            {
                itemTube = new ItemTube("item-tube"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 1));
                    speed = 0.065f;
                    displayedSpeed = 9f;
                    bridgeReplacement=itemOverpass;
                    researchCost = with(DeterraItems.quartz, 5);
                }};
                itemOverpass = new ItemOverpass("item-overpass"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 6, DeterraItems.magnetite, 3));
                    range=4;
                    researchCost = with(DeterraItems.quartz, 50,DeterraItems.magnetite,40);
                }};
                filter = new Sorter("filter"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 4, DeterraItems.magnetite, 2));
                    researchCost = with(DeterraItems.quartz, 50,DeterraItems.magnetite,40);
                }};
                splitter = new Router("splitter"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 5));
                    researchCost = with(DeterraItems.quartz, 50);
                }};
                itemGate = new OverflowGate("item-gate"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 4, DeterraItems.magnetite, 2));
                    researchCost = with(DeterraItems.quartz, 50,DeterraItems.magnetite,40);
                }};
            }
        }
    }
}
