package thrallmod.potions;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import thrallmod.powers.OverloadPower;
import thrallmod.powers.OverloadPowerApply;

public class KeyPotion extends AbstractPotion
{
    public static final String POTION_ID = "Keypotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public KeyPotion() {
        super( NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOTTLE, PotionColor.SKILL);
        this.potency = getPotency();
        this.description = DESCRIPTIONS[0];
        this.isThrown = false;
        this.targetRequired = false;
        this.tips.add(new PowerTip(this.name, this.description));
        //this.tips.add(new PowerTip(DESCRIPTIONS[1], DESCRIPTIONS[2]));
    }

    public void use(AbstractCreature target)
    {
        AbstractPlayer p = AbstractDungeon.player;
        if(p.hasPower(OverloadPower.POWER_ID))
        {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, OverloadPower.POWER_ID));
        }
        if(p.hasPower(OverloadPowerApply.POWER_ID))
        {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(OverloadPowerApply.sum));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, OverloadPowerApply.POWER_ID));
        }
    }

    public AbstractPotion makeCopy() {
        return new KeyPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}
