package frc.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.robot.OI
import frc.robot.subsystems.Arm
import frc.robot.util.MathUtil
import harkerrobolib.commands.IndefiniteCommand

class MoveArmManual : IndefiniteCommand() {

    init {
        requires(Arm)
    }

    override fun execute() {
        var joystickInput = MathUtil.mapOutput(OI.driverGamepad.rightY, OI.XBOX_DEADBAND)
        if (Arm.getTalonCurrent() >= Arm.TALON_CURRENT_SPIKE)
            joystickInput = 0.0
        if (Math.signum(joystickInput).toInt() == OI.JOYSTICK_UP)
            Arm.armMotionPercentOutput(joystickInput, Arm.ArmDirection.UP)
        else
            Arm.armMotionPercentOutput(joystickInput, Arm.ArmDirection.DOWN)
    }
}