package thrallmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Likkim extends AbstractThrallCard
{
    public static final String ID = "Likkim";
    public static final	String IMG = "cards/likkim.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 8;
    private static final int UPGRADE_BONUS = 2;

    public Likkim()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDamage = POWER;

        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if(p.hasPower("Overload") || p.hasPower("Overloadapply"))
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage*2, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

            if (p.hasPower("Hallazealpower")) {
                AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage));
            }
        }
        else
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

            if (p.hasPower("Hallazealpower")) {
                AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage / 2));
            }
        }
    }

    public AbstractCard makeCopy() {
        return new Likkim();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
