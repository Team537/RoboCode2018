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
	private SendableChooser<Double> delayChooser;
	
	public ChooserAuto() {
		stationChooser = new SendableChooser<>();
		stationChooser.addObject("None", "N0");
		CreateStationChoice("Red", 1);
		CreateStationChoice("Red", 2);
		CreateStationChoice("Red", 3);
		CreateStationChoice("Blue", 1);
		CreateStationChoice("Blue", 2);
		CreateStationChoice("Blue", 3);
		SmartDashboard.putData("Auto Station", stationChooser);

		modeChooser = new SendableChooser<>();
		modeChooser.addObject("Cross", "C");
		modeChooser.addObject("Switch", "T");
		modeChooser.addDefault("Testing", "Z");
		SmartDashboard.putData("Auto Mode", modeChooser);
		
		delayChooser = new SendableChooser<>();
		delayChooser.addDefault("0.0s", 0.0);
		for (double i = 1.0; i <= 7.0; i += 1.0) {
			delayChooser.addObject(i + "s", i);
		}
		SmartDashboard.putData("Auto Delay", delayChooser);
	}

	private void CreateStationChoice(String alliance, int location) {
		String fullName = alliance + " " + location;
		String cName = "" + alliance.charAt(0) + location;

		if (DriverStation.getInstance().getAlliance().name() == alliance && DriverStation.getInstance().getLocation() == location) {
			stationChooser.addDefault(fullName, cName);
		} else {
			stationChooser.addObject(fullName, cName);
		}
	}
	
	private String fixNull(String string, String normal) {
		if (string == null || string.isEmpty()) {
			return normal;
		}
		
		return string;
	}

	public Command getSelected() {
		String stationLocation = fixNull(stationChooser.getSelected(), "R1").toUpperCase();
		char mode = fixNull(modeChooser.getSelected(), "C").toUpperCase().charAt(0);
		double delay = delayChooser.getSelected();
		String gameData = fixNull(DriverStation.getInstance().getGameSpecificMessage(), "RRR").toUpperCase();
		
	//	char alliance = allianceLocation.charAt(0);
		int location = Character.getNumericValue(stationLocation.charAt(1));
		boolean isSwitchLeft = gameData.charAt(0) == 'L';

		if (mode == 'C') {
			return new AutoCross(location, isSwitchLeft, delay);
		} else if (mode == 'T') {
			return new AutoSwitch(location, isSwitchLeft, delay);
		} else if (mode == 'Z') {
			return new AutoTesting();
		}
		
		return null;
	}
}
