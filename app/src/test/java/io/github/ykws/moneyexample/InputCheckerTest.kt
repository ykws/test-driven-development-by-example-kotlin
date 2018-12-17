package io.github.ykws.moneyexample

import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalArgumentException

class InputCheckerTest {
  lateinit var target: InputChecker

  @Before
  fun setUp() {
    target = InputChecker()
  }

  @After
  fun tearDown() {
  }

  @Test
  fun isValid() {
    val actual = target.isValid("foo")
    assertThat(actual).isTrue()
  }

  @Test(expected = IllegalArgumentException::class)
  fun isValid_givenNull_throwsIllegalArgumentException() {
    target.isValid(null)
  }

  @Test
  fun isValid_givenLessThan3_returnsFalse() {
    val actual = target.isValid("ab")
    assertThat(actual).isFalse()
  }

  @Test
  fun isValid_givenAlphabetic_returnsTrue() {
    val actual = target.isValid("abc")
    assertThat(actual).isTrue()
  }

  @Test
  fun isValid_givenNumeric_returnsTrue() {
    val actual = target.isValid("123")
    assertThat(actual).isTrue()
  }

  @Test
  fun isValid_givenAlphaNumeric_returnsTrue() {
    val actual = target.isValid("abc123")
    assertThat(actual).isTrue()
  }

  @Test
  fun isValid_givenInvalidCharacter_returnsFalse() {
    val actual = target.isValid("abc@123")
    assertThat(actual).isFalse()
  }
}