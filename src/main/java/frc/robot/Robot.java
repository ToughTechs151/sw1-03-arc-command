package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/** Now our robot is getting more extensive. Since for the first
 * time we need to run different commands based on what mode we 
 * are in, we add an autonomousInit method to get the autonmous
 * command from the Robot Container and then schedule it. 
 * When we change modes again to teleop, we check to see if the
 * autonomous command was scheduled and if so, we cancel it. 
 * Once it is canceled the default command will automatically 
 * be scheduled again. Remember, the default command gets 
 * scheduled when there is no other command scheduled for
 * the subsystem.
 *
 * We also add a testInit method. When we are in test mode
 * we don't want to run any command, so we cancel all running
 * commands. Again, this will schedule the default commands
 * so keep this in mind and make sure the default command
 * actually does waht you want it to in each mode.
 **/
public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private RobotContainer m_robotcontainer;

  @Override
  public void robotInit() {
    m_robotcontainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotcontainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

}
