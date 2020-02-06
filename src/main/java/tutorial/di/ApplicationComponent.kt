package tutorial.di

import dagger.Component
import tutorial.feature1.Feature1ViewModel1
import tutorial.feature1.Feature1ViewModel2
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {

    /**
     * Declaring a provision method allows clients of the component
     * to create a new instance of the provided type.
     *
     * When declaring a provision method, Dagger requires
     * annotations that instruct how the instance should be created.
     * This can be done with @Inject at the constructor of the provided type
     * or by defining an @Provides factory method in any of the component's
     * modules.
     */
    fun getFeature1ViewModel1(): Feature1ViewModel1

    fun getFeature1ViewModel2(): Feature1ViewModel2
}