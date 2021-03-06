package joshie.harvest.festivals.newyears;

import joshie.harvest.api.HFApi;
import joshie.harvest.api.npc.NPC;
import joshie.harvest.api.quests.HFQuest;
import joshie.harvest.cooking.HFCooking;
import joshie.harvest.cooking.item.ItemIngredients.Ingredient;
import joshie.harvest.cooking.item.ItemMeal.Meal;
import joshie.harvest.npcs.HFNPCs;
import joshie.harvest.quests.base.QuestFestivalMultichat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

import static joshie.harvest.core.registry.ShippingRegistry.SELL_VALUE;

@HFQuest("festival.new.years")
public class NewYearsQuests extends QuestFestivalMultichat {
    public NewYearsQuests() {
        setNPCs(HFNPCs.CARPENTER, HFNPCs.FLOWER_GIRL, HFNPCs.MAYOR, HFNPCs.TRADER);
    }

    @Override
    @Nullable
    protected String getLocalizedScript(NPC npc) {
        //Yulif tells the player it's been another great new year and he's grateful to have been around since the very beginning
        //Yulif then gives the player 7 plates of cooked riceballs
        if (npc == HFNPCs.CARPENTER) return getLocalized("riceballs");
            //Jade says she's glad that the new year is here and that she misses the earlier days
            //She is grateful and gives you 3 bamboo rice
        else if (npc == HFNPCs.FLOWER_GIRL) return getLocalized("rice");
            //The mayor is happy about a new year having passed, she will give player a random gift
            //of gold, based on their relationship with the mayor, she'll give you one gold for every point
        else if (npc == HFNPCs.MAYOR) return getLocalized("gold");
            //The trader has had a great time, he explains where he comes from they don't celebrate the new year with riceballs
            //But with wine, so he happily gives the player a bottle of wine
        else if (npc == HFNPCs.TRADER) return getLocalized("wine");
        else return null;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onChatClosed(EntityPlayer player, NPC npc) {
        if (npc == HFNPCs.CARPENTER) rewardItem(player, HFCooking.MEAL.getCreativeStack(Meal.RICEBALLS_TOASTED, 7));
        else if (npc == HFNPCs.FLOWER_GIRL) rewardItem(player, HFCooking.MEAL.getCreativeStack(Meal.RICE_BAMBOO, 3));
        else if (npc == HFNPCs.MAYOR) rewardGold(player, HFApi.player.getRelationsForPlayer(player).getRelationship(npc.getUUID()));
        else if (npc == HFNPCs.TRADER) {
            ItemStack wine = HFCooking.INGREDIENTS.getStackFromEnum(Ingredient.WINE);
            wine.setStackDisplayName("Canard-Duchêne");
            wine.getTagCompound().setLong(SELL_VALUE, 5000L);
            rewardItem(player, wine);
        }
    }
}
