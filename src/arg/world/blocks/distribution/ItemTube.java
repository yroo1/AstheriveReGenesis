package arg.world.blocks.distribution;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.TextureRegion;
import arc.math.*;
import arc.util.*;
import arc.math.geom.*;
import mindustry.world.blocks.distribution.*;
import mindustry.gen.Building;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.Tile;
import mindustry.entities.units.BuildPlan;
import arg.content.deterra.blocks.DeterraDistribution;

import static mindustry.Vars.*;

public class ItemTube extends Conveyor {
    //AWFUL
    public TextureRegion[][] topRegions = new TextureRegion[4][4];
    public ItemTube(String name){
        super(name);
    }
    @Override
    public void load(){
        super.load();
        bridgeReplacement = DeterraDistribution.itemOverpass; //i hate you
        junctionReplacement = null;
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++) {
                topRegions[i][j]=Core.atlas.find(name+"-top-"+String.valueOf(i)+"-"+String.valueOf(j));
            }
        }
    }
    @Override
    public boolean blends(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        return (otherblock.outputsItems() || (lookingAt(tile, rotation, otherx, othery, otherblock) && otherblock.hasItems))
        && lookingAtEither(tile, rotation, otherx, othery, otherrot, otherblock);
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{Core.atlas.find(name+"-preview")};
    }
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        int[] bits = getTiling(plan, list);

        if(bits == null) return;

        TextureRegion region = Core.atlas.find(name+"-preview");
        Draw.rect(region, plan.drawx(), plan.drawy(), region.width * bits[1] * region.scl(), region.height * bits[2] * region.scl(), plan.rotation * 90);
    }
    public class ItemTubeBuild extends ConveyorBuild {
        @Override
        public void draw(){
            super.draw();
            //if it work it works
            int drawrot = (blendbits==1?(blendscly!=-1?rotation:rotation-1)
                        :blendbits==2?(blendscly!=-1?rotation:rotation-2)
                        :blendbits==4?rotation-2:rotation);
            if (blendbits==4) drawrot-=1;
            drawrot%=4;
            if (drawrot<0) drawrot+=4;
            int drawbits = blendbits==4?2:blendbits;
            //draw extra conveyors facing this one for non-square tiling purposes
            Draw.z(Layer.blockUnder);
            for(int i = 0; i < 4; i++){
                if((blending & (1 << i)) != 0){
                    int dir = rotation-i;
                    int rot = -90*(rotation%2);
                    Draw.rect(sliced(topRegions[0][0], i != 0 ? SliceMode.bottom : SliceMode.top), x + Geometry.d4x(dir) * tilesize*0.75f, y + Geometry.d4y(dir) * tilesize*0.75f,rot);
                }
            }
            Draw.z(Layer.block);
            Draw.rect(topRegions[drawbits][drawrot], x, y, 0);
        }
    }
}
