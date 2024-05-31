package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Random;

public class Unstableevolution extends AbstractThrallCard
{
    public static final String ID = "Unstable";
    public static final	String IMG = "cards/unstable.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;

    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 1;

    public Unstableevolution()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);

        this.baseMagicNumber = POWER;
        this.magicNumber = this.baseMagicNumber;

        this.isEthereal = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<Integer> occ = new ArrayList<Integer>();

        //손에는카드가 무조건 있을거니까 0, 덱1, 버린카드2
        occ.add(0);
        if(!p.drawPile.isEmpty())
        {
            occ.add(1);
        }
        if(!p.discardPile.isEmpty())
        {
            occ.add(2);
        }

        Random random = new Random();
        int r = occ.get(random.nextInt(occ.size()));

        AbstractCard c;
        if(r == 0)//손에있는카드
        {
            AbstractDungeon.actionManager.addToBottom(new UpgradeRandomCardAction());
        }
        else if(r == 1)//덱에있는카드
        {
            c = p.drawPile.getRandomCard(true);
            for(int i = 0; i<p.drawPile.size() ; i++)
            {
                if(c.upgraded)
                {
                    c = p.drawPile.getRandomCard(true);
                }
            }
            if (!c.upgraded && c.type != CardType.CURSE)
            {
                c.upgrade();
            }
        }
        else if(r == 2)//사용한카드
        {
            c = p.discardPile.getRandomCard(true);
            for(int i = 0; i<p.discardPile.size() ; i++)
            {
                if(c.upgraded)
                {
                    c = p.discardPile.getRandomCard(true);
                }
            }
            if (!c.upgraded && c.type != CardType.CURSE)
            {
                c.upgrade();
            }
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this));
    }

    public AbstractCard makeCopy() {
        return new Unstableevolution();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.isEthereal = false;
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
