package arg.content.deterra.blocks;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.defense.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.Sounds;
import arg.world.blocks.defense.*;
import arg.graphics.*;
import arg.content.deterra.*;
import mindustry.content.*;
import mindustry.entities.effect.MultiEffect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.RegionPart.*;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.*;
import mindustry.content.Fx.*;
import mindustry.Vars;
import mindustry.content.Liquids;
import mindustry.entities.part.RegionPart;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumeLiquids;
import mindustry.world.draw.DrawTurret;
import mindustry.entities.effect.*;

import static mindustry.type.ItemStack.with;
import static mindustry.Vars.*;

public class DeterraTurrets {
    public static Block
            fracture,frail,blare;
    public static void load() {
        {
            {
                fracture = new ItemTurret("fracture"){{
                    requirements(Category.turret, with(DeterraItems.quartz, 25, DeterraItems.magnetite, 20));
                    researchCost = with(DeterraItems.quartz, 25, DeterraItems.magnetite, 20);
  
                    health = 140;
                    maxAmmo = 5;
                    outlineColor = GenesisPal.deterraOutline;
                    reload = 80f;
                    inaccuracy = 0.5f;
                    size = 2;
                    recoil = 1f;
                    range = 14 * Vars.tilesize;
                    rotateSpeed = 2.5f;
                    squareSprite = false;
                    shootSound = Sounds.shootDisperse;
                    minWarmup = 0.8f;
                    shootWarmupSpeed = 0.07f;
                    shootY = 0f;

                    ammo(
                        DeterraItems.quartz, new BasicBulletType(3.8f, 39) {{
                            lifetime = 30f;
                            
                            width = 8f;
                            height = 14f;
                            weaveMag = 2;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            hitColor = backColor = trailColor = GenesisPal.quartzDark;
                            frontColor = GenesisPal.quartz;
                            trailWidth = 2.1f;
                            trailLength = 7;
                            shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                            smokeEffect = Fx.shootBigSmoke;
                        }}
                    );
                    drawer = new DrawTurret(){{
                        parts.add(
                            new RegionPart("-barrel"){{
                                progress = PartProgress.recoil;
                                mirror = false;
                                under = true;
                                moveY = -2.5f;
                            }}
                        );
                    }};
                }};
                frail = new ItemTurret("frail"){{
                    requirements(Category.turret, with(DeterraItems.quartz, 20, DeterraItems.magnetite, 25));
                    researchCost = with(DeterraItems.quartz, 100, DeterraItems.magnetite, 120);
  
                    health = 150;
                    maxAmmo = 5;
                    outlineColor = GenesisPal.deterraOutline;
                    reload = 17f;
                    inaccuracy = 3f;
                    size = 2;
                    recoil = 1f;
                    range = 19 * Vars.tilesize;
                    rotateSpeed = 3f;
                    squareSprite = false;
                    shootSound = Sounds.shootDisperse;
                    minWarmup = 0.8f;
                    shootWarmupSpeed = 0.07f;
                    shootY = 1.5f;

                    ammo(
                        DeterraItems.magnetite, new BasicBulletType(5.5f, 10) {{
                            lifetime = 28f;
                            
                            width = 7f;
                            height = 16f;
                            weaveMag = 2;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            hitColor = backColor = trailColor = GenesisPal.magnetiteDark;
                            frontColor = GenesisPal.magnetite;
                            trailWidth = 2.1f;
                            trailLength = 7;
                            shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                            smokeEffect = Fx.shootBigSmoke;
                        }}
                    );
                    shoot = new ShootAlternate(5.2f);
                    recoils = 2;
                    drawer = new DrawTurret(){{
                        for(int i = 0; i < 2; i ++){
                            int f = i;
                            parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                                progress = PartProgress.recoil;
                                recoilIndex = f;
                                under = true;
                                moveY = -2f;
                            }});
                        }
                    }};
                }};
                blare = new ItemTurret("blare"){{
                    requirements(Category.turret, with(DeterraItems.quartz, 75, DeterraItems.magnetite, 50, DeterraItems.polterite, 30));
                    researchCost = with(DeterraItems.quartz, 750, DeterraItems.magnetite, 500, DeterraItems.polterite, 100);
  
                    health = 330;
                    maxAmmo = 5;
                    outlineColor = GenesisPal.deterraOutline;
                    reload = 130f;
                    inaccuracy = 0f;
                    size = 3;
                    recoil = 1.5f;
                    range = 21 * Vars.tilesize;
                    rotateSpeed = 1.5f;
                    squareSprite = true;
                    shootSound = Sounds.shootTank;
                    minWarmup = 0.85f;
                    shootWarmupSpeed = 0.06f;
                    shootY = -0.5f;
                    shake = 3.1f;

                    ammo(
                        DeterraItems.polterite, new BasicBulletType(7.1f, 63) {{
                            lifetime = 24f;
                            
                            ammoMultiplier = 1;
                            width = 12f;
                            height = 20f;
                            weaveMag = 2;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                            hitColor = backColor = trailColor = GenesisPal.polteriteDark;
                            frontColor = GenesisPal.polterite;
                            trailWidth = 3f;
                            trailLength = 10;
                            shootEffect = new MultiEffect(Fx.shootBigColor, Fx.shootTitan);
                            smokeEffect = Fx.shootBigSmoke2;
                            fragBullets = 8;
                            fragLifeMin = 0f;
                            fragRandomSpread = 180f;
                    
                            fragBullet = new BasicBulletType(4f,7){{
                                shootEffect = Fx.none;
                                lifetime = 13f;
                                hitEffect = despawnEffect = Fx.hitBulletColor;
                                hitColor = backColor = trailColor = GenesisPal.polteriteDark;
                            }};
                        }}
                    );
                    drawer = new DrawTurret(){{
                        parts.add(
                            new RegionPart("-mid"){{
                                progress = PartProgress.recoil;
                                heatProgress = PartProgress.recoil;
                                mirror = false;
                                under = true;
                                moveY = -2.5f;
                            }}
                        );
                    }};
                }};
            }
        }
    }
}
