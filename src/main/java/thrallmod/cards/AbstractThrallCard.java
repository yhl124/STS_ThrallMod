package thrallmod.cards;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import thrallmod.ThrallMod;
import thrallmod.patches.AbstractCardEnum;

public abstract class AbstractThrallCard extends CustomCard{
	public boolean reshuffleOnUse = false; //if true -> don't discard on next use, has to be reset in the "use" method
	public boolean reshuffleOnDiscardFromHand = false; //if true -> reshuffle in draw pile if discarded while in hand, not used atm
	protected AbstractCard cardPreviewTooltip;
	
	public AbstractThrallCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardRarity rarity, CardTarget target, int cardPool){
		super(id, name, ThrallMod.getResourcePath(img), cost, rawDescription, type, AbstractCardEnum.THRALL, rarity, target);
	}

	@Override
	public void render(SpriteBatch sb, boolean selected) {
		if (!Settings.hideCards) {
			if (this.flashVfx != null) {
				this.flashVfx.render(sb);
			}
			try {
				Field f = AbstractCard.class.getDeclaredField("hovered"); //NoSuchFieldException
				f.setAccessible(true);
				this.renderCard(sb, (boolean) f.get(this), selected);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

			this.hb.render(sb);
		}
	}

	@Override
	public void renderUpgradePreview(SpriteBatch sb) {
		this.upgraded = true;
		this.name = this.originalName + "+";
		this.initializeTitle();

		try {
			Field f = AbstractCard.class.getDeclaredField("hovered");
			f.setAccessible(true);
			this.renderCard(sb, (boolean) f.get(this), false);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		this.name = this.originalName;
		this.initializeTitle();
		this.upgraded = false;
		this.resetAttributes();
	}

	@Override
	public void renderWithSelections(SpriteBatch sb) {
		this.renderCard(sb, false, true);
	}

	private void renderCard(SpriteBatch sb, boolean hovered, boolean selected) {
		try  {
			Method isOnScreenMethod = AbstractCard.class.getDeclaredMethod("isOnScreen");
			isOnScreenMethod.setAccessible(true);
			Method updateGlowMethod = AbstractCard.class.getDeclaredMethod("updateGlow");
			updateGlowMethod.setAccessible(true);
			Method renderGlowMethod = AbstractCard.class.getDeclaredMethod("renderGlow",SpriteBatch.class);
			renderGlowMethod.setAccessible(true);
			Method renderImageMethod = AbstractCard.class.getDeclaredMethod("renderImage",SpriteBatch.class,boolean.class,boolean.class);
			renderImageMethod.setAccessible(true);
			Method renderTitleMethod = AbstractCard.class.getDeclaredMethod("renderTitle",SpriteBatch.class);
			renderTitleMethod.setAccessible(true);
			Method renderTypeMethod = AbstractCard.class.getDeclaredMethod("renderType",SpriteBatch.class);
			renderTypeMethod.setAccessible(true);
			Method renderTintMethod = AbstractCard.class.getDeclaredMethod("renderTint",SpriteBatch.class);
			renderTintMethod.setAccessible(true);
			Method renderEnergyMethod = AbstractCard.class.getDeclaredMethod("renderEnergy",SpriteBatch.class);
			renderEnergyMethod.setAccessible(true);
			Method renderBackMethod = AbstractCard.class.getDeclaredMethod("renderBack",SpriteBatch.class,boolean.class,boolean.class);
			renderBackMethod.setAccessible(true);;

			if (!Settings.hideCards) {
				if (!(boolean)isOnScreenMethod.invoke(this)) {
					return;
				}
				if (!this.isFlipped) {
					updateGlowMethod.invoke(this);
					renderGlowMethod.invoke(this, sb);
					if (type == CardType.CURSE) { //workaround to have witch curses with black background
						color = CardColor.CURSE;
					}
					renderImageMethod.invoke(this, sb, hovered, selected);
					if (type == CardType.CURSE) {
						color = AbstractCardEnum.THRALL;
					}
					renderTypeMethod.invoke(this, sb);
					renderTitleMethod.invoke(this, sb);
					if (Settings.lineBreakViaCharacter) {
						renderDescriptionCN(sb);
					} else {
						renderDescription(sb);
					}
					renderTintMethod.invoke(this, sb);
					renderEnergyMethod.invoke(this, sb);
				} else {
					renderBackMethod.invoke(this, sb, hovered, selected);
					hb.render(sb);
				}
			}
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void renderDescriptionCN(SpriteBatch sb) {
		try {
			Method renderDescriptionCNMethod = AbstractCard.class.getDeclaredMethod("renderDescriptionCN",SpriteBatch.class);
			renderDescriptionCNMethod.setAccessible(true);
			renderDescriptionCNMethod.invoke(this, sb);
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void renderDescription(SpriteBatch sb) {
		try {
			Method renderDescriptionMethod = AbstractCard.class.getDeclaredMethod("renderDescription",SpriteBatch.class);
			renderDescriptionMethod.setAccessible(true);
			renderDescriptionMethod.invoke(this, sb);
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void renderCardTip(SpriteBatch sb) {
		super.renderCardTip(sb);
		boolean renderTip = (boolean) ReflectionHacks.getPrivate(this, AbstractCard.class, "renderTip");
		
		if (cardPreviewTooltip != null && !Settings.hideCards && renderTip) {
			if (AbstractDungeon.player != null && AbstractDungeon.player.isDraggingCard) {
				return;
			}
			float dx = (AbstractCard.IMG_WIDTH / 1.2f + 16f) * drawScale;
			float dy = (AbstractCard.IMG_WIDTH / 4f) * drawScale;
			if (current_x > Settings.WIDTH * 0.75f) {
				cardPreviewTooltip.current_x = current_x + dx;
			} else {
				cardPreviewTooltip.current_x = current_x - dx;
			}
			cardPreviewTooltip.current_y = current_y + dy;
			cardPreviewTooltip.drawScale = drawScale/1.5f;
			cardPreviewTooltip.render(sb);
		}
	}
}
