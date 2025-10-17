package dev.ugamii.whoareyou.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createConfigScreen;
    }

    private Screen createConfigScreen(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.of("Who are you?"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("whoareyou.config.nameDisplaying"))

                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("whoareyou.config.nameDisplaying.own"))
                                .binding(
                                        WhoAreYouConfig.enabledOwnNameDefault,
                                        () -> WhoAreYouConfig.enabledOwnName,
                                        value -> WhoAreYouConfig.enabledOwnName = value
                                )
                                .controller(TickBoxControllerBuilder::create)
                                .build())

                        .option(Option.<Boolean>createBuilder()
                                .name(Text.translatable("whoareyou.config.nameDisplaying.otherPlayers"))
                                .binding(
                                        WhoAreYouConfig.enabledOtherPlayersNameDefault,
                                        () -> WhoAreYouConfig.enabledOtherPlayersName,
                                        value -> WhoAreYouConfig.enabledOtherPlayersName = value
                                )
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())
                .save(WhoAreYouConfig::save)
                .build()
                .generateScreen(parent);
    }
}
