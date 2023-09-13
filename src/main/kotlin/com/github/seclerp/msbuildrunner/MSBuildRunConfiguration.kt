package com.github.seclerp.msbuildrunner

import com.intellij.execution.Executor
import com.intellij.execution.configurations.*
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessHandlerFactory
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project


class MSBuildRunConfiguration(project: Project,
                              factory: ConfigurationFactory?,
                              name: String?
) : RunConfigurationBase<MSBuildRunConfigurationOptions>(project, factory, name) {
    override fun getState(executor: Executor, environment: ExecutionEnvironment): RunProfileState {
        return object : CommandLineState(environment) {
            override fun startProcess(): ProcessHandler {
                val commandLine = GeneralCommandLine(getScriptName())
                val processHandler = ProcessHandlerFactory.getInstance()
                    .createColoredProcessHandler(commandLine)
                ProcessTerminatedListener.attach(processHandler)
                return processHandler
            }
        }
    }

    fun getScriptName(): String {
        return options.scriptName
    }

    override fun getOptions(): MSBuildRunConfigurationOptions {
        return super.getOptions() as MSBuildRunConfigurationOptions
    }

    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration> {
        return MSBuildSettingsEditor()
    }
}

