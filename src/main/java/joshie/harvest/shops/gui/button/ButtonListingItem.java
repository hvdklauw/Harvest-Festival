package joshie.harvest.shops.gui.button;

import joshie.harvest.api.shops.IPurchaseableMaterials;
import joshie.harvest.core.helpers.StackRenderHelper;
import joshie.harvest.shops.gui.GuiNPCShop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class ButtonListingItem extends ButtonListing<IPurchaseableMaterials> {
    private final ItemStack icon;
    private final int cost;

    public ButtonListingItem(ItemStack icon, int cost, GuiNPCShop shop, IPurchaseableMaterials purchasable, int buttonId, int x, int y) {
        super(shop, purchasable, buttonId, x, y);
        this.icon = icon;
        this.cost = cost;
    }

    @Override
    protected void drawForeground(Minecraft mc, FontRenderer fontrenderer, int j) {
        StackRenderHelper.drawStack(purchasable.getDisplayStack(), xPosition + 2, yPosition + 1, 1F);
        drawString(fontrenderer, displayString, xPosition + 20, yPosition + (height - 8) / 2, j);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        //Draw the cost
        String cost = shop.getCostAsString(this.cost);
        int width = fontrenderer.getStringWidth(cost);
        StackRenderHelper.drawStack(icon, xPosition + 188 - width, yPosition + 1, 1F);
        drawString(fontrenderer, cost, xPosition + 180 - width, yPosition + (height - 8) / 2, j);
    }
}
