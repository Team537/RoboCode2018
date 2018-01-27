package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.commands.AgitationAuto;
import org.usfirst.frc.team537.robot.commands.AgitatorAgitate;
import org.usfirst.frc.team537.robot.commands.CollectorIntake;
import org.usfirst.frc.team537.robot.commands.FeederFeed;
import org.usfirst.frc.team537.robot.commands.ShooterClimb;
import org.usfirst.frc.team537.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick joystickBox = new Joystick(RobotMap.Driver.BOX_PORT);
	public final Joystick joystickPrimary = new Joystick(RobotMap.Driver.PRIMARY_PORT);
	public final Joystick joystickSecondary = new Joystick(RobotMap.Driver.SECONDARY_PORT);

	public OI() {
		new JoystickButton(joystickBox, RobotMap.ControlBox.COLLECTOR_IN).whileHeld(new CollectorIntake(true));
		new JoystickButton(joystickBox, RobotMap.ControlBox.COLLECTOR_OUT).whileHeld(new CollectorIntake(false));

		new JoystickButton(joystickBox, RobotMap.ControlBox.AGRIGATOR_IN).whileHeld(new AgitatorAgitate(false));
		new JoystickButton(joystickBox, RobotMap.ControlBox.AGRIGATOR_OUT).whileHeld(new AgitationAuto());
		new JoystickButton(joystickBox, RobotMap.ControlBox.FLYWHEEL_TOGGLE).whileHeld(new ShooterShoot());
		new JoystickButton(joystickBox, RobotMap.ControlBox.FIRE_BUTTON).whileHeld(new FeederFeed());

		new JoystickButton(joystickBox, RobotMap.ControlBox.CLIMB_TOGGLE).whileHeld(new ShooterClimb());
		new JoystickButton(joystickBox, 3).whileHeld(new ShooterClimb()); // X, TODO: Make box not shit!

		// new JoystickButton(joystickPrimary, RobotMap.KeysF310.A_BUTTON).whileHeld(new LightsCycle(null, 5, Lights.Colour.WHITE, Lights.Colour.BLUE, Lights.Colour.YELLOW, Lights.Colour.GREEN, Lights.Colour.RED, Lights.Colour.MAGENTA));
	}
}
