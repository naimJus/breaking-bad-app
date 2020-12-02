package com.example.thebreakingbadapp.di.scope

import javax.inject.Scope

/** <p>
 * The [PerFragment] scoping annotation specifies that the lifespan of a dependency be the same
 * as that of a [androidx.fragment.app.Fragment]. This is used to annotate dependencies that behave like a singleton
 * within the lifespan of an [androidx.fragment.app.Fragment].
 * <p>
 * [javax.inject.Singleton] is used to specify that the lifespan of a dependency be the same as that of the
 * Application.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class PerFragment