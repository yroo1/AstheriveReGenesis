package arg.content.deterra;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class DeterraItems{
    public static Item
    quartz, magnetite, carbon, polterite, nylon;
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

        nylon = new Item("nylon", Color.valueOf("7AC194")){{
            hardness = 5;
            cost = 1.5f;
        }};
        
        deterraItems.addAll(
        quartz, magnetite, carbon, polterite, nylon
        );
    }
}
