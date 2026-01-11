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

public class KikreaPlanetGenerator extends PlanetGenerator {
    public float heightScl = 1.3f, octaves = 8, persistence = 1.2f, heightPow = 2f, heightMult = 1.3f;

    @Override
    public float getHeight(Vec3 position) {
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    float rawHeight(Vec3 position) {
        return Simplex.noise3d(seed, octaves, persistence, 1f / heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    @Override
    public void getColor(Vec3 position, Color out) {
        Block block = rawHeight(position) < 0.5f ? Blocks.deepTaintedWater : rawHeight(position) < 0.6f ? Blocks.taintedWater : rawHeight(position) < 0.68f ? Blocks.crystallineStone : Blocks.crystalFloor

        out.set(block.mapColor).a(1f - block.albedo);
    }
}