rootProject.name = "ericsplugins"

include(":chatbox")
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
include(":powerskiller")
include(":pvphelper")
include(":nightmarezone")
include(":minnowsbot")
include(":gargoylefighter")
include(":fishingtrawler")
include(":cursealch")
include(":tickfishinghotkey")
include(":zulrah")
include(":motherloadmine")


for (project in rootProject.children) {
    project.apply {
        projectDir = file(name)
        buildFileName = "${name.toLowerCase()}.gradle.kts"

        require(projectDir.isDirectory) { "Project '${project.path} must have a $projectDir directory" }
        require(buildFile.isFile) { "Project '${project.path} must have a $buildFile build script" }
    }
}
