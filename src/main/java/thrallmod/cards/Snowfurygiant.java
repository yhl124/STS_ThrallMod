package thrallmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Snowfurygiant extends AbstractThrallCard
{
    public static final String ID = "Snowfurygiant";
    public static final	String IMG = "cards/snowfurygiant.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final	String NAME = cardStrings.NAME;
    public static final	String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = CardType.ATTACK;

    private static final int POOL = 1;

    private static final int COST = 7;
    private static final int POWER = 25;
    private static final int UPGRADE_BONUS = 10;

    public Snowfurygiant() {
        super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
        this.baseDamage = POWER;
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m)
    {
        if(card.cardID == "Crackle" || card.cardID == "AncestralKnow" || card.cardID == "Elementaldestruction" || card.cardID == "Forkedlightning" || card.cardID == "Lavaburst"
                || card.cardID == "Lightningbolt" || card.cardID == "Lightningstorm" || card.cardID == "Volcano" || card.cardID == "Doomhammer" || card.cardID == "Earthelemental"
                || card.cardID == "Drakkaridefender" || card.cardID == "Jinyu" || card.cardID == "Fireguard" || card.cardID == "Finderskeepers" || card.cardID == "Beakeredlightning"
                || card.cardID == "Zap" || card.cardID == "Dustdevil" || card.cardID == "Dunemaul") {
            if (this.cost > 0) {
                upgradeBaseCost(this.cost - card.baseMagicNumber);
            }
        }
    }

    public AbstractCard makeCopy()
    {
        return new Snowfurygiant();
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_BONUS);
        }
    }
}
