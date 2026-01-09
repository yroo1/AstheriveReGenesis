package arg.content.terraplasm;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.storage.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.Sounds;
import mindustry.content.*;
import arg.world.blocks.terraplasm.*;
import arg.content.deterra.*;
import arg.world.blocks.defense.*;
import arg.graphics.*;
import arg.content.GenesisFx;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumeLiquids;
import mindustry.world.draw.DrawTurret;
import mindustry.entities.effect.*;
import mindustry.entities.effect.MultiEffect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.RegionPart.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.content.Fx.*;
import mindustry.Vars;
import mindustry.content.Liquids;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootSpread;

import static mindustry.type.ItemStack.with;

public class Terraplasm {
    public static Block
            falseCore,
            root, branchNode, sighter, heart, harvester, spawningBulb,
            pulseSource, spitter;
    public static void load() {
        {
            {
                falseCore = new CoreBlock("false-core"){{
                    //fake core to imitate attack mode
                    targetable = false;
                    forceDark = true;
                    privileged = true;
                    size = 1;
                }};
                root = new Root("root"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 1));
                    health = 10;
                }};
                
                branchNode = new BioBridge("branch-node"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 1));
                }};

                sighter = new BioEye("sighter"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 1));
                    health = 20;
                }};
                
                harvester = new BioDrill("harvester"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 0));
                    size = 2;
                }};
                heart = new BioHeart("heart"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 1));
                    size = 3;
                    health = 700;
                }};
                pulseSource = new PulseSource("pulse-source"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 1));
                }};
                spawningBulb = new BioSpawner("spawning-bulb"){{
                    requirements(Category.distribution, with(DeterraItems.quartz, 1));
                    size = 3;
                    unitType = TerraUnitTypes.turf;
                    health = 500;
                }};
                
                spitter = new BioTurret("spitter"){{
                    requirements(Category.turret, with(DeterraItems.quartz, 5,DeterraItems.magnetite,4));
                    outlineColor = GenesisPal.terraOutline;
                    reload = 45f;
                    inaccuracy = 8f;
                    size = 2;
                    recoil = 7f;
                    range = 11 * Vars.tilesize;
                    rotateSpeed = 4f;
                    squareSprite = false;
                    shootSound = Sounds.shootFlame;
                    minWarmup = 0.8f;
                    shootWarmupSpeed = 0.07f;
                    shootY = 2;
                    
                    shootType = new BulletType(4.89f, 24f){{
                        ammoMultiplier = 3f;
                        hitSize = 7f;
                        lifetime = 18f;
                        pierce = true;
                        collidesAir = true;
                        statusDuration = 60f * 2;
                        shootEffect = GenesisFx.shootSpitter;
                        hitEffect = Fx.hitFlameSmall;
                        despawnEffect = Fx.none;
                        status = StatusEffects.burning;
                        keepVelocity = false;
                        hittable = false;
                    }};
                    drawer = new DrawTurret(){{
                        parts.add(
                            new RegionPart("-shell-right"){{
                                progress = PartProgress.recoil;
                                under = false;
                                moveRot = -7;
                            }}
                        );
                        parts.add(
                            new RegionPart("-shell-left"){{
                                progress = PartProgress.recoil;
                                under = false;
                                moveRot = 7;
                            }}
                        );
                    }};
                }};
            }
        }
    }
}
