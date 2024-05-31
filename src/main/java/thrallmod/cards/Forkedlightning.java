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

public class Forkedlightning extends AbstractThrallCard{
    public static final String ID = "Forkedlightning";
    public static final	String IMG = "cards/forkedlightning.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 10;
    private static final int OVERLOAD = 1;
    private static final int UPGRADE_BONUS = 5;

    public Forkedlightning()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);

        this.baseDamage = POWER;
        this.baseMagicNumber = OVERLOAD;
        this.magicNumber = this.baseMagicNumber;

        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<Integer> num = new ArrayList<>();
        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        Random random = new Random();
        int r ;

        for(int i = 0; i < temp ; i++)
        {
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if(!mo.isDead)
            {
                num.add(i);
            }
        }

        if(num.size() == 1)
        {
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(0);
            AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            if(p.hasPower("Hallazealpower"))
            {
                AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage/2));
            }
        }
        else
        {
            for(int j = 0; j < 2 ; j ++)
            {
                r = random.nextInt(num.size());

                AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(num.get(r));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                num.remove(r);

                if(p.hasPower("Hallazealpower"))
                {
                    AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage/2));
                }
            }
        }
        AbstractDungeon.player.addPower(new OverloadPower(p, OVERLOAD));
    }

    public AbstractCard makeCopy() {
        return new Forkedlightning();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
