package arg.world.blocks.terraplasm;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.TextureRegion;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.world.blocks.defense.*;
import mindustry.gen.Building;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.Tile;
import mindustry.graphics.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.type.*;
import mindustry.world.meta.*;
import mindustry.gen.*;
import java.util.Random;
import arg.graphics.*;
import arg.content.terraplasm.Terraplasm;

import static mindustry.Vars.*;

public class BioBridge extends BioBlock {
    public TextureRegion bridgeRegion;
    
    public BioBridge(String name){
        super(name);
        update=true;
        isRoot=false;
        pulseScale=0.5f;
        priority = TargetPriority.transport;
        solid = true;
        underBullets = false;
        hasItems = true;
        itemCapacity = 1;
        unloadable = false;
    }
    @Override
    public void load(){
        super.load();
        bridgeRegion = Core.atlas.find(name+"-bridge");
    }
    public class BioBridgeBuild extends BioBuilding {
        public Item lastItem;
        public int itemTargetX = -1, itemTargetY = -1;
        
        @Override
        public void updateTile(){
            super.updateTile();
        }
        @Override
        public void updatePulse(){
            super.updatePulse();
            
            if(lastItem == null && items.any()){
                lastItem = items.first();
            }
            if(itemTargetX == -1 || itemTargetY == -1){
                if(getNearestHeart()!=null){
                    itemTargetX = getNearestHeart().tile.x;
                    itemTargetY = getNearestHeart().tile.y;
                }
            }
            if(lastItem != null && itemTargetX != -1 && itemTargetY != -1) {
                Building target = null;
                float bestDist = Float.POSITIVE_INFINITY; //FEAR THE INFINITE POWER
                for(int i=0;i<4;i++){
                    Building adj;
                    adj = tile.nearby(Geometry.d4(i).x,Geometry.d4(i).y).build;
                    if(adj != null && (adj.block instanceof Root || adj.block instanceof BioHeart)){
                        float dist = Mathf.dst(itemTargetX, itemTargetY, adj.tile.x, adj.tile.y);
                        if(dist<bestDist&&adj.acceptItem(this, lastItem)){
                            target = adj;
                            bestDist = dist;
                        }
                    }
                }
                for(int xm = -7+1;xm<=7;xm++){
                    for(int ym = -7+1;ym<=7;ym++){
                        Building adj = tile.nearby(xm,ym).build;
                        if(adj != null && adj.block instanceof BioBridge){
                            float dist = Mathf.dst(itemTargetX, itemTargetY, adj.tile.x, adj.tile.y);
                            if(dist<bestDist&&adj.acceptItem(this, lastItem)){
                                target = adj;
                                bestDist = dist;
                            }
                        }
                    }
                }
                if(target != null && target instanceof BioBuilding && target.acceptItem(this, lastItem)){
                    target.handleItem(this, lastItem);
                    items.remove(lastItem, 1);
                    lastItem = null;
                }
            }
        }
        
        @Override
        public void draw(){
            Draw.z(Layer.blockUnder+0.2f);
            drawPulse(block.region,drawPulseScale);
            for(int xm = -7+1;xm<=7;xm++){
                for(int ym = -7+1;ym<=7;ym++){
                    Tile other = tile.nearby(xm,ym);
                    if(other != null && other.build != null && other.build instanceof BioBridgeBuild otherbuild){
                        //prevent two BioBridges from drawing two bridge at once (only one draws it instead as shared bridge (OUR BRIDGE))
                        if(y<other.build.y) continue;
                        if(y==other.build.y && x>other.build.x) continue;
                        
                        Draw.z(Layer.blockUnder+0.19f);
                        float
                        angle = Angles.angle(x, y, other.build.x, other.build.y),
                        cx = (x + other.build.x)/2f,
                        cy = (y + other.build.y)/2f,
                        len = Mathf.dst(x,y,other.build.x,other.build.y),
                        growHeight = (float)Math.min(growProgress,otherbuild.growProgress);

                        //flips angle if the shading is facing down, flip thyself
                        if(angle>=90f) angle+=180f;
                        angle%=360f;
                        
                        Draw.rect(bridgeRegion, cx, cy, len, (bridgeRegion.height * (growHeight+1)) * bridgeRegion.scl(), angle);
                    }
                }
            }
            Draw.z(Layer.blockUnder+0.21f);
            if(lastItem!=null){
                Draw.rect(lastItem.fullIcon, x, y, itemSize, itemSize);
            }
        }

        public Building getNearestHeart() {
            return Units.findAllyTile(team, x, y, 1000, b -> b.block instanceof BioHeart);
        }

        //item mechanic

        @Override
        public int acceptStack(Item item, int amount, Teamc source){
            return 0;
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return team == source.team && lastItem == null && items.total() == 0;
        }

        @Override
        public void handleItem(Building source, Item item){
            items.add(item, 1);
            lastItem = item;
        }

        @Override
        public int removeStack(Item item, int amount){
            int result = super.removeStack(item, amount);
            if(result != 0 && item == lastItem){
                lastItem = null;
            }
            return result;
        }
    }
}
