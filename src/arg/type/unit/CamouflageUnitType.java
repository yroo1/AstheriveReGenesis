package arg.type.unit;

import arc.graphics.g2d.*;
import arc.util.*;
import arc.graphics.*;
import mindustry.entities.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;
import arg.content.deterra.DeterraItems;
import arg.graphics.*;
import mindustry.entities.abilities.*;
import mindustry.core.*;
import mindustry.game.*;
//idk what to import bro 🙏
import arc.math.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

/** BioUnitType but camouflage, yay. */
public class CamouflageUnitType extends TerraUnitType{
    public CamouflageUnitType(String name){
        super(name);
    }
    @Override
    public boolean targetable(Unit unit, Team targeter){
        return (targetable || (vulnerableWithPayloads && unit instanceof Payloadc p && p.hasPayload()))&&!(unit.health>=unit.maxHealth);
    }
    @Override
    public void applyColor(Unit unit){
        Draw.color();
        if(healFlash){
            Tmp.c1.set(Color.white).lerp(healColor, Mathf.clamp(unit.healTime - unit.hitTime));
        }
        Draw.mixcol(Tmp.c1, Math.max(unit.hitTime, !healFlash ? 0f : Mathf.clamp(unit.healTime)));

        if(unit.drownTime > 0 && unit.lastDrownFloor != null){
            Draw.mixcol(Tmp.c1.set(unit.lastDrownFloor.mapColor).mul(0.83f), unit.drownTime * 0.9f);
        }
        if(unit.health>=unit.maxHealth){
            float red = 0;
            float green = 0;
            float blue = 0;
            int fcount = 0;
            for(int xm = -3+1;xm<=3;xm++){
                for(int ym = -3+1;ym<=3;ym++){
                    Tile other = world.tile((int)(Math.round(unit.x/tilesize))+xm,(int)(Math.round(unit.y/tilesize))+ym);
                    red+=other.floor().mapColor.r;
                    green+=other.floor().mapColor.g;
                    blue+=other.floor().mapColor.b;
                    fcount++;
                }
            }
            Color c = new Color();
            Draw.mixcol(Tmp.c1.set(c.set(red/fcount,green/fcount,blue/fcount)).mul(0.95f),0.72f);
        }
        //this is horribly scuffed.
        //i know, anuke.
        if(renderer != null && renderer.overlays != null){
            renderer.overlays.checkApplySelection(unit);
        }
    }
}
