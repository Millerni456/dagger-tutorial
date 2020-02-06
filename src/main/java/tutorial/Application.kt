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

        println()

        /**
         * Service1 is scoped to the component (both are marked @Singleton).
         * For this reason, the same instance is used for the latter two object graphs.
         * One thing to note is that the short-term dependencies have references to the long-term
         * dependencies.
         * When the short-term dependencies need to be freed, they can do so freely.
         * In the scenario where Service1 is transient and Feature1ViewModel2 is @Singleton, the
         * service instances would not be freed until the view-model instances are freed.
         */

        val f1vm2_instance3_fromComponent2 = applicationComponent2.getFeature1ViewModel2()
        println(f1vm2_instance3_fromComponent2)
        println(f1vm2_instance3_fromComponent2.service1)

        val f1vm2_instance4_fromComponent2 = applicationComponent2.getFeature1ViewModel2()
        println(f1vm2_instance4_fromComponent2)
        println(f1vm2_instance4_fromComponent2.service1)


        /**
         * By using our second instance of the ApplicationComponent, we can see that
         * Service1 is not truly a singleton. The Service1 instance in ApplicationComponent 1
         * is not the same instance as ApplicationComponent 2.
         * However, both are shared within the scope of their own components.
         */
        assert(f1vm2_instance1.service1 == f1vm2_instance2.service1)
        assert(f1vm2_instance2.service1 != f1vm2_instance3_fromComponent2.service1)
        assert(f1vm2_instance3_fromComponent2.service1 == f1vm2_instance4_fromComponent2.service1)


        printMemoryUsage("Objects initialized")
    }
}