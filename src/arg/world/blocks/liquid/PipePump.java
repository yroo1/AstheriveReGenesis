package arg.world.blocks.liquid;

import arc.Core;
import arc.math.geom.*;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.graphics.*;
import mindustry.world.blocks.liquid.*;
import mindustry.core.*;
import mindustry.world.*;
import mindustry.entities.*;
import mindustry.entities.units.*;

import static mindustry.Vars.*;

public class PipePump extends GenesisLiquidBlock{
    public TextureRegion topRegion;
    public final int timerFlow = timers++;

    public PipePump(String name){
        super(name);
        solid = true;
        rotate = true;
        noUpdateDisabled = true;
        floating = true;
        underBullets = false;
        canOverdrive = false;
        priority = TargetPriority.transport;
    }

    @Override
    public void load(){
        super.load();
        topRegion = Core.atlas.find(name+"-top");
    }
    
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, topRegion};
    }

    public class PipePumpBuild extends GenesisLiquidBuild{

        @Override
        public void updateTile(){
            Building front = front(), back = back();

            if(front != null && back != null && back.block.hasLiquids && back.liquids != null && front.team == team && back.team == team){
                if(liquids.currentAmount() > 0.0001f && timer(timerFlow, 1)){
                    transferLiquid(front, 5, liquids.current());
                }
            }
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            Draw.rect(topRegion, x, y, rotation * 90);
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid){
            return (liquids.current() == liquid || liquids.currentAmount() < 0.2f) && source == back() && !(source instanceof PipePumpBuild);
        }
    }
}
