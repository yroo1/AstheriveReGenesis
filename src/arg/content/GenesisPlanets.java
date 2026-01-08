package arg.content;

import arc.func.Cons;
import arc.graphics.Color;
import arc.math.Rand;
import arc.math.geom.Mat3D;
import arc.util.Tmp;
import arc.struct.Seq;
import arg.content.deterra.blocks.DeterraEnv;
import arg.content.deterra.blocks.DeterraStorage;
import mindustry.content.Blocks;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.AsteroidGenerator;
import mindustry.maps.planet.ErekirPlanetGenerator;
import arg.planet.*;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.meta.Env;
import mindustry.maps.planet.*;

public class GenesisPlanets{
    public static Planet
            //star
            orrin,khessar,nroo,

    // planets
    deterra,

    // muns
    thrata;

    public static void load(){
        // regions stars
        orrin = new Planet("orrin", null, 6f, 0){{
            accessible = true;
            hasAtmosphere = true;
            solarSystem = this;
            meshLoader = () -> new SunMesh(
                    this, 7, 8, 0.4f, 0.7f, 1.4f, 1.6f, 1.2f,

                    Color.valueOf("8EAFC3"),
                    Color.valueOf("A1C6CF"),
                    Color.valueOf("B8D9DD"),
                    Color.valueOf("D4EDEF"),
                    Color.valueOf("FFFFFF")
            );
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 7, 2.7f, 0.1f, 5, Color.valueOf("EEF3FF").a(0.4f),3,0.42f, 1f, 0.43f)
            );
        }};
        nroo = new Planet("nroo", orrin, 2.5f, 0){{
            accessible = false;
            hasAtmosphere = true;
            solarSystem = orrin;
            orbitRadius = 30f;

            meshLoader = () -> new SunMesh(
                    this, 6, 8, 1.5f, 1.5f, 1.4f, 1.6f, 1.2f,

                    Color.valueOf("E4D563"),
                    Color.valueOf("DED595"),
                    Color.valueOf("E6E1BE"),
                    Color.valueOf("FFFEFB")
            );
        }};
        hista = new Planet("hista", orrin, 2f, 0){{
            accessible = false;
            hasAtmosphere = true;
            solarSystem = orrin;
            orbitRadius = 95f;

            meshLoader = () -> new SunMesh(
                    this, 5, 7, 1.5f, 0.7f, 1.4f, 1.6f, 1.2f,

                    Color.valueOf("6930B3"),
                    Color.valueOf("A25CC3"),
                    Color.valueOf("D696DE"),
                    Color.valueOf("FEDCFF")
            );
        }};
        khessar = new Planet("khessar", orrin, 2f, 0){{
            accessible = true;
            hasAtmosphere = true;
            solarSystem = orrin;
            orbitRadius = 65f;

            meshLoader = () -> new SunMesh(
                    this, 5, 8, 0.4f, 0.7f, 1.4f, 1.6f, 1.2f,

                    Color.valueOf("A2615D"),
                    Color.valueOf("BD7771"),
                    Color.valueOf("D09287"),
                    Color.valueOf("EFC4B1"),
                    Color.valueOf("FFDFCB")
            );
        }};

        // region planets
        deterra = new Planet("deterra", khessar, 1f, 4){{
            accessible = true;
            hasAtmosphere = true;
            landCloudColor = Color.valueOf("DBF3FF");
            atmosphereColor = Color.valueOf("9AC0DB");
            atmosphereRadIn = 0.01f;
            atmosphereRadOut = 0.3f;
            orbitTime = 60f*20f;
            rotateTime = 60f*12.3f;
            orbitSpacing = 1;
            orbitRadius = 15f;
            iconColor = Color.valueOf("9AC0DB");
            solarSystem = orrin;
            alwaysUnlocked = clearSectorOnLose = true;
            allowLaunchLoadout = allowLaunchSchematics = false;
            defaultCore = DeterraStorage.coreProtocol;
            ruleSetter = r -> {
              r.fog = true;
              r.staticFog = false;
              r.ambientLight = Color.valueOf("121617F6");
              r.onlyDepositCore = true;
              r.waveTeam = GenesisTeams.terraplasm;
            };
            allowLaunchToNumbered = true;
            updateLighting = true;
            campaignRuleDefaults.fog = true;
            startSector = 2;
            minZoom = 0.05f;
            generator = new DeterraPlanetGenerator();
            meshLoader = () -> new MultiMesh(
                new HexMesh(this, 7)
            );
            cloudMeshLoader = () -> new MultiMesh(
                new HexSkyMesh(this, 11, 2.7f, 0.1f, 5, Color.valueOf("BAD1D4").a(0.4f), 7, 0.4f, 2f, 0.43f)             
            );
        }};
        thrata = new Planet("thrata", deterra, 0.3f, 2){{
            accessible = false;
            hasAtmosphere = false;
            landCloudColor = Color.valueOf("535D64");
            orbitTime = 60f*5f;
            rotateTime = 60f*6f;
            orbitSpacing = 1;
            orbitRadius = 4f;
            iconColor = Color.valueOf("535D64");
            solarSystem = orrin;
            alwaysUnlocked = true;
            generator = new ThrataPlanetGenerator();
            meshLoader = () -> new MultiMesh(
                new HexMesh(this, 6)
            );
        }};
    }
}
