package io.github.ykws.moneyexample

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test

import org.junit.Assert.*

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JetpackTest {
  @Test
  fun gettingContextTest() {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val appName = context.getString(R.string.app_name)
    assertEquals(appName, "MoneyExample")
  }
}