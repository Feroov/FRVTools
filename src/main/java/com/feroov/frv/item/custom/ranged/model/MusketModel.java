package com.feroov.frv.item.custom.ranged.model;

import com.feroov.frv.Frv;
import com.feroov.frv.item.custom.ranged.Musket;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MusketModel extends AnimatedGeoModel<Musket>
{
	@Override
	public ResourceLocation getModelResource(Musket object)
	{
		return new ResourceLocation(Frv.MOD_ID, "geo/musket.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Musket object)
	{
		return new ResourceLocation(Frv.MOD_ID, "textures/items/musket.png");
	}

	@Override
	public ResourceLocation getAnimationResource(Musket animatable)
	{
		return new ResourceLocation(Frv.MOD_ID, "animations/empty.animation.json");
	}
}
