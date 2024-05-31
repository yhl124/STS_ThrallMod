package thrallmod.characters;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.BaseMod;
import basemod.abstracts.CustomPlayer;

import thrallmod.ThrallMod;
import thrallmod.cards.Strike_Thrall;
import thrallmod.patches.AbstractCardEnum;
import thrallmod.patches.ThrallEnum;



public class ThrallCharacter extends CustomPlayer{
	
    public static final int ENERGY_PER_TURN = 3;

	public static final String SELECT_THRALL = "images/selectThrall.ogg";
    public static final String DISCIPLE_SKELETON_ATLAS = "images/char/idle/skeleton.atlas"; // spine animation atlas
    public static final String DISCIPLE_SKELETON_JSON = "images/char/idle/skeleton.json"; // spine animation json

    public static final String[] orbTextures = {
			"images/char/orb/layer1.png",
			"images/char/orb/layer2.png",
			"images/char/orb/layer3.png",
			"images/char/orb/layer4.png",
			"images/char/orb/layer5.png",
			"images/char/orb/layer6.png",
			"images/char/orb/layer1d.png",
			"images/char/orb/layer2d.png",
			"images/char/orb/layer3d.png",
			"images/char/orb/layer4d.png",
			"images/char/orb/layer5d.png"
	};

	public ThrallCharacter(String name) {
		super(name, ThrallEnum.THRALL, orbTextures, "images/char/orb/vfx.png", (String)null, (String)null);
		dialogX = drawX + 0.0f * Settings.scale;
		dialogY = drawY + 220.0f * Settings.scale;
		
		initializeClass(null, ThrallMod.CHAR_SHOULDER_2, ThrallMod.CHAR_SHOULDER_1, ThrallMod.CHAR_CORPSE,
				getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        this.atlas = new TextureAtlas();
        loadAnimation(DISCIPLE_SKELETON_ATLAS, DISCIPLE_SKELETON_JSON, 1.0F);
	}
	
	@Override
	public void applyEndOfTurnTriggers() {
		for (AbstractPower p : powers) {
			p.atEndOfTurn(true);
		}
	}

	@Override
	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<>();

        retVal.add("Strike_Thrall");
        retVal.add("Strike_Thrall");
        retVal.add("Strike_Thrall");
		retVal.add("Strike_Thrall");
		retVal.add("Strike_Thrall");
		retVal.add("Defend_Thrall");
		retVal.add("Defend_Thrall");
		retVal.add("Defend_Thrall");
		retVal.add("Defend_Thrall");
        retVal.add("Defend_Thrall");
		retVal.add("Lightningbolt");
		retVal.add("Thecoin");

		/*
		retVal.add("Unstable");
		retVal.add("Unstable");
		retVal.add("Thecoin");
		retVal.add("Thecoin");
		retVal.add("Thecoin");
		retVal.add("Doomhammer");
		retVal.add("Doomhammer");
		retVal.add("Jadecheiftain");
		*/

		return retVal;
		//return getAllCards();
	}
	
	//for debug
	private static ArrayList<String> getAllCards() {
		ArrayList<String> out = new ArrayList<String>();
		for (AbstractCard card : BaseMod.getCustomCardsToAdd()) {
			out.add(card.cardID);
		}
		return out;
	}
	
	@Override
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<>();
		retVal.add("Totem");
		//retVal.add("Lavashock");
		UnlockTracker.markRelicAsSeen("Totem");
		return retVal;
	}
	
	
	@Override
	public CharSelectInfo getLoadout() {
		return new CharSelectInfo("Thrall", "Hearthstone thrall",
				70, 70, 0, 99, 5,
			this, getStartingRelics(), getStartingDeck(), false);
	}
	

	@Override
	public String getTitle(PlayerClass var1) {
		return "Thrall";
	}

	@Override
	public CardColor getCardColor() {
		return AbstractCardEnum.THRALL;
	}

	@Override
	public List<CutscenePanel> getCutscenePanels() {
		List<CutscenePanel> panels = new ArrayList();
		panels.add(new CutscenePanel("images/scenes/ending1.png"));
		panels.add(new CutscenePanel("images/scenes/ending2.png"));
		panels.add(new CutscenePanel("images/scenes/ending3.png"));
		return panels;
	}

	@Override
	public void updateOrb(int orbCount) {
		this.energyOrb.updateOrb(orbCount);
	}

	@Override
	public TextureAtlas.AtlasRegion getOrb() {
		return new TextureAtlas.AtlasRegion(ImageMaster.loadImage(ThrallMod.CARD_ENERGY_ORB), 0, 0, 24, 24);
	}

	@Override
	public AbstractCard getStartCardForEvent() { return new Strike_Thrall(); }

	@Override
	public Color getCardTrailColor() {
		return Color.SLATE;
	}

	@Override
	public String getLeaderboardCharacterName() {
		return "Thrall";
	}

	@Override
	public int getAscensionMaxHPLoss() {
		return 4;
	}

	@Override
	public void doCharSelectScreenSelectEffect() {
		CardCrawlGame.sound.playA(SELECT_THRALL, MathUtils.random(-0.2f, 0.2f));
	}
	@Override
	public String getCustomModeCharacterButtonSoundKey() {
		return SELECT_THRALL;
	}


	@Override
	public String getLocalizedCharacterName() {
		return "Thrall";
	}


	@Override
	public AbstractPlayer newInstance() {
		return new ThrallCharacter(this.name);
	}
	
	@Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }
	
	@Override
	public Color getCardRenderColor() {
		return Color.SLATE;
	}
	
	@Override
	public String getVampireText() {
		 return Vampires.DESCRIPTIONS[5];
	}

	@Override
	public Color getSlashAttackColor() {
		return Color.SLATE;
	}
	
	@Override
	public String getSpireHeartText() {
		return "NL You invoke an ominous curse...";
	}
	
	@Override
	public AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.POISON, AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.POISON, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_VERTICAL};
	}
}
