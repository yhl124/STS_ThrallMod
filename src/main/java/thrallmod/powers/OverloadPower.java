package thrallmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thrallmod.ThrallMod;

public class OverloadPower extends AbstractThrallPower
{
    public static final String POWER_ID = "Overload";
    public static final String IMG = "powers/overload.png";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final	String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OverloadPower(AbstractCreature owner, int amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        updateDescription();
        this.img = new Texture(ThrallMod.getResourcePath(IMG));
    }

    @Override
    public void atStartOfTurn()
    {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, OverloadPower.POWER_ID));
        if(!owner.hasPower("Artifact"))
        {
            AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(amount));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,new OverloadPowerApply(owner, this.amount)));

    }

    @Override
    public void atEndOfTurn(boolean isPlayer)
    {
        if(this.amount <= 0)
        {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, OverloadPower.POWER_ID));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
