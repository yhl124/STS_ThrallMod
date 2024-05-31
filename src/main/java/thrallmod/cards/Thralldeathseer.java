package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thrallmod.powers.DThrallPower;

public class Thralldeathseer extends AbstractThrallCard
{
    public static final String ID = "Thralldeathseer";
    public static final	String IMG = "cards/thralldeathseer.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;

    private static final int POOL = 1;

    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;

    public Thralldeathseer()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(!p.hasPower("DThrallpower"))
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DThrallPower(p)));
        }

        for(AbstractCard tc : p.hand.group)
        {
            if(!tc.upgraded)
            {
                tc.upgrade();
            }
        }
    }

    public AbstractCard makeCopy()
    {
        return new Thralldeathseer();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}
