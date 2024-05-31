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
import thrallmod.powers.OverloadPower;

import java.util.Random;

public class Elementaldestruction extends AbstractThrallCard{
    public static final String ID = "Elementaldestruction";
    public static final	String IMG = "cards/elementaldestruction.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 3;
    private static final int POWER = 40;
    private static final int OVERLOAD = 3;
    private static final int UPGRADE_BONUS = 10;

    public Elementaldestruction()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDamage = POWER;
        this.baseMagicNumber = OVERLOAD;
        this.magicNumber = this.baseMagicNumber;


        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        Random random = new Random();
        int r ;

        for(int i = 0; i<temp ; i++)
        {
            r = random.nextInt(3);
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);

            if (r == 0)
            {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, damage+(r*10), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
            else if (r ==1)
            {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, damage+(r*10), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }
            else if (r ==2)
            {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, damage+(r*10), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
            }

            if(p.hasPower("Hallazealpower"))
            {
                AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, (damage+(r*10))/2));
            }
        }
        AbstractDungeon.player.addPower(new OverloadPower(p, OVERLOAD));
    }

    public AbstractCard makeCopy() {
        return new Elementaldestruction();
    }

    public void upgrade()
    {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}