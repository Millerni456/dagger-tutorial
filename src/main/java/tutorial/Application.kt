package tutorial

import tutorial.di.DaggerApplicationComponent

fun main(args: Array<String>) {
    Application()

    Thread.sleep(3000)
    System.gc()
    System.runFinalization()
    Thread.sleep(3000)
    printMemoryUsage("Out of scope")
}

class Application {

    init {
        System.gc()
        System.runFinalization()
        printMemoryUsage("Start of program")

        /**
         * Since ApplicationComponent has no modules, no dependencies, and
         * has no declared factories or builders, Dagger will generate the
         * two following ways to instantiate the component.
         */
        val applicationComponent1 = DaggerApplicationComponent.create()
        val applicationComponent2 = DaggerApplicationComponent.builder().build()
        printMemoryUsage("Components initialized")

        /**
         * Each of the following calls to a component's provision methods
         * creates a new object graph.
         * Each of these object graphs are completely disjoint since we are not reusing instances
         * at the component level.
         */
        val f1vm1_instance1 = applicationComponent1.getFeature1ViewModel1()
        println(f1vm1_instance1)

        val f1vm1_instance2 = applicationComponent1.getFeature1ViewModel1()
        println(f1vm1_instance2)

        val f1vm2_instance1 = applicationComponent1.getFeature1ViewModel2()
        println(f1vm2_instance1)
        println(f1vm2_instance1.service1)

        val f1vm2_instance2 = applicationComponent1.getFeature1ViewModel2()
        println(f1vm2_instance2)
        println(f1vm2_instance2.service1)

        printMemoryUsage("Objects initialized")
    }
}