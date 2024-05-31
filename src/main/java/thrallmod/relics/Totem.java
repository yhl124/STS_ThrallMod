package thrallmod.relics;

import java.util.Random;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Totem extends AbstractThrallRelic
{
    public static final String ID = "Totem";
    private static final AbstractRelic.RelicTier TIER = AbstractRelic.RelicTier.STARTER;
    private static final String IMG = "relics/totem.png";
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.FLAT;

    public Totem()
    {
        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onPlayerEndTurn()
    {
        AbstractPlayer p = AbstractDungeon.player;
        Random random = new Random();
        int r = random.nextInt(4);

        flash();
        if (r == 0)
        {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 2));
        }
        else if(r == 1)
        {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 7));
        }
        else if(r == 2)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        }
        else if(r == 3)
        {
            AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 7), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new Totem();
    }
}