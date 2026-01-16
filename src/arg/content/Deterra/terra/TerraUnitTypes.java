package arg.content.terraplasm;

import arc.*;
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
import mindustry.entities.abilities.*;
import mindustry.world.meta.*;
import arg.entities.abilities.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;

public class TerraUnitTypes{
    public static UnitType
    canopy, turf, boit, floa,
    monarch;
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
            health = 110;
            hitSize = 9f;
            omniMovement = false;
            rotateSpeed = 2.5f;
            drownTimeMultiplier = 2f;
            segments = 3;
            drawBody = false;
            crushDamage = 0.3f;
            aiController = HugAI::new;
            targetAir = false;

            segmentScl = 3f;
            segmentPhase = 5f;
            segmentMag = 0.5f;
            speed = 1.2f;
        }};
        boit = new TerraUnitType("boit"){{
            constructor = UnitWaterMove::create;
            health = 210;
            hitSize = 13f;
            omniMovement = false;
            rotateSpeed = 2f;
            targetAir = false;
            speed = 0.8f;
            faceTarget = false;
            
            trailLength = 28;
            waveTrailX = 4f;
            trailScl = 2f;
            
            moveSoundVolume = 0.4f;
            moveSound = Sounds.shipMove;
            abilities.add(new SpawnDeathAbility(){{
                unit = turf;
                amount = 2;
            }});
            weapons.add(new Weapon(){{
                shootOnDeath = true;
                targetUnderBlocks = false;
                reload = 24f;
                shootCone = 180f;
                ejectEffect = Fx.none;
                shootSound = Sounds.explosionCrawler;
                shootSoundVolume = 0.4f;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType(){{
                    collidesTiles = false;
                    collides = false;
                    rangeOverride = 25f;
                    hitEffect = Fx.pulverize;
                    speed = 0f;
                    splashDamageRadius = 44f;
                    instantDisappear = true;
                    splashDamage = 22f;
                    killShooter = true;
                    hittable = false;
                    collidesAir = true;
                }};
            }});
        }};
        floa = new TerraUnitType("floa"){{
            constructor = UnitEntity::create;
            health = 70;
            speed = 1.8f;
            accel = 0.08f;
            drag = 0.03f;
            flying = true;
            hitSize = 11f;
            targetAir = false;
            range = 140f;
            faceTarget = false;
            autoDropBombs = true;
            targetFlags = new BlockFlag[]{BlockFlag.drill, null};
            circleTarget = true;
            omniMovement = false;
            rotateSpeed = 2.5f;
            circleTargetRadius = 60f;
            abilities.add(new DrawWingAbility(0,0.5f,4,20,5));
            weapons.add(new Weapon(){{
                minShootVelocity = 1f;
                x = 4f;
                shootY = 0f;
                reload = 34f;
                shootCone = 180f;
                ejectEffect = Fx.none;
                inaccuracy = 20f;
                ignoreRotation = true;
                bullet = new BombBulletType(27f, 20f){{
                    width = 10f;
                    height = 10f;
                    hitEffect = Fx.flakExplosion;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;
                    damage = splashDamage * 0.5f;
                    
                    fragBullets = 4;
                    fragLifeMin = 0f;
                    fragRandomSpread =0f;
                    
                    fragBullet = new LiquidBulletType(GenesisLiquids.biomass){{
                        damage = 13;
                        speed = 2.5f;
                        drag = 0.009f;
                        shootEffect = Fx.none;
                        lifetime = 57f;
                        collidesAir = false;
                    }};
                }};
            }});    
        }};
        monarch = new TerraBossUnitType("monarch"){{
            constructor = LegsUnit::create;
            speed = 0.4f;
            drag = 0.3f;
            hitSize = 30f;
            rotateSpeed = 0.7f;
            targetAir = true;
            health = 1200;
            
            legCount = 4;
            legLength = 72f;
            legForwardScl = 0.6f;
            legMoveSpace = 1.4f;
            hovering = true;

            shadowElevation = 1.5f;
            groundLayer = Layer.legUnit + 1f;
            
            abilities.add(new DrawPulsingAbility("-heart",0,0,8,45));
            abilities.add(new DrawRegionAbility("-heart-top"));
        }};
    }
}
