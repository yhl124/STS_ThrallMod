package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thrallmod.powers.OverloadPower;
import thrallmod.powers.OverloadPowerApply;

public class Eternalsentinel extends AbstractThrallCard
{
    public static final String ID = "Eternalsentinel";
    public static final	String IMG = "cards/eternalsentinel.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 1;
    private static final int COST_UPGRADED = 0;

    public Eternalsentinel()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
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

    public AbstractCard makeCopy() {
        return new Eternalsentinel();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(COST_UPGRADED);
        }
    }
}
