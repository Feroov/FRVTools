package com.feroov.frv.item.custom.ranged.render;

import com.feroov.frv.item.custom.ranged.Musket;
import com.feroov.frv.item.custom.ranged.model.MusketModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class MusketRender extends GeoItemRenderer<Musket>
{
	public MusketRender() {
		super(new MusketModel());
	}
}