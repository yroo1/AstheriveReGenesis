package arg;

import arc.*;
import arc.util.*;
import arg.content.deterra.blocks.*;
import arg.content.deterra.*;
import arg.content.*;
import arg.content.terraplasm.*;
import arg.ui.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ARGMod extends Mod{

    public ARGMod(){
        Log.info("Loaded ARGMod constructor.");

        //listen for game load event
        /*
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("hi").row();
                dialog.cont.button("ok", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
        */
    }
    @Override
    public void init(){
        super.init();
        TeamsUI.init();
    }
    @Override
    public void loadContent(){
        GenesisTeams.load();
        GenesisStatusEffects.load();
        DeterraItems.load();
        GenesisLiquids.load();
        DeterraUnitTypes.load();
        TerraUnitTypes.load();
        DeterraBlocks.load();
        GenesisPlanets.load();
        DeterraSectorPresets.load();
        DeterraTechTree.load();
    }
}
