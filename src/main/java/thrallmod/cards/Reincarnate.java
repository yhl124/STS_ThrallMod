package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Reincarnate extends AbstractThrallCard
{
    public static final String ID = "Reincarnate";
    public static final	String IMG = "cards/reincarnate.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int POWER = 1;
    private static final int UPGRADE_BONUS = 1;

    public Reincarnate()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        //this.baseDraw = POWER;
        this.exhaust = true;
        this.baseMagicNumber = POWER;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(!p.exhaustPile.isEmpty())
        {
            AbstractCard c = p.exhaustPile.getRandomCard(true);
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
            p.exhaustPile.removeCard(c);
        }
        if(this.upgraded)
        {
            if(!p.exhaustPile.isEmpty())
            {
                AbstractCard c2 = p.exhaustPile.getRandomCard(true);
                if (p.hand.size() == 10) {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDiscardAction(c2, 1));
                    p.createHandIsFullDialog();
                } else {
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c2));
                    p.exhaustPile.removeCard(c2);
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Reincarnate();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
