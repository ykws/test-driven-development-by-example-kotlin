package io.github.ykws.moneyexample

import android.renderscript.ScriptGroup
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class InputCheckerTest {

  @Before
  fun setUp() {
  }

  @After
  fun tearDown() {
  }

  @Test
  fun isValid() {
    val target = InputChecker()
    val actual = target.isValid("foo")
    assertThat(actual, `is`(true))
  }

  @Test
  fun isValid_givenLessThan3_returnsFalse() {
    val target = InputChecker()
    val actual = target.isValid("ab")
    assertThat(actual, `is`(false))
  }

  @Test
  fun isValid_givenAlphaNumeric_returensTrue() {
    val target = InputChecker()
    val actual = target.isValid("abc123")
    assertThat(actual, `is`(true))
  }
}