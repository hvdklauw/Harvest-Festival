package joshie.harvest.debug;

import joshie.harvest.api.crops.Crop;
import joshie.harvest.api.trees.Tree;
import joshie.harvest.core.commands.HFCommand;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@HFCommand
@SuppressWarnings("unused")
public class CommandExportTreeList extends CommandExportHeld {
    @Override
    public String getCommandName() {
        return "export-trees-list";
    }

    @Override
    public boolean isExportable(ItemStack stack) {
        return true;
    }

    @Override
    protected void export(ItemStack stack, String... parameters) {
        StringBuilder builder = new StringBuilder();
        builder.append("<includeonly>{| class=\"wikitable\" id=\"navbox\"\n" +
                "! colspan=2 | [[Fruit Trees]]");

        List<String> fruitList = new ArrayList<>();
        List<String> treeList = new ArrayList<>();
        for (Crop crop: Crop.REGISTRY) {
            if (crop instanceof Tree) {
                fruitList.add(crop.getCropStack(1).getDisplayName());
                treeList.add(crop.getCropStack(1).getDisplayName() + " Tree");
            }
        }

        addToBuilderAndSort("Fruit", fruitList, builder);
        addToBuilderAndSort("Tree", treeList, builder);

        builder.append("\n|-\n" +
        "|}{{mainonly|[[Category:Fruit Trees]]}}</includeonly><noinclude>{{{{FULLPAGENAME}}/doc}}</noinclude>\n");
        Debug.save(builder);
    }

    private void addToBuilderAndSort(String name, List<String> list, StringBuilder builder, boolean sort) {
        if (sort) {
            builder.append("\n|-\n" +
                    "!" + name +"\n|");
            Collections.sort(list, (o1, o2) -> {return o1.compareTo(o2); });
        }

        for (int i = 0; i < list.size(); i++) {
            if (i != 0) builder.append(" • ");
            builder.append("[[" + list.get(i) + "]]");
        }
    }

    private void addToBuilderAndSort(String name, List<String> list, StringBuilder builder) {
        addToBuilderAndSort(name, list, builder, true);
    }
}
