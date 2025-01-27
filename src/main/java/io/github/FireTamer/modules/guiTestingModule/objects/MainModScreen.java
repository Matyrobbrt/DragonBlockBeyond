package io.github.FireTamer.modules.guiTestingModule.objects;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import io.github.FireTamer.DBB_Main;
import io.github.FireTamer.modules.guiTestingModule.objects.buttons.DBB_Button;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MainModScreen extends Screen {
    //private static final int TEXTURE_WIDTH = 714;
    //private static final int TEXTURE_HEIGHT = 600;

    private static final int TEXTURE_WIDTH = 1024;
    private static final int TEXTURE_HEIGHT = 1024;

    private static final ResourceLocation SCREEN_SELECTOR = new ResourceLocation(DBB_Main.MOD_ID, "textures/gui/player_menu_screen_selector.png");
    //private static final ResourceLocation SCREEN_SELECTOR = new ResourceLocation(DBB_Main.MOD_ID, "textures/gui/player_menu_screen_selector_2.png");

    public MainModScreen() {
        //super(new TranslationTextComponent("menu.player_screen"));
        super(new StringTextComponent(""));
        this.width = 714;
        this.height = 600;
    }

    protected void init() {
        this.createScreen();
    }

    private void createScreen() {
        int i = -16;
        int j = 98;

        Timer timer1 = new Timer(2000, actionEvent -> this.addButton(new DBB_Button(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20, new TranslationTextComponent("menu.returnToGame"), (p_213070_1_) -> {
            this.minecraft.setScreen((Screen)null);
            this.minecraft.mouseHandler.grabMouse();
            }))
        );

        timer1.setRepeats(false);
        timer1.start();


        /**
        this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20, new TranslationTextComponent("menu.returnToGame"), (p_213070_1_) -> {
            this.minecraft.setScreen((Screen)null);
            this.minecraft.mouseHandler.grabMouse();
        }));
        **/
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void tick() {
        super.tick();
    }

    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(stack);
        //drawCenteredString(stack, this.font, this.title, this.width / 2, 40, 16777215);

        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }

    @Override
    public void renderBackground(MatrixStack stack) {
        super.renderBackground(stack);
        GlStateManager._color4f(1, 1, 1, 1);
        this.minecraft.getTextureManager().bind(SCREEN_SELECTOR);
        int relX = (this.width - TEXTURE_WIDTH) / 714;
        int relY = (this.height - TEXTURE_HEIGHT) / 600;
        this.blit(stack, relX, relY, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        //super.renderBackground(stack);

        // draw the background
        //int xStart = (this.width - this.width) / 1024;
        //int yStart = (this.height - this.height) / 1024;
        //this.minecraft.getTextureManager().bind(SCREEN_SELECTOR);
        //this.blit(stack, xStart, yStart, 0,0, this.width, this.height);
    }
}
