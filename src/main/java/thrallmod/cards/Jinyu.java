package thrallmod.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thrallmod.powers.OverloadPower;

public class Jinyu extends AbstractThrallCard
{
    public static final String ID = "Jinyu";
    public static final	String IMG = "cards/jinyu.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 5;
    private static final int OVERLOAD = 1;
    private static final int UPGRADE_BONUS = 5;

    public Jinyu()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);

        //this.baseDamage = POWER;
        this.baseMagicNumber = OVERLOAD;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(!upgraded) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, POWER));
        }
        else if(upgraded)
        {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, POWER+2));
        }
        AbstractDungeon.player.addPower(new OverloadPower(p, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Jinyu();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
