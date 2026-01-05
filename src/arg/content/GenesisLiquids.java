package arg.content;

import arc.graphics.*;
import mindustry.type.*;
import mindustry.content.*;

public class GenesisLiquids{
    public static Liquid watergel, biomass, oxygen, tritagen;
    public static void load(){

        watergel = new Liquid("watergel", Color.valueOf("4C5757")){{
            heatCapacity = 0.6f;
            effect = StatusEffects.wet;
            boilPoint = 0.6f;
            gasColor = Color.grays(0.9f);
            alwaysUnlocked = true;
        }};

        biomass = new CellLiquid("biomass", Color.valueOf("98915E")){{
            heatCapacity = 0.3f;
            temperature = 0.7f;
            viscosity = 0.4f;
            flammability = 0f;
            capPuddles = false;
            spreadTarget = Liquids.water;
            moveThroughBlocks = true;
            incinerable = false;
            blockReactive = false;
            canStayOn.addAll(Liquids.water, watergel, Liquids.oil, Liquids.cryofluid);

            colorFrom = Color.valueOf("524809");
            colorTo = Color.valueOf("D4C98A");
        }};

        oxygen = new Liquid("oxygen", Color.valueOf("52557F")){{
            gas = true;
            barColor = Color.valueOf("52557F");
            explosiveness = 1f;
            flammability = 1f;
        }};

        tritagen = new Liquid("tritagen", Color.valueOf("8BE7AF")){{
            gas = true;
            barColor = Color.valueOf("8BE7AF");
        }};
    }
}
