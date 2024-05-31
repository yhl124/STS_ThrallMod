package thrallmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Menacingnimbus extends AbstractThrallCard
{
    public static final String ID = "Menacingnimbus";
    public static final	String IMG = "cards/menacingnimbus.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 0;
    private static final int POWER = 5;
    private static final int UPGRADE_BONUS = 2;

    public Menacingnimbus()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.exhaust = true;
        this.baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK);
        while(c.rarity == CardRarity.RARE)
        {
            c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK);
        }

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if(p.hasPower("Hallazealpower"))
        {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage/2));
        }
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c,1));
    }

    public AbstractCard makeCopy() {
        return new Menacingnimbus();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
