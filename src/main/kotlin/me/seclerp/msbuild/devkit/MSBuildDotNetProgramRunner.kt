package me.seclerp.msbuild.devkit

import com.intellij.execution.configurations.RunProfile
import com.jetbrains.rider.debugger.DotNetProgramRunner

class MSBuildDotNetProgramRunner : DotNetProgramRunner() {
    override fun canRun(executorId: String, runConfiguration: RunProfile): Boolean {
        return runConfiguration is MSBuildRunConfiguration
    }
}