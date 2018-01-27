package org.usfirst.frc.team537.robot.choosers;

import org.usfirst.frc.team537.robot.autos.AutoCross;
import org.usfirst.frc.team537.robot.autos.AutoSwitch;
import org.usfirst.frc.team537.robot.autos.AutoTesting;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ChooserAuto {
	private SendableChooser<String> stationChooser;
	private SendableChooser<String> modeChooser;
	
	public ChooserAuto() {
		stationChooser = new SendableChooser<>();
		stationChooser.addObject("None", "N0");
		CreateStationChoice(DriverStation.Alliance.Red, 1);
		CreateStationChoice(DriverStation.Alliance.Red, 2);
		CreateStationChoice(DriverStation.Alliance.Red, 3);
		CreateStationChoice(DriverStation.Alliance.Blue, 1);
		CreateStationChoice(DriverStation.Alliance.Blue, 2);
		CreateStationChoice(DriverStation.Alliance.Blue, 3);
		SmartDashboard.putData("Station", stationChooser);

		modeChooser = new SendableChooser<>();
		modeChooser.addObject("Cross", "C");
		modeChooser.addObject("Switch", "T");
		modeChooser.addDefault("Testing", "Z");
		SmartDashboard.putData("Auto", modeChooser);
	}

	private void CreateStationChoice(DriverStation.Alliance alliance, int location) {
		String fullName = alliance.name() + " " + location;
		String cName = "" + alliance.name().charAt(0) + location;

		if (DriverStation.getInstance().getAlliance() == alliance && DriverStation.getInstance().getLocation() == location) {
			stationChooser.addDefault(fullName, cName);
		} else {
			stationChooser.addObject(fullName, cName);
		}
	}

	public Command getSelected() {
		String station = stationChooser.getSelected();
		String mode = modeChooser.getSelected();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		int location = Character.getNumericValue(station.charAt(1));
		boolean isSwitchLeft = gameData.charAt(0) == 'L';

		if (mode.charAt(0) == 'C') {
			return new AutoCross(location);
		} else if (station.charAt(0) == 'T') {
			return new AutoSwitch(location, isSwitchLeft);
		} else if (station.charAt(0) == 'Z') {
			return new AutoTesting();
		}
		
		return null;
	}
}
