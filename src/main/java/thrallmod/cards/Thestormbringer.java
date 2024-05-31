package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Random;

public class Thestormbringer extends AbstractThrallCard
{
    public static final String ID = "Thestormbringer";
    public static final	String IMG = "cards/thestormbringer.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;

    public Thestormbringer()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int hand_num = p.hand.size();
        Random random = new Random();

        for(int i = 0; i<hand_num ; i++)
        {
            p.hand.removeTopCard();
        }

        for(int j = 0; j < hand_num ; j++)
        {
            int r = random.nextInt(4);
            AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat();
            while(c.rarity == CardRarity.COMMON || c.rarity == CardRarity.SPECIAL || c.rarity == CardRarity.CURSE)
            {
                c = AbstractDungeon.returnTrulyRandomCardInCombat();
            }
            if(c.cost > 0)
            {
                c.modifyCostForTurn(-1);
            }
            if(!c.upgraded && r == 3)
            {
                c.upgrade();
            }
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
        }
    }

    public AbstractCard makeCopy() {
        return new Thestormbringer();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}
