package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Devolve extends AbstractThrallCard
{
    public static final String ID = "Devolve";
    public static final	String IMG = "cards/devolve.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 3;
    //private static final int COST_UPGRADED = 1;
    //private static final int UPGRADE_BONUS = 1;

    public Devolve()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        //this.baseDraw = POWER;
        this.baseMagicNumber = POWER;
        this.magicNumber = this.baseMagicNumber;

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        for(int i=0 ; i < temp ; i++)
        {
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber));
            //AbstractDungeon.actionManager.addToBottom(new SpotWeaknessAction(magicNumber, mo)); //내 힘이 증가한다;;
        }
    }

    public AbstractCard makeCopy() {
        return new Devolve();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}