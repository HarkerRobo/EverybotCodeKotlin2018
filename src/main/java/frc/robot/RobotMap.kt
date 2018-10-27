package frc.robot

import com.ctre.phoenix.motorcontrol.NeutralMode


object CAN_IDs {
    const val LEFT_MASTER_ID = 3
    const val RIGHT_MASTER_ID = 5
    const val LEFT_FOLLOWER_ID = 2
    const val RIGHT_FOLLOWER_ID = 6
    const val LEFT_INTAKE_ID = 1
    const val RIGHT_INTAKE_ID = 4
    const val ARM_TALON_ID = 0
}

object Global {
    const val TIMEOUT = 10

    //PID constants
    const val PID_PRIMARY = 0;
    const val PID_AUXILIARY = 1;
    const val hasTwoControllers = false
}

