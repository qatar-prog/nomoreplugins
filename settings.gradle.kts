rootProject.name = "nomoreplugins"

include(":nomorewintertodt")
include(":nomoreagility")
include(":ahkassistcombat")
include(":ahkassistskilling")
include(":ahkgrounditems")
include(":ahkgroundmarkers")
include(":ahkinventorytags")
include(":ahknpchighlight")
include(":ahkobjectindicators")
include(":amiscplugin")

for (project in rootProject.children) {
    project.apply {
        projectDir = file(name)
        buildFileName = "${name.toLowerCase()}.gradle.kts"

        require(projectDir.isDirectory) { "Project '${project.path} must have a $projectDir directory" }
        require(buildFile.isFile) { "Project '${project.path} must have a $buildFile build script" }
    }
}
