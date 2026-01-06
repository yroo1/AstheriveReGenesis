package arg.content.deterra.blocks;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.environment.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.gen.Sounds.*;
import mindustry.content.Fx;
import mindustry.world.meta.*;
import arg.content.deterra.DeterraItems;
import arg.world.blocks.environment.*;

import static mindustry.type.ItemStack.with;

public class DeterraEnv {
    public static Block
            //h
            //this code is so messy lmao
            shallowWatergel, deepWatergel,
            //cromosand
            cromosandFloor,cromosandLightlyHardenedFloor,cromosandHardenedFloor,
            cromosandHardenedWall,
            cromosandHardenedBoulder,
            //eonstone
            eonstoneFloor, eonstoneLightlyErodedFloor, eonstoneErodedFloor,
            eonstoneWall, eonstoneErodedWall,
            carbonicEonstoneErodedWall,
            eonstoneErodedVent,
            //ranston
            ranston, platedRanston,
            //idk
            alyogelDeepFloor,alyogelFloor,
            eonstoneBoulder,eonstoneErodedBoulder,
            smallSporfloreFern, sporfloreFern,
            sporfloreTree, largeSporfloreTree,
            eonstoneHugeVent,
            //bioplastic
            bioplasticFloor,
            //major
            majorFloor1,majorFloor2,majorFloor3,majorFloor4,majorWall,
            //this code is so messy lmao
            eonstoneQuartzCluster, oreQuartz,
            eonstoneMagnetiteCluster, oreMagnetite;
    public static void load() {
        {
            {
                //h
                //liquid
                deepWatergel = new GenesisFloor("deep-watergel"){{
                    speedMultiplier = 0.2f;
                    variants = 3;
                    liquidDrop = Liquids.water;
                    isLiquid = true;
                    cacheLayer = CacheLayer.water;
                    albedo = 0.95f;
                    supportsOverlay = false;
                    drownTime = 120;
                }};
                shallowWatergel = new GenesisFloor("shallow-watergel"){{
                    speedMultiplier = 0.4f;
                    variants = 3;
                    liquidDrop = Liquids.water;
                    isLiquid = true;
                    cacheLayer = CacheLayer.water;
                    albedo = 0.95f;
                    supportsOverlay = true;
                }};
                //cromosand
                //liquid
                cromosandFloor = new GenesisFloor("cromosand-floor"){{
                    variants = 5;
                }};
                //floor
                cromosandLightlyHardenedFloor = new GenesisFloor("cromosand-lightly-hardened-floor"){{
                    variants = 5;
                }};
                cromosandHardenedFloor = new GenesisFloor("cromosand-hardened-floor"){{
                    variants = 5;
                }};
                //wall
                cromosandHardenedWall = new StaticTree("cromosand-hardened-wall"){{variants = 5;}};
                //prop
                cromosandHardenedBoulder = new Prop("cromosand-hardened-boulder"){{
                    variants = 2;
                    cromosandHardenedFloor.asFloor().decoration = this;
                    cromosandLightlyHardenedFloor.asFloor().decoration = this;
                }};
                //eonstone
                //floor
                eonstoneFloor = new GenesisFloor("eonstone-floor", 5);
                eonstoneLightlyErodedFloor = new GenesisFloor("eonstone-lightly-eroded-floor", 5);
                eonstoneErodedFloor = new GenesisFloor("eonstone-eroded-floor", 5);
                //wall
                eonstoneWall = new StaticTree("eonstone-wall"){{variants = 5;}};
                eonstoneErodedWall = new StaticWall("eonstone-eroded-wall"){{variants = 4;}};
                carbonicEonstoneErodedWall = new StaticWall("carbonic-eonstone-eroded-wall"){{
                    variants = 3;
                    itemDrop = DeterraItems.carbon;
                }};
                //liquid
                alyogelDeepFloor = new GenesisFloor("alyogel-deep-floor"){{
                    speedMultiplier = 0.2f;
                    variants = 3;
                    liquidDrop = Liquids.water;
                    isLiquid = true;
                    cacheLayer = CacheLayer.water;
                    albedo = 0.95f;
                    supportsOverlay = false;
                }};
                alyogelFloor = new GenesisFloor("alyogel-floor"){{
                    speedMultiplier = 0.4f;
                    variants = 4;
                    liquidDrop = Liquids.water;
                    isLiquid = true;
                    cacheLayer = CacheLayer.water;
                    albedo = 0.95f;
                    supportsOverlay = true;
                }};
                //prop
                eonstoneBoulder = new Prop("eonstone-boulder"){{
                    variants = 2;
                    eonstoneLightlyErodedFloor.asFloor().decoration = this;
                    eonstoneFloor.asFloor().decoration = this;
                }};
                eonstoneErodedBoulder = new Prop("eonstone-eroded-boulder"){{
                    variants = 2;
                    eonstoneErodedFloor.asFloor().decoration = this;
                }};
                //vent
                eonstoneErodedVent = new SteamVent("eonstone-eroded-vent"){{
                    parent = blendGroup = eonstoneErodedFloor;
                    attributes.set(Attribute.steam, 1f);
                }};
                //ranston wow very creative name omg
                //floor
                ranston = new Floor("ranston",4);
                platedRanston = new Floor("plated-ranston",4);
                //bioplastic
                bioplasticFloor = new BioFloor("bioplastic-floor"){{
                    drawEdgeOut=false;
                    drawEdgeIn=false;
                }};
                //major
                majorFloor1= new TiledFloor("major-floor-1"){{
                    drawEdgeOut=false;
                    drawEdgeIn=false;
                }};
                majorFloor2= new TiledFloor("major-floor-2"){{
                    drawEdgeOut=false;
                    drawEdgeIn=false;
                }};
                majorFloor3= new TiledFloor("major-floor-3"){{
                    drawEdgeOut=false;
                    drawEdgeIn=false;
                }};
                majorFloor4= new TiledFloor("major-floor-4"){{
                    drawEdgeOut=false;
                    drawEdgeIn=false;
                }};
                majorWall= new TiledStaticWall("major-wall"){{
                    }};
                //anything else
                sporfloreFern = new SeaBush("sporflore-fern"){{
                    lobesMin = 4;
                    lobesMax = 6;
                    magMin = 4;
                    magMax = 6;
                }};
                smallSporfloreFern = new SeaBush("small-sporflore-fern"){{
                    lobesMin = 4;
                    lobesMax = 5;
                    magMin = 5;
                    magMax = 7;
                }};
                sporfloreTree = new TallBlock("sporflore-tree"){{
                    variants = 2;
                }};
                largeSporfloreTree = new TallBlock("large-sporflore-tree"){{
                    variants = 2;
                }};
                eonstoneHugeVent = new SteamVent("eonstone-huge-vent"){{
                    variants = 0;
                }};
                //ore thing
                eonstoneQuartzCluster = new OreCluster("eonstone-quartz-cluster"){{
                    parent = blendGroup = eonstoneErodedFloor;
                    itemDrop = DeterraItems.quartz;
                }};
                oreQuartz = new OreBlock("ore-quartz", DeterraItems.quartz);
                eonstoneMagnetiteCluster = new OreCluster("eonstone-magnetite-cluster"){{
                    parent = blendGroup = eonstoneErodedFloor;
                    itemDrop = DeterraItems.magnetite;
                }};
                oreMagnetite = new OreBlock("ore-magnetite", DeterraItems.magnetite);
            }
        }
    }
}
