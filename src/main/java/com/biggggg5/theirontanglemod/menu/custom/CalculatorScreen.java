package com.biggggg5.theirontanglemod.menu.custom;

import com.biggggg5.theirontanglemod.TheIronTangleMod;
import com.biggggg5.theirontanglemod.networking.packet.OpenListPayload;
import com.biggggg5.theirontanglemod.networking.packet.SaveTextPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class CalculatorScreen extends AbstractContainerScreen<CalculatorMenu> {
    private static final Identifier GUI_TEXTURE =
            Identifier.fromNamespaceAndPath(TheIronTangleMod.MOD_ID, "textures/gui/calculator/destination_calculator_fuelscreen_gui.png");
    private static final Identifier FUEL_TEXTURE =
            Identifier.fromNamespaceAndPath(TheIronTangleMod.MOD_ID, "textures/gui/calculator/fuel_tank.png");
    private EditBox textBox;

    public CalculatorScreen(CalculatorMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        int boxX = leftPos + 8;
        int boxY = topPos + 15;

        textBox = new EditBox(this.font, boxX, boxY, 109, 18, Component.literal("Text"));
        textBox.setMaxLength(64);
        addRenderableWidget(textBox);

        addRenderableWidget(Button.builder(Component.literal("✔"), btn -> saveText())
                .bounds(boxX + 111, boxY, 18, 18)
                .build());

        addRenderableWidget(Button.builder(Component.literal("Destination Selection"), btn -> openDestinationMenu())
                .bounds(boxX, boxY + 30, 109, 18)
                .build());

        setInitialFocus(textBox);
        textBox.setValue(menu.blockEntity.getPortalName());

    }

    private void openDestinationMenu(){
        ClientPacketDistributor.sendToServer(new OpenListPayload(menu.blockEntity.getBlockPos()));
    }

    private void saveText() {
        String text = textBox.getValue();
        menu.blockEntity.setPortalName(text);
        ClientPacketDistributor.sendToServer(new SaveTextPayload(menu.blockEntity.getBlockPos(), text));
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE, x, y, 0, 0,
                imageWidth, imageHeight, 256, 256);

        renderFuelTank(graphics, x, y);
    }

    private void renderFuelTank(GuiGraphicsExtractor guiGraphics, int x, int y) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, FUEL_TEXTURE,x + 144, y + 42, 0, 0,
                7, menu.getFuelLevel(), 7, 19);
    }

    @Override
    public boolean keyPressed(KeyEvent event) {
        if (event.isEscape()) {
            this.minecraft.player.closeContainer();
            return true;
        } else {
            return !this.textBox.keyPressed(event) && !this.textBox.canConsumeInput() ? super.keyPressed(event) : true;
        }
    }
}
