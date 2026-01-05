package arg.content;

import arc.graphics.Color;
import mindustry.game.Team;
import arg.graphics.GenesisPal;

public class GenesisTeams {
    public static Team terraplasm;

    public static void load() {
        terraplasm = newTeam(7, "terraplasm", GenesisPal.terraplasmTeam);
    }

    //modify any of 256 teams' properties
    private static Team newTeam(int id, String name, Color color) {
        Team team = Team.get(id);
        team.name = name;
        team.color.set(color);
        team.palette[0] = color;
        team.palette[1] = color.cpy().mul(0.7f);
        team.palette[2] = color.cpy().mul(0.4f);
        for(int i = 0; i < 3; i++){
            team.palettei[i] = team.palette[i].rgba();
        }

        return team;
    }
}