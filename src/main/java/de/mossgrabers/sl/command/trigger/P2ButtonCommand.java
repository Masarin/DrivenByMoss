// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.sl.command.trigger;

import de.mossgrabers.framework.ButtonEvent;
import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.command.core.AbstractTriggerCommand;
import de.mossgrabers.framework.daw.AbstractTrackBankProxy;
import de.mossgrabers.sl.SLConfiguration;
import de.mossgrabers.sl.controller.SLControlSurface;


/**
 * Command to handle the P2 buttons.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class P2ButtonCommand extends AbstractTriggerCommand<SLControlSurface, SLConfiguration>
{
    private boolean isUp;


    /**
     * Constructor.
     *
     * @param isUp True if is up button
     * @param model The model
     * @param surface The surface
     */
    public P2ButtonCommand (final boolean isUp, final Model model, final SLControlSurface surface)
    {
        super (model, surface);
        this.isUp = isUp;
    }


    /** {@inheritDoc} */
    @Override
    public void execute (final ButtonEvent event)
    {
        if (event != ButtonEvent.DOWN)
            return;

        final AbstractTrackBankProxy tb = this.model.getCurrentTrackBank ();
        if (this.isUp)
        {
            if (!tb.canScrollTracksDown ())
                return;
            tb.scrollTracksPageDown ();
        }
        else
        {
            if (!tb.canScrollTracksUp ())
                return;
            tb.scrollTracksPageUp ();
        }
        this.selectTrack (0);
    }
}
