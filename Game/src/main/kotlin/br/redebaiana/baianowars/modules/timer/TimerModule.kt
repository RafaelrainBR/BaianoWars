package br.redebaiana.baianowars.modules.timer

import br.redebaiana.baianowars.api.module.Module

class TimerModule : Module("Timer") {

    var timeElapsed = 0
        private set

    override suspend fun onSecPassed() {
        timeElapsed++
    }
}