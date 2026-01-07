package arg.type.unit;

import arc.math.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;
import arg.content.deterra.DeterraItems;
import arg.graphics.*;
import arg.content.*;
import mindustry.entities.abilities.*;
import arg.content.deterra.blocks.*;
import arg.world.blocks.environment.*;

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
    
    /*
    @Override
    public void killed(Unit unit){
        for(int xm = -3+1;xm<=3;xm++){
            for(int ym = -3+1;ym<=3;ym++){
                Tile other = world.tile((int)(Math.round(unit.x/tilesize))+xm,(int)(Math.round(unit.y/tilesize))+ym);
                float dist = Mathf.sqrt(Mathf.pow(unit.x/tilesize-xm,2)+Mathf.pow(unit.x/tilesize-xm,2));
                if(other.floor() instanceof GenesisFloor otherfloor){
                    //no
                }
            }
        }
    }
    */
}
