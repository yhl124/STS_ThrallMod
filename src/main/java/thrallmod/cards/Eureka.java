package thrallmod.cards;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Random;

public class Eureka extends AbstractThrallCard
{
    public static final String ID = "Eureka";
    public static final	String IMG = "cards/eureka.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 1;
    private static final int UPGRADE_BONUS = 1;
    private static final int UPGRADE_COST = 0;

    public Eureka()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);

        this.baseMagicNumber = POWER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractCard c;
        c = p.hand.getRandomCard(CardType.POWER, true);

        CardGroup g1 = p.hand.getPowers();
        if (g1.size() > 0)
        {
            c.modifyCostForTurn(-c.costForTurn);
            p.useCard(c, null, 0);
            //AbstractDungeon.actionManager.addToBottom(new UseCardAction(c, p));
        }
        /*
        if (this.upgraded)
        {
            CardGroup g2 = p.hand.getPowers();
            if (g2.size() > 0)
            {
                int r = random.nextInt(p.hand.size());
                c2 = p.hand.getNCardFromTop(r);
                while(c == c2 || c2.type != CardType.POWER)
                {
                    r = random.nextInt(p.hand.size());
                    c2 = p.hand.getNCardFromTop(r);
                }

                c2.modifyCostForTurn(-c2.costForTurn);
                p.useCard(c2, null, 0);
                //AbstractDungeon.actionManager.addToBottom(new UseCardAction(c2, p));
            }
        }
        */
    }

    public AbstractCard makeCopy()
    {
        return new Eureka();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}