package thrallmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thrallmod.ThrallMod;

public class Troggpower extends AbstractThrallPower
{
    public static final String POWER_ID = "Troggpower";
    public static final String IMG = "powers/troggpower.png";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final	String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Troggpower(AbstractCreature owner)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        //this.amount = amount;
        updateDescription();
        this.img = new Texture(ThrallMod.getResourcePath(IMG));
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m)
    {
        if(card.cardID == "Crackle" || card.cardID == "AncestralKnow" || card.cardID == "Elementaldestruction" || card.cardID == "Forkedlightning" || card.cardID == "Lavaburst"
                || card.cardID == "Lightningbolt" || card.cardID == "Lightningstorm" || card.cardID == "Volcano" || card.cardID == "Doomhammer" || card.cardID == "Earthelemental"
                || card.cardID == "Drakkaridefender" || card.cardID == "Jinyu" || card.cardID == "Fireguard" || card.cardID == "Finderskeepers" || card.cardID == "Beakeredlightning"
                || card.cardID == "Zap" || card.cardID == "Dustdevil" || card.cardID == "Dunemaul")
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new StrengthPower(owner, card.baseMagicNumber), card.baseMagicNumber));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
