package thrallmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Murksparkeel extends AbstractThrallCard
{
    public static final String ID = "Murksparkeel";
    public static final	String IMG = "cards/murksparkeel.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 8;
    private static final int UPGRADE_BONUS = 2;

    public Murksparkeel()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        boolean check = true;

        for(AbstractCard tc : p.drawPile.group)
        {
            if(tc.cost%2 == 0)
            {
                check = false;
            }
        }

        if(check == false)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m ,new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));

            if(p.hasPower("Hallazealpower"))
            {
                AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage/2));
            }
        }

        if(check == true)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m ,new DamageInfo(p, damage*3, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));

            if(p.hasPower("Hallazealpower"))
            {
                AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, (damage*3)/2));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Murksparkeel();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
