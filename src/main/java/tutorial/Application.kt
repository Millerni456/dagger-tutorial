package tutorial

import tutorial.di.DaggerApplicationComponent

fun main(args: Array<String>) {

}

class Application {

    /**
     * Since ApplicationComponent has no modules, no dependencies, and
     * has no declared factories or builders, Dagger will generate the
     * two following ways to instantiate the component.
     */

    val applicatinComponent1 = DaggerApplicationComponent.create()
    val applicatinComponent2 = DaggerApplicationComponent.builder().build()

    init {

    }
}