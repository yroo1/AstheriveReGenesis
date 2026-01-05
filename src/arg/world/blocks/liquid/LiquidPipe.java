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

public class LiquidPipe extends GenesisLiquidBlock{
    public TextureRegion[][] atlasRegion = new TextureRegion[12][4];
    public TextureRegion[][] bottomAtlasRegion = new TextureRegion[12][4];
    
    public int[] horBitmask = {
        //0 bit
        3,
        //1 bit >
        0,
        //2 bit >^
        3,0,
        //3 bit ^
        3,4,3,0,
        //4 bit <^
        3,0,3,0,3,4,3,0,
        //5 bit <
        2,1,2,1,5,5,5,7,2,1,2,1,2,9,2,1,
        //6 bit <v
        3,0,3,0,3,4,3,0,3,0,3,0,3,4,3,0,2,1,2,1,5,5,5,7,2,1,2,1,2,9,2,1,
        //7 bit v
        3,4,3,4,3,4,3,8,3,4,3,4,3,4,3,8,5,4,5,4,5,10,5,11,5,4,5,4,7,11,7,8,3,4,3,4,3,4,3,8,3,4,3,4,3,4,3,8,2,6,2,6,9,11,9,10,2,6,2,6,2,8,2,6,
        //8 bit >v
        3,0,3,0,3,4,3,0,3,0,3,0,3,4,3,0,2,1,2,1,5,5,5,7,2,1,2,1,2,9,2,1,3,0,3,0,3,4,3,0,3,0,3,0,3,4,3,0,2,1,2,1,5,5,5,7,2,1,2,1,2,9,2,1,3,0,3,0,3,6,3,0,3,0,3,0,3,6,3,0,5,8,5,8,5,11,5,9,5,8,5,8,7,10,7,7,3,0,3,0,3,6,3,0,3,0,3,0,3,6,3,0,2,1,2,1,9,9,9,7,2,1,2,1,2,6,2,1
    };
    public int[] verBitmask = {
        //0 bit
        3,
        //1 bit >
        3,
        //2 bit >^
        3,3,
        //3 bit ^
        2,1,2,2,
        //4 bit <^
        3,3,3,3,2,1,2,2,
        //5 bit <
        3,3,3,3,1,3,1,3,3,3,3,3,2,1,2,2,
        //6 bit <v
        3,3,3,3,2,1,2,2,3,3,3,3,2,1,2,2,3,3,3,3,1,3,1,3,3,3,3,3,2,1,2,2,
        //7 bit v
        0,0,0,0,1,3,1,1,0,0,0,0,1,3,1,1,0,2,0,2,2, 0,2, 1,0,2,0,2,2, 0,2,2,0,0,0,0,1,3,1,1,0,0,0,0,1,3,1,1,0,2,0,2,0, 3,0, 1,0,2,0,2,1,3,1,0,
        //8 bit >v
        3,3,3,3,2,1,2,2,3,3,3,3,2,1,2,2,3,3,3,3,1,3,1,3,3,3,3,3,2,1,2,2,3,3,3,3,2,1,2,2,3,3,3,3,2,1,2,2,3,3,3,3,1,3,1,3,3,3,3,3,2,1,2,2,0,0,0,0,1,3,1,1,0,0,0,0,1,3,1,1,0,0,0,0,2,2,2,2,0,0,0,0,2,2,2,0,0,0,0,0,1,3,1,1,0,0,0,0,1,3,1,1,0,0,0,0,0,3,0,1,0,0,0,0,1,1,1,1
    };

    public LiquidPipe(String name){
        super(name);
        solid = false;
        //you cant disable liquid bro
        noUpdateDisabled = false;
        floating = true;
        underBullets = true;
        canOverdrive = false;
        priority = TargetPriority.transport;
    }

    @Override
    public void load(){
        super.load();
        int y = 0;
        for(int cy = 0; cy < 4; cy++, y += 32){
            int x = 0;
            for(int cx = 0; cx < 12; cx++, x += 32){
                atlasRegion[cx][cy] = new TextureRegion(Core.atlas.find(name+"-atlas"), x, y, 32, 32);
                bottomAtlasRegion[cx][cy] = new TextureRegion(Core.atlas.find(name+"-bottom-atlas"), x, y, 32, 32);
            }
        }
    }
    
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(bottomAtlasRegion[3][3], plan.drawx(), plan.drawy());
        Draw.rect(atlasRegion[3][3], plan.drawx(), plan.drawy());
    }

    public class LiquidPipeBuild extends GenesisLiquidBuild{
        public int blending;
        @Override
        public void updateTile(){
            super.updateTile();
            blending = 0;
            for(int i = 0; i < 8; i++){
                if(blends(world.tile(tile.x + Geometry.d8[i].x, tile.y + Geometry.d8[i].y))){
                    blending |= (1 << i);
                }
            }
            dumpLiquid(liquids.current());
        }

        boolean blends(Tile other){
            if(other != null && other.build != null){
                if(other.build instanceof LiquidPipeBuild || (other.build.block instanceof PipePump && (tile.build == other.build.back() || tile.build == other.build.front()))){
                    return true;
                }
            }
            return false;
        }

        @Override
        public void draw(){
            Draw.rect(bottomAtlasRegion[horBitmask[blending]][verBitmask[blending]], x, y);
            if(liquids.currentAmount() > 0.001f){
                drawTiledFrames(size, x, y, 0, liquids.current(), liquids.currentAmount() / liquidCapacity);
            }
            Draw.rect(atlasRegion[horBitmask[blending]][verBitmask[blending]], x, y);
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid){
            return (source instanceof LiquidPipeBuild || source.block instanceof PipePump) && (liquids.current() == liquid || liquids.currentAmount() < 0.2f);
        }

        @Override
        public boolean canDumpLiquid(Building to, Liquid liquid){
            return (to != null && (to.block instanceof LiquidPipe || to.block instanceof PipePump));
        }
    }
}
