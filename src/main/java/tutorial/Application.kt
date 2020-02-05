package tutorial

import tutorial.di.DaggerApplicationComponent

fun main(args: Array<String>) {

}

class Application {

    val applicatinComponent1 = DaggerApplicationComponent.create()
    val applicatinComponent2 = DaggerApplicationComponent.builder().build()

    init {

    }
}