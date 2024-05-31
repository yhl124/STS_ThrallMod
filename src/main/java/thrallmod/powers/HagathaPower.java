package thrallmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thrallmod.ThrallMod;

public class HagathaPower extends AbstractThrallPower
{
    public static final String POWER_ID = "Hagathapower";
    public static final String IMG = "powers/hagathapower.png";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final	String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HagathaPower(AbstractCreature owner)
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
        if(card.type == AbstractCard.CardType.SKILL)
        {
            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.SKILL);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, 1));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}