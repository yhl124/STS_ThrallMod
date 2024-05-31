package thrallmod.relics;

import com.badlogic.gdx.graphics.Texture;

import basemod.abstracts.CustomRelic;
import thrallmod.ThrallMod;

public abstract class AbstractThrallRelic extends CustomRelic
{
	public AbstractThrallRelic(String id, String img, RelicTier tier, LandingSound sfx)
	{
		super(id, new Texture(ThrallMod.getResourcePath(img)), tier, sfx);
	}
}