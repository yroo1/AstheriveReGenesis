package arg.content.terraplasm;

import arc.graphics.*;
import arc.math.Interp;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.*;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.graphics.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ai.types.*;
import arg.type.unit.*;
import arg.content.*;
import mindustry.type.weapons.*;
import mindustry.content.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;

public class TerraUnitTypes{
    public static UnitType
    canopy, turf;
    public static void load(){
        canopy = new CamouflageUnitType("canopy"){{
            constructor = LegsUnit::create;
            speed = 0.9f;
            drag = 0.3f;
            hitSize = 11f;
            rotateSpeed = 3f;
            targetAir = false;
            health = 140;
            
            legCount = 3;
            legLength = 28f;
            legForwardScl = 0.6f;
            legMoveSpace = 1.4f;
            hovering = true;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1f;

            weapons.add(new Weapon("arg-canopy-weapon"){{
                constructor = UnitEntity::create;
                top = false;
                shootY = 3f;
                reload = 4f;
                ejectEffect = Fx.none;
                recoil = 1f;
                x = 6f;
                shootSound = Sounds.shootAtrax;

                bullet = new LiquidBulletType(GenesisLiquids.biomass){{
                    damage = 7;
                    speed = 2.8f;
                    drag = 0.08f;
                    shootEffect = Fx.shootSmall;
                    lifetime = 50f;
                    collidesAir = false;
                }};
            }});
        }};
        turf = new TerraUnitType("turf"){{
            constructor = CrawlUnit::create;
            health = 100;
            hitSize = 9f;
            omniMovement = false;
            rotateSpeed = 2.5f;
            drownTimeMultiplier = 2f;
            segments = 3;
            drawBody = false;
            crushDamage = 0.5f;
            aiController = HugAI::new;
            targetAir = false;

            segmentScl = 3f;
            segmentPhase = 5f;
            segmentMag = 0.5f;
            speed = 1.2f;
        }};
    }
}
