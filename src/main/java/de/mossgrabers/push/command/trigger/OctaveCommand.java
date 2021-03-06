// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.push.command.trigger;

import de.mossgrabers.framework.ButtonEvent;
import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.command.core.AbstractTriggerCommand;
import de.mossgrabers.framework.view.TransposeView;
import de.mossgrabers.framework.view.View;
import de.mossgrabers.push.PushConfiguration;
import de.mossgrabers.push.controller.PushControlSurface;


/**
 * Command for the octave up/down keys.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class OctaveCommand extends AbstractTriggerCommand<PushControlSurface, PushConfiguration>
{
    private boolean isUp;


    /**
     * Constructor.
     *
     * @param isUp True if octave up otherwise down
     * @param model The model
     * @param surface The surface
     */
    public OctaveCommand (final boolean isUp, final Model model, final PushControlSurface surface)
    {
        super (model, surface);
        this.isUp = isUp;
    }


    /** {@inheritDoc} */
    @Override
    public void execute (final ButtonEvent event)
    {
        final View activeView = this.surface.getViewManager ().getActiveView ();
        if (activeView == null || !(activeView instanceof TransposeView))
            return;

        if (this.isUp)
            ((TransposeView) activeView).onOctaveUp (event);
        else
            ((TransposeView) activeView).onOctaveDown (event);
    }
}
