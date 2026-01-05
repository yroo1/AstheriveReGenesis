package arg.type.unit;

import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;
import arg.content.deterra.DeterraItems;
import arg.graphics.*;

/** Config class for special uwu unit properties. */
public class DeterraUnitType extends UnitType{

    public DeterraUnitType(String name){
        super(name);
        outlineColor = GenesisPal.deterraOutline;
        researchCostMultiplier = 8f;
    }
}
