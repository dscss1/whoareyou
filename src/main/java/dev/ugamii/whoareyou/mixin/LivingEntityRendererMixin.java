package dev.ugamii.whoareyou.mixin;

import dev.ugamii.whoareyou.config.WhoAreYouConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
	@Inject(
			method = "hasLabel(Lnet/minecraft/entity/LivingEntity;D)Z",
			at = @At("HEAD"),
			cancellable = true
	)
	private void whoareyou$showNameLabel(LivingEntity entity, double distanceSq, CallbackInfoReturnable<Boolean> cir) {
		var cameraEntity = MinecraftClient.getInstance().cameraEntity;

		if (entity == null || cameraEntity == null) return;

		if (WhoAreYouConfig.enabledOwnName && entity == cameraEntity) {
			cir.setReturnValue(MinecraftClient.isHudEnabled());
		} else if (WhoAreYouConfig.enabledOtherPlayersName && entity != cameraEntity) {
			cir.setReturnValue(MinecraftClient.isHudEnabled());
		}
	}
}
