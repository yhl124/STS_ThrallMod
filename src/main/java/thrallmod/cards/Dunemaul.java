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

import java.util.ArrayList;
import java.util.Random;

public class Dunemaul extends AbstractThrallCard
{
    public static final String ID = "Dunemaul";
    public static final	String IMG = "cards/dunemaul.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 2;
    private static final int POWER = 22;
    private static final int OVERLOAD = 1;
    private static final int UPGRADE_BONUS = 4;

    public Dunemaul()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDamage = POWER;
        this.baseMagicNumber = OVERLOAD;
        this.magicNumber = this.baseMagicNumber;

        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        int num = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        for(int i = 0; i < num ; i++)
        {
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if(!mo.isDead)
            {
                numbers.add(i);
            }
        }

        Random random = new Random();
        int r = random.nextInt(2);
        int s = random.nextInt(numbers.size());

        if(numbers.size() == 1)
        {
            for(int i = 0 ; i < 2 ; i++)
            {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
        }
        else if(numbers.size() >= 2)
        {
            if (r == 0)
            {
                for(int i = 0; i < 2 ; i++)
                {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                }
            }
            else if (r == 1)
            {
                AbstractMonster mo = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(s);
                while (mo == m)
                {
                    s = random.nextInt(numbers.size());
                    mo = (AbstractMonster) AbstractDungeon.getCurrRoom().monsters.monsters.get(s);
                }
                for (int j = 0; j < 2; j++)
                {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                }
            }
        }

        if(p.hasPower("Hallazealpower"))
        {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage));
        }
        AbstractDungeon.player.addPower(new OverloadPower(p, OVERLOAD));
    }

    public AbstractCard makeCopy() {
        return new Dunemaul();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
