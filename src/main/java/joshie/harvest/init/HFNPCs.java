package joshie.harvest.init;

import static joshie.harvest.npc.NPC.Age.ADULT;
import static joshie.harvest.npc.NPC.Age.CHILD;
import static joshie.harvest.npc.NPC.Age.ELDER;
import static joshie.harvest.npc.NPC.Gender.FEMALE;
import static joshie.harvest.npc.NPC.Gender.MALE;

import java.util.Collection;
import java.util.HashMap;

import joshie.harvest.api.HFApi;
import joshie.harvest.api.npc.INPC;
import joshie.harvest.api.npc.INPCRegistry;
import joshie.harvest.calendar.CalendarDate;
import joshie.harvest.calendar.Season;
import joshie.harvest.npc.NPC;
import joshie.harvest.npc.NPC.Age;
import joshie.harvest.npc.NPC.Gender;
import joshie.harvest.player.Town;

public class HFNPCs implements INPCRegistry {
    private static HashMap<String, INPC> npcs = new HashMap();

    @Override
    public Collection<INPC> getNPCs() {
        return npcs.values();
    }

    @Override
    public INPC get(String string) {
        return npcs.get(string);
    }

    @Override
    public INPC register(INPC npc) {
        npcs.put(npc.getUnlocalizedName(), npc);
        return npc;
    }

    @Override
    public INPC register(String unlocalised, Gender gender, Age age, int dayOfBirth, Season seasonOfBirth) {
        return register(new NPC(unlocalised, gender, age, new CalendarDate(dayOfBirth, seasonOfBirth, 1)));
    }

    public static INPC goddess; //The Goddess                        (SPAWN) - done
    public static INPC animal_owner; // Owner of the Animal Barn     (BARN)
    public static INPC cafe_owner; // Owner of the Cafe              (CAFE) - done
    public static INPC cafe_granny;// Granny of Caf� Owner           (CAFE) - done
    public static INPC seed_owner; // Sister of Yulif                (CARPENTER) - done
    public static INPC tool_owner; // Blacksmith                     (BLACKSMITH) - done
    public static INPC priest; //Married to mayor                    (CHURCH) - done
    public static INPC mayor; //Married to priest                    (TOWNHALL) - done
    public static INPC daughter_1; //Daughter of Mayor and Priest    (TOWNHALL) - done
    public static INPC daughter_2; //Daughter of Mayor and Priest    (TOWNHALL) - done
    public static INPC clock_worker; //The clock worker              (CLOCKWORKERS) - done
    public static INPC clockmaker_child; // Clockmakers Child        (CLOCKWORKERS) - done
    public static INPC gs_owner; //Owner of general store            (GENERAL STORE) - done
    public static INPC miner; //Works in the mines                   (MINING HUT) - done
    public static INPC fisherman; //Fisherman                        (FISHING HUT) - done
    public static INPC milkmaid; //Works in the Barn, Milking Cows   (GENERAL STORE) - done
    public static INPC builder; //Builds stuff for the players       (SPAWN) - done
    public static INPC poultry; //Poultry Farm Owner                 (POULTRY FARM) - done

    public static void preInit() {
        goddess = HFApi.NPC.register("goddess", FEMALE, ADULT, 8, Season.SPRING).setHeight(1.2F, 0.05F);
        animal_owner = HFApi.NPC.register("jim", MALE, ADULT, 26, Season.SPRING);
        cafe_owner = HFApi.NPC.register("liara", FEMALE, ADULT, 17, Season.SPRING);
        seed_owner = HFApi.NPC.register("jade", FEMALE, ADULT, 14, Season.SPRING);
        daughter_1 = HFApi.NPC.register("cloe", FEMALE, ADULT, 3, Season.SPRING);
        priest = HFApi.NPC.register("thomas", MALE, ELDER,  9, Season.SUMMER);
        clockmaker_child = HFApi.NPC.register("fenn", MALE, CHILD,  25, Season.SUMMER);
        cafe_granny = HFApi.NPC.register("katlin", FEMALE, ELDER,  12, Season.SUMMER);
        mayor = HFApi.NPC.register("jamie", FEMALE, ELDER,  8, Season.SUMMER);
        builder = HFApi.NPC.register("yulif", MALE, ADULT,  19, Season.SUMMER).setIsBuilder();
        tool_owner = HFApi.NPC.register("daniel", MALE, ADULT,  1, Season.WINTER);
        daughter_2 = HFApi.NPC.register("abi", FEMALE, CHILD,  27, Season.WINTER);
        clock_worker = HFApi.NPC.register("tiberius", MALE, ADULT,  15, Season.WINTER);
        gs_owner = HFApi.NPC.register("jenni", FEMALE, ADULT,  7, Season.WINTER);
        miner = HFApi.NPC.register("brandon", MALE, ADULT,  13, Season.AUTUMN).setIsMiner();
        fisherman = HFApi.NPC.register("jacob", MALE, ADULT,  28, Season.AUTUMN);
        milkmaid = HFApi.NPC.register("candice", FEMALE, ADULT,  5, Season.AUTUMN);
        poultry = HFApi.NPC.register("ondra", MALE, ADULT,  16, Season.AUTUMN);
    }

    public static void init() {
        goddess.setHome(HFBuildings.goddessPond, Town.GODDESS);
        animal_owner.setHome(HFBuildings.barn, Town.JIM);
        cafe_owner.setHome(HFBuildings.cafe, Town.LIARA);
        seed_owner.setHome(HFBuildings.carpenter, Town.JADE);
        daughter_1.setHome(HFBuildings.townhall, Town.CLOE);
        priest.setHome(HFBuildings.townhall, Town.TOWNHALL_ADULT_BEDROOM);
        clockmaker_child.setHome(HFBuildings.clockmaker, Town.FENN);
        cafe_granny.setHome(HFBuildings.cafe, Town.KATLIN);
        mayor.setHome(HFBuildings.townhall, Town.JAMIE);
        builder.setHome(HFBuildings.carpenter, Town.CARPENTER_DOWNSTAIRS);
        tool_owner.setHome(HFBuildings.blacksmith, Town.DANIEL);
        daughter_2.setHome(HFBuildings.townhall, Town.ABI);
        clock_worker.setHome(HFBuildings.clockmaker, Town.TIBERIUS);
        gs_owner.setHome(HFBuildings.supermarket, Town.JENNI);
        miner.setHome(HFBuildings.miningHut, Town.BRANDON);
        fisherman.setHome(HFBuildings.fishingHut, Town.JACOB);
        milkmaid.setHome(HFBuildings.supermarket, Town.CANDICE);
        poultry.setHome(HFBuildings.poultryFarm, Town.ONDRA);
    }
}