package io.github.ykws.moneyexample.weather

import org.junit.After
import org.junit.Before

import org.assertj.core.api.Assertions.*
import com.nhaarman.mockitokotlin2.*
import org.junit.Test

class WeatherForecastTest {
  lateinit var satellite: Satellite
  lateinit var weatherForecast: WeatherForecast
  lateinit var recorder: WeatherRecorder

  @Before
  fun setUp() {
    satellite = mock(name = "MockSatellite")
    whenever(satellite.getWeather(any(), any())).thenAnswer { invocation ->
      val latitude = invocation.arguments[0] as Double
      val longitude = invocation.arguments[1] as Double

      if (latitude in 20.424086..45.550999 && longitude in 122.933872..153.980789) {
        return@thenAnswer Weather.SUNNY
      } else {
        return@thenAnswer Weather.RAINY
      }
    }

    recorder = mock(name = "MockRecorder")

    val formatter = WeatherFormatter()
    weatherForecast = WeatherForecast(satellite, recorder, formatter)
  }

  @After
  fun tearDown() {
  }

  @Test
  fun shouldBringUmbrella_givenInJapan_returnsFalse() {
    val actual = weatherForecast.shouldBringUmbrella(35.669784, 139.817728)
    assertThat(actual).isFalse()
  }

  @Test
  fun shouldBringUmbrella_givenBurlingame_returnTrue() {
    val actual = weatherForecast.shouldBringUmbrella(37.580006, -122.345106)
    assertThat(actual).isTrue()
  }

  @Test
  fun recordCurrentWeather_assertRecorderCalled() {
    weatherForecast.recordCurrentWeather(37.580006, -122.345106)
    verify(recorder, times(1)).record(any())
  }
}