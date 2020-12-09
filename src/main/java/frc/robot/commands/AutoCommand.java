package frc.robot.commands;

import static frc.robot.Constants.AutoConstants.*;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoCommand extends SequentialCommandGroup {
    private final DriveSubsystem m_subsystem;

    /**
     * In the autonomous command we want to delay a little while after autonmous
     * mode begins, then set the drive to a certain speed for a set period of time,
     * and then trun off the moter. We can see how we can use the Sequential command
     * group to build the automomous command out of separate instantiations of the
     * other DrivecCommand. And we use the withTimeout decorator to terminate the 
     * subcommand after a given period of time. 
     *
     * There is more than one way to add command to a sequential command group, 
     * but this method is the easiest and most clear. There are several methods
     * in the WPIlib that can be used to put command together into more complex 
     * commands and many of them have decorators like withTimeout to modify the
     * way they are executed.
     **/
    public AutoCommand(final DriveSubsystem subsystem) {
        addRequirements(subsystem);
        m_subsystem = subsystem;
        addCommands(new DriveCommand(m_subsystem, 0.0, 0.0).withTimeout(kAutoWaitSeconds));
        addCommands(new DriveCommand(m_subsystem, AutoConstants.kAutoSpeed, 0).withTimeout(kAutoDriveTime));
        addCommands(new DriveCommand(m_subsystem, 0.0, 0.0));
    };
}
