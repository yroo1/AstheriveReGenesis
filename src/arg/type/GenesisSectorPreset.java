package arg.type;

import mindustry.type.*;
import mindustry.ctype.*;

public class GenesisSectorPreset extends SectorPreset{
    public GenesisSector sector;
    //thank you itzcraft!!
    public boolean requireRestart = false;

    public GenesisSectorPreset(String name, Planet planet, int sector){
        super(name, planet, sector);
    }

    @Override
    public ContentType getContentType(){
        return ContentType.sector;
    }
}