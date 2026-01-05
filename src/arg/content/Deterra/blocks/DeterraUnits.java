package arg.content.deterra.blocks;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.units.*;
import mindustry.world.draw.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.Sounds;
import arg.content.*;
import arg.content.deterra.DeterraItems;
import arg.content.deterra.*;
import mindustry.content.*;

import static mindustry.type.ItemStack.with;

public class DeterraUnits {
    public static Block
            mechAssembler;
    public static void load() {
        {
            {
                mechAssembler = new UnitFactory("mech-assembler"){{
                    requirements(Category.units, with(DeterraItems.quartz, 40, DeterraItems.magnetite,30));
                    plans.add(new UnitPlan(DeterraUnitTypes.saber, 12 * 60f, with(DeterraItems.quartz, 10,DeterraItems.magnetite, 15)));
                    consumePower(60/60f);
                    size = 3;
                    researchCost = with(DeterraItems.quartz,120,DeterraItems.magnetite,80);
                }};
            }
        }
    }
}
