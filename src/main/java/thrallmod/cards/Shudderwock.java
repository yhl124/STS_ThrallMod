package thrallmod.cards;

import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Random;

public class Shudderwock extends AbstractThrallCard
{
    public static final String ID = "Shudderwock";
    public static final	String IMG = "cards/shudderwock.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 4;
    private static final int UPGRADE_COST = 3;

    public Shudderwock()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.exhaust = true;
        //this.baseMagicNumber = POWER;
        //this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<Integer> num = new ArrayList<>();
        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        Random random = new Random();
        int r ;

        for(int i = 0; i < temp ; i++)
        {
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if(!mo.isDead)
            {
                num.add(i);
            }
        }

        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat)
        {
            r = random.nextInt(num.size());
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(num.get(r));
            if(c.type == CardType.ATTACK && c.target == CardTarget.ENEMY && c.cost != -1)
            {
                AbstractCard c2 = c.makeCopy();
                c2.modifyCostForTurn(-c2.costForTurn);
                c2.exhaust = true;

                AbstractDungeon.actionManager.addToBottom(new ShowCardAction(c2));
                p.useCard(c2, mo , 0);
            }
            else if(c.type == CardType.ATTACK && c.target == CardTarget.ALL_ENEMY && c.cost != -1)
            {
                AbstractCard c2 = c.makeCopy();
                c2.modifyCostForTurn(-c2.costForTurn);
                c2.exhaust = true;

                AbstractDungeon.actionManager.addToBottom(new ShowCardAction(c2));
                p.useCard(c2, mo , 0);
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Shudderwock();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }
}
