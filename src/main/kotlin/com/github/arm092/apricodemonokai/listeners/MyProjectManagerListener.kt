package com.github.arm092.apricodemonokai.listeners

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.github.arm092.apricodemonokai.services.MyProjectService

internal class MyProjectManagerListener : ProjectManagerListener {

//    override fun projectOpened(project: Project) {
//        project.service<MyProjectService>()
//
//        System.getenv("CI")
//            ?: TODO("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
//    }
}
