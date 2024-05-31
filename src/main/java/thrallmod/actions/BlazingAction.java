package thrallmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

public class BlazingAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private AbstractPlayer p;

    public BlazingAction()
    {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update()
    {
        if (this.duration == Settings.ACTION_DUR_FAST)
        {
            AbstractDungeon.cardRewardScreen.discoveryOpen(AbstractCard.CardType.SKILL);
            tickDuration();
            return;
        }

        if (!this.retrieveCard)
        {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null)
            {
                AbstractCard c = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                c.current_x = (-1000.0F * Settings.scale);
                if (AbstractDungeon.player.hand.size() < 10) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(c, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                AbstractDungeon.cardRewardScreen.discoveryCard= null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
    }
}
