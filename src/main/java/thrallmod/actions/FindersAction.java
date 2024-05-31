package thrallmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import thrallmod.cards.*;
import thrallmod.patches.AbstractCardEnum;
import thrallmod.relics.Lavashock;

import java.util.ArrayList;

public class FindersAction extends AbstractGameAction
{
    private boolean retrieveCard = false;
    private AbstractPlayer p;

    public FindersAction()
    {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update()
    {
        RewardItem i = new RewardItem();
        if (this.duration == Settings.ACTION_DUR_FAST)
        {
            CardGroup tmp;
            tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            tmp.addToRandomSpot(new Crackle());
            tmp.addToRandomSpot(new Volcano());
            tmp.addToRandomSpot(new Lavaburst());
            tmp.addToRandomSpot(new Lightningstorm());
            tmp.addToRandomSpot(new Lightningbolt());
            tmp.addToRandomSpot(new Jinyu());
            tmp.addToRandomSpot(new Elementaldestruction());
            tmp.addToRandomSpot(new Forkedlightning());
            tmp.addToRandomSpot(new Drakkaridefender());
            tmp.addToRandomSpot(new Doomhammer());
            tmp.addToRandomSpot(new Fireguard());
            tmp.addToRandomSpot(new Earthelemental());
            tmp.addToRandomSpot(new Finderskeepers());

            /*
            ArrayList<AbstractCard> cardss = new ArrayList<>();

            cardss.add(new Crackle());
            cardss.add(new Volcano());
            cardss.add(new Lavaburst());
            cardss.add(new Ancestralknow());
            cardss.add(new Lightningbolt());
            cardss.add(new Lightningstorm());
            cardss.add(new Jinyu());
            cardss.add(new Elementaldestruction());
            cardss.add(new Forkedlightning());
            cardss.add(new Drakkaridefender());
            cardss.add(new Doomhammer());
            cardss.add(new Fireguard());
            cardss.add(new Earthelemental());
            cardss.add(new Finderskeepers());
            */

            //AbstractDungeon.cardRewardScreen.discoveryOpen();
            //AbstractDungeon.cardRewardScreen.codexOpen();


            AbstractDungeon.cardRewardScreen.open(tmp.group, i , "Choose a card.");
            tickDuration();
            return;
        }
        if (!this.retrieveCard)
        {
            if (AbstractDungeon.cardRewardScreen.rItem != null)
            {
                AbstractCard disCard = AbstractDungeon.cardRewardScreen.rItem.cards.get(0);
                disCard.current_x = (-1000.0F * Settings.scale);
                if (AbstractDungeon.player.hand.size() < 10) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                //disCard.upgrade();
                AbstractDungeon.cardRewardScreen.rItem = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
        /*
        if (!this.retrieveCard)
        {
            if (AbstractDungeon.cardRewardScreen.codexCard != null)
            {
                AbstractCard disCard = AbstractDungeon.cardRewardScreen.codexCard.makeStatEquivalentCopy();
                disCard.current_x = (-1000.0F * Settings.scale);
                if (AbstractDungeon.player.hand.size() < 10) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                //disCard.upgrade();
                AbstractDungeon.cardRewardScreen.codexCard = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
        */
    }
}
