package com.marvelheroes.common.test.rules

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Function
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Callable

/**
 * JUnit Test Rule which overrides RxJava and Android schedulers for use in unit tests.
 *
 *
 * All schedulers are replaced with Schedulers.trampoline().
 */
class RxSchedulerRule : TestRule {

    private val schedulerInstance = Schedulers.trampoline()

    private val schedulerFunction = Function<Scheduler, Scheduler> { schedulerInstance }

    private val schedulerFunctionLazy =
        Function<Callable<Scheduler>, Scheduler> { schedulerInstance }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                RxAndroidPlugins.reset()
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerFunctionLazy)

                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler(schedulerFunction)
                RxJavaPlugins.setNewThreadSchedulerHandler(schedulerFunction)
                RxJavaPlugins.setComputationSchedulerHandler(schedulerFunction)

                base.evaluate()

                RxAndroidPlugins.reset()
                RxJavaPlugins.reset()
            }
        }
    }
}
