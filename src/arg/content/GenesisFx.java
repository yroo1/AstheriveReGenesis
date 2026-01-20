package arg.content;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.Vec2;
import arc.util.Time;
import mindustry.entities.Effect;
import mindustry.gen.Bullet;
import mindustry.graphics.*;
import arg.graphics.GenesisPal;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.randLenVectors;

public class GenesisFx{
    public static final Effect
    bulbPop = new Effect(10f, 80f, e -> {
        color(GenesisPal.terraPurple, GenesisPal.terraPurple, e.fin());
        stroke(e.fout() * 3f);
        Lines.circle(e.x, e.y, e.fin() * 32f);
    }),
    boitPodPop = new Effect(10f, 80f, e -> {
        color(GenesisPal.terraCyan, GenesisPal.terraCyan, e.fin());
        stroke(e.fout() * 6f);
        Lines.circle(e.x, e.y, e.fin() * 52f);
    }),
    shootSpitter = new Effect(32f, 80f, e -> {
        color(Pal.lightFlame, Pal.darkFlame, Color.gray, e.fin());

        randLenVectors(e.id, 12, e.finpow() * 88f, e.rotation, 10f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 0.65f + e.fout() * 1.5f);
        });
    }).followParent(false),
    shootMonarch = new Effect(170f, 80f, e -> {
        color(GenesisPal.biomassLight, GenesisPal.biomass, GenesisPal.biomassDark, e.fin());

        randLenVectors(e.id, 16, e.finpow() * 40f, e.rotation, 45f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 0.4f + e.fout() * 3f);
        });
    }).followParent(false);
}