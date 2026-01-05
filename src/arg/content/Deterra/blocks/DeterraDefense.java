package arg.content.deterra.blocks;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.defense.*;
import mindustry.world.draw.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.Sounds;
import arg.world.blocks.defense.*;
import arg.content.*;
import arg.content.deterra.DeterraItems;
import mindustry.content.*;

import static mindustry.type.ItemStack.with;

public class DeterraDefense {
    public static Block
            quartzWall,
            renewer;
    public static void load() {
        {
            {
                quartzWall = new ConnectedWall("quartz-wall"){{
                    requirements(Category.defense, with(DeterraItems.quartz, 6));
                    health = 340;
                }};
                renewer = new RegenProjector("renewer"){{
                    requirements(Category.effect, with(DeterraItems.quartz, 30, DeterraItems.magnetite, 30, DeterraItems.polterite, 20));
                    size = 3;
                    health = 400;
                    drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-mid", -1, true), new DrawRegion("-top", 2, true), new DrawPulseShape(false));
                }};
            }
        }
    }
}
