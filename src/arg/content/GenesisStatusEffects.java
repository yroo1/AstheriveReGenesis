package arg.content;

import arc.math.*;
import arc.graphics.*;
import mindustry.content.*;
import mindustry.type.*;

import static mindustry.content.StatusEffects.*;

public class GenesisStatusEffects {
    public static StatusEffect seen;
    public static void load(){
        seen = new StatusEffect("seen"){{
            color = Color.valueOf("ffab84");
            effect = Fx.none;
        }};
    }
}
