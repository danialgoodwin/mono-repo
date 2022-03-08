package dev.goodwin.experimental

fun main() {
    println("main")
}

class MyExperimentalConsoleApp {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println("")
            log("main(...), args = ${args.contentToString()}")
        }

        private fun log(message: String) {
            println(message)
        }

    }

}

