package thrallmod.relics;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thrallmod.actions.SelectTotemAction;

import java.util.Random;

public class Totemicslam extends AbstractThrallRelic
{
    public static final String ID = "Totemicslam";
    private static final AbstractRelic.RelicTier TIER = RelicTier.BOSS;
    private static final String IMG = "relics/totemicslam.png";
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.FLAT;

    public Totemicslam()
    {

        super(ID, IMG, TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription()
    {
        return DESCRIPTIONS[0];
    }

    @Override
    public void obtain()
    {
        if (AbstractDungeon.player.hasRelic("Totem"))
        {
            instantObtain(AbstractDungeon.player, 0, false);
        } else {
            super.obtain();
        }
    }

    @Override
    //onPlayerTurnEnd
    public void atTurnStart()
    {
        flash();
        AbstractDungeon.actionManager.addToBottom(new SelectTotemAction());
    }

    @Override
    public AbstractRelic makeCopy()
    {
        return new Totemicslam();
    }
}