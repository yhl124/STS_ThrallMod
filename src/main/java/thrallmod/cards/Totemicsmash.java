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
import thrallmod.actions.SelectTotemAction;

import java.util.Random;

public class Totemicsmash extends AbstractThrallCard
{
    public static final String ID = "Totemicsmash";
    public static final	String IMG = "cards/totemicsmash.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 1;
    private static final int POWER = 8;

    public Totemicsmash() {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDamage = POWER;

        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        if(!upgraded)
        {
            Random random = new Random();
            int r  = random.nextInt(4);

            if(r == 0)
            {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Basicattack()));
            }
            else if(r == 1)
            {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Basicheal()));
            }
            else if(r == 2)
            {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Basicstrength()));
            }
            else if(r == 3)
            {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Basicarmor()));
            }
        }

        else if(upgraded)
        {
            AbstractDungeon.actionManager.addToBottom(new SelectTotemAction());
        }

        if(p.hasPower("Hallazealpower"))
        {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage/2));
        }
    }

    public AbstractCard makeCopy()
    {
        return new Totemicsmash();
    }

    public void upgrade()
    {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
