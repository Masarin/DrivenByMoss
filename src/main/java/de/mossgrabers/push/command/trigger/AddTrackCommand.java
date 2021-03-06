// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.push.command.trigger;

import de.mossgrabers.framework.ButtonEvent;
import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.command.core.AbstractTriggerCommand;
import de.mossgrabers.framework.daw.AbstractTrackBankProxy;
import de.mossgrabers.framework.daw.ApplicationProxy;
import de.mossgrabers.push.PushConfiguration;
import de.mossgrabers.push.controller.PushControlSurface;


/**
 * Command to trigger the Add track.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class AddTrackCommand extends AbstractTriggerCommand<PushControlSurface, PushConfiguration>
{
    /**
     * Constructor.
     *
     * @param model The model
     * @param surface The surface
     */
    public AddTrackCommand (final Model model, final PushControlSurface surface)
    {
        super (model, surface);
    }


    /** {@inheritDoc} */
    @Override
    public void execute (final ButtonEvent event)
    {
        if (event != ButtonEvent.DOWN)
            return;

        AbstractTrackBankProxy tb = this.model.getTrackBank ();
        final ApplicationProxy application = this.model.getApplication ();
        if (this.surface.isShiftPressed ())
        {
            application.addEffectTrack ();
            tb = this.model.getEffectTrackBank ();
        }
        else if (this.surface.isSelectPressed ())
            application.addAudioTrack ();
        else
            application.addInstrumentTrack ();

        final AbstractTrackBankProxy bank = tb;
        this.surface.scheduleTask ( () -> {
            final int pos = bank.getTrackCount () - 1;
            bank.scrollToChannel (pos);
            bank.select (pos % bank.getNumTracks ());
        }, 200);
    }
}
