package arg.planet;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.Tmp;
import arc.util.noise.*;
import arg.content.deterra.blocks.DeterraEnv;
import mindustry.content.Blocks;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Block;

public class ThrataPlanetGenerator extends PlanetGenerator {
    public float heightScl = 0.7f, octaves = 6, persistence = 0.6f, heightPow = 2f, heightMult = 1.1f;
    
    Vec3 craterPos = new Vec3(0.1503837331804,0.852868531952,0.5);
    @Override
    public float getHeight(Vec3 position) {
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    float rawHeight(Vec3 position) {
        return Simplex.noise3d(seed, octaves, persistence, 1f / heightScl, 10f + position.x, 10f + position.y, 10f + position.z) - (position.dst(craterPos)<0.4f?0.4f-((float)-Math.pow(position.dst(craterPos),8)+(float)Math.pow(position.dst(craterPos),4)*Mathf.sqrt(2)):0f);
    }

    @Override
    public void getColor(Vec3 position, Color out) {
        Block block = rawHeight(position) < 0.4f ? Blocks.mud : rawHeight(position) < 0.5f ? Blocks.dirt : rawHeight(position) < 0.6f ? Blocks.rhyolite : rawHeight(position) < 0.7f ? Blocks.regolith : DeterraEnv.eonstoneErodedFloor;

        out.set(block.mapColor).a(1f - block.albedo);
    }
}