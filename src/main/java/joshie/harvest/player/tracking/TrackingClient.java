package joshie.harvest.player.tracking;

import joshie.harvest.api.knowledge.Note;
import joshie.harvest.core.helpers.ChatHelper;
import joshie.harvest.core.helpers.TextHelper;
import joshie.harvest.core.util.holders.ItemStackHolder;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

@SideOnly(Side.CLIENT)
public class TrackingClient extends Tracking {
    public Set<ResourceLocation> getLearntRecipes() {
        return recipes;
    }

    public Set<ResourceLocation> getLearntNotes() {
        return notes;
    }

    @Override
    public boolean learnNote(Note note) {
        if (super.learnNote(note)) {
            if (note.isSecret()) ChatHelper.displayChat(TextHelper.translate("note.discovered") + " " + TextFormatting.AQUA + note.getTitle());
            else ChatHelper.displayChat(TextHelper.translate("note.learnt") + " " + TextFormatting.YELLOW + note.getTitle());
            return true;
        } else return false;
    }

    public void setObtained(Set<ItemStackHolder> obtained) {
        this.obtained = obtained;
    }

    public void setRecipes(Set<ResourceLocation> recipes) {
        this.recipes = recipes;
    }

    public void setNotes(Set<ResourceLocation> notes) {
        this.notes = notes;
    }
}