package thrallmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thrallmod.cards.*;

public class SelectTotemAction extends AbstractGameAction
{
    private AbstractPlayer p;

    public SelectTotemAction()
    {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update()
    {
        CardGroup tmp;

        if (this.duration == Settings.ACTION_DUR_MED)
        {
            tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            tmp.addToRandomSpot(new Basicarmor());
            tmp.addToRandomSpot(new Basicheal());
            tmp.addToRandomSpot(new Basicstrength());
            tmp.addToRandomSpot(new Basicattack());

            AbstractDungeon.gridSelectScreen.open(tmp, 1, "Choose a Card", false);
            tickDuration();
            return;
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0)
        {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards)
            {
                c.unhover();
                if (this.p.hand.size() == 10)
                {
                    this.p.masterDeck.moveToDiscardPile(c);
                    //this.p.drawPile.moveToDiscardPile(c);
                    this.p.createHandIsFullDialog();
                }
                else
                {
                    this.p.masterDeck.removeCard(c);
                    this.p.hand.addToTop(c);
                }
                this.p.hand.refreshHandLayout();
                this.p.hand.applyPowers();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.p.hand.refreshHandLayout();
        }
        tickDuration();
    }
}