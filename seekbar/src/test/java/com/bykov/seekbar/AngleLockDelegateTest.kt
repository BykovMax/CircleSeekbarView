package com.bykov.seekbar

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class AngleLockDelegateTest {

    private lateinit var lockDelegate: AngleLockDelegate


    @Before
    fun initDelegate() {
        lockDelegate = AngleLockDelegate()
    }


    @Test
    fun lockIncrease() {
        listOf(330f, 340f, 350f, 359f, 2f, 10f, 20f).forEach {
            lockDelegate.correct(it)
        }
        Assert.assertEquals(359f, lockDelegate.actualAngle)
    }


    @Test
    fun lockDecrease() {
        listOf(20f, 5f, 1f, 0f, 360f, 355f, 356F)
            .forEach { lockDelegate.correct(it) }
        Assert.assertEquals(0f, lockDelegate.actualAngle)
    }


    @Test
    fun unlockClickEnd() {
        listOf(330f, 358f, 359f, 0.7f, 0.18f, 359.9f, 358.9f, 358f)
            .forEach { lockDelegate.correct(it) }
        Assert.assertEquals(358f, lockDelegate.actualAngle)
    }

    @Test
    fun unlockClickStart() {
        listOf(30f, 18f, 2f, 0.7f, 0.18f, 359.9f, 358.9f, 350f, 359f, 0f, 1.3f)
            .forEach { lockDelegate.correct(it) }
        Assert.assertEquals(1.3f, lockDelegate.actualAngle)
    }


    @Test
    fun negativeValues() {
        lockDelegate.correct(-30f)
        Assert.assertEquals(0f, lockDelegate.actualAngle)
    }

    @Test
    fun largeNegativeValues() {
        lockDelegate.correct(-3630f)
        Assert.assertEquals(0f, lockDelegate.actualAngle)
    }

    @Test
    fun largeValues() {
        lockDelegate.correct(3630f)
        Assert.assertEquals(359f, lockDelegate.actualAngle)
    }

}