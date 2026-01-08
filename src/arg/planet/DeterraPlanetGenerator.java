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

public class DeterraPlanetGenerator extends PlanetGenerator {
    public float heightScl = 1.2f, octaves = 9, persistence = 0.8f, heightPow = 2.2f, heightMult = 1.1f;

    @Override
    public float getHeight(Vec3 position) {
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    float rawHeight(Vec3 position) {
        return Simplex.noise3d(seed, octaves, persistence, 1f / heightScl, 10f + position.x, 10f + position.y, 10f + position.z)-Math.abs(position.y)*0.4f;
    }

    @Override
    public void getColor(Vec3 position, Color out) {
        Block block = rawHeight(position) < 0.35f ? DeterraEnv.deepWatergel : rawHeight(position) < 0.45f ? DeterraEnv.shallowWatergel : rawHeight(position) < 0.5f ? DeterraEnv.alyogelFloor : rawHeight(position) < 0.6f ? DeterraEnv.eonstoneFloor : rawHeight(position) < 0.64f ? DeterraEnv.eonstoneErodedFloor : rawHeight(position) < 0.7f ? DeterraEnv.ranston : DeterraEnv.platedRanston;

        out.set(block.mapColor).a(1f - block.albedo);
    }
}