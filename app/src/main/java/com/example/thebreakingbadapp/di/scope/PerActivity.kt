package com.example.thebreakingbadapp.di.scope

import javax.inject.Scope

/** <p>
 * The [PerActivity] scoping annotation specifies that the lifespan of a dependency be the same
 * as that of an [android.app.Activity]. This is used to annotate dependencies that behave like a singleton
 * within the lifespan of an [android.app.Activity].
 * <p>
 * [javax.inject.Singleton] is used to specify that the lifespan of a dependency be the same as that of the
 * Application.
 */
@MustBeDocumented
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity