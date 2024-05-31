package thrallmod.relics;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Omegamind extends AbstractThrallRelic
{
    static final String ID = "Omegamind";
    private static final AbstractRelic.RelicTier TIER = AbstractRelic.RelicTier.UNCOMMON;
    private static final String IMG = "relics/omegamind.png";
    private static final AbstractRelic.LandingSound SOUND = LandingSound.MAGICAL;

    public Omegamind() {
        super(ID, IMG, TIER, SOUND);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m)
    {
        AbstractPlayer p = AbstractDungeon.player;

        if(this.counter == 10 && card.type == AbstractCard.CardType.ATTACK && card.target == AbstractCard.CardTarget.ENEMY)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, (card.damage)/2));
            setCounter(0);
        }

        else if(card.type == AbstractCard.CardType.ATTACK && card.target == AbstractCard.CardTarget.ENEMY)
        {
            this.counter++;
        }
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Omegamind();
    }
}