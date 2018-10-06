package frc.robot.commands


import edu.wpi.first.wpilibj.command.Command
import frc.robot.Global
import frc.robot.IntakeConstants
import frc.robot.OI
import frc.robot.subsystems.Intake
import frc.robot.util.MathUtil

class IntakeOuttakeManual : Command() {

    init {
        requires(Intake)
    }

    override fun end() {

    }

    override fun execute() {
        var intakeOutput = MathUtil.mapOutput(OI.gamepad.leftTrigger, Global.DEADBAND)
        var outtakeOutput = OI.gamepad.rightTrigger
        if (intakeOutput > outtakeOutput) {
            Intake.intakeOuttakeCube(intakeOutput, Intake.IntakeDirection.IN)
        } else {
            Intake.intakeOuttakeCube(outtakeOutput, Intake.IntakeDirection.OUT)
        }
    }

    override fun initialize() {

    }

    /**
     * Determines that command never ends.
     */
    override fun isFinished(): Boolean = false

}
