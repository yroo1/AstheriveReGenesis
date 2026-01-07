package arg.world.blocks.environment;

import arc.*;
import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.graphics.MultiPacker.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import java.util.*;

import static mindustry.Vars.*;

//no idea why this is here, let it exist
public class GenesisFloor extends Floor{
    public GenesisFloor(String name){
        super(name);
    }
    public GenesisFloor(String name, int variants){
        super(name);
        this.variants = variants;
        placeableLiquid = true;
        allowRectanglePlacement = true;
        instantBuild = true;
        ignoreBuildDarkness = true;
        obstructsLight = false;
        placeEffect = Fx.rotateBlock;
    }
}