package arg.content.deterra;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class DeterraItems{
    public static Item
    quartz, magnetite, carbon, polterite, terraCompound;
    public static final Seq<Item> deterraItems = new Seq<>();

    public static void load(){
        quartz = new Item("quartz", Color.valueOf("BCC3BF")){{
            hardness = 1;
            cost = 0.5f;
            alwaysUnlocked = true;
        }};

        magnetite = new Item("magnetite", Color.valueOf("6C6E75")){{
            hardness = 2;
            cost = 0.7f;
        }};

        carbon = new Item("carbon", Color.valueOf("808480")){{
            hardness = 1;
            cost = 0.5f;
        }};

        polterite = new Item("polterite", Color.valueOf("888283")){{
            hardness = 3;
            cost = 0.8f;
        }};

        terraCompound = new Item("terra-compound", Color.valueOf("6F8F6C")){{
            hardness = 20;
            cost = 5f;
            charge = 8f;
            radioactivity = 2f;
        }};
        
        deterraItems.addAll(
        quartz, magnetite, carbon, polterite, terraCompound
        );
    }
}
