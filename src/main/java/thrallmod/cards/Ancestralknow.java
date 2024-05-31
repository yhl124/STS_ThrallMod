package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thrallmod.powers.OverloadPower;

public class Ancestralknow extends AbstractThrallCard
{
    public static final String ID = "Ancestralknow";
    public static final	String IMG = "cards/ancestralknowledge.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 4;
    private static final int OVERLOAD = 2;

    public Ancestralknow()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDraw = POWER;
        this.baseMagicNumber = OVERLOAD;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 4));
        AbstractDungeon.player.addPower(new OverloadPower(p, OVERLOAD));
    }

    public AbstractCard makeCopy() {
        return new Ancestralknow();
    }

    public void upgrade()
    {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
        }
    }
}
