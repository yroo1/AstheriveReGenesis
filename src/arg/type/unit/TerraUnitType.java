package arg.type.unit;

import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;
import arg.content.deterra.DeterraItems;
import arg.graphics.*;
import arg.content.*;
import mindustry.entities.abilities.*;

/** Config class for special uwu unit properties. */
public class TerraUnitType extends UnitType{

    public TerraUnitType(String name){
        super(name);
        outlineColor = GenesisPal.terraEonstoneOutline;
        envDisabled = Env.none;
        drawCell = false;
        useUnitCap = false; //i will not regret this
        lightRadius = 0;

        abilities.add(new RegenAbility(){{
            percentAmount = 1f / (80f * 60f) * 100f;
        }});

        abilities.add(new LiquidExplodeAbility(){{
            liquid = GenesisLiquids.biomass;
        }});

        healFlash = false;
    }
}
