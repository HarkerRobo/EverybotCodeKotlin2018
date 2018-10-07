package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.robot.*
import edu.wpi.first.wpilibj.command.Subsystem
import frc.robot.commands.DriveWithVelocityManual
import frc.robot.commands.MoveArmManual
import frc.robot.util.MathUtil

object Arm : Subsystem() {

    val armTalon: TalonSRX = TalonSRX(CAN_IDs.ARM_TALON_ID)

    enum class ArmDirection {
        UP, DOWN
    }


    override fun initDefaultCommand() {
        defaultCommand = MoveArmManual()
    }


    fun talonInit() {
        invertTalons()
        setNeutralModes()
        setCurrentLimits()
    }

    private fun invertTalons() {
        armTalon.inverted = ArmConstants.ARM_INVERTED
    }

    private fun setNeutralModes() {
        armTalon.setNeutralMode(ArmConstants.TALON_NEUTRAL_MODE)
    }

    private fun setCurrentLimits() {
        armTalon.configPeakCurrentDuration(ArmConstants.TALON_PEAK_TIME, Global.TIMEOUT)
        armTalon.configPeakCurrentLimit(ArmConstants.TALON_PEAK_CURRENT, Global.TIMEOUT)
        armTalon.configContinuousCurrentLimit(ArmConstants.TALON_CONTINUOUS_CURRENT, Global.TIMEOUT)
        armTalon.enableCurrentLimit(ArmConstants.TALON_CURRENT_ENABLE)

    }

    /**
     * Sets armTalon's percent output based on a specified direction.
     *
     * @param output percent at which motor will turn
     * @param armDirection direction arm will move
     */
    fun armMotionPercentOutput(output: Double, direction: ArmDirection) {
        var modifiedOutput = MathUtil.constrainOutput(output, ArmConstants.MAX_MOTION_SPEED, ArmConstants.MIN_MOTION_SPEED)
        if(direction == ArmDirection.UP) {
            armTalon.set(ControlMode.PercentOutput, modifiedOutput * ArmConstants.TALON_MOTION_DIRECTION)
        }
        else {
            armTalon.set(ControlMode.PercentOutput, modifiedOutput * -ArmConstants.TALON_MOTION_DIRECTION)
        }
    }

    fun getTalonCurrent() = armTalon.outputCurrent

}