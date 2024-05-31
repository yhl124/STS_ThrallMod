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

import java.util.ArrayList;
import java.util.Random;

public class Volcano extends AbstractThrallCard
{
    public static final String ID = "Volcano";
    public static final	String IMG = "cards/volcano.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;
    //public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 2;
    private static final int POWER = 40;
    private static final int OVERLOAD = 2;
    private static final int UPGRADE_BONUS = 5;

    public Volcano()
    {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDamage = POWER;

        this.baseMagicNumber = OVERLOAD;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        ArrayList<Integer> mindex = new ArrayList<Integer>();
        ArrayList<Integer> mdamage = new ArrayList<Integer>();
        int num = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        for(int i = 0; i < num ; i++)
        {
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if(!mo.isDead)
            {
                mindex.add(i);
                mdamage.add(0);
            }
        }

        Random random = new Random();
        int r ;

        for(int j = 0; j<damage ; j++)
        {
            r = random.nextInt(mdamage.size());
            mdamage.set(r, mdamage.get(r)+1 );
        }
        for(int k = 0 ; k < mindex.size() ; k++)
        {
            AbstractMonster mo = (AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(mindex.get(k));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, new DamageInfo(p, mdamage.get(k), damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new com.megacrit.cardcrawl.vfx.combat.FlameParticleEffect(mo.hb.cX, mo.hb.cY)));
        }

        if(p.hasPower("Hallazealpower"))
        {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, damage/2));
        }
        AbstractDungeon.player.addPower(new OverloadPower(p, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Volcano();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
        }
    }
}