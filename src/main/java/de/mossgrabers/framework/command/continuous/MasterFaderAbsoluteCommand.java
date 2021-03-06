// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.framework.command.continuous;

import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.command.core.AbstractContinuousCommand;
import de.mossgrabers.framework.configuration.Configuration;
import de.mossgrabers.framework.controller.ControlSurface;


/**
 * Command to change the volume of the master track.
 *
 * @param <S> The type of the control surface
 * @param <C> The type of the configuration
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class MasterFaderAbsoluteCommand<S extends ControlSurface<C>, C extends Configuration> extends AbstractContinuousCommand<S, C>
{
    /**
     * Constructor.
     *
     * @param model The model
     * @param surface The surface
     */
    public MasterFaderAbsoluteCommand (final Model model, final S surface)
    {
        super (model, surface);
    }


    /** {@inheritDoc} */
    @Override
    public void execute (final int value)
    {
        this.model.getMasterTrack ().setVolume (value);
    }
}
