package com.github.arm092.apricodemonokai.services

import com.intellij.openapi.project.Project
import com.github.arm092.apricodemonokai.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
