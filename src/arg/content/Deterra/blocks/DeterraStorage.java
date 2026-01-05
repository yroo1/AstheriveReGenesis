package arg.content.deterra.blocks;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.storage.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.Sounds;
import mindustry.content.*;
import arg.content.deterra.DeterraUnitTypes;
import arg.content.deterra.*;

import static mindustry.type.ItemStack.with;

public class DeterraStorage {
    public static Block
            coreProtocol;
    public static void load() {
        {
            {
                coreProtocol = new CoreBlock("core-protocol"){{
                    requirements(Category.effect, with(DeterraItems.quartz, 700,DeterraItems.magnetite, 500));
                    size = 4;
                    isFirstTier = true;
                    squareSprite = false;
                    itemCapacity = 4000;
                    unitType = DeterraUnitTypes.ward;
                    alwaysUnlocked = true;
                }};
            }
        }
    }
}
