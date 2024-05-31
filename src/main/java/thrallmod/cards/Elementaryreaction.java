package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import javax.swing.*;

public class Elementaryreaction extends AbstractThrallCard
{
    public static final String ID = "Elementaryreaction";
    public static final	String IMG = "cards/elementaryreaction.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 1;
    private static final int UPGRADE_BONUS = 1;

    public Elementaryreaction()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDraw = POWER;
        this.baseMagicNumber = POWER;
        this.magicNumber = this.baseMagicNumber;
        //this.tags.add(BaseModCardTags.);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(p.drawPile.isEmpty())
        {
            p.discardPile.moveToBottomOfDeck(p.discardPile.getTopCard());
        }
        AbstractCard c = p.drawPile.getTopCard();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));

        if(this.upgraded)
        {
            AbstractCard c2;
            if(p.drawPile.isEmpty())
            {
                p.discardPile.moveToBottomOfDeck(p.discardPile.getTopCard());
                c2 = p.drawPile.getTopCard();
            }
            else
            {
                c2 = p.drawPile.getNCardFromTop(1);
            }
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c2));
        }
    }

    public AbstractCard makeCopy() {
        return new Elementaryreaction();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_BONUS);
        }
    }
}
