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
import thrallmod.powers.OverloadPower;

import java.util.Random;

public class Crackle extends AbstractThrallCard
{
    public static final String ID = "Crackle";
    public static final	String IMG = "cards/crackle.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 25;
    private static final int OVERLOAD = 2;
    private static final int UPGRADE_BONUS = 5;

    public Crackle() {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDamage = POWER;
        this.baseMagicNumber = OVERLOAD;
        this.magicNumber = this.baseMagicNumber;


        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Random random = new Random();
        int r = random.nextInt(6);

        if (r == 0)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage+r, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        else if (r == 1)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage+r, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        else if (r == 2)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage+r, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        else if (r == 3)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage+r, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        else if (r == 4)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage+r, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        else if (r == 5)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage+r, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }

        AbstractDungeon.player.addPower(new OverloadPower(p, OVERLOAD));
        //AbstractDungeon.actionManager.addToBottom(new Hallazealaction(damage+r));
        if(p.hasPower("Hallazealpower"))
        {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage/2));
        }
    }

    public AbstractCard makeCopy() {
        return new Crackle();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}