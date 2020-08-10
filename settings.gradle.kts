rootProject.name = "ericsplugins"

include(":nomorewintertodt")
include(":nomoreagility")
include(":nomorecombat")
include(":nomoreskilling")
include(":nomoregrounditems")
include(":nomoregroundmarkers")
include(":nomoreinventorytags")
include(":nomorenpchighlight")
include(":nomoreobjectindicators")
include(":amiscplugin")
include(":aplugintutorial")
include(":chatbox")
include(":nomoremenuindicators")
include(":playerstateindicators")
include(":botutils")
include(":ExtUtils")
include(":pestcontrol")
include(":mudrunecrafter")
include(":chinlogin1")
include(":chinlogin2")
include(":chinlogin3")
include(":chinlogin4")
include(":autologin1")
include(":autologin2")
include(":autologin3")
include(":xptracker")
include(":tickfishing")

for (project in rootProject.children) {
    project.apply {
        projectDir = file(name)
        buildFileName = "${name.toLowerCase()}.gradle.kts"

        require(projectDir.isDirectory) { "Project '${project.path} must have a $projectDir directory" }
        require(buildFile.isFile) { "Project '${project.path} must have a $buildFile build script" }
    }
}
