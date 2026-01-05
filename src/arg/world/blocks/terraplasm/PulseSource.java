package arg.world.blocks.terraplasm;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.TextureRegion;
import arc.math.*;
import arc.util.*;
import arc.math.geom.*;
import mindustry.world.blocks.defense.*;
import mindustry.gen.Building;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.Tile;
import mindustry.graphics.*;
import mindustry.content.*;

import static mindustry.Vars.*;

public class PulseSource extends BioBlock {
    public PulseSource(String name){
        super(name);
    }
    public class PulseSourceBuild extends BioBuilding {        
        @Override
        public void updateTile() {
            if (true){
                if (pulseTimer<45f) {
                    pulseTimer+=delta();
                } else {
                    updatePulse();
                    pulseTimer=0;
                }
            }
        }
        public void updatePulse() {
            //TODO rework back to this->pulse
            if (true) {
                for (int i=0;i<4;i++) {
                    Building advroot = tile.nearbyBuild(i);
                    if (advroot instanceof BioBuilding advbuild) {
                        if (!advbuild.pulsed) {                        
                            advbuild.biopulse=Math.max(advbuild.biopulse,32);
                        }
                    }
                }
            }
        }
    }
}
