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
import mindustry.core.*;
import mindustry.entities.units.*;

import static mindustry.Vars.*;

public class ItemOverpass extends DuctBridge {
    public TextureRegion dir1;
    public TextureRegion dir2;
    public TextureRegion side;
    public ItemOverpass(String name){
         super(name);
    }
    @Override
    public void load(){
        super.load();
        dir1=Core.atlas.find(name+"-dir1");
        dir2=Core.atlas.find(name+"-dir2");
        side=Core.atlas.find(name+"-dir-side");
    }
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(dir1, plan.drawx(), plan.drawy(), plan.rotation * 90);
        Draw.rect(side, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, dir1, side};
    }
    @Override
    public void drawBridge(int rotation, float x1, float y1, float x2, float y2, @Nullable Color liquidColor){
        Draw.alpha(Renderer.bridgeOpacity);
        float
        angle = Angles.angle(x1, y1, x2, y2),
        shadedAngle = rotation==0||rotation==2?0:270, //i copied entire drawBridge() just to change this lol
        cx = (x1 + x2)/2f,
        cy = (y1 + y2)/2f,
        len = Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)) - size * tilesize;

        Draw.rect(bridgeRegion, cx, cy, len, bridgeRegion.height * bridgeRegion.scl(), shadedAngle);
        if(liquidColor != null){
            Draw.color(liquidColor, liquidColor.a * Renderer.bridgeOpacity);
            Draw.rect(bridgeLiquidRegion, cx, cy, len, bridgeLiquidRegion.height * bridgeLiquidRegion.scl(), angle);
            Draw.color();
            Draw.alpha(Renderer.bridgeOpacity);
        }
        if(bridgeBotRegion.found()){
            Draw.color(0.4f, 0.4f, 0.4f, 0.4f * Renderer.bridgeOpacity);
            Draw.rect(bridgeBotRegion, cx, cy, len, bridgeBotRegion.height * bridgeBotRegion.scl(), angle);
            Draw.reset();
        }
        Draw.alpha(Renderer.bridgeOpacity);

        for(float i = 6f; i <= len + size * tilesize - 5f; i += 5f){
            Draw.rect(arrowRegion, x1 + Geometry.d4x(rotation) * i, y1 + Geometry.d4y(rotation) * i, angle);
        }

        Draw.reset();
    }
    public class ItemOverpassBuild extends DuctBridgeBuild {
        @Override
        public void draw(){
            Draw.rect(region, x, y);
            if (rotation<2){
                Draw.rect(dir1, x, y, rotation*90);
            } else {
                Draw.rect(dir2, x, y, rotation*90);
            }
            Draw.rect(side, x, y, rotation%2==0?0:270);
            var link = findLink();
            if(link != null){
                Draw.z(Layer.power - 1);
                drawBridge(rotation, x, y, link.x, link.y, null);
            }
        }
    }
}
