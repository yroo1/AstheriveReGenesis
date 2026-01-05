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
            fracture,frail;
    public static void load() {
        {
            {
                fracture = new ItemTurret("fracture"){{
                    requirements(Category.turret, with(DeterraItems.quartz, 40, DeterraItems.magnetite, 30));
                    researchCost = with(DeterraItems.quartz, 100, DeterraItems.magnetite, 75);
  
                    health = 140;
                    itemCapacity = 5;
                    outlineColor = GenesisPal.deterraOutline;
                    reload = 80f;
                    inaccuracy = 0.5f;
                    size = 2;
                    recoil = 1f;
                    range = 14 * Vars.tilesize;
                    rotateSpeed = 3f;
                    squareSprite = false;
                    shootSound = Sounds.shootDisperse;
                    minWarmup = 0.8f;
                    shootWarmupSpeed = 0.07f;
                    shootY = 0f;

                    ammo(
                        DeterraItems.quartz, new BasicBulletType(3.8f, 90) {{
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
                                moveY = -2f;
                            }}
                        );
                    }};
                }};
                frail = new ItemTurret("frail"){{
                    requirements(Category.turret, with(DeterraItems.quartz, 30, DeterraItems.magnetite, 45));
                    researchCost = with(DeterraItems.quartz, 100, DeterraItems.magnetite, 120);
  
                    health = 150;
                    itemCapacity = 5;
                    outlineColor = GenesisPal.deterraOutline;
                    reload = 35f;
                    inaccuracy = 3f;
                    size = 2;
                    recoil = 1f;
                    range = 19 * Vars.tilesize;
                    rotateSpeed = 2f;
                    squareSprite = false;
                    shootSound = Sounds.shootDisperse;
                    minWarmup = 0.8f;
                    shootWarmupSpeed = 0.07f;
                    shootY = 1.5f;

                    ammo(
                        DeterraItems.magnetite, new BasicBulletType(5.5f, 34) {{
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
                                moveY = -1.5f;
                            }});
                        }
                    }};
                }};
            }
        }
    }
}
