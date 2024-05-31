package thrallmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thrallmod.ThrallMod;

public class FrogPower extends AbstractThrallPower
{
    public static final String POWER_ID = "Frogpower";
    public static final String IMG = "powers/frogpower.png";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FrogPower(AbstractCreature owner, int amount)
    {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        this.amount = amount;
        updateDescription();
        this.img = new Texture(ThrallMod.getResourcePath(IMG));
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m)
    {
        if (card.type == AbstractCard.CardType.SKILL)
        {
            for(int i = 0; i<amount ; i++)
            {
                boolean check = false;
                if(AbstractDungeon.player.drawPile.isEmpty())
                {
                    AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
                }
                for(AbstractCard tc : AbstractDungeon.player.drawPile.group)
                {
                    if(tc.type == AbstractCard.CardType.SKILL)
                    {
                        check = true;
                        break;
                    }
                }
                if (check == true)
                {
                    flash();
                    AbstractCard c = AbstractDungeon.player.drawPile.getRandomCard(AbstractCard.CardType.SKILL, true);
                    AbstractDungeon.player.drawPile.moveToHand(c, AbstractDungeon.player.hand);
                    AbstractDungeon.player.drawPile.removeCard(c);
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
